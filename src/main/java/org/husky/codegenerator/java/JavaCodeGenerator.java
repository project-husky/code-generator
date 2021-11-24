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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.printer.configuration.DefaultConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultPrinterConfiguration;
import com.github.javaparser.printer.configuration.Indentation;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.CaseUtils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import static com.github.javaparser.printer.configuration.DefaultPrinterConfiguration.ConfigOption.*;

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
	private static final char[] DELIMITERS = new char[] { '-', '_', ' ' };

	/**
	 * The Constant REGEX to be used for Pascal, camel, kebab and snake casing.
	 */
	private static final Pattern REGEX = Pattern.compile("(?<=[a-z0-9])[A-Z0-9]");

	/**
	 * The class is not instantiable.
	 */
	private JavaCodeGenerator() {
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
	 * @param outFile
	 *            the out file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void completeAndSave(final CompilationUnit compilationUnit, final File outFile) throws IOException {
		// Sort all
		final NodeList<ImportDeclaration> imports = compilationUnit.getImports();
		imports.sort(new ImportComparator());

		for (final ClassOrInterfaceDeclaration cl : compilationUnit.findAll(ClassOrInterfaceDeclaration.class)) {
			cl.getMembers().sort(new BodyDeclarationsComparator());
		}
		final ParseResult<CompilationUnit> javaSource = new JavaParser().parse(compilationUnit.toString());
		final String fileHeader = JavaCodeGenerator.getFileHeader();
		String generatedClassFileContent = javaSource.getResult().get().toString(getPrinterConfiguration());

		if (!generatedClassFileContent.startsWith(fileHeader)) {
			generatedClassFileContent = JavaCodeGenerator.getFileHeader() + "\r\n" + generatedClassFileContent;
		}
		FileUtils.writeStringToFile(outFile, generatedClassFileContent, StandardCharsets.UTF_8);
	}

	/**
	 * Gets the pretty printer configuration that is used to generate Java code.
	 *
	 * @return the pretty printer configuration.
	 */
	public static PrinterConfiguration getPrinterConfiguration() {
		final PrinterConfiguration config = new DefaultPrinterConfiguration();
		config.addOption(new DefaultConfigurationOption(ORDER_IMPORTS, true));
		config.addOption(new DefaultConfigurationOption(COLUMN_ALIGN_PARAMETERS, true));
		config.addOption(new DefaultConfigurationOption(INDENTATION, new Indentation(Indentation.IndentType.SPACES,
				4)));
		return config;
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
		final File fileHeaderFile = new File(
				System.getProperty("user.dir") + "/src/main/resources/format/JavaFileHeader.txt");
		String fileHeaderContent = "";

		try {
			fileHeaderContent = FileUtils.readFileToString(fileHeaderFile, StandardCharsets.UTF_8);
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
	public static String toCamelCase(final String value) {
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
	private static String toEssentials(final String value) {
		final String temp = value.replaceAll("[^A-Za-z0-9]", " ");
		final Matcher m = REGEX.matcher(temp);
		final StringBuilder sb = new StringBuilder();
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
	public static String toKebabCase(final String value) {
		final Matcher m = REGEX.matcher(toPascalCase(value));
		final StringBuilder sb = new StringBuilder();
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
	public static String toKebabCaseCaps(final String value) {
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
	public static String toPascalCase(final String value) {
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
	public static String toSnakeCase(final String value) {
		Matcher m = REGEX.matcher(toPascalCase(value));
		final StringBuilder sb = new StringBuilder();
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
	public static String toSnakeCaseCaps(final String value) {
		return toSnakeCase(value).toUpperCase();
	}
}
