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
package org.ehealth_connector.codegenerator.cda.rest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;

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
	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
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

		HashMap<String, String> artDecorProjectMap = new HashMap<String, String>();
		artDecorProjectMap.put("cdachemed-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ad1bbr-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ad2bbr-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ch-epr-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ch-pharm-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ch-pcc-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("hl7chcda-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("IHE-PCC-", "https://art-decor.ihe-europe.net/decor/services/");

		ArtDecorRestClient artDecorRestClient = new ArtDecorRestClient(artDecorProjectMap, dir);

		File dest;

		// MedicationCardDocument
		String templateId;
		templateId = "2.16.756.5.30.1.1.10.1.3";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic");

		// MedicationDispenseDocument
		templateId = "2.16.756.5.30.1.1.10.1.5";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic");

		// MedicationListDocument
		templateId = "2.16.756.5.30.1.1.10.1.13";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic");

		// MedicationPrescriptionDocument
		templateId = "2.16.756.5.30.1.1.10.1.4";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic");

		// MedicationTreatmentPlanDocument
		templateId = "2.16.756.5.30.1.1.10.1.7";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic");

		// PharmaceuticalAdviceDocument
		templateId = "2.16.756.5.30.1.1.10.1.6";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic");

		// tests
		dest = new File(dir);
		assertTrue(dest.listFiles().length == 15);
		dest = new File(dir + "kit");
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

		HashMap<String, String> artDecorProjectMap = new HashMap<String, String>();
		artDecorProjectMap.put("cdachlrep-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("hl7chcda-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ch-epr-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ch-palm-", "https://art-decor.org/decor/services/");
		artDecorProjectMap.put("ch-pcc-", "https://art-decor.org/decor/services/");
		// for Other Relevant Observations Section - coded
		artDecorProjectMap.put("cdachvacd-", "https://art-decor.org/decor/services/");

		ArtDecorRestClient artDecorRestClient = new ArtDecorRestClient(artDecorProjectMap, dir);

		artDecorRestClient.downloadTemplateRecursive("2.16.756.5.30.1.1.10.1.10",
				"2019-07-30T18:01:10");

		File dest;
		dest = new File(dir);
		assertTrue(dest.listFiles().length == 8);
		dest = new File(dir + "kit");
		assertTrue(dest.listFiles().length > 1);
	}

}
