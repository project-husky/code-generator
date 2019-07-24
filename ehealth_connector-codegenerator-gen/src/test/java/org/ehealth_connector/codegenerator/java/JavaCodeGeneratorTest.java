/*
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
package org.ehealth_connector.codegenerator.java;

import static com.github.javaparser.ast.Modifier.privateModifier;
import static com.github.javaparser.ast.Modifier.publicModifier;
import static com.github.javaparser.ast.Modifier.staticModifier;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.ehealth_connector.codegenerator.cda.config.ContentProfileConfig;
import org.junit.Test;

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
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import com.github.javaparser.printer.PrettyPrinterConfiguration.IndentType;

public class JavaCodeGeneratorTest {

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
	 * (including sorting of imports, fields and methods). Remember that
	 */
	@Test
	public void javaClassFileTest() {

		CompilationUnit compilationUnit = new CompilationUnit();

		compilationUnit.setPackageDeclaration("org.ehealth_connector.codegenerator.cda");

		compilationUnit.addImport("org.ehealth_connector.codegenerator.cda.java.JavaCodeGenerator");
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
		parameter.setName("myEhcType");
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
		PrettyPrinterConfiguration ppc = new PrettyPrinterConfiguration()
				.setIndentType(IndentType.TABS).setIndentSize(1);
		ParseResult<CompilationUnit> javaSource = new JavaParser()
				.parse(compilationUnit.toString());

		String generatedClassFileContent = JavaCodeGenerator.getFileHeader() + "\r\n"
				+ javaSource.getResult().get().toString(ppc);
		// This is for debugging purposes, only:
		System.out.println(generatedClassFileContent);

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

			expectedContent = FileUtils.readFileToString(expectedFile, Charsets.UTF_8);

			// This is for debugging purposes, only:
			// System.out.println(expectedContent);
		} catch (IOException e) {
			fail(e.getMessage());
		}

		assertTrue(expectedContent.equals(generatedClassFileContent));

	}
}
