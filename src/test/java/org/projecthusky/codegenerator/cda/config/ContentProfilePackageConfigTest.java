/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.cda.config;

import java.net.MalformedURLException;

import org.projecthusky.common.utils.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		String targetNamespace1 = "org.projecthusky.codegenerator.cda.emed";
		String targetDir1 = Util.getTempDirectory() + "/emed";

		ContentProfileConfig contentProfileConfig1 = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl1).withTargetDir(targetDir1)
				.withTargetNamespace(targetNamespace1).build();

		contentProfileConfig1.addTemplateId(artDecorDocTemplateId1, "dynamic");
		contentProfileConfig1.addProject(artDecorPrefix1, artDecorBaseUrl1);

		String artDecorBaseUrl2 = "https://foo.bar";
		String artDecorPrefix2 = "cdachlrep-";
		String artDecorDocTemplateId2 = "2.16.756.5.30.1.1.10.1.10";
		String targetNamespace2 = "org.projecthusky.codegenerator.cda.lrep";
		String targetDir2 = Util.getTempDirectory() + "/lrep";

		ContentProfileConfig contentProfileConfig2 = ContentProfileConfig.builder()
				.withArtDecorBaseUrl(artDecorBaseUrl2).withTargetDir(targetDir2)
				.withTargetNamespace(targetNamespace2).build();

		contentProfileConfig2.addTemplateId(artDecorDocTemplateId2, "dynamic");
		contentProfileConfig1.addProject(artDecorPrefix2, artDecorBaseUrl2);

		String description = "This is a test package for multiple content profiles";
		ContentProfilePackageConfig contentProfilePackageConfig = ContentProfilePackageConfig
				.builder().withDescription(description).build();

		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig1);
		contentProfilePackageConfig.addContentProfileConfig(contentProfileConfig2);

		assertEquals(2, contentProfilePackageConfig.getContentProfileConfigList().size());

		assertTrue(artDecorBaseUrl1.toString().equals(contentProfilePackageConfig
				.getContentProfileConfigList().get(0).getArtDecorBaseUrl()));
		assertTrue(artDecorPrefix1.equals(contentProfilePackageConfig.getContentProfileConfigList()
				.get(0).getArtDecorProjectMap().keySet().toArray()[0]));

		assertTrue(artDecorDocTemplateId1
				.equals(contentProfilePackageConfig.getContentProfileConfigList().get(0)
						.getArtDecorDocTemplateMap().keySet().toArray()[0]));

		assertTrue(targetNamespace2.equals(contentProfilePackageConfig.getContentProfileConfigList()
				.get(1).getTargetNamespace()));
		assertTrue(targetDir2.equals(
				contentProfilePackageConfig.getContentProfileConfigList().get(1).getTargetDir()));

	}
}
