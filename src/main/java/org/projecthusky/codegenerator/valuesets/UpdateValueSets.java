/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.valuesets;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.Type;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.projecthusky.codegenerator.cda.rest.ValueSetRestClient;
import org.projecthusky.codegenerator.java.JavadocUtils;
import org.projecthusky.codegenerator.java.JavaCodeGenerator;
import org.projecthusky.common.basetypes.CodeBaseType;
import org.projecthusky.common.enums.LanguageCode;
import org.projecthusky.common.enums.ValueSetEnumInterface;
import org.projecthusky.valueset.api.ValueSetManager;
import org.projecthusky.valueset.api.ValueSetPackageManager;
import org.projecthusky.valueset.config.ValueSetConfig;
import org.projecthusky.valueset.config.ValueSetPackageConfig;
import org.projecthusky.valueset.enums.DesignationType;
import org.projecthusky.valueset.model.ValueSet;
import org.projecthusky.valueset.model.ValueSetEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.Generated;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

import static com.github.javaparser.ast.Modifier.*;
import static java.util.Arrays.asList;
import static org.projecthusky.codegenerator.valuesets.ValueSetUtil.getSourceFileName;
import static org.projecthusky.codegenerator.valuesets.ValueSetUtil.loadPrimaryType;
import static org.projecthusky.common.enums.LanguageCode.*;

/**
 * This class generates the CH-EPR value sets from the downloaded JSON files from art-decor.org.
 * <a href="https://github.com/project-husky/code-generator/wiki/Swiss-EPR-code-generator">See the wiki</a>
 * for additional information on how to use this class.
 */
public class UpdateValueSets {

    /**
     * Javadoc comment prefix for the code fields.
     */
    private static final Map<LanguageCode, String> CODE_JAVADOC_PREFIX = Map.of(ENGLISH,
            "Code for ", GERMAN, "Code für ", FRENCH, "Code de ", ITALIAN, "Code per ");

    /**
     * List of all languages that should be used to generate javadoc comments.
     */
    private static final List<LanguageCode> LANGUAGE_CODES = asList(ENGLISH, GERMAN, FRENCH, ITALIAN);

    private static final Logger LOG = LoggerFactory.getLogger(UpdateValueSets.class);

    /**
     * Shortcut for the internal type of a string.
     */
    private static final Type STRING_TYPE = com.github.javaparser.StaticJavaParser
            .parseClassOrInterfaceType("String");

    /**
     * Relative path where to find the Java template text file for regular enums.
     */
    private static final String TEMPLATE_FILE_LOCATION = "format/template.java.txt";

    /**
     * Relative path where to find the Java template text file for FHIR enums.
     */
    private static final String FHIR_TEMPLATE_FILE_LOCATION = "format/template_fhir.java.txt";

    /**
     * Class name in the template that will be replaced with the actual generated enum name.
     */
    private static final String TEMPLATE_NAME_TO_REPLACE = "TemplateNameToReplace";
    /**
     * Package name in the template that will be replaced with the actual generated enum name.
     */
    private static final String TEMPLATE_PACKAGE_NAME_TO_REPLACE = "TemplatePackageNameToReplace";

    /**
     * Adds all concepts of the value set definition as enum elements to the given enum type.
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
                String designation = valueSetEntry.getDesignation(language,
                        DesignationType.PREFERRED);
                if (designation == null && ENGLISH == language)
                    designation = valueSetEntry.getCodeBaseType().getDisplayName();
                if (designation != null) {
                    values.add(new StringLiteralExpr(designation));
                    javadocEnum.append(buildJavadocComment(language, JavadocUtils.cleanArtDecorHtml(designation)));
                    javadocConstant.append(buildJavadocComment(language,
                            CODE_JAVADOC_PREFIX.get(language) + JavadocUtils.cleanArtDecorHtml(designation)));
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
     * Formats a javadoc comment HTML snippet in the given language.
     *
     * @param language The language code to add.
     * @param comment  The comment to add.
     * @return The HTML snippet of the comment.
     */
    private static String buildJavadocComment(final LanguageCode language,
                                              final String comment) {
        return language.getCodeValue().substring(0, 2).toUpperCase(Locale.ROOT)
                + ": "
                + comment
                + (!comment.endsWith(".") ? "." : "")
                + "<br>\n";
    }

