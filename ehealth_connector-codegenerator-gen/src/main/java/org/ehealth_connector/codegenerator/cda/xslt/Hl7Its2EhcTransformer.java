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
package org.ehealth_connector.codegenerator.cda.xslt;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

//import org.ehealth_connector.validation.service.schematron.RuleSetTransformer;
//import org.ehealth_connector.validation.service.transform.StylesheetFactory;
//import org.ehealth_connector.validation.service.transform.StylesheetURIResolver;
//import org.ehealth_connector.validation.service.transform.Transformation;
//import org.ehealth_connector.validation.service.transform.TransformationException;
//import org.ehealth_connector.validation.service.util.JarUtils;
//
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

/**
 *
 * TODO This class acts as XSL transformer of all templates downloaded by the
 * ArtDecorRestClient. It performs XML stylesheet transformations (XSLT) based
 * on Hl7Its2Ehc.xsl.
 *
 */
public class Hl7Its2EhcTransformer {

	public static void transform(File inputFile, File outputFile) throws SaxonApiException {
		Processor processor = new Processor(false);
		XsltCompiler compiler = processor.newXsltCompiler();
		XsltExecutable xsl = compiler
				.compile(new StreamSource(new File(System.getProperty("user.dir")
						+ "/src/main/resources/stylesheets/Hl7Its2EhcCdaGen.xsl")));
		XsltTransformer transformer = xsl.load();

		Serializer out = processor.newSerializer();
		out.setOutputFile(outputFile);
		out.setOutputProperty(Serializer.Property.METHOD, "xml");
		out.setOutputProperty(Serializer.Property.ENCODING, "UTF-8");
		out.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
		out.setOutputProperty(Serializer.Property.INDENT, "no");

		transformer.setSource(new StreamSource(inputFile));
		transformer.setDestination(out);

		transformer.transform();

	}

	public static void transform(String inputFn, String outputFn) throws SaxonApiException {
		transform(new File(inputFn), new File(outputFn));
	}

}
