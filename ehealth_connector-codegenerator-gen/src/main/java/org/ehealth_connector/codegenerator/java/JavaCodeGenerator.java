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
package org.ehealth_connector.codegenerator.java;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.CaseUtils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import com.github.javaparser.printer.PrettyPrinterConfiguration.IndentType;

/**
 * <div class="en">This class contains some methods to assist the Java Code
 * generation by using the JavaParser engine. See https://javaparser.org/ for
 * more information.</div>
 *
 * <div class="de">Diese Klasse enthält einige Methoden zur Unterstützung der
 * Java-Codegenerierung mithilfe der JavaParser-Engine. Weitere Informationen:
 * https://javaparser.org/.</div>
 *
 */
public class JavaCodeGenerator {

	/**
	 * The Constant DELIMITERS to be used for Pascal, camel, kebab and snake
	 * casing.
	 */
	private final static char[] DELIMITERS = new char[] { '-', '_', ' ' };

	/**
	 * The Constant REGEX to be used for Pascal, camel, kebab and snake casing.
	 */
	private final static String REGEX = "(?<=[a-z0-9])[A-Z0-9]";

	/**
	 * <div class="en">Sorts the class content alphabetically and saves it to
	 * the given file.</div>
	 *
	 * <div class="de">Sortiert den Klasseninhalt alphabetisch und speichert ihn
	 * in der angegebenen Datei.</div>
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param outFile
	 *            the out file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void completeAndSave(CompilationUnit compilationUnit, File outFile)
			throws IOException {

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

		String fileHeader = JavaCodeGenerator.getFileHeader();

		String generatedClassFileContent = javaSource.getResult().get().toString(ppc);

		if (!generatedClassFileContent.startsWith(fileHeader))
			generatedClassFileContent = JavaCodeGenerator.getFileHeader() + "\r\n"
					+ generatedClassFileContent;

		// This is for debugging purposes, only:
		// System.out.println(generatedClassFileContent);

		FileUtils.writeStringToFile(outFile, generatedClassFileContent, Charsets.UTF_8);

	}

	/**
	 * <div class="en">Sorts the class content alphabetically and saves it to
	 * the given file.</div>
	 *
	 * <div class="de">Sortiert den Klasseninhalt alphabetisch und speichert ihn
	 * in der angegebenen Datei.</div>
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void completeAndSave(CompilationUnit compilationUnit, String fileName)
			throws IOException {
		completeAndSave(compilationUnit, new File(fileName));
	}

	/**
	 * <div class="en">Gets the eHealth Connectors default Java file
	 * header.</div>
	 *
	 * <div class="de">Ruft den Standard Java File Header des eHealth Connectors
	 * ab.</div>
	 *
	 * @return the file header
	 */
	public static String getFileHeader() {
		File fileHeaderFile = new File(
				System.getProperty("user.dir") + "/src/main/resources/format/JavaFileHeader.txt");
		String fileHeaderContent = "";

		try {
			fileHeaderContent = FileUtils.readFileToString(fileHeaderFile, Charsets.UTF_8);
		} catch (IOException e1) {
			// Do nothing
		}
		return fileHeaderContent;
	}

	/**
	 * <div class="en">Converts the given String to camelCase notation.</div>
	 *
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
	 * snake casing.</div>
	 *
	 * <div class="de">Bereitet den angegebenen String vor für Pascal-, Kamel-,
	 * Kebab- und Snake Notationen.</div>
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
	 * (lowercase letters).</div>
	 *
	 * <div class="de">Konvertiert den angegebenen String in die
	 * kebab-case-Notation (Kleinbuchstaben)</div>
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
	 * (capital/uppercase letters).</div>
	 *
	 * <div class="de">Konvertiert den angegebenen String in die
	 * KEBAB-CASE-Notation (Grossbuchastaben)</div>
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
	 *
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
	 * (lowercase letters).</div>
	 *
	 * <div class="de">Konvertiert den angegebenen String in die
	 * snake_case-Notation (Kleinbuchstaben)</div>
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
	 * (capital/uppercase letters).</div>
	 *
	 * <div class="de">Konvertiert den angegebenen String in die
	 * SNAKE_CASE-Notation (Grossbuchastaben)</div>
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String toSnakeCaseCaps(String value) {
		return toSnakeCase(value).toUpperCase();
	}

}
