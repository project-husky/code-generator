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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.projecthusky.codegenerator.cda.model.CdaTemplate;
import org.projecthusky.codegenerator.java.JavaCodeGenerator;
import org.projecthusky.common.utils.Util;

import net.sf.saxon.s9api.SaxonApiException;

/**
 * The test class for ArtDecor2JavaGenerator.
 */
public class ArtDecor2JavaGeneratorTest {

	/**
	 * Java code generation test the content profile CDA-CH-EMED.
	 *
	 * @throws SaxonApiException
	 *             the saxon api exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SecurityException
	 *             the security exception
	 */
	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
	public void doEmedTest()
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		// Initialization
		ArtDecor2JavaGenerator artDecor2JavaGenerator = null;
		HashMap<String, CdaTemplate> templateIndex = new HashMap<>();
		HashMap<String, String> valueSetIndex = new HashMap<>();
		ArrayList<CdaTemplate> templateList = new ArrayList<>();
		String srcFilePath;
		String templateId;

		// Common properties
		String prefix = "cdachemed-";
		String packageName = "org.projecthusky.cda.ch.emed.v097";
		String dstFilePath = Util.getCurrentDirectory()
				+ "../../api-java/husky-cda/husky-cda-ch";
		String fileHeader = JavaCodeGenerator.getFileHeader();
		srcFilePath = Util.getTempDirectory() + "/test/";

		// MedicationCardDocument
		templateId = "2.16.756.5.30.1.1.10.1.3";
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, valueSetIndex,
				templateList, srcFilePath, dstFilePath, packageName, fileHeader, prefix,
				new URL(ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL));
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationDispenseDocument
		templateId = "2.16.756.5.30.1.1.10.1.5";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationListDocument
		templateId = "2.16.756.5.30.1.1.10.1.13";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationPrescriptionDocument
		templateId = "2.16.756.5.30.1.1.10.1.4";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// MedicationTreatmentPlanDocument
		templateId = "2.16.756.5.30.1.1.10.1.7";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// PharmaceuticalAdviceDocument
		templateId = "2.16.756.5.30.1.1.10.1.6";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		artDecor2JavaGenerator.createJavaClasses();

	}

	/**
	 * Java code generation test the content profile CDA-CH-LREP.
	 *
	 * @throws SaxonApiException
	 *             the saxon api exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SecurityException
	 *             the security exception
	 */
	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
	public void doLrepTest()
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		// Initialization
		ArtDecor2JavaGenerator artDecor2JavaGenerator = null;
		HashMap<String, CdaTemplate> templateIndex = new HashMap<String, CdaTemplate>();
		HashMap<String, String> valueSetIndex = new HashMap<String, String>();
		ArrayList<CdaTemplate> templateList = new ArrayList<CdaTemplate>();
		String srcFilePath;
		String templateId;

		// Common properties
		String prefix = "cdachlrep-";
		String packageName = "org.projecthusky.cda.ch.lrep.v140";
		String dstFilePath = Util.getCurrentDirectory()
				+ "../../api-java/husky-cda/husky-cda-ch";
		String fileHeader = JavaCodeGenerator.getFileHeader();

		// Swiss Laboratory Report - General Report
		templateId = "2.16.756.5.30.1.1.10.1.10";
		srcFilePath = Util.getTempDirectory() + "/test/";
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, valueSetIndex,
				templateList, srcFilePath, dstFilePath, packageName, fileHeader, prefix,
				new URL(ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL));
		artDecor2JavaGenerator.doOneTemplate(templateId);
		artDecor2JavaGenerator.createJavaClasses();

	}

	// This is for debugging purposes, only. Do not commit this test with the
	// test annotation enabled!
	// @Test
	public void doVacdTest()
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		// Initialization
		ArtDecor2JavaGenerator artDecor2JavaGenerator = null;
		HashMap<String, CdaTemplate> templateIndex = new HashMap<String, CdaTemplate>();
		HashMap<String, String> valueSetIndex = new HashMap<String, String>();
		ArrayList<CdaTemplate> templateList = new ArrayList<CdaTemplate>();
		String srcFilePath;
		String templateId;

		// Common properties
		String prefix = "cdachvacd-";
		String packageName = "org.projecthusky.cda.ch.vacd.v210";
		String dstFilePath = Util.getCurrentDirectory()
				+ "../../api-java/husky-cda/husky-cda-ch";
		String fileHeader = JavaCodeGenerator.getFileHeader();
		srcFilePath = Util.getTempDirectory() + "/test/";

		// Immunization Certificate
		templateId = "2.16.756.5.30.1.1.10.1.15";
		artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null, templateIndex, valueSetIndex,
				templateList, srcFilePath, dstFilePath, packageName, fileHeader, prefix,
				new URL(ArtDecor2JavaManager.ART_DECOR_DEFAULT_SERVER_BASE_URL));
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// Immunization Administration
		templateId = "2.16.756.5.30.1.1.10.1.16";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// Immunization Recommendation Request
		templateId = "2.16.756.5.30.1.1.10.1.17";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// Immunization Recommendation Response
		templateId = "2.16.756.5.30.1.1.10.1.18";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		// Vaccination Record
		templateId = "	2.16.756.5.30.1.1.10.1.19";
		artDecor2JavaGenerator.prepareForAnotherTemplate();
		artDecor2JavaGenerator.doOneTemplate(templateId);

		artDecor2JavaGenerator.createJavaClasses();

	}
}
