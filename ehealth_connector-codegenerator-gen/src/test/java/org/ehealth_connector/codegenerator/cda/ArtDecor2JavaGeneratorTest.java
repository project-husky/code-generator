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
package org.ehealth_connector.codegenerator.cda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.ehealth_connector.codegenerator.java.JavaCodeGenerator;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.junit.Test;

import net.sf.saxon.s9api.SaxonApiException;

public class ArtDecor2JavaGeneratorTest {
	/**
	 * Do all tests.
	 *
	 * @throws SaxonApiException
	 * @throws IOException
	 */
	@Test
	public void doAllTests() throws SaxonApiException, IOException {

		String templateId = "2.16.756.5.30.1.1.10.1.10";

		ArrayList<CdaElement> cdaMemberList = new ArrayList<CdaElement>();
		HashMap<String, String> templateIndex = new HashMap<String, String>();
		String srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "test" + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		String dstFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator();
		String fileHeader = JavaCodeGenerator.getFileHeader();

		ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null,
				templateIndex, srcFilePath, dstFilePath, "org.ehealth_connector.codegenerator.cda",
				fileHeader);

		artDecor2JavaGenerator.doOneTemplate(templateId);

	}

}
