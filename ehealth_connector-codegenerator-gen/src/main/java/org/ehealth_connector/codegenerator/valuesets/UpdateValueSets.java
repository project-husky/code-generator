/*
 *
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
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

import static java.util.Arrays.asList;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.DEFAULT_CHARSET;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.VALUE_SET_CONCEPTS_PATH;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.buildEnumName;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.buildValueSetUrl;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.getDisplayName;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.getSourceFileName;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.getValueSetDefinitionFile;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.loadConfiguration;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.loadPrimaryType;
import static org.ehealth_connector.codegenerator.valuesets.ValueSetUtil.loadValueSetDefinition;
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

import org.apache.commons.io.FileUtils;
import org.ehealth_connector.codegenerator.ch.valuesets.model.ValueSet;
import org.ehealth_connector.codegenerator.ch.valuesets.model.ValueSetConfiguration;
import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.common.enums.ValueSetEnumInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier.Keyword;
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
import com.jayway.jsonpath.JsonPath;

/**
 * <div class="en">This class generates the CH-EPR value sets from the
 * downloaded JSON files from art-decor.org</div>
 */
public class UpdateValueSets {

	private static Logger log = LoggerFactory.getLogger(UpdateValueSets.class);

	/**
	 * <div class="en">Javadoc comment prefix for the code fields.</div>
	 */
	private static final Map<LanguageCode, String> CODE_JAVADOC_PREFIX = ImmutableMap.of(ENGLISH,
			"Code for ", GERMAN, "Code für ", FRENCH, "Code de ", ITALIAN, "Code per ");

	/**
	 * <div class="en">Base path where to find the config files for the
	 * generator (YAML and JSON files).</div>
	 */
	private static final String CONFIG_FILE_BASE_PATH = "./";

	/**
	 * <div class="en">List of all languages that should be used to generate
	 * javadoc comments.</div>
	 */
	private static final List<LanguageCode> LANGUAGE_CODES = asList(ENGLISH, GERMAN, FRENCH,
			ITALIAN);

	/**
	 * <div class="en">Java code formatter/pretty printer configuration used to
	 * write Java code.</div>
	 */
	private static final PrettyPrinterConfiguration PRETTY_PRINTER_CONFIGURATION = new PrettyPrinterConfiguration()
			.setIndentType(IndentType.TABS).setMaxEnumConstantsToAlignHorizontally(1);

	/**
	 * <div class="en">Relative path to the root of the maven project
	 * structure.</div>
	 */
	private static final String PROJECT_ROOT_RELATIVE_PATH = "../../../";

