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
package org.ehealth_connector.codegenerator.cda;

import static com.github.javaparser.ast.Modifier.privateModifier;
import static com.github.javaparser.ast.Modifier.publicModifier;
import static com.github.javaparser.ast.Modifier.staticModifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.CaseUtils;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * <div class="en">This is the main class of the ART-DECOR to Java code
 * generator. It orchestrates the individual modules (such as REST Client to
 * ART-DECOR, XSLT, ANTLR and the final Java Class file creation).</div>
 *
 * <div class="de">Es orchestriert die einzelnen Module (z. B. REST-Client to
 * ART-DECOR, XSLT, ANTLR und die endgültige Erstellung der
 * Java-Klassendateien).</div>
 *
 * <pre>
 * TODOs:
 * 1. Javadoc bestehende Methoden
 * 2. Sorted List für Imports
 * 3. Member und Method sorter
 * </pre>
 *
 */
public class JavaCodeGenerator {

	/**
	 * The Constant REGEX to be used for Pascal, camel, kebab and snake casing.
	 */
	private final static String REGEX = "(?<=[a-z0-9])[A-Z0-9]";

	/**
	 * The Constant DELIMITERS to be used for Pascal, camel, kebab and snake
	 * casing.
	 */
	private final static char[] DELIMITERS = new char[] { '-', '_', ' ' };

	/**
	 * <div class="en">Converts the given String to camelCase notation.</div>
	 * <div class="de">Konvertiert den angegebenen String in die
	 * camelCase-Notation.</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String toCamelCase(String value) {
		return CaseUtils.toCamelCase(toEssentials(value), false, DELIMITERS);
	}

	/**
	 * <div class="en">Prepares the given String for Pascal, camel, kebab and
	 * snake casing.</div> <div class="de">Bereitet den angegebenen String vor
	 * für Pascal-, Kamel-, Kebab- und Snake Notationen.</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	private static String toEssentials(String value) {
		String temp = value.replaceAll("[^A-Za-z0-9]", " ");
		Matcher m = Pattern.compile(REGEX).matcher(temp);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, " " + m.group().toLowerCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * <div class="en">Converts the given String to kebab-case notation
	 * (lowercase letters).</div> <div class="de">Konvertiert den angegebenen
	 * String in die kebab-case-Notation (Kleinbuchstaben)</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String toKebabCase(String value) {
		Matcher m = Pattern.compile(REGEX).matcher(toPascalCase(value));
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "-" + m.group().toLowerCase());
		}
		m.appendTail(sb);
		return sb.toString().toLowerCase();
	}

	/**
	 * <div class="en">Converts the given String to KEBAB-CASE notation
	 * (capital/uppercase letters).</div> <div class="de">Konvertiert den
	 * angegebenen String in die KEBAB-CASE-Notation (Grossbuchastaben)</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String toKebabCaseCaps(String value) {
		return toKebabCase(value).toUpperCase();
	}

	/**
	 * <div class="en">Converts the given String to PascalCase notation.</div>
	 * <div class="de">Konvertiert den angegebenen String in die
	 * PascalCase-Notation.</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String toPascalCase(String value) {
		return CaseUtils.toCamelCase(toEssentials(value), true, DELIMITERS);
	}

	/**
	 * <div class="en">Converts the given String to snake_case notation
	 * (lowercase letters).</div> <div class="de">Konvertiert den angegebenen
	 * String in die snake_case-Notation (Kleinbuchstaben)</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String toSnakeCase(String value) {
		Matcher m = Pattern.compile(REGEX).matcher(toPascalCase(value));
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "_" + m.group().toLowerCase());
		}
		m.appendTail(sb);
		return sb.toString().toLowerCase();
	}

	/**
	 * <div class="en">Converts the given String to SNAKE_CASE notation
	 * (capital/uppercase letters).</div> <div class="de">Konvertiert den
	 * angegebenen String in die SNAKE_CASE-Notation (Grossbuchastaben)</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String toSnakeCaseCaps(String value) {
		return toSnakeCase(value).toUpperCase();
	}

	/**
	 * Instantiates a new java code generator. Default constructor.
	 */
	public JavaCodeGenerator() {

	}

	/**
	 * Demo. This is for development and debug purposes, only.
	 */
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
