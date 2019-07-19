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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JavaCodeGeneratorTest {

	public void demo() {
		JavaCodeGenerator javaCodeGenerator = new JavaCodeGenerator();
		javaCodeGenerator.demo();

	}

	/**
	 * Do all tests.
	 */
	@Test
	public void doAllTests() {

		// this is for debugging purposes, only
		demo();

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

}
