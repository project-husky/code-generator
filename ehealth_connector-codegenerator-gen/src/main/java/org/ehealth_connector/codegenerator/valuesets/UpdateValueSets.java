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

import static com.github.javaparser.ast.Modifier.finalModifier;
import static com.github.javaparser.ast.Modifier.publicModifier;
import static com.github.javaparser.ast.Modifier.staticModifier;
import static java.util.Arrays.asList;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.getSourceFileName;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.loadPrimaryType;
import static org.ehealth_connector.common.enums.LanguageCode.ENGLISH;
import static org.ehealth_connector.common.enums.LanguageCode.FRENCH;
import static org.ehealth_connector.common.enums.LanguageCode.GERMAN;
import static org.ehealth_connector.common.enums.LanguageCode.ITALIAN;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.common.mdht.enums.ValueSetEnumInterface;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.ehealth_connector.valueset.api.ValueSetManager;
import org.ehealth_connector.valueset.api.ValueSetPackageManager;
import org.ehealth_connector.valueset.config.ValueSetConfig;
import org.ehealth_connector.valueset.config.ValueSetPackageConfig;
import org.ehealth_connector.valueset.enums.DesignationType;
import org.ehealth_connector.valueset.model.ValueSet;
import org.ehealth_connector.valueset.model.ValueSetEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import com.github.javaparser.printer.PrettyPrinterConfiguration.IndentType;
import com.google.common.collect.ImmutableMap;

/**
 * <div class="en">This class generates the CH-EPR value sets from the
 * downloaded JSON files from art-decor.org</div>
 */
public class UpdateValueSets {

	private final static String ART_DECOR_VALUE_SET_PACKAGE_CONFIG = "ArtDecorInfrastructure-201908.yaml";
	/**
	 * <div class="en">Javadoc comment prefix for the code fields.</div>
	 */
	private static final Map<LanguageCode, String> CODE_JAVADOC_PREFIX = ImmutableMap.of(ENGLISH,
			"Code for ", GERMAN, "Code für ", FRENCH, "Code de ", ITALIAN, "Code per ");

	/**
	 * <div class="en">Base path where to find the config files for the
	 * generator (YAML and JSON files).</div>
	 */
	private static final String CONFIG_FILE_BASE_PATH = System.getProperty("user.dir")
			+ "/src/main/resources/valuesets/";

	/**
	 * <div class="en">Settings for the Java Code Formatter application. It
	 * contains the same settings as the formatter XML, just in another file
	 * format. Best way is to export the formatter from eclipse preferences,
	 * remove the XML stuff and put it in the prefs file.</div>
	 */
	private static final String FORMATTER_PREFS = System.getProperty("user.dir")
			+ "/src/main/resources/format/org.eclipse.jdt.core.prefs";
	/**
	 * <div class="en">List of all languages that should be used to generate
	 * javadoc comments.</div>
	 */
	private static final List<LanguageCode> LANGUAGE_CODES = asList(ENGLISH, GERMAN, FRENCH,
			ITALIAN);;

	private static Logger log = LoggerFactory.getLogger(UpdateValueSets.class);

	/**
	 * <div class="en">Java code formatter/pretty printer configuration used to
	 * write Java code.</div>
	 */
	private static final PrettyPrinterConfiguration PRETTY_PRINTER_CONFIGURATION = new PrettyPrinterConfiguration()
			.setIndentType(IndentType.TABS).setIndentSize(1);

	/**
	 * <div class="en">Relative path to the root of the maven project
	 * structure.</div>
	 */
	private static final String PROJECT_ROOT_RELATIVE_PATH = "../../";

	/**
	 * <div class="en">Shortcut for the internal type of a string.</div>
	 */
	private static final Type STRING_TYPE = com.github.javaparser.StaticJavaParser
			.parseClassOrInterfaceType("String");

	// private final static String SWISS_EPR_VALUE_SET_PACKAGE_CONFIG =
	// "SwissEprValueSetPackageConfig-201704.0-stable.yaml";
	// private final static String SWISS_EPR_VALUE_SET_PACKAGE_CONFIG =
	// "SwissEprValueSetPackageConfig-201704.3-beta.yaml";
	private final static String SWISS_EPR_VALUE_SET_PACKAGE_CONFIG = "SwissEprValueSetPackageConfig-201907.0-beta.yaml";

