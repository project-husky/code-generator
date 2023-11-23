/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.cda.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.projecthusky.common.utils.Util;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
		content = IOUtils.toString(is, StandardCharsets.UTF_8);

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

		String dir = Util.getTempDirectory() + "/test/";

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
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic", templateId);

		// MedicationDispenseDocument
		templateId = "2.16.756.5.30.1.1.10.1.5";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic", templateId);

		// MedicationListDocument
		templateId = "2.16.756.5.30.1.1.10.1.13";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic", templateId);

		// MedicationPrescriptionDocument
		templateId = "2.16.756.5.30.1.1.10.1.4";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic", templateId);

		// MedicationTreatmentPlanDocument
		templateId = "2.16.756.5.30.1.1.10.1.7";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic", templateId);

		// PharmaceuticalAdviceDocument
		templateId = "2.16.756.5.30.1.1.10.1.6";
		artDecorRestClient.downloadTemplateRecursive(templateId, "dynamic", templateId);

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

		String dir = Util.getTempDirectory() + "/test/";

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
				"2019-07-30T18:01:10", "2.16.756.5.30.1.1.10.1.10");

		File dest;
		dest = new File(dir);
		assertTrue(dest.listFiles().length == 8);
		dest = new File(dir + "kit");
		assertTrue(dest.listFiles().length > 1);
	}

}
