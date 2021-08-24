/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */

package org.ehealth_connector.codegenerator.valuesets;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import com.github.javaparser.printer.PrettyPrinterConfiguration.IndentType;
import org.apache.commons.io.FileUtils;
import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.common.mdht.enums.ValueSetEnumInterface;
import org.ehealth_connector.valueset.api.ValueSetManager;
import org.ehealth_connector.valueset.api.ValueSetPackageManager;
import org.ehealth_connector.valueset.config.ValueSetConfig;
import org.ehealth_connector.valueset.config.ValueSetPackageConfig;
import org.ehealth_connector.valueset.enums.DesignationType;
import org.ehealth_connector.valueset.model.ValueSet;
import org.ehealth_connector.valueset.model.ValueSetEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.Generated;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.github.javaparser.ast.Modifier.*;
import static java.util.Arrays.asList;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.getSourceFileName;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.loadPrimaryType;
import static org.ehealth_connector.common.enums.LanguageCode.*;

/**
 * <div class="en">This class generates the CH-EPR value sets from the
 * downloaded JSON files from art-decor.org.
 * <a href="https://gitlab.com/ehealth-connector/api/-/wikis/Updating-the-Swiss-EPR-metadata">See
 * the wiki</a> for additional information on how to use this class.</div>
 */
public class UpdateValueSets {

    /**
     * <div class="en">Javadoc comment prefix for the code fields.</div>
     */
    private static final Map<LanguageCode, String> CODE_JAVADOC_PREFIX = Map.of(ENGLISH,
            "Code for ", GERMAN, "Code für ", FRENCH, "Code de ", ITALIAN, "Code per ");

    /**
     * <div class="en">Base path where to find the config files for the
     * generator (YAML and JSON files).</div>
     */
    private static final String CONFIG_FILE_BASE_PATH = System.getProperty("user.dir")
            + "/ehealth_connector-codegenerator-gen/src/main/resources/valuesets/";

    /**
     * <div class="en">List of all languages that should be used to generate
     * javadoc comments.</div>
     */
    private static final List<LanguageCode> LANGUAGE_CODES = asList(ENGLISH, GERMAN, FRENCH, ITALIAN);

    private static final Logger LOG = LoggerFactory.getLogger(UpdateValueSets.class);

    /**
     * <div class="en">Java code formatter/pretty printer configuration used to
     * write Java code.</div>
     */
    private static final PrettyPrinterConfiguration PRETTY_PRINTER_CONFIGURATION = new PrettyPrinterConfiguration()
            .setIndentType(IndentType.TABS).setIndentSize(1).setOrderImports(true);

    /**
     * <div class="en">Shortcut for the internal type of a string.</div>
     */
    private static final Type STRING_TYPE = com.github.javaparser.StaticJavaParser
            .parseClassOrInterfaceType("String");

    /**
     * As published on April 1, 2021: https://www.e-health-suisse.ch/technik-semantik/semantische-interoperabilitaet/metadaten.html
     */
    private static final String SWISS_EPR_VALUE_SET_PACKAGE_CONFIG = "SwissEprValueSetPackageConfig-20210401.yaml";

    /**
     * <div class="en">Relative path where to find the Java template text
     * file.</div>
     */
    private static final String TEMPLATE_FILE_LOCATION = "ehealth_connector-codegenerator-gen/src/main/resources" +
            "/format/template.java.txt";

    /**
     * <div class="en">Class name in the template that will be replaced with the
     * actual generated enum name.</div>
     */
    private static final String TEMPLATE_NAME_TO_REPLACE = "TemplateNameToReplace";
    /**
     * <div class="en">Package name in the template that will be replaced with
     * the actual generated enum name.</div>
     */
    private static final String TEMPLATE_PACKAGE_NAME_TO_REPLACE = "TemplatePackageNameToReplace";

