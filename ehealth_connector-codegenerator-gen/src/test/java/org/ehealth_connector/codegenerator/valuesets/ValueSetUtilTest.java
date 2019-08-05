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

import static org.junit.Assert.assertEquals;

import org.ehealth_connector.valueset.model.ValueSet;
import org.junit.Test;

/**
 * The test class for ValueSetUtil.
 */
public class ValueSetUtilTest {

	/**
	 * Test correct building of enum names .
	 */
	@Test
	public void testBuildEnumName() {

		assertEquals("HOSPITAL", ValueSet.buildEnumName("Hospital"));

		assertEquals("LABORATORY_SERVICE", ValueSet.buildEnumName("Laboratory service"));

		assertEquals("ANY_TEXT_AND_ANY_TEXT", ValueSet.buildEnumName("Any text &amp; any text"));

		assertEquals("ACCIDENT_AND_EMERGENCY", ValueSet.buildEnumName("Accident &amp; emergency"));

		assertEquals("CLIENT_OR_PATIENT_HOME",
				ValueSet.buildEnumName("Client's or patient's home"));

		assertEquals("IMMUNIZATION_CONTENT", ValueSet.buildEnumName("Immunization Content (IC)"));

		assertEquals("IMMUNIZATION_CONTENT", ValueSet.buildEnumName("IMMUNIZATION__CONTENT"));

		assertEquals("IMMUNIZATION_CONTENT", ValueSet.buildEnumName("IMMUNIZATION___CONTENT"));

	}

}
