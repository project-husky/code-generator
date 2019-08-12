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
 * This line is intended for UTF-8 encoding checks, do not modify/delete: Ã¤Ã¶Ã¼Ã©Ã¨
 *
 */
package org.ehealth_connector.codegenerator.cda.rest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.junit.Test;

/**
 * The test class for ArtDecorRestClient.
 */
public class ArtDecorRestClientTest {

	/**
	 * Test ART-DECOR project index download.
	 *
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void projectIndexTest() throws ClientProtocolException, IOException {

		String content = "";
		InputStream is = ArtDecorRestClient.getArtDecorProjectIndex(
				new URL("https://art-decor.org/decor/services/"), "cdachlrep-");
		content = IOUtils.toString(is, Charsets.UTF_8);

		assertTrue(content.startsWith("<return prefix=\"cdachlrep-\""));
	}

	/**
	 * Test download of all ART-DECOR templates used for the content profile
	 * CDA-CH-EMED.
	 *
	 * @throws Exception
	 *             the exception
	 */
	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
	public void templateTestRecursiveCdaChEmed() throws Exception {

		String dir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator();
		ArtDecorRestClient artDecorRestClient = new ArtDecorRestClient(
				new URL("https://art-decor.org/decor/services/"), dir);

		artDecorRestClient.addArtDecorProject("cdachemed-");
		artDecorRestClient.addArtDecorProject("ad1bbr-");
		artDecorRestClient.addArtDecorProject("ad2bbr-");
		artDecorRestClient.addArtDecorProject("ch-epr-");
		artDecorRestClient.addArtDecorProject("ch-pharm-");
		artDecorRestClient.addArtDecorProject("ch-pcc-");
		artDecorRestClient.addArtDecorProject("hl7chcda-");

		File dest;

		// MedicationCardDocument
		String templateId;
		templateId = "2.16.756.5.30.1.1.10.1.3";
		artDecorRestClient.downloadTemplateRecursive("cdachemed-", templateId, "dynamic");
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId);
		assertTrue(dest.listFiles().length == 2);
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator() + "kit");
		assertTrue(dest.listFiles().length > 1);

		// MedicationDispenseDocument
		templateId = "2.16.756.5.30.1.1.10.1.5";
		artDecorRestClient.downloadTemplateRecursive("cdachemed-", templateId, "dynamic");
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId);
		assertTrue(dest.listFiles().length == 2);
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator() + "kit");
		assertTrue(dest.listFiles().length > 1);

		// MedicationListDocument
		templateId = "2.16.756.5.30.1.1.10.1.13";
		artDecorRestClient.downloadTemplateRecursive("cdachemed-", templateId, "dynamic");
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId);
		assertTrue(dest.listFiles().length == 2);
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator() + "kit");
		assertTrue(dest.listFiles().length > 1);

		// MedicationPrescriptionDocument
		templateId = "2.16.756.5.30.1.1.10.1.4";
		artDecorRestClient.downloadTemplateRecursive("cdachemed-", templateId, "dynamic");
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId);
		assertTrue(dest.listFiles().length == 2);
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator() + "kit");
		assertTrue(dest.listFiles().length > 1);

		// MedicationTreatmentPlanDocument
		templateId = "2.16.756.5.30.1.1.10.1.7";
		artDecorRestClient.downloadTemplateRecursive("cdachemed-", templateId, "dynamic");
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId);
		assertTrue(dest.listFiles().length == 2);
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator() + "kit");
		assertTrue(dest.listFiles().length > 1);

		// PharmaceuticalAdviceDocument
		templateId = "2.16.756.5.30.1.1.10.1.6";
		artDecorRestClient.downloadTemplateRecursive("cdachemed-", templateId, "dynamic");
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId);
		assertTrue(dest.listFiles().length == 2);
		dest = new File(dir + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator() + "kit");
		assertTrue(dest.listFiles().length > 1);

	}

	/**
	 * Test download of all ART-DECOR templates used for the content profile
	 * CDA-CH-LREP.
	 *
	 * @throws Exception
	 *             the exception
	 */
	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
	public void templateTestRecursiveCdaChLrep() throws Exception {

		String dir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator();
		ArtDecorRestClient artDecorRestClient = new ArtDecorRestClient(
				new URL("https://art-decor.org/decor/services/"), dir);

		artDecorRestClient.addArtDecorProject("cdachlrep-");
		artDecorRestClient.addArtDecorProject("hl7chcda-");
		artDecorRestClient.addArtDecorProject("ch-epr-");
		artDecorRestClient.addArtDecorProject("ch-palm-");
		artDecorRestClient.addArtDecorProject("ch-pcc-");
		artDecorRestClient.addArtDecorProject("ch-pharm-");

		// for Other Relevant Observations Section - coded
		artDecorRestClient.addArtDecorProject("cdachvacd-");

		artDecorRestClient.downloadTemplateRecursive("cdachlrep-", "2.16.756.5.30.1.1.10.1.10",
				"2019-07-30T18:01:10");

		File dest;
		dest = new File(
				dir + FileUtil.getPlatformSpecificPathSeparator() + "2.16.756.5.30.1.1.10.1.10");
		assertTrue(dest.listFiles().length == 2);
		dest = new File(
				dir + FileUtil.getPlatformSpecificPathSeparator() + "2.16.756.5.30.1.1.10.1.10"
						+ FileUtil.getPlatformSpecificPathSeparator() + "kit");
		assertTrue(dest.listFiles().length > 1);
	}

	/**
	 * Test a single template download from ART-DECOR.
	 *
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void templateTestSingle() throws ClientProtocolException, IOException {

		String content = "";
		InputStream is = ArtDecorRestClient.getArtDecorTemplate(
				new URL("https://art-decor.org/decor/services/"), "cdachlrep-",
				"2.16.756.5.30.1.1.10.1.10", "2018-04-19T00:00:00");
		content = IOUtils.toString(is, Charsets.UTF_8);

		// This is just to write down the expected content. Do not commit with
		// the following line enabled!
		// FileUtils.writeStringToFile(expectedFile, content, Charsets.UTF_8);

		String expectedContent = IOUtils.toString(
				getClass().getResourceAsStream("/2.16.756.5.30.1.1.10.1.10.xml"), Charsets.UTF_8);

		assertTrue(expectedContent.equals(content));
	}
}