    /**
     * <div class="en">Adds all concepts of the value set definition as enum
     * elements to the given enum type.</div>
     *
     * @param enumType The enum type representing parsed Java enum class.
     * @param valueSet The concepts from the value set definition file.
     */
    private static void addEnumElements(EnumDeclaration enumType, ValueSet valueSet) {
        List<ValueSetEntry> list = valueSet.getSortedEntryListRecursive();
        final List<ValueSetEntry> duplicates = ValueSetUtil.getDuplicates(list);
        if (!duplicates.isEmpty()) {
            final StringBuilder temp = new StringBuilder();
            for (final ValueSetEntry valueSetEntry : duplicates) {
                if (!temp.isEmpty()) {
                    temp.append(", ");
                }
                temp.append(valueSetEntry.getCodeBaseType().getDisplayName());
            }
            LOG.warn("WARNING: ValueSet '{}' contains duplicate(s): {}", valueSet.getDisplayName(), temp);
            list = ValueSetUtil.removeDuplicates(list);
        }

        for (final ValueSetEntry valueSetEntry : list) {
            final String enumConstantName = valueSetEntry.getEnumConstantName();
            final String code = valueSetEntry.getCodeBaseType().getCode();
            final String codeSystem = valueSetEntry.getCodeBaseType().getCodeSystem();
            final String displayName = valueSetEntry.getCodeBaseType().getDisplayName();

            final NodeList<Expression> values = new NodeList<>();
            final StringBuilder javadocEnum = new StringBuilder();
            final StringBuilder javadocConstant = new StringBuilder();

            values.add(new StringLiteralExpr(code));
            values.add(new StringLiteralExpr(codeSystem));
            values.add(new StringLiteralExpr(displayName));

            // build comments per language
            for (final LanguageCode language : LANGUAGE_CODES) {
                String designation = valueSetEntry.getDesignation(language, DesignationType.PREFERRED);
                if (designation == null)
                    designation = valueSetEntry.getDesignation(language, DesignationType.PREFERRED);
                if (designation == null && ENGLISH == language)
                    designation = valueSetEntry.getCodeBaseType().getDisplayName();
                if (designation != null) {
                    values.add(new StringLiteralExpr(designation));
                    javadocEnum.append(buildJavadocComment(language, designation));
                    javadocConstant.append(buildJavadocComment(language,
                            CODE_JAVADOC_PREFIX.get(language) + designation));
                } else
                    values.add(new StringLiteralExpr("TOTRANSLATE"));

            }

            // the enum constant with all values
            final EnumConstantDeclaration enumConstant = new EnumConstantDeclaration(new NodeList<>(),
                    new SimpleName(enumConstantName), new NodeList<>(values), new NodeList<>());
            enumConstant.setJavadocComment(javadocEnum.toString());
            enumType.addEntry(enumConstant);

            // the static final code field for each concept
            enumType.addFieldWithInitializer(STRING_TYPE, enumConstantName + "_CODE",
                            new StringLiteralExpr(code), publicModifier().getKeyword(),
                            staticModifier().getKeyword(), finalModifier().getKeyword())
                    .setJavadocComment(javadocConstant.toString());
        }
    }

    /**
     * <div class="en">Formats a javadoc comment HTML snippet in the given
     * language.</div>
     *
     * @param language The language code to add.
     * @param comment  The comment to add.
     * @return The HTML snippet of the comment.
     */
    private static String buildJavadocComment(LanguageCode language, String comment) {
        return language.getCodeValue().substring(0, 2).toUpperCase(Locale.ROOT) + ": " + comment
                + "<br>\n";
    }

    /**
     * <div class="en">Creates an enum definition class.</div>
     *
     * @param baseJavaFolder          The base Java source folder (relative to the root of the project hierarchy) where
     *                                the Java package structure begins.
     * @param fullyQualifiedClassName the fully qualified class name
     * @throws IOException When reading or writing the Java source file fails.
     */
    public static void createEnumClassFromTemplate(String baseJavaFolder,
                                                   String fullyQualifiedClassName) throws IOException {

        String className = fullyQualifiedClassName
                .substring(fullyQualifiedClassName.lastIndexOf('.') + 1);
        String packageName = fullyQualifiedClassName.substring(0,
                fullyQualifiedClassName.lastIndexOf('.'));

        String templateString = FileUtils
                .readFileToString(new File(TEMPLATE_FILE_LOCATION), StandardCharsets.UTF_8)
                .replaceAll(TEMPLATE_NAME_TO_REPLACE, className)
                .replaceAll(TEMPLATE_PACKAGE_NAME_TO_REPLACE, packageName);

        ParseResult<CompilationUnit> javaSource = new JavaParser().parse(templateString);

        FileUtils.write(getSourceFileName(baseJavaFolder, fullyQualifiedClassName),
                javaSource.getResult().get().toString(PRETTY_PRINTER_CONFIGURATION),
                StandardCharsets.UTF_8);

    }

