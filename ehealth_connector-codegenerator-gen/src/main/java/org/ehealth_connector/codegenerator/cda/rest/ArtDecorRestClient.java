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
package org.ehealth_connector.codegenerator.cda.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
import org.ehealth_connector.common.utils.Util;
import org.ehealth_connector.common.utils.XmlUtil;
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
	 * @param prefix
	 *            the prefix
	 * @return the art decor project index
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getArtDecorProjectIndex(String baseUrl, String prefix)
			throws ClientProtocolException, IOException {
		return getArtDecorXml(baseUrl + "ProjectIndex?prefix=" + prefix + "&format=xml");
	}

	/**
	 * <div class="en">Downloads the ART-DECOR template with the given id from
	 * the given project/base url.</div>
	 *
	 * <div class="de">Lädt das ART-DECOR Template mit der angegebenen ID von
	 * der angegebenen Projekt-/Basis-URL herunter.</div>
	 *
	 * @param baseUrl
	 *            the base url
	 * @param prefix
	 *            the prefix
	 * @param templateId
	 *            the template id
	 * @param effectiveDate
	 *            the effective date
	 * @return the art decor template
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getArtDecorTemplate(String baseUrl, String prefix, String templateId,
			String effectiveDate) throws ClientProtocolException, IOException {
		return getArtDecorTemplate(baseUrl, prefix, templateId, templateId, effectiveDate);
	}

	/**
	 * <div class="en">Downloads the ART-DECOR template with the given id from
	 * the given project/base url.</div>
	 *
	 * <div class="de">Lädt das ART-DECOR Template mit der angegebenen ID von
	 * der angegebenen Projekt-/Basis-URL herunter.</div>
	 *
	 * @param baseUrl
	 *            the base url
	 * @param prefix
	 *            the prefix
	 * @param documentTemplateId
	 *            the document template id
	 * @param templateId
	 *            the template id
	 * @param effectiveDate
	 *            the effective date
	 * @return the art decor template
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getArtDecorTemplate(String baseUrl, String prefix,
			String documentTemplateId, String templateId, String effectiveDate)
			throws ClientProtocolException, IOException {
		return getArtDecorXml(baseUrl + "RetrieveTemplate?prefix=" + prefix + "&id=" + templateId
				+ "&effectiveDate=" + effectiveDate + "&format=xmlnowrapper");
	}

	/**
	 * <div class="en">Downloads the XML from the given ART-DECOR url.</div>
	 *
	 * <div class="de">Lädt das XML von der angegebenen ART-DECOR-URL
	 * herunter.</div>
	 *
	 * @param url
	 *            the url
	 * @return the art decor xml
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static InputStream getArtDecorXml(String url)
			throws ClientProtocolException, IOException {
		InputStream retVal = null;

		// create HTTP Client
		HttpClient httpClient = HttpClientBuilder.create().build();

		// Create new getRequest with below mentioned URL
		HttpGet getRequest = new HttpGet(url);

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

	/** The base url. */
	private String baseUrl;

	/** The project indexes. */
	private ArrayList<Document> projectIndexes = new ArrayList<Document>();

	/** The templates. */
	private ArrayList<String> templates;

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
	 * @param baseUrl
	 *            the base URL
	 * @param baseDir
	 *            the base dir
	 */
	public ArtDecorRestClient(String baseUrl, String baseDir) {

		if (baseUrl == null)
			throw new RuntimeException(
					"baseUrl is null. This is not allowed for an ART-DECOR REST client instance.");

		if (baseDir == null)
			throw new RuntimeException(
					"baseDir is null. This is not allowed for an ART-DECOR REST client instance.");

		this.baseDir = baseDir;
		this.baseUrl = baseUrl;
		this.templates = new ArrayList<String>();
	}

	/**
	 * Adds the art decor project. <div class="en">Adds the project with the
	 * given project prefix to the internal project list. The project list is
	 * needed to resolve the url's of referenced templates.</div>
	 *
	 * <div class="de">Fügt das Projekt mit dem angegebenen Projekt-Prefix zur
	 * internen Projektliste hinzu. Die Projektliste wird benötigt, um die URLs
	 * von referenzierten Vorlagen aufzulösen.</div>
	 *
	 * @param prefix
	 *            the prefix
	 * @throws Exception
	 *             the exception
	 */
	public void addArtDecorProject(String prefix) throws Exception {
		addArtDecorProject(baseUrl, prefix);
	}

	/**
	 * Adds the art decor project. <div class="en">Adds the project with the
	 * given baseUrl and project prefix to the internal project list. The
	 * project list is needed to resolve the url's of referenced
	 * templates.</div>
	 *
	 * <div class="de">Fügt das Projekt mit der angegebenen Basis-URL und dem
	 * angegebenen Projekt-Prefix zur internen Projektliste hinzu. Die
	 * Projektliste wird benötigt, um die URLs von referenzierten Vorlagen
	 * aufzulösen.</div>
	 *
	 * @param baseUrl
	 *            the base url
	 * @param prefix
	 *            the prefix
	 * @throws Exception
	 *             the exception
	 */
	public void addArtDecorProject(String baseUrl, String prefix) throws Exception {
		InputStream is = getArtDecorProjectIndex(baseUrl, prefix);
		Document doc = XmlUtil.getXmlDocument(is);
		if (doc != null) {
			projectIndexes.add(doc);
		}
		String fn = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
				+ "ProjectIndex-" + prefix + ".xml";
		XmlUtil.writeXmlDocumentToFile(doc, new File(fn));
	}

	/**
	 * <div class="en">Downloads the given template and all recursively
	 * referenced "includes" or "contains" templates.</div>
	 *
	 * <div class="de">Lädt die angegebene Vorlage und alle rekursiv
	 * referenzierten "include" oder "contains" Vorlagen herunter.</div>
	 *
	 * @param artDecorPrefix
	 *            the art decor prefix
	 * @param documentTemplateId
	 *            the document template id
	 * @param effectiveDate
	 *            the effective date
	 * @return true, if successful
	 */
	public boolean downloadTemplateRecursive(String artDecorPrefix, String documentTemplateId,
			String effectiveDate) {
		return downloadTemplateRecursive(artDecorPrefix, documentTemplateId, effectiveDate,
				documentTemplateId, null);
	}

	/**
	 * <div class="en">Downloads the given template and all recursively
	 * referenced "includes" or "contains" templates.</div>
	 *
	 * <div class="de">Lädt die angegebene Vorlage und alle rekursiv
	 * referenzierten "include" oder "contains" Vorlagen herunter.</div>
	 *
	 * @param prefix
	 *            the prefix
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
	public boolean downloadTemplateRecursive(String prefix, String documentTemplateId,
			String effectiveDate, String templateId, String type) {
		boolean retVal = false;
		try {
			String ed = "";
			if (!effectiveDate.equals("dynamic"))
				ed = "&effectiveDate=" + effectiveDate;
			String dir = baseDir;
			if (!dir.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
				dir += FileUtil.getPlatformSpecificPathSeparator();
			dir += documentTemplateId;
			if (templateId.equals(documentTemplateId)) {
				File targetDir = new File(dir);
				if (targetDir.exists())
					FileUtils.deleteDirectory(targetDir);
				FileUtils.forceMkdir(targetDir);

			} else {
				dir += FileUtil.getPlatformSpecificPathSeparator() + "kit";
				File targetDir = new File(dir);
				if (!targetDir.exists())
					FileUtils.forceMkdir(targetDir);
			}

			if (!templates.contains(templateId)) {
				templates.add(templateId);
				InputStream is = getArtDecorTemplate(baseUrl, prefix, documentTemplateId,
						templateId, effectiveDate);
				Document doc = XmlUtil.getXmlDocument(is);

				File targetFile = new File(
						dir + FileUtil.getPlatformSpecificPathSeparator() + templateId + ".xml");

				XmlUtil.writeXmlDocumentToFile(doc, targetFile);

				if (doc != null) {
					NodeList nl;
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();
					XPathExpression expr = null;

					expr = xpath.compile("//include");
					nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
					processNodeList(nl, prefix, documentTemplateId, templateId, "includes");

					expr = xpath.compile("//@contains");
					nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
					processNodeList(nl, prefix, documentTemplateId, templateId, "contains");
				}
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
	private String getPrefix(String templateId) throws XPathExpressionException {
		String retVal = "";
		NodeList nl;
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//template[@id='" + templateId + "']");
		for (Document projectIndex : projectIndexes) {
			nl = (NodeList) expr.evaluate(projectIndex, XPathConstants.NODESET);
			if (nl.getLength() > 0) {
				XPathExpression expr1 = xpath.compile("/return/@prefix");
				nl = (NodeList) expr1.evaluate(projectIndex, XPathConstants.NODESET);
				retVal = nl.item(0).getNodeValue();
			}
			if (!"".equals(retVal))
				break;
		}
		return retVal;
	}

	/**
	 * Processes the given node list.
	 *
	 * @param nl
	 *            the nl
	 * @param artDecorPrefix
	 *            the art decor prefix
	 * @param documentTemplateId
	 *            the document template id
	 * @param templateId
	 *            the template id
	 * @param type
	 *            the type
	 * @throws XPathExpressionException
	 *             the x path expression exception
	 */
	private void processNodeList(NodeList nl, String artDecorPrefix, String documentTemplateId,
			String templateId, String type) throws XPathExpressionException {
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
				System.out.println("include: " + id + " (flexibility=" + flexibility + ")");
			}
			if ("contains".equals(type)) {
				id = nl.item(i).getNodeValue();
				System.out.println("contains: " + id);
			}

			String prefix = getPrefix(id);

			if ("".equals(prefix))
				System.out.println("*** ERROR: Prefix not found for templateId " + id);
			else
				downloadTemplateRecursive(prefix, documentTemplateId, flexibility, id, type);
		}
	}

}
