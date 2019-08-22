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
package org.ehealth_connector.codegenerator.cda.xslt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.ehealth_connector.codegenerator.cda.rest.ArtDecorRestClientTest;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.junit.Test;

import net.sf.saxon.s9api.SaxonApiException;

/**
 * The test class for Hl7Its2EhcTransformer.
 */
public class Hl7Its2EhcTransformerTest {

	/**
	 * Transformation test for all ART-DECOR HL7 ITS XML files of the content
	 * profile CDA-CH-EMED.
	 *
	 * @throws Exception
	 *             the exception
	 */
	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
	public void transformationTestEmed() throws Exception {

		String templateId;

		// MedicationCardDocument
		templateId = "2.16.756.5.30.1.1.10.1.3";
		transformOneDocumentTemplate(templateId);

		// MedicationDispenseDocument
		templateId = "2.16.756.5.30.1.1.10.1.5";
		transformOneDocumentTemplate(templateId);

		// MedicationListDocument
		templateId = "2.16.756.5.30.1.1.10.1.13";
		transformOneDocumentTemplate(templateId);

		// MedicationPrescriptionDocument
		templateId = "2.16.756.5.30.1.1.10.1.4";
		transformOneDocumentTemplate(templateId);

		// MedicationTreatmentPlanDocument
		templateId = "2.16.756.5.30.1.1.10.1.7";
		transformOneDocumentTemplate(templateId);

		// PharmaceuticalAdviceDocument
		templateId = "2.16.756.5.30.1.1.10.1.6";
		transformOneDocumentTemplate(templateId);

	}

	/**
	 * Transformation test for all ART-DECOR HL7 ITS XML files of the content
	 * profile CDA-CH-LREP.
	 *
	 * @throws Exception
	 *             the exception
	 */
	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
	public void transformationTestLrep() throws Exception {

		String templateId;

		// Swiss Laboratory Report - General Report
		templateId = "2.16.756.5.30.1.1.10.1.10";
		transformOneDocumentTemplate(templateId);

	}

	/**
	 * Test a single transformation.
	 *
	 * @throws SaxonApiException
	 *             the saxon api exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void transformationTestSingle() throws SaxonApiException, IOException {

		File destFile = new File(
				Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
						+ "2.16.756.5.30.1.1.10.1.10_transformed.xml");

		File srcFile = new File(System.getProperty("user.dir")
				+ "/src/test/resources/2.16.756.5.30.1.1.10.1.10.xml");

		// Prepare Cleanup
		destFile.deleteOnExit();

		Hl7Its2EhcTransformer.transform(srcFile, destFile);

		String expectedContent = IOUtils
				.toString(getClass().getResourceAsStream(
						"/2.16.756.5.30.1.1.10.1.10_transformed.xml"), Charsets.UTF_8)
				.replace("\r\n", "\n");

		String content = FileUtils.readFileToString(destFile, Charsets.UTF_8).replace("\r\n", "\n");

		assertTrue(expectedContent.equals(content));
	}

	private void transformOneDocumentTemplate(String templateId) throws Exception {
		String dir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		File srcFile = new File(dir + templateId + ".xml");
		File dstFile = new File(dir + templateId + "_transformed.xml");

		File folder = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + "kit");

		// Prepare test environment
		boolean prepare = false;
		if (folder.exists())
			prepare = (folder.listFiles().length == 0);
		else
			prepare = !srcFile.exists();

		if (prepare) {
			ArtDecorRestClientTest test = new ArtDecorRestClientTest();
			test.templateTestRecursiveCdaChLrep();
		}

		Hl7Its2EhcTransformer.transform(srcFile, dstFile);

		int countTransformed = 0;
		for (final File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				if (!file.getName().endsWith("_transformed.xml")) {
					srcFile = file;
					dstFile = new File(file.getAbsolutePath().replace(".xml", "_transformed.xml"));

					Hl7Its2EhcTransformer.transform(srcFile, dstFile);
					countTransformed++;
				}
			}
		}

		assertEquals((countTransformed * 2), folder.listFiles().length);

	}
}
