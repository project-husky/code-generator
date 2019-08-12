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
package org.ehealth_connector.codegenerator.cda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.ehealth_connector.codegenerator.cda.config.ConfigurationException;
import org.ehealth_connector.codegenerator.cda.config.ContentProfileConfig;
import org.ehealth_connector.codegenerator.cda.config.ContentProfilePackageConfig;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.junit.Test;

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
	public void saveLoadTestContentProfileConfig() throws IOException, ConfigurationException {
		ArtDecor2JavaManager artDecor2JavaManager = new ArtDecor2JavaManager();
		File configFile = new File(Util.getTempDirectory()
				+ FileUtil.getPlatformSpecificPathSeparator() + "ContentProfileConfig.yml");

		// Medication Card document
		String artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_MAIN_SERVER_BASE_URL;
		String artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.3";
		String artDecorPrefix = "cdachemed-";
		String targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "emed";
		String targetNamespace = "org.ehealth_connector.cda.ch.emed";

		ContentProfileConfig contentProfileConfig = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

		// Prepare cleanup
		configFile.deleteOnExit();

		// Save a content profile config
		artDecor2JavaManager.saveContentProfileConfig(contentProfileConfig, configFile);

		// load the saved package
		ArtDecor2JavaManager artDecor2JavaManager2 = new ArtDecor2JavaManager();
		ContentProfileConfig contentProfileConfig2 = artDecor2JavaManager2
				.loadContentProfileConfig(configFile);

		assertTrue(artDecorBaseUrl.equals(contentProfileConfig2.getArtDecorBaseUrl()));
		assertTrue(artDecorPrefix.equals(contentProfileConfig2.getArtDecorPrefix()));
		assertTrue(artDecorDocTemplateId.equals(contentProfileConfig2.getArtDecorDocTemplateId()));
		assertTrue(targetNamespace.equals(contentProfileConfig2.getTargetNamespace()));
		assertTrue(targetDir.equals(contentProfileConfig2.getTargetDir()));

	}

	/**
	 * Tests for save and load a content profile package configuration on the
	 * example of CDA-CH-EMED.
	 *
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	@Test
	public void saveLoadTestContentProfilePackageConfig()
			throws IOException, ConfigurationException {
		ArtDecor2JavaManager artDecor2JavaManager = new ArtDecor2JavaManager();
		File configFile = new File(Util.getTempDirectory()
				+ FileUtil.getPlatformSpecificPathSeparator() + "ContentProfilePackageConfig.yml");

		// Create a content profile package config
		ContentProfilePackageConfig contentProfilePackageConfig = ContentProfilePackageConfig
				.builder().withDescription("A content profile package").build();

		// Medication Card document
		String artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_MAIN_SERVER_BASE_URL;
		String artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.3";
		String artDecorPrefix = "cdachemed-";
		String targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "emed";
		String targetNamespace = "org.ehealth_connector.cda.ch.emed";

		ContentProfileConfig contentProfileConfig = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig);

		// Medication Dispense document
		artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_MAIN_SERVER_BASE_URL;
		artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.5";
		artDecorPrefix = "cdachemed-";
		targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "emed";
		targetNamespace = "org.ehealth_connector.cda.ch.emed";

		contentProfileConfig = ContentProfileConfig.builder().withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig);

		// Medication List document
		artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_MAIN_SERVER_BASE_URL;
		artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.13";
		artDecorPrefix = "cdachemed-";
		targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "emed";
		targetNamespace = "org.ehealth_connector.cda.ch.emed";

		contentProfileConfig = ContentProfileConfig.builder().withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig);

		// Medication Prescription document
		artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_MAIN_SERVER_BASE_URL;
		artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.4";
		artDecorPrefix = "cdachemed-";
		targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "emed";
		targetNamespace = "org.ehealth_connector.cda.ch.emed";

		contentProfileConfig = ContentProfileConfig.builder().withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig);

		// Medication Treatment Plan document
		artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_MAIN_SERVER_BASE_URL;
		artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.7";
		artDecorPrefix = "cdachemed-";
		targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "emed";
		targetNamespace = "org.ehealth_connector.cda.ch.emed";

		contentProfileConfig = ContentProfileConfig.builder().withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig);

		// Pharmaceutical Advice document
		artDecorBaseUrl = ArtDecor2JavaManager.ART_DECOR_MAIN_SERVER_BASE_URL;
		artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.6";
		artDecorPrefix = "cdachemed-";
		targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "emed";
		targetNamespace = "org.ehealth_connector.cda.ch.emed";

		contentProfileConfig = ContentProfileConfig.builder().withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

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
		assertEquals(count, contentProfilePackageConfig.getContentProfileConfigList().size());

		assertTrue(artDecorBaseUrl.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(count - 1).getArtDecorBaseUrl()));
		assertTrue(artDecorPrefix.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(count - 1).getArtDecorPrefix()));
		assertTrue(artDecorDocTemplateId.equals(contentProfilePackageConfig2
				.getContentProfileConfigList().get(count - 1).getArtDecorDocTemplateId()));
		assertTrue(targetNamespace.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(count - 1).getTargetNamespace()));
		assertTrue(targetDir.equals(contentProfilePackageConfig2.getContentProfileConfigList()
				.get(count - 1).getTargetDir()));

	}
}