	/**
	 * <div class="en">Relative path where to find the Java template text
	 * file.</div>
	 */
	private static final String TEMPLATE_FILE_LOCATION = "src/main/resources/valuesets/template.java.txt";

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
	 * @param enumType
	 *            The enum type representing parsed Java enum class.
	 * @param concepts
	 *            The concepts from the value set definition file.
	 */
	private static void addEnumElements(EnumDeclaration enumType, ValueSet valueSet) {

		ArrayList<ValueSetEntry> list;
		list = valueSet.getSortedEntryList();

		// TODO: nested lists not supported, yet (due to duplicate enum names).
		// list = valueSet.getSortedEntryListRecursive();

		for (ValueSetEntry valueSetEntry : list) {
			String enumConstantName = ValueSet
					.buildEnumName(valueSetEntry.getCodeBaseType().getDisplayName());
			String preferredDesignation = valueSetEntry.getDesignation(LanguageCode.ENGLISH,
					DesignationType.PREFERRED);
			if (preferredDesignation != null)
				enumConstantName = ValueSet.buildEnumName(preferredDesignation);
			String code = valueSetEntry.getCodeBaseType().getCode();
			String codeSystem = valueSetEntry.getCodeBaseType().getCodeSystem();
			String displayName = valueSetEntry.getCodeBaseType().getDisplayName();

			NodeList<Expression> values = new NodeList<>();
			StringBuilder javadocEnum = new StringBuilder();
			StringBuilder javadocConstant = new StringBuilder();

			values.add(new StringLiteralExpr(code));
			values.add(new StringLiteralExpr(codeSystem));
			values.add(new StringLiteralExpr(displayName));

			// build comments per language
			javadocEnum.append("<!-- @formatter:off -->\n");
			javadocConstant.append("<!-- @formatter:off -->\n");
			for (LanguageCode language : LANGUAGE_CODES) {
				String designation = valueSetEntry.getDesignation(language,
						DesignationType.PREFERRED);
				if (designation == null)
					designation = valueSetEntry.getDesignation(language, DesignationType.PREFERRED);
				if ((designation == null) && (ENGLISH.equals(language)))
					designation = valueSetEntry.getCodeBaseType().getDisplayName();
				if (designation != null) {
					values.add(new StringLiteralExpr(designation));
					javadocEnum.append(buildJavadocComment(language, designation));
					javadocConstant.append(buildJavadocComment(language,
							CODE_JAVADOC_PREFIX.get(language) + designation));
				} else
					values.add(new StringLiteralExpr("TOTRANSLATE"));

			}
			javadocEnum.append("<!-- @formatter:on -->\n");
			javadocConstant.append("<!-- @formatter:on -->\n");

			// the enum constant with all values
			EnumConstantDeclaration enumConstant = new EnumConstantDeclaration(new NodeList<>(),
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
	 * @param language
	 *            The language code to add.
	 * @param comment
	 *            The comment to add.
	 * @return The HTML snippet of the comment.
	 */
	private static String buildJavadocComment(LanguageCode language, String comment) {
		return "<div class=\"" + language.getCodeValue().substring(0, 2) + "\">" + comment
				+ "</div>\n";
	}

	// /**
	// * <div class="en">Parses the description of a value set from its parsed
	// * JSON definition.</div>
	// *
	// * @param language
	// * The language of the description to parse.
	// * @param descriptions
	// * The description JSON object.
	// * @return The description in the desired language.
	// * @throws IllegalStateException
	// * If no description was found.
	// */
	// private static String getDescription(LanguageCode language, Object
	// descriptions)
	// throws IllegalStateException {
	// List<Map<String, String>> filteredDescriptions =
	// JsonPath.read(descriptions,
	// "$..[?(@.language =~ /" + language.getCodeValue() + ".*/i)]");
	// if (filteredDescriptions == null || filteredDescriptions.isEmpty()) {
	// return "no designation found for language " + language;
	// } else
	// return filteredDescriptions.get(0).get("content");
	// }

	/**
	 * <div class="en">Creates an enum definition class.</div>
	 *
	 * @param baseJavaFolder
	 *            The base Java source folder (relative to the root of the
	 *            project hierarchy) where the Java package structure begins.
	 * @param className
	 *            The fully qualified Java class name of the enum to update.
	 * @param valueSetDefinition
	 *            The parsed value set definition file.
	 * @throws IOException
	 *             When reading or writing the Java source file fails.
	 */
	private static void createEnumClassFromTemplate(String baseJavaFolder,
			String fullyQualifiedclassName) throws IOException {

		String className = fullyQualifiedclassName
				.substring(fullyQualifiedclassName.lastIndexOf('.') + 1);
		String packageName = fullyQualifiedclassName.substring(0,
				fullyQualifiedclassName.lastIndexOf('.'));

		String templateString = FileUtils
				.readFileToString(new File(TEMPLATE_FILE_LOCATION), Charsets.UTF_8)
				.replaceAll(TEMPLATE_NAME_TO_REPLACE, className)
				.replaceAll(TEMPLATE_PACKAGE_NAME_TO_REPLACE, packageName);

		ParseResult<CompilationUnit> javaSource = new JavaParser().parse(templateString);

		FileUtils.write(getSourceFileName(baseJavaFolder, fullyQualifiedclassName),
				javaSource.getResult().get().toString(PRETTY_PRINTER_CONFIGURATION),
				Charsets.UTF_8);

	}

	/**
	 * <div class="en">The main entry for the value set generator.</div>
	 *
	 * @param args
	 *            command line arguments: 1. Full path and filename to eclipse;
	 *            2. Full path to the workspace application
	 * @throws Exception
	 *             When any operation fails.
	 */
	public static void main(String[] args) throws Exception {

		log.debug("Update value sets");
		System.out.print("===== Update value sets =====\n");

		File eclipseApp = null;
		File orgWorkspacePath = null;
		boolean argsOk = false;

		if (args.clone().length >= 2) {
			argsOk = true;
			final String eclipseApplicationPath = args[0].toString();
			if (eclipseApplicationPath != null) {
				eclipseApp = new File(eclipseApplicationPath);
				if (!eclipseApp.exists()) {
					System.out.println("ERROR: Eclipse application does not exist ("
							+ eclipseApplicationPath + ")");
					argsOk = false;
				} else {
					if (!eclipseApp.isFile()) {
						System.out.println("ERROR: Eclipse application is not a file ("
								+ eclipseApplicationPath + ")");
						argsOk = false;
					}
				}
			}

			final String orgWorkspacePathString = args[1].toString();
			if (orgWorkspacePathString != null) {
				orgWorkspacePath = new File(orgWorkspacePathString);
				if (!orgWorkspacePath.exists()) {
					System.out.println(
							"ERROR: Workspace does not exist (" + orgWorkspacePathString + ")");
					argsOk = false;
				} else {
					if (!orgWorkspacePath.isDirectory()) {
						System.out.println("ERROR: Workspace is not a directory ("
								+ orgWorkspacePathString + ")");
						argsOk = false;
					}
				}
			}
		} else {
			System.out.println("Usage:");
			System.out.println("");
			System.out.println("UpdateValueSets <eclipse> <workspace>");
			System.out.println("");
			System.out.println(
					"  eclipse:   First parameter must be the full path and filename of your eclipse application");
			System.out.println(
					"             (e.g. C:\\JavaProgramme\\eclipse\\rcp-2019-06\\eclipse\\eclipse.exe");
			System.out.println("");
			System.out.println(
					"  workspace: Second parameter must be the full path to your current workspace directory");
			System.out.println(
					"             Note: It will be copied into a temp folder, as the current one is in use by Eclipse IDE");
			argsOk = false;
		}

		if (!argsOk) {
			System.out.println("");
			System.out.println("***");
			System.out.println("Try again :-)");
			return;
		}

		final String tempWorkspacePathString = Util.getTempDirectory()
				+ FileUtil.getPlatformSpecificPathSeparator() + "tmpWS_"
				+ orgWorkspacePath.getName();
		final File tempWorkspacePath = new File(tempWorkspacePathString);

		FileUtils.deleteDirectory(tempWorkspacePath);
		FileUtils.copyDirectoryToDirectory(orgWorkspacePath, tempWorkspacePath);

		System.out.println("Config base dir: " + new File(CONFIG_FILE_BASE_PATH).getAbsolutePath());
		System.out.println("Eclipse runtime: " + eclipseApp.getAbsolutePath());
		System.out.println("Temp. workspace: " + tempWorkspacePath.getAbsolutePath());

		String fnPackageConfig = CONFIG_FILE_BASE_PATH + SWISS_EPR_VALUE_SET_PACKAGE_CONFIG;
		// String fnPackageConfig = CONFIG_FILE_BASE_PATH +
		// ART_DECOR_VALUE_SET_PACKAGE_CONFIG;

		ValueSetPackageManager valueSetPackageManager;
		valueSetPackageManager = new ValueSetPackageManager();
		ValueSetPackageConfig valueSetPackageConfig = valueSetPackageManager
				.loadValueSetPackageConfig(fnPackageConfig);

		ValueSetManager valueSetManager = new ValueSetManager();
		for (ValueSetConfig valueSetConfig : valueSetPackageConfig.listValueSetConfigs()) {
			System.out.print("Processing enum: " + valueSetConfig.getClassName() + "\n");

			// This is for debug purposes, only:
			// if ("org.ehealth_connector.security.ch.epr.enums.PurposeOfUse"
			// .equals(valueSetConfig.getClassName()))
			// System.out.print("---> Let me break, here\n");

			System.out.print("- downloading ValueSet...");
			ValueSet valueSet = valueSetManager.downloadValueSet(valueSetConfig);
			System.out.print("done.\n");

			System.out.print("- creating Java Class...");
			String baseJavaFolder = PROJECT_ROOT_RELATIVE_PATH + "/"
					+ valueSetConfig.getProjectFolder();
			String fullyQualifiedclassName = valueSetConfig.getClassName();

			// This is for debug purposes, only:
			// valueSetManager.saveValueSet(valueSet, "/temp/" +
			// valueSet.getName() + ".yaml");

			// delete existing class file
			getSourceFileName(baseJavaFolder, fullyQualifiedclassName).delete();

			// create the class file
			createEnumClassFromTemplate(baseJavaFolder, fullyQualifiedclassName);

			File classFile = updateEnumClass(valueSet.getIdentificator().getRoot(),
					valueSet.getName(), baseJavaFolder, valueSetConfig.getClassName(), valueSet);

			// Apply formatter
			try {
				Util.runExternalCommand(
						eclipseApp + " -application org.eclipse.jdt.core.JavaCodeFormatter -data "
								+ tempWorkspacePath + " -config " + FORMATTER_PREFS + " "
								+ classFile.getAbsolutePath());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("\n");

			// This is for debug purposes, only (do one class, only):
			// break;
		}

		FileUtils.deleteDirectory(tempWorkspacePath);

		System.out.println(
				"Processed " + valueSetPackageConfig.listValueSetConfigs().size() + " enums.");

	}

	/**
	 * <div class="en">Remove everything from an enum type, leaving an empty
	 * definition body.</div>
	 *
	 * @param enumType
	 *            The enum to clean out.
	 */
	private static void removeEverything(EnumDeclaration enumType) {
		List<Node> allNodes = new ArrayList<>();
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
	 * @param body
	 *            The parsed body declaration of the Java class that holds the
	 *            constant.
	 * @param constantName
	 *            The name of the constant to replace the value of.
	 * @param value
	 *            The value to set.
	 */
	private static void replaceConstantValue(TypeDeclaration<? extends TypeDeclaration<?>> body,
			String constantName, String value) {
		body.getFieldByName(constantName).ifPresent(
				field -> field.getVariable(0).setInitializer(new StringLiteralExpr(value)));
	}

	/**
	 * <div class="en">Replaces the value of a annotation parameter.</div>
	 *
	 * @param annotation
	 *            The annotation that holds the parameter.
	 * @param parameterName
	 *            The name of the parameter to replace the value of.
	 * @param value
	 *            The value to set.
	 */
	private static void replaceParameterValue(AnnotationExpr annotationExpr, String parameterName,
			String value) {
		List<Node> parameters = annotationExpr.getChildNodes();
		for (Node parameter : parameters) {
			if (parameter instanceof MemberValuePair) {
				MemberValuePair memberValuePair = (MemberValuePair) parameter;
				if (parameterName.equals(memberValuePair.getNameAsString())) {
					memberValuePair.setValue(new StringLiteralExpr(value));
				}

			}
		}
	}

	/**
	 * <div class="en">Updates an existing enum definition and adds the value
	 * set elements as enum constants. Modifies the Java code of the enum
	 * class.</div>
	 *
	 * @param id
	 *            The unique code id that identifies the enum.
	 * @param valueSetName
	 *            The value set name of the enum.
	 * @param baseJavaFolder
	 *            The base Java source folder (relative to the root of the
	 *            project hierarchy) where the Java package structure begins.
	 * @param className
	 *            The fully qualified Java class name of the enum to update.
	 * @param valueSet
	 *            the value set
	 * @return the Java class file
	 * @throws IOException
	 *             When reading or writing the Java source file fails.
	 * @throws IllegalStateException
	 *             If the class does not declare an Enum type.
	 */
	private static File updateEnumClass(String id, String valueSetName, String baseJavaFolder,
			String className, ValueSet valueSet) throws IOException, IllegalStateException {

		JavaParser javaParser = new JavaParser();
		ParseResult<CompilationUnit> javaSource = javaParser
				.parse(getSourceFileName(baseJavaFolder, className));
		TypeDeclaration<?> primaryType = loadPrimaryType(javaSource.getResult().get());

		if (primaryType.isTopLevelType() && primaryType.isEnumDeclaration()) {
			EnumDeclaration enumType = ((EnumDeclaration) primaryType);

			// clear content and add all enum elements
			removeEverything(enumType);
			enumType.addImplementedType(ValueSetEnumInterface.class);
			enumType.addModifier(publicModifier().getKeyword());
			addEnumElements(enumType, valueSet);

			// add main javadoc
			StringBuilder javadoc = new StringBuilder();
			javadoc.append("<!-- @formatter:off -->\n");
			for (LanguageCode language : LANGUAGE_CODES) {
				String desc = valueSet.getDescription(language);
				if (desc == null)
					desc = "no designation found for language " + language;
				javadoc.append(buildJavadocComment(language, desc));
			}
			javadoc.append("<!-- @formatter:on -->\n");
			enumType.setJavadocComment(javadoc.toString());

			// add all members from template file
			String templateString = FileUtils.readFileToString(new File(TEMPLATE_FILE_LOCATION))
					.replaceAll(TEMPLATE_NAME_TO_REPLACE, enumType.getNameAsString());
			ParseResult<CompilationUnit> templateSource = javaParser.parse(templateString);
			TypeDeclaration<?> templateType = templateSource.getResult().get().getType(0);
			templateType.getMembers().forEach(enumType::addMember);

			// replace constant values and imports
			replaceConstantValue(enumType, "VALUE_SET_ID", id);
			replaceConstantValue(enumType, "VALUE_SET_NAME", valueSetName);

			// replace imports with those found in the template
			new ArrayList<>(javaSource.getResult().get().getImports())
					.forEach(javaSource.getResult().get()::remove);
			templateSource.getResult().get().getImports()
					.forEach(javaSource.getResult().get()::addImport);

			// @generated
			AnnotationExpr generated = templateType.getAnnotationByClass(Generated.class).get();
			replaceParameterValue(generated, "value",
					"org.ehealth_connector.codegenerator.ch.valuesets.UpdateValueSets");
			if (primaryType.getAnnotationByClass(Generated.class).isPresent()) {
				primaryType.getAnnotationByClass(Generated.class).get().remove();
			}
			primaryType.addAnnotation(generated);

		} else {
			throw new IllegalStateException(
					"Class with name " + className + " does not declare an Enum type.");
		}

		File destFile = getSourceFileName(baseJavaFolder, className);
		String classFileContent = javaSource.getResult().get()
				.toString(PRETTY_PRINTER_CONFIGURATION);
		classFileContent = classFileContent.replace("import java.util.Map;",
				"import java.util.Map;\n");
		classFileContent = classFileContent.replace("import javax.annotation.Generated;",
				"import javax.annotation.Generated;\n");
		FileUtils.write(destFile, classFileContent, Charsets.UTF_8);

		return destFile.getAbsoluteFile();
	}

}
