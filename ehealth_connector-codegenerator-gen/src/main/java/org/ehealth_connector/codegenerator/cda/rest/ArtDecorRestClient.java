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

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
 *
 * TODO: This class acts as “downloader” of all used templates by a given
 * Document Template. It recursively goes through all includes and contains of
 * an ART-DECOR template.
 *
 */
public class ArtDecorRestClient {

	/**
	 * This is for debugging puropses without internet connection, only. Do not
	 * forget to switch this parameter back to false, before commit!
	 */
	private static boolean doLoadFromLocalFiles = false;

	public static InputStream getArtDecorProjectIndex(String baseUrl, String prefix)
			throws ClientProtocolException, IOException {
		if (!doLoadFromLocalFiles) {
			return getArtDecorXml(baseUrl + "ProjectIndex?prefix=" + prefix + "&format=xml");
		} else {
			String fn = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
					+ "ProjectIndex-" + prefix + ".xml";
			File localFile = new File(fn);
			System.out.println(
					"Getting the Project Index from local file: " + localFile.getAbsolutePath());
			return IOUtils.toInputStream(FileUtils.readFileToString(localFile, Charsets.UTF_8));
		}

	}

	public static InputStream getArtDecorTemplate(String baseUrl, String prefix, String templateId,
			String effectiveDate) throws ClientProtocolException, IOException {
		return getArtDecorTemplate(baseUrl, prefix, templateId, templateId, effectiveDate);
	}

	public static InputStream getArtDecorTemplate(String baseUrl, String prefix,
			String documentTemplateId, String templateId, String effectiveDate)
			throws ClientProtocolException, IOException {
		if (!doLoadFromLocalFiles) {
			return getArtDecorXml(baseUrl + "RetrieveTemplate?prefix=" + prefix + "&id="
					+ templateId + "&effectiveDate=" + effectiveDate + "&format=xmlnowrapper");
		} else {
			String fn = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
					+ documentTemplateId + FileUtil.getPlatformSpecificPathSeparator() + templateId
					+ ".xml";
			File localFile = new File(fn);
			if (!localFile.exists()) {
				fn = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
						+ documentTemplateId + FileUtil.getPlatformSpecificPathSeparator()
						+ "includes" + FileUtil.getPlatformSpecificPathSeparator() + templateId
						+ ".xml";
				localFile = new File(fn);
			}
			System.out.println(
					"Getting the Template from local file: " + localFile.getAbsolutePath());
			return IOUtils.toInputStream(FileUtils.readFileToString(localFile, Charsets.UTF_8));
		}
	}

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

	private ArrayList<Document> projectIndexes = new ArrayList<Document>();

	private ArrayList<String> templates;
	private String baseUrl;

	private String baseDir;

	public ArtDecorRestClient(String baseURL, String baseDir) {
		this.baseDir = baseDir;
		this.baseUrl = baseURL;
		this.templates = new ArrayList<String>();
	}

	public void addArtDecorProject(String prefix) throws Exception {
		addArtDecorProject(baseUrl, prefix);
	}

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

	public boolean downloadTemplateRecursive(String artDecorPrefix, String documentTemplateId,
			String effectiveDate) {
		return downloadTemplateRecursive(artDecorPrefix, documentTemplateId, effectiveDate,
				documentTemplateId, null);
	}

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
