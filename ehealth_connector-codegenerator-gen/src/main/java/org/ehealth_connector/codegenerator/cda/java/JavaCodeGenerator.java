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
package org.ehealth_connector.codegenerator.cda.java;

import static com.github.javaparser.ast.Modifier.privateModifier;
import static com.github.javaparser.ast.Modifier.publicModifier;
import static com.github.javaparser.ast.Modifier.staticModifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.CaseUtils;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class JavaCodeGenerator {

	// TODO: hier weiterfahren
	// 1. Javadoc bestehende Methoden
	// 2. Sorted List für Imports
	// 3. Member und Method sorter
	//
	/**
	 * <pre>
	 * camelCase
	 * PascalCase
	 * snake_case
	 * kebab-case
	</pre>
	 */

	private final static String REGEX = "(?<=[a-z0-9])[A-Z0-9]";
	private final static char[] DELIMITERS = new char[] { '-', '_', ' ' };

	public static String toCamelCase(String value) {
		return CaseUtils.toCamelCase(toEssentials(value), false, DELIMITERS);
	}

	protected static String toEssentials(String value) {
		String temp = value.replaceAll("[^A-Za-z0-9]", " ");
		Matcher m = Pattern.compile(REGEX).matcher(temp);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, " " + m.group().toLowerCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static String toKebabCase(String value) {
		Matcher m = Pattern.compile(REGEX).matcher(toPascalCase(value));
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "-" + m.group().toLowerCase());
		}
		m.appendTail(sb);
		return sb.toString().toLowerCase();
	}

	public static String toKebabCaseCaps(String value) {
		return toKebabCase(value).toUpperCase();
	}

	public static String toPascalCase(String value) {
		return CaseUtils.toCamelCase(toEssentials(value), true, DELIMITERS);
	}

	public static String toSnakeCase(String value) {
		Matcher m = Pattern.compile(REGEX).matcher(toPascalCase(value));
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "_" + m.group().toLowerCase());
		}
		m.appendTail(sb);
		return sb.toString().toLowerCase();
	}

	public static String toSnakeCaseCaps(String value) {
		return toSnakeCase(value).toUpperCase();
	}

	public JavaCodeGenerator() {

	}

	protected void demo() {
		CompilationUnit compilationUnit = new CompilationUnit();

		compilationUnit.addImport("org.ehealth_connector.codegenerator.cda.java.JavaCodeGenerator");

		ClassOrInterfaceDeclaration myClass1 = compilationUnit.addClass("MyClass").setPublic(true);
		myClass1.setJavadocComment("This is a sample Class");
		myClass1.addField(int.class, "A_CONSTANT", publicModifier().getKeyword(),
				staticModifier().getKeyword());
		myClass1.addField(String.class, "name", privateModifier().getKeyword());
		// System.out.println(myClass1);
		// System.out.println();

		ClassOrInterfaceDeclaration myClass2 = compilationUnit.addClass("X").setPublic(true);
		myClass2.setJavadocComment("This is another sample Class");
		myClass2.addField(String.class, "abc", publicModifier().getKeyword());
		// System.out.println(myClass2);
		// System.out.println();

		ClassOrInterfaceDeclaration myClass3 = compilationUnit
				.addClass(toPascalCase("my camel caser")).setPublic(true);
		myClass3.setJavadocComment("Camel Case sample Class");
		myClass3.addField(String.class, toCamelCase("My method"), publicModifier().getKeyword());
		// System.out.println(myClass3);
		// System.out.println();

		System.out.println(compilationUnit);

	}
}
