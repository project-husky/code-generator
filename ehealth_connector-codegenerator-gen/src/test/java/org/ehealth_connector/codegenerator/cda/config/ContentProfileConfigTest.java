/*
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
package org.ehealth_connector.codegenerator.cda.config;

import static org.junit.Assert.assertTrue;

import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.junit.Test;

/**
 * The test class for ContentProfileConfig.
 */
public class ContentProfileConfigTest {

	/**
	 * Do all tests.
	 */
	@Test
	public void doAllTests() {

		// Basic ContentProfileConfig elements tests

		String artDecorBaseUrl = "https://foo.bar";
		String artDecorPrefix = "cdachlrep-";
		String artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.10";
		String targetNamespace = "org.ehealth_connector.codegenerator.cda";
		String targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "test";

		ContentProfileConfig contentProfileConfig = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl)
				.withArtDecorDocTemplateId(artDecorDocTemplateId).withArtDecorPrefix(artDecorPrefix)
				.withTargetDir(targetDir).withTargetNamespace(targetNamespace).build();

		assertTrue(artDecorBaseUrl.equals(contentProfileConfig.getArtDecorBaseUrl()));
		assertTrue(artDecorPrefix.equals(contentProfileConfig.getArtDecorPrefix()));
		assertTrue(artDecorDocTemplateId.equals(contentProfileConfig.getArtDecorDocTemplateId()));
		assertTrue(targetNamespace.equals(contentProfileConfig.getTargetNamespace()));
		assertTrue(targetDir.equals(contentProfileConfig.getTargetDir()));

	}
}
