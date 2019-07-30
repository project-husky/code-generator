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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.ehealth_connector.codegenerator.java.JavaCodeGenerator;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;

import net.sf.saxon.s9api.SaxonApiException;

public class ArtDecor2JavaGeneratorTest {
	// @Test
	public void doEmedTest()
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		// Initialization
		ArtDecor2JavaGenerator artDecor2JavaGenerator = null;
		HashMap<String, String> templateIndex = new HashMap<String, String>();
		ArrayList<CdaTemplate> templateList = new ArrayList<CdaTemplate>();
		String dstFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator();
		String fileHeader = JavaCodeGenerator.getFileHeader();
		String srcFilePath;
		String templateId;

		// MedicationCardDocument
		templateId = "2.16.756.5.30.1.1.10.1.3";
		srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, templateList,
				srcFilePath, dstFilePath, "org.ehealth_connector.testonly.cda.ch.emed", fileHeader,
				null);
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationDispenseDocument
		templateId = "2.16.756.5.30.1.1.10.1.5";
		srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, templateList,
				srcFilePath, dstFilePath, "org.ehealth_connector.testonly.cda.ch.emed", fileHeader,
				null);
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationListDocument
		templateId = "2.16.756.5.30.1.1.10.1.13";
		srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, templateList,
				srcFilePath, dstFilePath, "org.ehealth_connector.testonly.cda.ch.emed", fileHeader,
				null);
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationPrescriptionDocument
		templateId = "2.16.756.5.30.1.1.10.1.4";
		srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, templateList,
				srcFilePath, dstFilePath, "org.ehealth_connector.testonly.cda.ch.emed", fileHeader,
				null);
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationTreatmentPlanDocument
		templateId = "2.16.756.5.30.1.1.10.1.7";
		srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, templateList,
				srcFilePath, dstFilePath, "org.ehealth_connector.testonly.cda.ch.emed", fileHeader,
				null);
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// PharmaceuticalAdviceDocument
		templateId = "2.16.756.5.30.1.1.10.1.6";
		srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator() + "test"
				+ FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, templateList,
				srcFilePath, dstFilePath, "org.ehealth_connector.testonly.cda.ch.emed", fileHeader,
				null);
		artDecor2JavaGenerator.doOneTemplate(templateId);

	}

	// @Test
	public void doLrepTest()
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		String templateId = "2.16.756.5.30.1.1.10.1.10";
		// String packageName = "org.ehealth_connector.testonly.cda.ch.lrep";
		String packageName = "org.ehealth_connector.cda.ch.lrep";

		HashMap<String, String> templateIndex = new HashMap<String, String>();
		ArrayList<CdaTemplate> templateList = new ArrayList<CdaTemplate>();

		String srcFilePath = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "test" + FileUtil.getPlatformSpecificPathSeparator() + templateId
				+ FileUtil.getPlatformSpecificPathSeparator();
		// String dstFilePath = Util.getTempDirectory() +
		// FileUtil.getPlatformSpecificPathSeparator()
		// + "lrep" + FileUtil.getPlatformSpecificPathSeparator();
		String dstFilePath = "C:\\src\\git\\eHC-AD2J\\api-java\\ehealth_connector-cda\\ehealth_connector-cda-ch\\src\\main\\java\\org\\ehealth_connector\\cda\\ch\\lrep";
		FileUtils.deleteDirectory(new File(dstFilePath));

		String fileHeader = JavaCodeGenerator.getFileHeader();

		ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null,
				templateIndex, templateList, srcFilePath, dstFilePath, packageName, fileHeader,
				null);

		artDecor2JavaGenerator.doOneTemplate(templateId);

	}
}