    /**
     * Creates an enum definition class.
     *
     * @param baseJavaFolder          The base Java source folder (relative to the root of the project hierarchy) where
     *                                the Java package structure begins.
     * @param fullyQualifiedClassName the fully qualified class name
     * @param forFhirConsumption      Whether the enum is for FHIR consumption.
     * @throws IOException When reading or writing the Java source file fails.
     */
    public static void createEnumClassFromTemplate(final String baseJavaFolder,
                                                   final String fullyQualifiedClassName,
                                                   final boolean forFhirConsumption) throws IOException {
        final String className = fullyQualifiedClassName.substring(fullyQualifiedClassName.lastIndexOf('.') + 1);
        final String packageName = fullyQualifiedClassName.substring(0, fullyQualifiedClassName.lastIndexOf('.'));

        final String templateString = getTemplate(forFhirConsumption)
                .replace(TEMPLATE_NAME_TO_REPLACE, className)
                .replace(TEMPLATE_PACKAGE_NAME_TO_REPLACE, packageName);

        final Optional<CompilationUnit> javaSource = new JavaParser().parse(templateString).getResult();
        if (javaSource.isEmpty()) {
            throw new RuntimeException("The enum source parsing has failed");
        }

        FileUtils.write(getSourceFileName(baseJavaFolder, fullyQualifiedClassName),
                javaSource.get().toString(JavaCodeGenerator.getPrinterConfiguration()),
                StandardCharsets.UTF_8);
    }

    /**
     * The main entry for the value set generator.
     *
     * @param javaSourceDir      The Java source directory.
     * @param packageConfig      The package config file.
     * @param forFhirConsumption Whether the enum is for FHIR consumption.
     */
    public static void updateValueSets(final File javaSourceDir,
                                       final File packageConfig,
                                       final boolean forFhirConsumption) {
        LOG.info("Java source dir: {}", javaSourceDir.getAbsolutePath());
        LOG.info("Package config : {}", packageConfig.getAbsolutePath());

        try {
            final ValueSetPackageManager valueSetPackageManager = new ValueSetPackageManager();
            final ValueSetPackageConfig valueSetPackageConfig =
                    valueSetPackageManager.loadValueSetPackageConfig(packageConfig.getAbsolutePath());
            final var valueSetRestClient = new ValueSetRestClient();

            for (ValueSetConfig valueSetConfig : valueSetPackageConfig.getValueSetConfigList()) {
                LOG.debug("Processing enum: {}", valueSetConfig.getClassName());

                LOG.debug("Downloading value set from {}", valueSetConfig.getSourceUrl());
                final ValueSet valueSet = valueSetRestClient.fetchValueSet(valueSetConfig);

                LOG.debug("Creating Java class file");
                final String baseJavaFolder = javaSourceDir.getAbsolutePath() + "/" + valueSetConfig.getProjectFolder();
                final String fullyQualifiedClassName = valueSetConfig.getClassName();

                // delete existing class file
                if (Files.exists(getSourceFileName(baseJavaFolder, fullyQualifiedClassName).toPath())) {
                    Files.delete(getSourceFileName(baseJavaFolder, fullyQualifiedClassName).toPath());
                }

                if (forFhirConsumption) {
                    replaceOidsWithSystems(valueSet);
                }

                // create the class file
                createEnumClassFromTemplate(baseJavaFolder, fullyQualifiedClassName, forFhirConsumption);
                updateEnumClass(valueSet.getIdentificator().getRoot(), valueSet.getName(), baseJavaFolder,
                        valueSetConfig.getClassName(), valueSet, forFhirConsumption);
            }
            LOG.info("Processed {} enums.", valueSetPackageConfig.getValueSetConfigList().size());
        } catch (final Exception e) {
            LOG.error("Uncaught exception: ", e);
        }
    }

    /**
     * Replaces OIDs from the IHE world with systems URIs from the FHIR world.
     */
    private static void replaceOidsWithSystems(final ValueSet valueSet) {
        for (final ValueSetEntry entry : valueSet.getValueSetEntryList()) {
            switch (entry.getCodeBaseType().getCodeSystem()) {
                case "2.16.840.1.113883.6.8":
                    entry.getCodeBaseType().setCodeSystem("http://unitsofmeasure.org");
                    break;
                case "2.16.840.1.113883.6.96":
                    entry.getCodeBaseType().setCodeSystem("http://snomed.info/sct");
                    break;
                case "2.16.840.1.113883.4.642.4.76":
                    entry.getCodeBaseType().setCodeSystem("http://fhir.ch/ig/ch-emed/CodeSystem/event-timing");
                    break;
            }
        }
    }

