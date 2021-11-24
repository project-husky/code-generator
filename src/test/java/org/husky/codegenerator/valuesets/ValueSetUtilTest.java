/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.valuesets;

import org.husky.valueset.model.ValueSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class for ValueSetUtil.
 */
public class ValueSetUtilTest {

	/**
	 * Test correct building of enum names .
	 */
	@Test
	public void testBuildEnumName() {

		assertEquals("HOSPITAL", ValueSet.buildEnumName("Hospital", 0));

		assertEquals("HOSPITAL_L1", ValueSet.buildEnumName("Hospital", 1));

		assertEquals("HOSPITAL_L2", ValueSet.buildEnumName("Hospital", 2));

		assertEquals("LABORATORY_SERVICE", ValueSet.buildEnumName("Laboratory service", 0));

		assertEquals("ANY_TEXT_AND_ANY_TEXT", ValueSet.buildEnumName("Any text &amp; any text", 0));

		assertEquals("ACCIDENT_AND_EMERGENCY",
				ValueSet.buildEnumName("Accident &amp; emergency", 0));

		assertEquals("CLIENT_OR_PATIENT_HOME",
				ValueSet.buildEnumName("Client's or patient's home", 0));

		assertEquals("IMMUNIZATION_CONTENT",
				ValueSet.buildEnumName("Immunization Content (IC)", 0));

		assertEquals("IMMUNIZATION_CONTENT", ValueSet.buildEnumName("IMMUNIZATION__CONTENT", 0));

		assertEquals("IMMUNIZATION_CONTENT", ValueSet.buildEnumName("IMMUNIZATION___CONTENT", 0));

	}

}
