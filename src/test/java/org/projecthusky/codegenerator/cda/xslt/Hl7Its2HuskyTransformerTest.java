/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.cda.xslt;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.projecthusky.codegenerator.cda.rest.ArtDecorRestClientTest;
import org.projecthusky.common.utils.Util;

import net.sf.saxon.s9api.SaxonApiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class for Hl7Its2HuskyTransformer.
 */
public class Hl7Its2HuskyTransformerTest {

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
				Util.getTempDirectory() + "/src/test/resources/2.16.756.5.30.1.1.10.1.10_transformed.xml");

		File srcFile = new File(System.getProperty("user.dir")
				+ "/src/test/resources/2.16.756.5.30.1.1.10.1.10.xml");

		// Prepare Cleanup
		destFile.deleteOnExit();

		Hl7Its2HuskyTransformer.transform(srcFile, destFile);

		String expectedContent = IOUtils
				.toString(new File(System.getProperty("user.dir") + "/src/test/resources/2.16.756.5.30.1.1.10.1" +
				".10_transformed.xml").toURI(), StandardCharsets.UTF_8)
				.replace("\r\n", "\n");

		String content = FileUtils.readFileToString(destFile, StandardCharsets.UTF_8).replace("\r\n", "\n");

		assertEquals(expectedContent, content);
	}

	private void transformOneDocumentTemplate(String templateId) throws Exception {
		String dir = Util.getTempDirectory() + "/test/" + templateId + "/";
		File srcFile = new File(dir + templateId + ".xml");
		File dstFile = new File(dir + templateId + "_transformed.xml");

		File folder = new File(dir + "/kit");

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

		Hl7Its2HuskyTransformer.transform(srcFile, dstFile);

		int countTransformed = 0;
		for (final File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				if (!file.getName().endsWith("_transformed.xml")) {
					srcFile = file;
					dstFile = new File(file.getAbsolutePath().replace(".xml", "_transformed.xml"));

					Hl7Its2HuskyTransformer.transform(srcFile, dstFile);
					countTransformed++;
				}
			}
		}

		assertEquals((countTransformed * 2), folder.listFiles().length);
	}
}