	/**
	 * <div class="en">Shortcut for the internal type of a string.</div>
	 */
	private static final Type STRING_TYPE = com.github.javaparser.StaticJavaParser
			.parseClassOrInterfaceType("String");

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
	private static void addEnumElements(EnumDeclaration enumType,
			List<Map<String, Object>> concepts) {
		for (Map<String, Object> concept : concepts) {

			String enumConstantName = buildEnumName(getDisplayName(ENGLISH, concept));
			String code = concept.get("code").toString();
			String codeSystem = concept.get("codeSystem").toString();

			NodeList<Expression> values = new NodeList<>();
			StringBuilder javadocEnum = new StringBuilder();
			StringBuilder javadocConstant = new StringBuilder();

			values.add(new StringLiteralExpr(code));
			values.add(new StringLiteralExpr(codeSystem));
			values.add(new StringLiteralExpr(concept.get("displayName").toString()));

			// build comments per language
			javadocEnum.append("<!-- @formatter:off -->\n");
			javadocConstant.append("<!-- @formatter:off -->\n");
			for (LanguageCode language : LANGUAGE_CODES) {
				values.add(new StringLiteralExpr(getDisplayName(language, concept)));
				String displayName = getDisplayName(language, concept);
				javadocEnum.append(buildJavadocComment(language, displayName));
				javadocConstant.append(buildJavadocComment(language,
						CODE_JAVADOC_PREFIX.get(language) + displayName));
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
					new StringLiteralExpr(code), Keyword.PUBLIC, Keyword.STATIC, Keyword.FINAL)
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
		return " <div class=\"" + language.getCodeValue().substring(0, 2) + "\">" + comment
				+ "</div>\n";
	}

	/**
	 * <div class="en">Creates an enum definition class.</div>
	 *
	 * @param id
	 *            The unique code id that identifies the enum.
	 * @param codeSystemName
	 *            The code system name of the enum.
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
	private static void createEnumClassFromTemplate(String id, String codeSystemName,
			String baseJavaFolder, String fullyQualifiedclassName) throws IOException {

		String className = fullyQualifiedclassName
				.substring(fullyQualifiedclassName.lastIndexOf('.') + 1);
		String packageName = fullyQualifiedclassName.substring(0,
				fullyQualifiedclassName.lastIndexOf('.'));

		String templateString = FileUtils
				.readFileToString(new File(TEMPLATE_FILE_LOCATION), DEFAULT_CHARSET)
				.replaceAll(TEMPLATE_NAME_TO_REPLACE, className)
				.replaceAll(TEMPLATE_PACKAGE_NAME_TO_REPLACE, packageName);

		ParseResult<CompilationUnit> javaSource = new JavaParser().parse(templateString);

		FileUtils.write(getSourceFileName(baseJavaFolder, fullyQualifiedclassName),
				javaSource.getResult().get().toString(PRETTY_PRINTER_CONFIGURATION),
				DEFAULT_CHARSET);

	}

	/**
	 * <div class="en">Parses the description of a value set from its parsed
	 * JSON definition.</div>
	 *
	 * @param language
	 *            The language of the description to parse.
	 * @param descriptions
	 *            The description JSON object.
	 * @return The description in the desired language.
	 * @throws IllegalStateException
	 *             If no description was found.
	 */
	private static String getDescription(LanguageCode language, Object descriptions)
			throws IllegalStateException {
		List<Map<String, String>> filteredDescriptions = JsonPath.read(descriptions,
				"$..[?(@.language =~ /" + language.getCodeValue() + ".*/i)]");
		if (filteredDescriptions == null || filteredDescriptions.isEmpty()) {
			return "no designation found for language " + language;
		} else
			return filteredDescriptions.get(0).get("content");
	}

	/**
	 * <div class="en">The main entry for the value set generator.</div>
	 *
	 * @param args
	 *            command line arguments (none used)
	 * @throws Exception
	 *             When any operation fails.
	 */
	public static void main(String[] args) throws Exception {
		log.debug("Update value sets");
		System.out.print("===== Update value sets =====\n");

		System.out.print("Configuration base path: " + CONFIG_FILE_BASE_PATH + "\n");
		ValueSetConfiguration configuration = loadConfiguration(CONFIG_FILE_BASE_PATH);

		System.out.print("Configuration base url: " + configuration.getBaseUrl() + "\n");
		System.out.print(" \n");

		int count = 0;
		for (ValueSet valueSet : configuration.getValueSets()) {

			System.out.print("Processing value set: " + valueSet.getCodeSystemName() + "\n");

			boolean downloadIfNotInFileSystem = false;
			if (!getValueSetDefinitionFile(CONFIG_FILE_BASE_PATH, valueSet).exists()) {
				downloadIfNotInFileSystem = true;
				System.out.print("Download definition: "
						+ buildValueSetUrl(configuration.getBaseUrl(), valueSet) + "\n");
			} else {
				System.out.print("Definition file exists, no download\n");
			}

			System.out.print("Read definition file: "
					+ getValueSetDefinitionFile(CONFIG_FILE_BASE_PATH, valueSet).getName() + "\n");

			Map<String, Object> valueSetDefinition = loadValueSetDefinition(CONFIG_FILE_BASE_PATH,
					valueSet, downloadIfNotInFileSystem, configuration.getBaseUrl());

			String baseJavaFolder = PROJECT_ROOT_RELATIVE_PATH + "/" + valueSet.getProjectFolder();
			String fullyQualifiedclassName = valueSet.getClassName();

			// delete existing class file
			getSourceFileName(baseJavaFolder, fullyQualifiedclassName).delete();

			if (!getSourceFileName(baseJavaFolder, fullyQualifiedclassName).exists()) {
				System.out.print("Create class from template: " + fullyQualifiedclassName + "\n");

				createEnumClassFromTemplate(valueSet.getId(), valueSet.getCodeSystemName(),
						baseJavaFolder, fullyQualifiedclassName);
			}

			System.out.print("Project folder: " + valueSet.getProjectFolder() + "\n");

			System.out.print("Updating class: " + valueSet.getClassName() + " ... ");

			updateEnumClass(valueSet.getId(), valueSet.getCodeSystemName(), baseJavaFolder,
					valueSet.getClassName(), valueSetDefinition);

			System.out.print("done.\n\n");
			count++;
		}

		System.out.println("Processed " + count + " value sets.");
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
	 * @param codeSystemName
	 *            The code system name of the enum.
	 * @param baseJavaFolder
	 *            The base Java source folder (relative to the root of the
	 *            project hierarchy) where the Java package structure begins.
	 * @param className
	 *            The fully qualified Java class name of the enum to update.
	 * @param valueSetDefinition
	 *            The parsed value set definition file.
	 * @throws IOException
	 *             When reading or writing the Java source file fails.
	 * @throws IllegalStateException
	 *             If the class does not declare an Enum type.
	 */
	private static void updateEnumClass(String id, String codeSystemName, String baseJavaFolder,
			String className, Map<String, Object> valueSetDefinition)
			throws IOException, IllegalStateException {

		JavaParser javaParser = new JavaParser();
		ParseResult<CompilationUnit> javaSource = javaParser
				.parse(getSourceFileName(baseJavaFolder, className));
		TypeDeclaration<?> primaryType = loadPrimaryType(javaSource.getResult().get());

		if (primaryType.isTopLevelType() && primaryType.isEnumDeclaration()) {
			EnumDeclaration enumType = ((EnumDeclaration) primaryType);

			// clear content and add all enum elements
			removeEverything(enumType);
			enumType.addImplementedType(ValueSetEnumInterface.class);
			addEnumElements(enumType, JsonPath.read(valueSetDefinition, VALUE_SET_CONCEPTS_PATH));

			// add main javadoc
			StringBuilder javadoc = new StringBuilder();
			javadoc.append("<!-- @formatter:off -->\n");
			for (LanguageCode language : LANGUAGE_CODES) {
				javadoc.append(buildJavadocComment(language,
						getDescription(language, valueSetDefinition.get("desc"))));
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
			replaceConstantValue(enumType, "VALUE_SET_NAME", codeSystemName);

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

		FileUtils.write(getSourceFileName(baseJavaFolder, className),
				javaSource.getResult().get().toString(PRETTY_PRINTER_CONFIGURATION),
				DEFAULT_CHARSET);
	}
}