    /**
     * Remove everything from an enum type, leaving an empty definition body.
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
     * Replaces the value of a constant in the parsed type declaration of a Java class.
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
     * Replaces the value of a annotation parameter.
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
     * Updates an existing enum definition and adds the value set elements as enum constants. Modifies the Java code of
     * the enum class.
     *
     * @param id             The unique code id that identifies the enum.
     * @param valueSetName   The value set name of the enum.
     * @param baseJavaFolder The base Java source folder (relative to the root of the project hierarchy) where the Java
     *                       package structure begins.
     * @param className      The fully qualified Java class name of the enum to update.
     * @param valueSet       the value set
     * @param forFhirConsumption Whether the enum is for FHIR consumption.
     * @return the Java class file
     * @throws IOException           When reading or writing the Java source file fails.
     * @throws IllegalStateException If the class does not declare an Enum type.
     */
    @SuppressWarnings("squid:S3457")
    public static File updateEnumClass(final String id,
                                       final String valueSetName,
                                       final String baseJavaFolder,
                                       final String className,
                                       final ValueSet valueSet,
                                       final boolean forFhirConsumption) throws IOException, IllegalStateException {
        final var javaParser = new JavaParser();
        final Optional<CompilationUnit> javaSource = javaParser
                .parse(getSourceFileName(baseJavaFolder, className)).getResult();
        if (javaSource.isEmpty()) {
            throw new RuntimeException("");
        }
        final TypeDeclaration<?> primaryType = loadPrimaryType(javaSource.get());

        if (primaryType.isTopLevelType() && primaryType.isEnumDeclaration()) {
            final EnumDeclaration enumType = ((EnumDeclaration) primaryType);

            // clear content and add all enum elements
            removeEverything(enumType);
            enumType.addImplementedType(ValueSetEnumInterface.class);
            if (forFhirConsumption) {
                enumType.addImplementedType("FhirValueSetEnumInterface");
            }
            enumType.addModifier(publicModifier().getKeyword());
            addEnumElements(enumType, valueSet);

            // add main javadoc
            final var javadoc = new StringBuilder();
            javadoc.append(String.format("Enumeration of %s values\n", valueSet.getName()));
            javadoc.append("<p>\n");
            for (final LanguageCode language : LANGUAGE_CODES) {
                String desc = JavadocUtils.cleanArtDecorHtml(valueSet.getDescription(language));
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
            final String templateString = getTemplate(forFhirConsumption)
                    .replace(TEMPLATE_NAME_TO_REPLACE, enumType.getNameAsString());
            final Optional<CompilationUnit> templateSource = javaParser.parse(templateString).getResult();
            if (templateSource.isEmpty()) {
                throw new RuntimeException("");
            }
            final TypeDeclaration<?> templateType = templateSource.get().getType(0);
            templateType.getMembers().forEach(enumType::addMember);

            // replace constant values and imports
            replaceConstantValue(enumType, "VALUE_SET_ID", id);
            replaceConstantValue(enumType, "VALUE_SET_NAME", valueSetName);

            // replace imports with those found in the template
            new ArrayList<>(javaSource.get().getImports()).forEach(javaSource.get()::remove);
            templateSource.get().getImports().forEach(javaSource.get()::addImport);

            // @generated annotation
            final AnnotationExpr generated;
            if (primaryType.getAnnotationByClass(Generated.class).isPresent()) {
                generated = primaryType.getAnnotationByClass(Generated.class).get();
            } else {
                generated = templateType.getAnnotationByClass(Generated.class).get();
                primaryType.addAnnotation(generated);
            }
            replaceParameterValue(generated, "value",
                    "org.projecthusky.codegenerator.ch.valuesets.UpdateValueSets");
            replaceParameterValue(generated, "date", LocalDate.now().toString());

            // check if there is a unique code system, add it as a constant if so
            final List<String> codeSystemIds = valueSet.getValueSetEntryList().stream()
                    .map(ValueSetEntry::getCodeBaseType)
                    .map(CodeBaseType::getCodeSystem)
                    .distinct()
                    .toList();
            if (codeSystemIds.size() == 1 && codeSystemIds.get(0) != null) {
                replaceConstantValue(enumType, "CODE_SYSTEM_ID", codeSystemIds.get(0));
            } else {
                primaryType.getFieldByName("CODE_SYSTEM_ID").ifPresent(primaryType::remove);
            }
        } else {
            throw new IllegalStateException("Class with name " + className + " does not declare an Enum type.");
        }

        final File destFile = getSourceFileName(baseJavaFolder, className);
        String classFileContent = javaSource.get().toString(JavaCodeGenerator.getPrinterConfiguration());
        classFileContent = classFileContent.replace("import java.util.Map;", "import java.util.Map;\n");
        classFileContent = classFileContent.replace("import javax.annotation.processing.Generated;",
                "import javax.annotation.processing.Generated;\n");
        FileUtils.write(destFile, classFileContent, StandardCharsets.UTF_8);

        return destFile.getAbsoluteFile();
    }

    /**
     * Returns the enum template file content.
     *
     * @param forFhirConsumption Whether the enum is for FHIR consumption.
     * @throws IOException if the file is not readable.
     */
    private static String getTemplate(final boolean forFhirConsumption) throws IOException {
        if (forFhirConsumption) {
            return IOUtils.toString(
                    Objects.requireNonNull(
                            UpdateValueSets.class.getClassLoader().getResourceAsStream(FHIR_TEMPLATE_FILE_LOCATION),
                            "The template file is not found in " + FHIR_TEMPLATE_FILE_LOCATION
                    ),
                    StandardCharsets.UTF_8
            );
        }
        return IOUtils.toString(
                Objects.requireNonNull(
                        UpdateValueSets.class.getClassLoader().getResourceAsStream(TEMPLATE_FILE_LOCATION),
                        "The template file is not found in " + TEMPLATE_FILE_LOCATION
                ),
                StandardCharsets.UTF_8
        );
    }
}
