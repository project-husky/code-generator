/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.java;

import static com.github.javaparser.ast.Modifier.privateModifier;
import static com.github.javaparser.ast.Modifier.publicModifier;
import static com.github.javaparser.ast.Modifier.staticModifier;
import static com.github.javaparser.printer.configuration.DefaultPrinterConfiguration.ConfigOption.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.github.javaparser.printer.configuration.*;
import org.apache.commons.io.FileUtils;
import org.husky.codegenerator.cda.config.ContentProfileConfig;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import org.junit.jupiter.api.Test;

/**
 * The test class for JavaCodeGenerator.
 */
public class JavaCodeGeneratorTest {

	/**
	 * Test to check whether casiing is done correctly.
	 */
	@Test
	public void casingTests() {

		String value = "Hi How areYouToday-at 9?";

		// this is for debugging purposes, only
		// System.out.println("original: " + value);
		// System.out.println("toEssentials: " +
		// JavaCodeGenerator.toEssentials(value));
		// System.out.println("toCamelCase: " +
		// JavaCodeGenerator.toCamelCase(value));
		// System.out.println("toPascalCase: " +
		// JavaCodeGenerator.toPascalCase(value));
		// System.out.println("toSnakeCase: " +
		// JavaCodeGenerator.toSnakeCase(value));
		// System.out.println("toSnakeCaseCaps: " +
		// JavaCodeGenerator.toSnakeCaseCaps(value));
		// System.out.println("toKebabCase: " +
		// JavaCodeGenerator.toKebabCase(value));
		// System.out.println("toKebabCaseCaps: " +
		// JavaCodeGenerator.toKebabCaseCaps(value));

		assertTrue("hiHowAreYouTodayAt9".equals(JavaCodeGenerator.toCamelCase(value)));
		assertTrue("HiHowAreYouTodayAt9".equals(JavaCodeGenerator.toPascalCase(value)));
		assertTrue("hi_how_are_you_today_at_9".equals(JavaCodeGenerator.toSnakeCase(value)));
		assertTrue("HI_HOW_ARE_YOU_TODAY_AT_9".equals(JavaCodeGenerator.toSnakeCaseCaps(value)));
		assertTrue("hi-how-are-you-today-at-9".equals(JavaCodeGenerator.toKebabCase(value)));
		assertTrue("HI-HOW-ARE-YOU-TODAY-AT-9".equals(JavaCodeGenerator.toKebabCaseCaps(value)));

	}

	/**
	 * Tests whether the Java Code generator generates the desired content
	 * (including sorting of imports, fields and methods).
	 */
	@Test
	public void javaClassFileTest() {

		CompilationUnit compilationUnit = new CompilationUnit();

		compilationUnit.setPackageDeclaration("org.husky.codegenerator.cda");

		compilationUnit.addImport("org.husky.codegenerator.cda.java.JavaCodeGenerator");
		compilationUnit.addImport("com.sun.xml.bind.v2.*");
		compilationUnit.addImport("java.util.Comparator");

		FieldDeclaration field;
		MethodDeclaration method;
		Parameter parameter;

		ClassOrInterfaceDeclaration myClass = compilationUnit
				.addClass(JavaCodeGenerator.toPascalCase("my camel caser")).setPublic(true);
		myClass.setJavadocComment("This is a sample Class");
		myClass.setExtendedTypes(
				new NodeList<ClassOrInterfaceType>(new ClassOrInterfaceType(null, "Blah")));
		method = myClass.addMethod(JavaCodeGenerator.toCamelCase("My method"),
				publicModifier().getKeyword());
		method.setType(boolean.class);
		method.setJavadocComment("This is a sample method (function returning a value).");

		parameter = new Parameter();
		parameter.setName("myString");
		parameter.setType(String.class);
		method.addParameter(parameter);

		parameter = new Parameter();
		parameter.setName("myHuskyType");
		parameter.setType(ContentProfileConfig.class);
		method.addParameter(parameter);

		method.addAndGetParameter(int.class, "myIntParam");

		method.createBody().addStatement("return true;");

		method = myClass.addMethod(JavaCodeGenerator.toCamelCase("Another great method"),
				publicModifier().getKeyword());
		method.setJavadocComment(
				"This is a sample void method having a long Javadoc description string that will be splitted into multiple lines by the Java code formatter at the very end of the Java class file creation by the JavaGenerator. Formatting is not tested, here. Therefore this comment is intended to appear on one single line!.");

		field = myClass.addField(String.class, "name", privateModifier().getKeyword());

		field = myClass.addField(String.class, "anotherMember", privateModifier().getKeyword());

		field = myClass.addField(int.class, "B_TEST_CONSTANT", publicModifier().getKeyword(),
				staticModifier().getKeyword());
		field.setJavadocComment("This is a sample constant.");

		field = myClass.addField(int.class, "A_CONSTANT", publicModifier().getKeyword(),
				staticModifier().getKeyword());
		field.setJavadocComment("This is a sample constant.");

		field = myClass.addField(int.class, "myInt", publicModifier().getKeyword());
		field.setJavadocComment("This is a sample field member");

		field = myClass.addField(String.class, "abc", publicModifier().getKeyword());
		field.setJavadocComment("This is a sample field member.");

		// Sort all
		NodeList<ImportDeclaration> imports = compilationUnit.getImports();
		imports.sort(new ImportComparator());

		for (ClassOrInterfaceDeclaration cl : compilationUnit
				.findAll(ClassOrInterfaceDeclaration.class)) {
			cl.getMembers().sort(new BodyDeclarationsComparator());
		}

		final PrinterConfiguration ppc = new DefaultPrinterConfiguration();
		ppc.addOption(new DefaultConfigurationOption(ORDER_IMPORTS, true));
		ppc.addOption(new DefaultConfigurationOption(COLUMN_ALIGN_PARAMETERS, true));
		ppc.addOption(new DefaultConfigurationOption(INDENTATION, new Indentation(Indentation.IndentType.SPACES, 4)));
		ParseResult<CompilationUnit> javaSource = new JavaParser()
				.parse(compilationUnit.toString());

		String generatedClassFileContent = JavaCodeGenerator.getFileHeader();
		generatedClassFileContent += "\n" + javaSource.getResult().get().toString(ppc);
		generatedClassFileContent = generatedClassFileContent.replace("\r\n", "\n");

		// This is for debugging purposes, only:
		// System.out.println(generatedClassFileContent);

		File expectedFile = new File(
				System.getProperty("user.dir") + "/src/test/resources/MyCamelCaser.java");
		String expectedContent = "";
		try {
			// This is just to save new "expected Content in case the test
			// before has been updated. Remember that the formatter is applied
			// as external command (eclipse formatter application) and
			// therefore not tested, here!
			// FileUtils.writeStringToFile(expectedFile,
			// generatedClassFileContent, Charsets.UTF_8);

			expectedContent = FileUtils.readFileToString(expectedFile, StandardCharsets.UTF_8)
					.replace("\r\n", "\n");

			// This is for debugging purposes, only:
			// System.out.println(expectedContent);
		} catch (IOException e) {
			fail(e.getMessage());
		}

		assertTrue(expectedContent.equals(generatedClassFileContent));

	}
}
