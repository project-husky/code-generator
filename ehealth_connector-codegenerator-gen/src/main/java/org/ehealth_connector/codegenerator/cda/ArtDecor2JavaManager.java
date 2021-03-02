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
package org.ehealth_connector.codegenerator.cda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.ehealth_connector.codegenerator.cda.config.ConfigurationException;
import org.ehealth_connector.codegenerator.cda.config.ContentProfileConfig;
import org.ehealth_connector.codegenerator.cda.config.ContentProfilePackageConfig;
import org.ehealth_connector.common.utils.CustomizedYaml;

/**
 * <div class="en">This class host som managing methods for the ART-DECOR to
 * Java Code Generator.</div>
 *
 * <div class="de">Diese Klasse enthält einige Verwaltungsmethoden für den
 * ART-DECOR to Java Code Generator.</div>
 *
 */
public class ArtDecor2JavaManager {

	/** The default ART-DECOR base URL (main ART-DECOR host). */
	public final static String ART_DECOR_DEFAULT_SERVER_BASE_URL = "https://art-decor.org/decor/services/";

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
	 * @throws ConfigurationException
	 *             the configuration exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ContentProfileConfig loadContentProfileConfig(File config)
			throws ConfigurationException, IOException {
		try (FileInputStream is = new FileInputStream(config)) {
			return loadContentProfileConfig(is);
		}
	}

	/**
	 * <div class="en">Loads a content profile configuration from the given
	 * stream.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil-Konfiguration aus dem angegebenen
	 * Stream.</div>
	 *
	 * @param inputStream
	 *            the config
	 * @return the content profile config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfileConfig loadContentProfileConfig(InputStream inputStream)
			throws ConfigurationException {
		InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
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
	 * <div class="de">Lädt eine Inhaltsprofil-Konfiguration aus der angegebenen
	 * Datei (der angegebene Dateiname muss den relativen oder vollständigen
	 * Pfad enthalten, um auf die Datei zuzugreifen).</div>
	 *
	 * @param fileName
	 *            the file name
	 * @return the content profile config
	 * @throws ConfigurationException
	 *             the configuration exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ContentProfileConfig loadContentProfileConfig(String fileName)
			throws ConfigurationException, IOException {
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
	 * @throws ConfigurationException
	 *             the configuration exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ContentProfilePackageConfig loadContentProfilePackageConfig(File config)
			throws ConfigurationException, IOException {
		try (FileInputStream is = new FileInputStream(config)) {
			return loadContentProfilePackageConfig(is);
		}
	}

	/**
	 * <div class="en">Loads a content profile package configuration from the
	 * given stream.</div>
	 *
	 * <div class="de">Lädt eine Inhaltsprofil Paket-Konfigurationaus dem
	 * angegebenen Stream.</div>
	 *
	 * @param inputStream
	 *            the config
	 * @return the content profile package config
	 * @throws ConfigurationException
	 *             the configuration exception
	 */
	public ContentProfilePackageConfig loadContentProfilePackageConfig(InputStream inputStream)
			throws ConfigurationException {
		InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
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
	 * <div class="de">Lädt eine Inhaltsprofil Paket-Konfiguration aus der
	 * angegebenen Datei (der angegebene Dateiname muss den relativen oder
	 * vollständigen Pfad enthalten, um auf die Datei zuzugreifen).</div>
	 *
	 * @param fileName
	 *            the file name
	 * @return the content profile package config
	 * @throws ConfigurationException
	 *             the configuration exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ContentProfilePackageConfig loadContentProfilePackageConfig(String fileName)
			throws ConfigurationException, IOException {
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
		saveContentProfileConfig(contentProfileConfig, new FileOutputStream(file));
	}

	/**
	 * <div class="en">Saves the given content profile configuration in YAML
	 * format to the given output stream.</div>
	 *
	 * <div class="de">Speichert die angegebene Inhaltsprofil-Konfiguration im
	 * YAML-Format in der angegebenen Datei.</div>
	 *
	 * @param contentProfileConfig
	 *            the content profile config
	 * @param outputStream
	 *            the outputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveContentProfileConfig(ContentProfileConfig contentProfileConfig,
			OutputStream outputStream) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
		writer.write(CustomizedYaml.getCustomizedYaml().dumpAsMap(contentProfileConfig));
		writer.flush();
		writer.close();
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
		saveContentProfilePackageConfig(config, new FileOutputStream(file));
	}

	/**
	 * <div class="en">Saves a content profile package configuration in YAML
	 * format into the given output stream.</div>
	 *
	 * <div class="de">Speichert eine Inhaltsprofil-Paket Konfiguration im
	 * YAML-Format in der angegebenen Datei.</div>
	 *
	 * @param config
	 *            the config
	 * @param outputStream
	 *            the outputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveContentProfilePackageConfig(ContentProfilePackageConfig config,
			OutputStream outputStream) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
		writer.write(CustomizedYaml.getCustomizedYaml().dumpAsMap(config));
		writer.flush();
		writer.close();
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