    /**
     * The main entry for the value set generator.
     *
     * @param args command line arguments. A single value is expected.
     */
    public static void main(String[] args) {
        LOG.info("Update value sets");

        if (args.length != 1) {
            LOG.info("Usage:");
            LOG.info("UpdateValueSets <javaSourceDir>");
            LOG.info("  javaSourceDir: This parameter must be the full path to the eHealthConnector-Suisse project " +
                    "directory (e.g. D:/Java/ehealtconnector-suisse)");
            LOG.info("");
            LOG.info("Example:");
            LOG.info("UpdateValueSets D:/Java/ehealtconnector-suisse");
            return;
        }
        final String javaSourceDirString = args[0];

        final File javaSourceDir;
        if (javaSourceDirString != null) {
            javaSourceDir = new File(javaSourceDirString);
            if (!javaSourceDir.exists()) {
                LOG.error("ERROR: Java source directory does not exist ({})", javaSourceDirString);
                return;
            } else {
                if (!javaSourceDir.isDirectory()) {
                    LOG.error(
                            "ERROR: Java source is not a directory ({})", javaSourceDirString);
                    return;
                }
            }
        } else {
            LOG.error("ERROR: Java source directory is null");
            return;
        }

        LOG.info("Config base dir: {}", new File(CONFIG_FILE_BASE_PATH).getAbsolutePath());
        LOG.info("Java source dir: {}", javaSourceDir.getAbsolutePath());

        final String fnPackageConfig = CONFIG_FILE_BASE_PATH + SWISS_EPR_VALUE_SET_PACKAGE_CONFIG;

        try {
            final ValueSetPackageManager valueSetPackageManager = new ValueSetPackageManager();
            final ValueSetPackageConfig valueSetPackageConfig =
                    valueSetPackageManager.loadValueSetPackageConfig(fnPackageConfig);

            final ValueSetManager valueSetManager = new ValueSetManager();
            for (ValueSetConfig valueSetConfig : valueSetPackageConfig.getValueSetConfigList()) {
                LOG.debug("Processing enum: {}", valueSetConfig.getClassName());

                LOG.debug("Downloading value set from {}", valueSetConfig.getSourceUrl());
                final ValueSet valueSet = valueSetManager.downloadValueSet(valueSetConfig);

                LOG.debug("Creating Java class file");
                final String baseJavaFolder = javaSourceDir.getAbsolutePath() + "/" + valueSetConfig.getProjectFolder();
                final String fullyQualifiedClassName = valueSetConfig.getClassName();

                // delete existing class file
                Files.delete(getSourceFileName(baseJavaFolder, fullyQualifiedClassName).toPath());

                // create the class file
                createEnumClassFromTemplate(baseJavaFolder, fullyQualifiedClassName);
                updateEnumClass(valueSet.getIdentificator().getRoot(), valueSet.getName(), baseJavaFolder,
                        valueSetConfig.getClassName(), valueSet);
            }
            LOG.info("Processed {} enums.", valueSetPackageConfig.getValueSetConfigList().size());
        } catch (final Exception e) {
            LOG.error("Uncaught exception: ", e);
        }
    }

    /**
     * <div class="en">Remove everything from an enum type, leaving an empty
     * definition body.</div>
     *
     * @param enumType The enum to clean out.
     */
    private static void removeEverything(final EnumDeclaration enumType) {
        final List<Node> allNodes = new ArrayList<>();
        allNodes.addAll(enumType.getEntries());
        allNodes.addAll(enumType.getFields());
        allNodes.addAll(enumType.getConstructors());
        allNodes.addAll(enumType.getMethods());
        allNodes.addAll(enumType.getChildNodes());
        allNodes.forEach(enumType::remove);
    }

    /**
     * <div class="en">Replaces the value of a constant in the parsed type
     * declaration of a Java class.</div>
     *
     * @param body         The parsed body declaration of the Java class that holds the constant.
     * @param constantName The name of the constant to replace the value of.
     * @param value        The value to set.
     */
    private static void replaceConstantValue(TypeDeclaration<? extends TypeDeclaration<?>> body,
                                             String constantName, String value) {
        body.getFieldByName(constantName).ifPresent(
                field -> field.getVariable(0).setInitializer(new StringLiteralExpr(value)));
    }

    /**
     * <div class="en">Replaces the value of a annotation parameter.</div>
     *
     * @param annotationExpr The annotation that holds the parameter.
     * @param parameterName  The name of the parameter to replace the value of.
     * @param value          The value to set.
     */
    private static void replaceParameterValue(AnnotationExpr annotationExpr, String parameterName, String value) {
        List<Node> parameters = annotationExpr.getChildNodes();
        for (Node parameter : parameters) {
            if (parameter instanceof MemberValuePair memberValuePair && parameterName.equals(memberValuePair.getNameAsString())) {
                memberValuePair.setValue(new StringLiteralExpr(value));
            }
        }
    }

