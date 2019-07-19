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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.ehealth_connector.common.utils.CustomizedYaml;

/**
 * TODO This class is the main entry point for the code generation. An eHC
 * maintainer has to launch this class as a Java application by providing the
 * base URL to ART-DECOR (e.g. https://art-decor.org/decor/services/), the
 * ART-DECOR prefix for the desired project (e.g. cdachlrep-) and the template
 * id of the desired Document Template (e.g. 2.16.756.5.30.1.1.10.1.10).
 *
 */
public class ArtDecor2JavaManager {

	public final static String ART_DECOR_MAIN_SERVER_BASE_URL = "https://art-decor.org/decor/services/";

	public final static void main(String[] args) {
		// args: at least the base URL to ART-DECOR, the ART-DECOR prefix for
		// the desired project and the template id of the desired Document
		// Template. These args are handover to the following method.
	}

	public void generateArtDecorDocumentTemplate(String baseUrl, String prefix, String templateId) {

	}

	/**
	 * <div class="en">Loads a content profile configuration from the given
	 * file.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil-Konfiguration aus der angegebenen
	 * Datei.</div>
	 *
	 * @param config
	 *            the config
	 * @return the content profile config
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfileConfig loadContentProfileConfig(File config)
			throws FileNotFoundException, ConfigurationException {
		return loadContentProfileConfig(new FileInputStream(config));
	}

	/**
	 * <div class="en">Loads a content profile configuration from the given
	 * stream.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil-Konfigurationaus dem angegebenen
	 * Stream.</div>
	 *
	 * @param config
	 *            the config
	 * @return the content profile config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfileConfig loadContentProfileConfig(InputStream config)
			throws ConfigurationException {
		return loadContentProfileConfig(new InputStreamReader(config, Charsets.UTF_8));
	}

	/**
	 * <div class="en">Loads a content profile configuration from the given
	 * stream reader.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil-Konfigurationaus dem angegebenen
	 * Stream-Reader.</div>
	 *
	 * @param reader
	 *            the reader
	 * @return the content profile config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfileConfig loadContentProfileConfig(InputStreamReader reader)
			throws ConfigurationException {

		ContentProfileConfig contentProfileConfig = new ContentProfileConfig();
		try {
			contentProfileConfig = CustomizedYaml.getCustomizedYaml().loadAs(reader,
					contentProfileConfig.getClass());
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ConfigurationException(e.getMessage());
		}

		return contentProfileConfig;

	}

	/**
	 * <div class="en">Loads a content profile configuration from the given file
	 * (the given filename must contain the relative or full path to access the
	 * file).</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil-Konfigurationaus der angegebenen
	 * Datei (der angegebene Dateiname muss den relativen oder vollständigen
	 * Pfad enthalten, um auf die Datei zuzugreifen).</div>
	 *
	 * @param fileName
	 *            the file name
	 * @return the content profile config
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfileConfig loadContentProfileConfig(String fileName)
			throws FileNotFoundException, ConfigurationException {
		return loadContentProfileConfig(new File(fileName));

	}

	/**
	 * <div class="en">Loads a content profile package configuration from the
	 * given file.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil Paket-Konfiguration aus der
	 * angegebenen Datei.</div>
	 *
	 * @param config
	 *            the config
	 * @return the content profile package config
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfilePackageConfig loadContentProfilePackageConfig(File config)
			throws FileNotFoundException, ConfigurationException {
		return loadContentProfilePackageConfig(new FileInputStream(config));
	}

	/**
	 * <div class="en">Loads a content profile package configuration from the
	 * given stream.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil Paket-Konfigurationaus dem
	 * angegebenen Stream.</div>
	 *
	 * @param config
	 *            the config
	 * @return the content profile package config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfilePackageConfig loadContentProfilePackageConfig(InputStream config)
			throws ConfigurationException {
		return loadContentProfilePackageConfig(new InputStreamReader(config, Charsets.UTF_8));
	}

	/**
	 * <div class="en">Loads a content profile package configuration from the
	 * given stream reader.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil Paket-Konfigurationaus dem
	 * angegebenen Stream-Reader.</div>
	 *
	 * @param reader
	 *            the reader
	 * @return the content profile package config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfilePackageConfig loadContentProfilePackageConfig(InputStreamReader reader)
			throws ConfigurationException {

		ContentProfilePackageConfig contentProfilePackageConfig = new ContentProfilePackageConfig();
		try {
			contentProfilePackageConfig = CustomizedYaml.getCustomizedYaml().loadAs(reader,
					contentProfilePackageConfig.getClass());
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ConfigurationException(e.getMessage());
		}

		return contentProfilePackageConfig;

	}

	/**
	 * <div class="en">Loads a content profile package configuration from the
	 * given file (the given filename must contain the relative or full path to
	 * access the file).</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil Paket-Konfigurationaus der
	 * angegebenen Datei (der angegebene Dateiname muss den relativen oder
	 * vollständigen Pfad enthalten, um auf die Datei zuzugreifen).</div>
	 *
	 * @param fileName
	 *            the file name
	 * @return the content profile package config
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfilePackageConfig loadContentProfilePackageConfig(String fileName)
			throws FileNotFoundException, ConfigurationException {
		return loadContentProfilePackageConfig(new File(fileName));

	}

	/**
	 * <div class="en">Saves the given content profile configuration in YAML
	 * format to the given file.</div>
	 *
	 * <div class="de">Speichert die angegebene Inhaltsprofil-Konfiguration im
	 * YAML-Format in der angegebenen Datei.</div>
	 *
	 * @param contentProfileConfig
	 *            the content profile config
	 * @param file
	 *            the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveContentProfileConfig(ContentProfileConfig contentProfileConfig, File file)
			throws IOException {
		FileUtils.writeByteArrayToFile(file, CustomizedYaml.getCustomizedYaml()
				.dumpAsMap(contentProfileConfig).getBytes(Charsets.UTF_8));
	}

	/**
	 * <div class="en">Saves the given content profile configuration in YAML
	 * format to the given file (the given filename must contain the relative or
	 * full path to access the file).</div>
	 *
	 * <div class="de">Speichert die angegebene Inhaltsprofil-Konfiguration im
	 * YAML-Format in der angegebenen Datei (der angegebene Dateiname muss den
	 * relativen oder vollständigen Pfad enthalten, um auf die Datei
	 * zuzugreifen).</div>
	 *
	 * @param contentProfileConfig
	 *            the content profile config
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveContentProfileConfig(ContentProfileConfig contentProfileConfig, String fileName)
			throws IOException {
		saveContentProfileConfig(contentProfileConfig, new File(fileName));
	}

	/**
	 * <div class="en">Saves a content profile package configuration in YAML
	 * format into the given File.</div>
	 *
	 * <div class="de">Speichert eine Inhaltsprofil-Paket Konfiguration im
	 * YAML-Format in der angegebenen Datei.</div>
	 *
	 * @param config
	 *            the config
	 * @param file
	 *            the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveContentProfilePackageConfig(ContentProfilePackageConfig config, File file)
			throws IOException {
		FileUtils.writeByteArrayToFile(file,
				CustomizedYaml.getCustomizedYaml().dumpAsMap(config).getBytes(Charsets.UTF_8));
	}

	/**
	 * <div class="en">Saves a content profile package configuration in YAML
	 * format into the given File (the given filename must contain the relative
	 * or full path to access the file).</div>
	 *
	 * <div class="de">Speichert eine Inhaltsprofil-Paket Konfiguration im
	 * YAML-Format in der angegebenen Datei (der angegebene Dateiname muss den
	 * relativen oder vollständigen Pfad enthalten, um auf die Datei
	 * zuzugreifen).</div>
	 *
	 * @param config
	 *            the config
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveContentProfilePackageConfig(ContentProfilePackageConfig config, String fileName)
			throws IOException {
		saveContentProfilePackageConfig(config, new File(fileName));
	}

}