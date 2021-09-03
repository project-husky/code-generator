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
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */
package org.ehealth_connector.codegenerator.cda.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <div class="en">This class acts as "downloader" of all used templates by a
 * given Document Template. It recursively goes through all includes and
 * contains of an ART-DECOR template and download each referenced template.
 * </div>
 *
 * <div class="de">Diese Klasse dient als "Downloader" aller von einer
 * bestimmten Dokumentvorlage verwendeten Templates. Er geht rekursiv alle
 * Includes und Contains jedes ART-DECOR Templates durch und lädt jedes
 * referenzierte Template herunter.</div>
 *
 */
public class ArtDecorRestClient {

    /** The log. */
    protected final static Logger log = LoggerFactory.getLogger(ArtDecorRestClient.class);

    /**
     *
     * <div class="en">Downloads the ART-DECOR project index for the given
     * project prefix.</div>
     *
     * <div class="de">Lädt den ART-DECOR-Projektindex für den angegebenen
     * Projekt-Prefix herunter.</div>
     *
     *
     *
     * @param baseUrl
     *            the base url
     * @param artDecorPrefix
     *            the prefix
     * @return the ART-DECOR project index
     * @throws MalformedURLException
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static InputStream getArtDecorProjectIndex(URL baseUrl, String artDecorPrefix)
            throws MalformedURLException, IOException {
        return getArtDecorXml(new URL(
                baseUrl.toString() + "ProjectIndex?prefix=" + artDecorPrefix + "&format=xml"));
    }

    /**
     * <div class="en">Downloads the XML from the given ART-DECOR url.</div>
     *
     * <div class="de">Lädt das XML von der angegebenen ART-DECOR-URL
     * herunter.</div>
     *
     * @param url
     *            the url
     * @return the ART-DECOR xml
     * @throws ClientProtocolException
     *             the client protocol exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static InputStream getArtDecorXml(URL url) throws ClientProtocolException, IOException {
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

    /** The base dir. */
    private String baseDir;

    /** The project indexes. */
    private ArrayList<Document> projectIndexes = new ArrayList<Document>();

    /** The templates. */
    private ArrayList<String> templates;

    /** The art decor project map. */
    private Map<String, String> artDecorProjectMap;

