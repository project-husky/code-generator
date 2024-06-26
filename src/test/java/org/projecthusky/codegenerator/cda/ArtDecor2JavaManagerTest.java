/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.cda;

import java.io.File;
import java.io.IOException;

import org.projecthusky.codegenerator.cda.config.ConfigurationException;
import org.projecthusky.codegenerator.cda.config.ContentProfileConfig;
import org.projecthusky.codegenerator.cda.config.ContentProfilePackageConfig;
import org.projecthusky.common.utils.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * The test class for ArtDecor2JavaManager.
 */
public class ArtDecor2JavaManagerTest {

	/**
	 * Tests for save and load a content profile configuration on the example of
	 * CDA-CH-EMED.
	 *
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	@Test
	public void saveLoadTestContentProfileConfigEmed() throws IOException, ConfigurationException {

		ArtDecor2JavaManager artDecor2JavaManager = new ArtDecor2JavaManager();
		File configFile = new File(
				Util.getTempDirectory() + "/ContentProfilePackageConfigCdaChEmedV097.yml");

		// Create a content profile package config
		ContentProfilePackageConfig contentProfilePackageConfig = ContentProfilePackageConfig
				.builder()
				.withDescription(
						"CDA-CH-EMED - Swiss eMedication, draft version 0.97 of February 22, 2021")
				.build();

		String artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL;
		String artDecorPrefix = "cdachemed-";
		String targetDir = "api-java/husky-cda/husky-cda-ch";
		String targetNamespace = "org.projecthusky.cda.ch.emed.v097";
		ContentProfileConfig contentProfileConfig = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl).withTargetDir(targetDir)
				.withTargetNamespace(targetNamespace).build();

		// Add the main prefix
		contentProfileConfig.addProject(artDecorPrefix,
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);

		// Add further prefixes (referenced projects)
		contentProfileConfig.addProject("ad1bbr-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("ad2bbr-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("ch-epr-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("ch-pharm-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("ch-pcc-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("hl7chcda-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("IHE-PCC-",
				"https://art-decor.ihe-europe.net/decor/services/");

		// Medication Card document
		contentProfileConfig.addTemplateId("2.16.756.5.30.1.1.10.1.3", "2016-05-13T00:00:00");

		// Medication Dispense document
		contentProfileConfig.addTemplateId("2.16.756.5.30.1.1.10.1.5", "2016-05-21T00:00:00");

		// Medication List document
		contentProfileConfig.addTemplateId("2.16.756.5.30.1.1.10.1.13", "2018-01-22T15:17:26");

		// Medication Prescription document
		contentProfileConfig.addTemplateId("2.16.756.5.30.1.1.10.1.4", "2016-05-21T00:00:00");

		// Medication Treatment Plan document
		contentProfileConfig.addTemplateId("2.16.756.5.30.1.1.10.1.7", "2017-04-12T13:57:31");

		// Pharmaceutical Advice document
		contentProfileConfig.addTemplateId("2.16.756.5.30.1.1.10.1.6", "2016-05-21T00:00:00");

		// Complete package config
		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig);

		// Prepare cleanup
		configFile.deleteOnExit();

		// Save a content profile config
		artDecor2JavaManager.saveContentProfilePackageConfig(contentProfilePackageConfig,
				configFile);

		// load the saved package
		ArtDecor2JavaManager artDecor2JavaManager2 = new ArtDecor2JavaManager();
		ContentProfilePackageConfig contentProfilePackageConfig2 = artDecor2JavaManager2
				.loadContentProfilePackageConfig(configFile);

		int count = 6;
		assertEquals(1, contentProfilePackageConfig.getContentProfileConfigList().size());
		assertEquals(count, contentProfilePackageConfig.getContentProfileConfigList().get(0)
				.getArtDecorDocTemplateMap().size());

		assertTrue(artDecorBaseUrl.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(0).getArtDecorBaseUrl()));
		assertTrue(contentProfilePackageConfig2.getContentProfileConfigList().get(0)
				.getArtDecorProjectMap().get(artDecorPrefix) != null);
		assertEquals(count, contentProfilePackageConfig2.getContentProfileConfigList().get(0)
				.getArtDecorDocTemplateMap().size());
		assertTrue(contentProfilePackageConfig2.getContentProfileConfigList().get(0)
				.getArtDecorDocTemplateMap().get("2.16.756.5.30.1.1.10.1.3") != null);
		assertTrue(contentProfilePackageConfig2.getContentProfileConfigList().get(0)
				.getArtDecorDocTemplateMap().get("2.16.756.5.30.1.1.10.1.6") != null);
		assertTrue(targetNamespace.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(0).getTargetNamespace()));
		assertTrue(targetDir.equals(
				contentProfilePackageConfig2.getContentProfileConfigList().get(0).getTargetDir()));

	}

	/**
	 * Tests for save and load a content profile configuration on the example of
	 * CDA-CH-EMED.
	 *
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	@Test
	public void saveLoadTestContentProfileConfigLrep() throws IOException, ConfigurationException {

		ArtDecor2JavaManager artDecor2JavaManager = new ArtDecor2JavaManager();
		File configFile = new File(
				Util.getTempDirectory() + "/ContentProfilePackageConfigCdaChLrepV133.yml");

		// Create a content profile package config
		ContentProfilePackageConfig contentProfilePackageConfig = ContentProfilePackageConfig
				.builder()
				.withDescription(
						"CDA-CH-LREP - Swiss Laboratory Report - General Report, draft version 1.3.3 of August 12, 2019")
				.build();

		String artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL;
		String artDecorPrefix = "cdachlrep-";
		String targetDir = "api-java/husky-cda/husky-cda-ch";
		String targetNamespace = "org.projecthusky.cda.ch.lrep.v133";
		ContentProfileConfig contentProfileConfig = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl).withTargetDir(targetDir)
				.withTargetNamespace(targetNamespace).build();

		// Add the main prefix
		contentProfileConfig.addProject(artDecorPrefix,
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);

		// Add further prefixes (referenced projects)
		contentProfileConfig.addProject("hl7chcda-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("ch-epr-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("ch-palm-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("ch-pcc-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);
		contentProfileConfig.addProject("cdachvacd-",
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);

		// Swiss Laboratory Report - General Report
		contentProfileConfig.addTemplateId("2.16.756.5.30.1.1.10.1.10", "2019-07-30T18:01:10");

		// Complete package config
		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig);

		// Prepare cleanup
		configFile.deleteOnExit();

		// Save a content profile config
		artDecor2JavaManager.saveContentProfilePackageConfig(contentProfilePackageConfig,
				configFile);

		// load the saved package
		ArtDecor2JavaManager artDecor2JavaManager2 = new ArtDecor2JavaManager();
		ContentProfilePackageConfig contentProfilePackageConfig2 = artDecor2JavaManager2
				.loadContentProfilePackageConfig(configFile);

		int count = 1;
		assertEquals(1, contentProfilePackageConfig.getContentProfileConfigList().size());
		assertEquals(count, contentProfilePackageConfig.getContentProfileConfigList().get(0)
				.getArtDecorDocTemplateMap().size());

		assertTrue(artDecorBaseUrl.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(0).getArtDecorBaseUrl()));
		assertTrue(contentProfilePackageConfig2.getContentProfileConfigList().get(0)
				.getArtDecorProjectMap().get(artDecorPrefix) != null);
		assertEquals(count, contentProfilePackageConfig2.getContentProfileConfigList().get(0)
				.getArtDecorDocTemplateMap().size());
		assertTrue(contentProfilePackageConfig2.getContentProfileConfigList().get(0)
				.getArtDecorDocTemplateMap().get("2.16.756.5.30.1.1.10.1.10") != null);
		assertTrue(targetNamespace.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(0).getTargetNamespace()));
		assertTrue(targetDir.equals(
				contentProfilePackageConfig2.getContentProfileConfigList().get(0).getTargetDir()));

	}
}
