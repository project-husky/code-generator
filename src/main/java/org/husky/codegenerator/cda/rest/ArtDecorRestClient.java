/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.cda.rest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.husky.common.utils.xml.XmlFactories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class acts as "downloader" of all used templates by a
 * given Document Template. It recursively goes through all includes and
 * contains of an ART-DECOR template and download each referenced template.
 */
public class ArtDecorRestClient {

    /**
     * The log.
     */
    protected static final Logger log = LoggerFactory.getLogger(ArtDecorRestClient.class);

    /**
     * Downloads the ART-DECOR project index for the given
     * project prefix.
     *
     * @param baseUrl        the base url
     * @param artDecorPrefix the prefix
     * @return the ART-DECOR project index
     * @throws MalformedURLException
     * @throws IOException           Signals that an I/O exception has occurred.
     */
    public static InputStream getArtDecorProjectIndex(final URL baseUrl, final String artDecorPrefix)
            throws MalformedURLException, IOException {
        return getArtDecorXml(new URL(
                baseUrl.toString() + "ProjectIndex?prefix=" + artDecorPrefix + "&format=xml"));
    }

    /**
     * Downloads the XML from the given ART-DECOR url.
     *
     * @param url the url
     * @return the ART-DECOR xml
     * @throws ClientProtocolException the client protocol exception
     * @throws IOException             Signals that an I/O exception has occurred.
     */
    public static InputStream getArtDecorXml(final URL url) throws ClientProtocolException, IOException {
        InputStream retVal = null;

        // create HTTP Client
        HttpClient httpClient = HttpClientBuilder.create().build();

        // Create new getRequest with below mentioned URL
        HttpGet getRequest = new HttpGet(url.toString());

        // Add additional header to getRequest which accepts application/xml
        // data
        getRequest.addHeader("accept", "application/xml");

        // Execute your request and catch response
        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException(
                    "Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }

        // Get-Capture Complete application/xml body response
        retVal = response.getEntity().getContent();
        return retVal;
    }

    /**
     * The base dir.
     */
    private final String baseDir;

    /**
     * The project indexes.
     */
    private final ArrayList<Document> projectIndexes = new ArrayList<>();

    /**
     * The templates.
     */
    private final ArrayList<String> templates;

    /**
     * The art decor project map.
     */
    private final Map<String, String> artDecorProjectMap;

    /**
     * Constructor for an ART-DECOR REST client instance. The
     * base url on the web and the base directory where to store the files have
     * to be provided (null is not allowed, here).
     *
     * @param baseDir the base dir
     */
    public ArtDecorRestClient(final Map<String, String> artDecorProjectMap,
                              String baseDir) {

        if (baseDir == null)
            throw new RuntimeException(
                    "baseDir is null. This is not allowed for an ART-DECOR REST client instance.");

        // make sure, the trailing path separator exists
        if (!baseDir.endsWith("/"))
            baseDir += "/";

        // prepare directory, where to put the downloaded files
        String dir = baseDir;
        File targetDir = new File(dir);
        try {
            if (targetDir.exists())
                FileUtils.deleteDirectory(targetDir);
            FileUtils.forceMkdir(targetDir);
            dir += "/kit";
            targetDir = new File(dir);
            if (!targetDir.exists())
                FileUtils.forceMkdir(targetDir);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }

        this.artDecorProjectMap = artDecorProjectMap;
        this.baseDir = baseDir;
        this.templates = new ArrayList<>();

        // download the project indexes
        for (final String prefix : artDecorProjectMap.keySet()) {
            try {
                addArtDecorProject(prefix, new URL(artDecorProjectMap.get(prefix)));
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Adds the ART-DECOR project.
     * <p>
     * Adds the project with the given baseUrl and project prefix to the internal project list. The project list is
     * needed to resolve the url's of referenced templates.
     *
     * @param artDecorPrefix the art decor prefix
     * @param baseUrl        the base url
     * @throws Exception the exception
     */
    public void addArtDecorProject(final String artDecorPrefix,
                                   final URL baseUrl) throws Exception {
        log.debug("Downloading Project Index {}", artDecorPrefix);
        InputStream is = getArtDecorProjectIndex(baseUrl, artDecorPrefix);
        Document doc = this.getXmlDocument(is);
        if (doc != null) {
            projectIndexes.add(doc);
        }
        String fn = baseDir + "ProjectIndex-" + artDecorPrefix + ".xml";
        this.writeXmlDocumentToFile(doc, new File(fn));
    }

    /**
     * Downloads the given template and all recursively
     * referenced "includes" or "contains" templates.
     *
     * @param documentTemplateId the document template id
     * @param effectiveDate      the effective date
     * @return true, if successful
     */
    public boolean downloadTemplateRecursive(String documentTemplateId, String effectiveDate) {
        return downloadTemplateRecursive(documentTemplateId, effectiveDate, documentTemplateId,
                null);
    }

    /**
     * Downloads the given template and all recursively
     * referenced "includes" or "contains" templates.
     *
     * @param documentTemplateId the document template id
     * @param effectiveDate      the effective date
     * @param templateId         the template id
     * @param type               the type
     * @return true, if successful
     */
    public boolean downloadTemplateRecursive(String documentTemplateId, String effectiveDate,
                                             String templateId, String type) {
        boolean retVal = false;
        try {
            // prepare directory, where to put the downloaded files
            String dir = baseDir;
            if (!dir.endsWith("/"))
                dir += "/";
            if (!templateId.equals(documentTemplateId)) {
                dir += "kit" + "/";
            }

            // download only, when it is not already there
            if (!templates.contains(templateId)) {
                templates.add(templateId);

                log.debug("Downloading template: {}", templateId);

                // download the template from ART-DECOR REST Service as stream
                InputStream is = getArtDecorTemplate(documentTemplateId, templateId, effectiveDate);

                // transform the stream to an XML document
                Document doc = this.getXmlDocument(is);

                // Write the downloaded yml to file
                File targetFile = new File(dir + templateId + ".xml");
                this.writeXmlDocumentToFile(doc, targetFile);

                // Process all contains and includes in the current template
                NodeList nl;
                XPathFactory xPathfactory = XPathFactory.newInstance();
                XPath xpath = xPathfactory.newXPath();
                XPathExpression expr = null;

                expr = xpath.compile("//include");
                nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                processNodeList(nl, documentTemplateId, templateId, "includes");

                expr = xpath.compile("//@contains");
                nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                processNodeList(nl, documentTemplateId, templateId, "contains");

                log.info("Downloading template complete: {}", templateId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;

    }

    /**
     * Gets the project prefix of the given template.
     *
     * @param templateId the template id
     * @return the prefix
     * @throws XPathExpressionException the x path expression exception
     */
    private String getArtDecorPrefix(String templateId) {
        String retVal = null;
        NodeList nl;
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr;

        try {
            expr = xpath.compile("//template[@id='" + templateId + "']");
            for (Document projectIndex : projectIndexes) {
                nl = (NodeList) expr.evaluate(projectIndex, XPathConstants.NODESET);
                if (nl.getLength() > 0) {
                    XPathExpression expr1 = xpath.compile("/return/@prefix");
                    nl = (NodeList) expr1.evaluate(projectIndex, XPathConstants.NODESET);
                    retVal = nl.item(0).getNodeValue();
                }
                if (retVal != null)
                    break;
            }
        } catch (XPathExpressionException e) {
            return null;
        }
        return retVal;
    }

    /**
     * Downloads the ART-DECOR template with the given id from
     * the given project/base url.
     *
     * @param documentTemplateId the document template id
     * @param templateId         the template id
     * @param effectiveDate      the effective date
     * @return the ART-DECOR template
     * @throws ClientProtocolException the client protocol exception
     * @throws IOException             Signals that an I/O exception has occurred.
     */
    public InputStream getArtDecorTemplate(String documentTemplateId, String templateId,
                                           String effectiveDate) throws ClientProtocolException, IOException {
        String prefix = getArtDecorPrefix(templateId);
        String baseUrl = artDecorProjectMap.get(prefix);
        if (prefix == null)
            throw new RuntimeException("No prefix found for template " + templateId
                    + ". Please complete the project map.");
        if (baseUrl == null)
            throw new RuntimeException("No ART-DECOR base url found for template " + templateId
                    + " (prefix: " + prefix + "). Please complete the project map.");
        return getArtDecorXml(
                new URL(baseUrl + "RetrieveTemplate?prefix=" + prefix + "&id="
                        + templateId + "&effectiveDate=" + effectiveDate + "&format=xmlnowrapper"));
    }

    /**
     * Processes the given node list.
     *
     * @param nl                 the nl
     * @param documentTemplateId the document template id
     * @param templateId         the template id
     * @param type               the type
     * @throws XPathExpressionException the x path expression exception
     */
    private void processNodeList(NodeList nl, String documentTemplateId, String templateId,
                                 String type) throws XPathExpressionException {
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = null;
            String id = "";
            String flexibility = "dynamic";

            if ("includes".equals(type)) {
                node = nl.item(i).getAttributes().getNamedItem("ref");
                if (node != null)
                    id = node.getNodeValue();
                node = nl.item(i).getAttributes().getNamedItem("flexibility");
                if (node != null)
                    flexibility = node.getNodeValue();
                log.debug("include: {} (flexibility={})", id, flexibility);
            }
            if ("contains".equals(type)) {
                id = nl.item(i).getNodeValue();
                log.debug("contains: {}", id);
            }

            String prefix = getArtDecorPrefix(id);

            if ("".equals(prefix)) {
                log.error("Prefix not found for templateId {}", id);
            } else
                downloadTemplateRecursive(documentTemplateId, flexibility, id, type);
        }
    }

    private Document getXmlDocument(final InputStream is) throws IOException, SAXException, ParserConfigurationException {
        final var documentBuilder = XmlFactories.newSafeDocumentBuilder();
        return documentBuilder.parse(is);
    }

    private void writeXmlDocumentToFile(final Document doc, final File outputFile) throws TransformerException, IOException {
        doc.setXmlStandalone(true);
        var docSource = new DOMSource(doc);
        try (final var outputStream = new FileOutputStream(outputFile)) {
            final var transformer = XmlFactories.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, StandardCharsets.UTF_8.name());
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(docSource, new StreamResult(outputStream));
        }
    }
}
