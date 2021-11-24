/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.cda.config;

import java.net.MalformedURLException;

import org.husky.codegenerator.cda.ArtDecor2JavaManager;
import org.husky.common.utils.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test class for ContentProfileConfig.
 */
public class ContentProfileConfigTest {

	/**
	 * Do all tests.
	 *
	 * @throws MalformedURLException
	 */
	@Test
	public void doAllTests() throws MalformedURLException {

		// Basic ContentProfileConfig elements tests

		String artDecorBaseUrl = "https://foo.bar";
		String artDecorPrefix = "cdachlrep-";
		String artDecorDocTemplateId = "2.16.756.5.30.1.1.10.1.10";
		String targetNamespace = "org.husky.codegenerator.cda";
		String targetDir = Util.getTempDirectory() + "/test";

		ContentProfileConfig contentProfileConfig = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl).withTargetDir(targetDir)
				.withTargetNamespace(targetNamespace).build();

		contentProfileConfig.addTemplateId(artDecorDocTemplateId, "dynamic");
		contentProfileConfig.addProject(artDecorPrefix,
				ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL);

		assertTrue(artDecorBaseUrl.toString().equals(contentProfileConfig.getArtDecorBaseUrl()));
		assertTrue(artDecorPrefix
				.equals(contentProfileConfig.getArtDecorProjectMap().keySet().toArray()[0]));
		assertEquals(1, contentProfileConfig.getArtDecorDocTemplateMap().size());
		assertTrue(artDecorDocTemplateId
				.equals(contentProfileConfig.getArtDecorDocTemplateMap().keySet().toArray()[0]));
		assertTrue(targetNamespace.equals(contentProfileConfig.getTargetNamespace()));
		assertTrue(targetDir.equals(contentProfileConfig.getTargetDir()));

	}
}
