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
package org.ehealth_connector.codegenerator.cda.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.junit.Test;

/**
 * The test class for ContentProfilePackageConfig.
 */
public class ContentProfilePackageConfigTest {

	/**
	 * Do all tests.
	 *
	 * @throws MalformedURLException
	 */
	@Test
	public void doAllTests() throws MalformedURLException {

		// Basic ContentProfileConfig elements tests

		String artDecorBaseUrl1 = "https://foo.bar";
		String artDecorPrefix1 = "cdachemed-";
		String artDecorDocTemplateId1 = "2.999";
		String targetNamespace1 = "org.ehealth_connector.codegenerator.cda.emed";
		String targetDir1 = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "emed";

		ContentProfileConfig contentProfileConfig1 = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl1)
				.withArtDecorDocTemplateId(artDecorDocTemplateId1)
				.withArtDecorPrefix(artDecorPrefix1).withTargetDir(targetDir1)
				.withTargetNamespace(targetNamespace1).build();

		String artDecorBaseUrl2 = "https://foo.bar";
		String artDecorPrefix2 = "cdachlrep-";
		String artDecorDocTemplateId2 = "2.16.756.5.30.1.1.10.1.10";
		String targetNamespace2 = "org.ehealth_connector.codegenerator.cda.lrep";
		String targetDir2 = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "lrep";

		ContentProfileConfig contentProfileConfig2 = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl2)
				.withArtDecorDocTemplateId(artDecorDocTemplateId2)
				.withArtDecorPrefix(artDecorPrefix2).withTargetDir(targetDir2)
				.withTargetNamespace(targetNamespace2).build();

		String description = "This is a test package for multiple content profiles";
		ContentProfilePackageConfig contentProfilePackageConfig = ContentProfilePackageConfig
				.builder().withDescription(description).build();

		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig1);
		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig2);

		assertEquals(2, contentProfilePackageConfig.getContentProfileConfigList().size());

		assertTrue(artDecorBaseUrl1.toString().equals(contentProfilePackageConfig
				.getContentProfileConfigList().get(0).getArtDecorBaseUrl()));
		assertTrue(artDecorPrefix1.equals(contentProfilePackageConfig.getContentProfileConfigList()
				.get(0).getArtDecorPrefix()));
		assertTrue(artDecorDocTemplateId1.equals(contentProfilePackageConfig
				.getContentProfileConfigList().get(0).getArtDecorDocTemplateId()));

		assertTrue(targetNamespace2.equals(contentProfilePackageConfig.getContentProfileConfigList()
				.get(1).getTargetNamespace()));
		assertTrue(targetDir2.equals(
				contentProfilePackageConfig.getContentProfileConfigList().get(1).getTargetDir()));

	}
}