    /**
     * <div class="en">Updates an existing enum definition and adds the value
     * set elements as enum constants. Modifies the Java code of the enum
     * class.</div>
     *
     * @param id             The unique code id that identifies the enum.
     * @param valueSetName   The value set name of the enum.
     * @param baseJavaFolder The base Java source folder (relative to the root of the project hierarchy) where the Java
     *                       package structure begins.
     * @param className      The fully qualified Java class name of the enum to update.
     * @param valueSet       the value set
     * @return the Java class file
     * @throws IOException           When reading or writing the Java source file fails.
     * @throws IllegalStateException If the class does not declare an Enum type.
     */
    public static File updateEnumClass(String id, String valueSetName, String baseJavaFolder,
                                       String className, ValueSet valueSet) throws IOException, IllegalStateException {

        final JavaParser javaParser = new JavaParser();
        final ParseResult<CompilationUnit> javaSource = javaParser
                .parse(getSourceFileName(baseJavaFolder, className));
        final TypeDeclaration<?> primaryType = loadPrimaryType(javaSource.getResult().get());

        if (primaryType.isTopLevelType() && primaryType.isEnumDeclaration()) {
            final EnumDeclaration enumType = ((EnumDeclaration) primaryType);

            // clear content and add all enum elements
            removeEverything(enumType);
            enumType.addImplementedType(ValueSetEnumInterface.class);
            enumType.addModifier(publicModifier().getKeyword());
            addEnumElements(enumType, valueSet);

            // add main javadoc
            final StringBuilder javadoc = new StringBuilder();
            javadoc.append(String.format("Enumeration of %s values\n", valueSet.getName()));
            javadoc.append("<p>\n");
            for (final LanguageCode language : LANGUAGE_CODES) {
                String desc = valueSet.getDescription(language);
                if (desc == null) {
                    desc = "No designation found.";
                }
                javadoc.append(buildJavadocComment(language, desc));
            }
            javadoc.append("<p>\n");
            javadoc.append(String.format("Identifier: %s<br>\n", valueSet.getIdentificator().getRoot()));
            javadoc.append(String.format("Effective date: %1$tF %1$tR<br>\n", valueSet.getVersion().getValidFrom()));
            javadoc.append(String.format("Version: %s<br>\n", valueSet.getVersion().getLabel()));
            javadoc.append(String.format("Status: %s\n", valueSet.getStatus()));
            enumType.setJavadocComment(javadoc.toString());

            // add all members from template file
            final String templateString = FileUtils.readFileToString(new File(TEMPLATE_FILE_LOCATION),
                            StandardCharsets.UTF_8)
                    .replaceAll(TEMPLATE_NAME_TO_REPLACE, enumType.getNameAsString());
            final ParseResult<CompilationUnit> templateSource = javaParser.parse(templateString);
            final TypeDeclaration<?> templateType = templateSource.getResult().get().getType(0);
            templateType.getMembers().forEach(enumType::addMember);

            // replace constant values and imports
            replaceConstantValue(enumType, "VALUE_SET_ID", id);
            replaceConstantValue(enumType, "VALUE_SET_NAME", valueSetName);

            // replace imports with those found in the template
            new ArrayList<>(javaSource.getResult().get().getImports()).forEach(javaSource.getResult().get()::remove);
            templateSource.getResult().get().getImports().forEach(javaSource.getResult().get()::addImport);

            // @generated annotation
            final AnnotationExpr generated;
            if (primaryType.getAnnotationByClass(Generated.class).isPresent()) {
                generated = primaryType.getAnnotationByClass(Generated.class).get();
            } else {
                generated = templateType.getAnnotationByClass(Generated.class).get();
                primaryType.addAnnotation(generated);
            }

            replaceParameterValue(generated, "value",
                    "org.ehealth_connector.codegenerator.ch.valuesets.UpdateValueSets");
            replaceParameterValue(generated, "date", LocalDate.now().toString());
        } else {
            throw new IllegalStateException("Class with name " + className + " does not declare an Enum type.");
        }

        final File destFile = getSourceFileName(baseJavaFolder, className);
        String classFileContent = javaSource.getResult().get().toString(PRETTY_PRINTER_CONFIGURATION);
        classFileContent = classFileContent.replace("import java.util.Map;", "import java.util.Map;\n");
        classFileContent = classFileContent.replace("import javax.annotation.processing.Generated;",
                "import javax.annotation.processing.Generated;\n");
        FileUtils.write(destFile, classFileContent, StandardCharsets.UTF_8);

        return destFile.getAbsoluteFile();
    }

}