    /**
     * <div class="en">Constructor for an ART-DECOR REST client instance. The
     * base url on the web and the base directory where to store the files have
     * to be provided (null is not allowed, here).</div>
     *
     * <div class="de">Konstruktor für eine ART-DECOR REST-Client-Instanz. Die
     * Basis-URL im Web und das Basisverzeichnis, in dem die Dateien gespeichert
     * werden sollen, müssen angegeben werden (null ist hier nicht
     * zulässig).</div>
     *
     * @param baseDir
     *            the base dir
     */
    public ArtDecorRestClient(Map<String, String> artDecorProjectMap, String baseDir) {

        if (baseDir == null)
            throw new RuntimeException(
                    "baseDir is null. This is not allowed for an ART-DECOR REST client instance.");

        // make sure, the trailing path separator exists
        if (!baseDir.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
            baseDir += FileUtil.getPlatformSpecificPathSeparator();

        // prepare directory, where to put the downloaded files
        String dir = baseDir;
        File targetDir = new File(dir);
        try {
            if (targetDir.exists())
                FileUtils.deleteDirectory(targetDir);
            FileUtils.forceMkdir(targetDir);
            dir += FileUtil.getPlatformSpecificPathSeparator() + "kit";
            targetDir = new File(dir);
            if (!targetDir.exists())
                FileUtils.forceMkdir(targetDir);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }

        this.artDecorProjectMap = artDecorProjectMap;
        this.baseDir = baseDir;
        this.templates = new ArrayList<String>();

        // download the project indexes
        for (String prefix : artDecorProjectMap.keySet()) {
            try {
                addArtDecorProject(prefix, (String) artDecorProjectMap.get(prefix));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Adds the ART-DECOR project. <div class="en">Adds the project with the
     * given project prefix to the internal project list. The project list is
     * needed to resolve the url's of referenced templates.</div>
     *
     * <div class="de">Fügt das Projekt mit dem angegebenen Projekt-Prefix zur
     * internen Projektliste hinzu. Die Projektliste wird benötigt, um die URLs
     * von referenzierten Vorlagen aufzulösen.</div>
     *
     * @param artDecorPrefix
     *            the prefix
     * @param artDecorBaseUrl
     *            the art decor base url
     * @throws Exception
     *             the exception
     */
    public void addArtDecorProject(String artDecorPrefix, String artDecorBaseUrl) throws Exception {
        addArtDecorProject(artDecorPrefix, new URL(artDecorBaseUrl));
    }

    /**
     * Adds the ART-DECOR project. <div class="en">Adds the project with the
     * given baseUrl and project prefix to the internal project list. The
     * project list is needed to resolve the url's of referenced
     * templates.</div>
     *
     * <div class="de">Fügt das Projekt mit der angegebenen Basis-URL und dem
     * angegebenen Projekt-Prefix zur internen Projektliste hinzu. Die
     * Projektliste wird benötigt, um die URLs von referenzierten Vorlagen
     * aufzulösen.</div>
     *
     * @param artDecorPrefix
     *            the art decor prefix
     * @param baseUrl
     *            the base url
     * @throws Exception
     *             the exception
     */
    public void addArtDecorProject(String artDecorPrefix, URL baseUrl) throws Exception {
        log.debug("Downloading Project Index {}", artDecorPrefix);
        InputStream is = getArtDecorProjectIndex(baseUrl, artDecorPrefix);
        Document doc = XmlUtil.getXmlDocument(is);
        if (doc != null) {
            projectIndexes.add(doc);
        }
        String fn = baseDir + "ProjectIndex-" + artDecorPrefix + ".xml";
        XmlUtil.writeXmlDocumentToFile(doc, new File(fn));
    }

    /**
     * <div class="en">Downloads the given template and all recursively
     * referenced "includes" or "contains" templates.</div>
     *
     * <div class="de">Lädt die angegebene Vorlage und alle rekursiv
     * referenzierten "include" oder "contains" Vorlagen herunter.</div>
     *
     * @param documentTemplateId
     *            the document template id
     * @param effectiveDate
     *            the effective date
     * @return true, if successful
     */
    public boolean downloadTemplateRecursive(String documentTemplateId, String effectiveDate) {
        return downloadTemplateRecursive(documentTemplateId, effectiveDate, documentTemplateId,
                null);
    }

    /**
     * <div class="en">Downloads the given template and all recursively
     * referenced "includes" or "contains" templates.</div>
     *
     * <div class="de">Lädt die angegebene Vorlage und alle rekursiv
     * referenzierten "include" oder "contains" Vorlagen herunter.</div>
     *
     * @param documentTemplateId
     *            the document template id
     * @param effectiveDate
     *            the effective date
     * @param templateId
     *            the template id
     * @param type
     *            the type
     * @return true, if successful
     */
    public boolean downloadTemplateRecursive(String documentTemplateId, String effectiveDate,
            String templateId, String type) {
        boolean retVal = false;
        try {
            // prepare directory, where to put the downloaded files
            String dir = baseDir;
            if (!dir.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
                dir += FileUtil.getPlatformSpecificPathSeparator();
            if (!templateId.equals(documentTemplateId)) {
                dir += "kit" + FileUtil.getPlatformSpecificPathSeparator();
            }

            // download only, when it is not already there
            if (!templates.contains(templateId)) {
                templates.add(templateId);

                log.debug("Downloading template: {}", templateId);

                // download the template from ART-DECOR REST Service as stream
                InputStream is = getArtDecorTemplate(documentTemplateId, templateId, effectiveDate);

                // transform the stream to an XML document
                Document doc = XmlUtil.getXmlDocument(is);

                // Write the downloaded yml to file
                File targetFile = new File(dir + templateId + ".xml");
                XmlUtil.writeXmlDocumentToFile(doc, targetFile);

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
     * @param templateId
     *            the template id
     * @return the prefix
     * @throws XPathExpressionException
     *             the x path expression exception
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
     * <div class="en">Downloads the ART-DECOR template with the given id from
     * the given project/base url.</div>
     *
     * <div class="de">Lädt das ART-DECOR Template mit der angegebenen ID von
     * der angegebenen Projekt-/Basis-URL herunter.</div>
     *
     * @param templateId
     *            the template id
     * @param effectiveDate
     *            the effective date
     * @return the ART-DECOR template
     * @throws ClientProtocolException
     *             the client protocol exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public InputStream getArtDecorTemplate(String templateId, String effectiveDate)
            throws ClientProtocolException, IOException {
        return getArtDecorTemplate(templateId, templateId, effectiveDate);
    }

    /**
     * <div class="en">Downloads the ART-DECOR template with the given id from
     * the given project/base url.</div>
     *
     * <div class="de">Lädt das ART-DECOR Template mit der angegebenen ID von
     * der angegebenen Projekt-/Basis-URL herunter.</div>
     *
     * @param documentTemplateId
     *            the document template id
     * @param templateId
     *            the template id
     * @param effectiveDate
     *            the effective date
     * @return the ART-DECOR template
     * @throws ClientProtocolException
     *             the client protocol exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
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
                new URL(baseUrl.toString() + "RetrieveTemplate?prefix=" + prefix + "&id="
                        + templateId + "&effectiveDate=" + effectiveDate + "&format=xmlnowrapper"));
    }

    /**
     * Processes the given node list.
     *
     * @param nl
     *            the nl
     * @param documentTemplateId
     *            the document template id
     * @param templateId
     *            the template id
     * @param type
     *            the type
     * @throws XPathExpressionException
     *             the x path expression exception
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

}
