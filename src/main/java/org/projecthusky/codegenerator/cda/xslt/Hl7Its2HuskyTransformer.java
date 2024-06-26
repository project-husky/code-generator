/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.cda.xslt;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

/**
 *
 * <div class="en">This class acts as XSL transformer of all templates
 * downloaded by the ArtDecorRestClient. It performs XML stylesheet
 * transformations (XSLT) based on Hl7Its2Husky.xsl.</div>
 *
 * <div class="de">Diese Klasse fungiert als XSL-Transformer aller vom
 * ArtDecorRestClient heruntergeladenen Templates. Er führt
 * XML-Stylesheet-Transformationen (XSLT) basierend auf Hl7Its2Husky.xsl
 * durch.</div>
 *
 */
public class Hl7Its2HuskyTransformer {

    /** The log. */
    protected static final Logger log = LoggerFactory.getLogger(Hl7Its2HuskyTransformer.class);

    /**
     * <div class="en">Performs one XSL transformation. The inputFile will be
     * transformed by using the internally stored XSL stylesheet and then stored
     * in the outputFile.</div>
     *
     * <div class="de">Führt eine XSL-Transformation durch. Die Eingabedatei
     * wird mithilfe des intern gespeicherten XSL-Stylesheets transformiert und
     * dann in der Ausgabedatei gespeichert.</div>
     *
     * @param inputFile
     *            the input file
     * @param outputFile
     *            the output file
     * @throws SaxonApiException
     *             the saxon api exception
     */
    public static void transform(File inputFile, File outputFile) throws SaxonApiException {
        log.debug("Transforming {}...", inputFile.getName());
        final Processor processor = new Processor(false);
        final XsltCompiler compiler = processor.newXsltCompiler();

        final XsltExecutable xsl = compiler.compile(new StreamSource(
                new File(System.getProperty("user.dir") + "/src/main/resources/stylesheets/Hl7Its2HuskyCdaGen.xsl")
        ));
        final XsltTransformer transformer = xsl.load();

        final Serializer out = processor.newSerializer();
        out.setOutputFile(outputFile);
        out.setOutputProperty(Serializer.Property.METHOD, "xml");
        out.setOutputProperty(Serializer.Property.ENCODING, "UTF-8");
        out.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
        out.setOutputProperty(Serializer.Property.INDENT, "no");

        transformer.setSource(new StreamSource(inputFile));
        transformer.setDestination(out);

        transformer.transform();
        log.debug("Transforming {} done.", inputFile.getName());
    }

    /**
     * <div class="en">Performs one XSL transformation. The inputFile will be
     * transformed by using the internally stored XSL stylesheet and then stored
     * in the outputFile.</div>
     *
     * <div class="de">Führt eine XSL-Transformation durch. Die Eingabedatei
     * wird mithilfe des intern gespeicherten XSL-Stylesheets transformiert und
     * dann in der Ausgabedatei gespeichert.</div>
     *
     * @param inputFn
     *            the input fn
     * @param outputFn
     *            the output fn
     * @throws SaxonApiException
     *             the saxon api exception
     */
    public static void transform(String inputFn, String outputFn) throws SaxonApiException {
        transform(new File(inputFn), new File(outputFn));
    }

}
