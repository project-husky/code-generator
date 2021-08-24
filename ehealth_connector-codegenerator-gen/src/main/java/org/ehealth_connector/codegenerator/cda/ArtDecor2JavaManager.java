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

import org.ehealth_connector.codegenerator.cda.config.ConfigurationException;
import org.ehealth_connector.codegenerator.cda.config.ContentProfilePackageConfig;
import org.ehealth_connector.common.utils.CustomizedYaml;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * <div class="en">This class host som managing methods for the ART-DECOR to Java Code
 * Generator.</div>
 *
 * <p><div class="de">Diese Klasse enthält einige Verwaltungsmethoden für den ART-DECOR to Java Code
 * Generator.</div>
 */
public class ArtDecor2JavaManager {

  /** The default ART-DECOR base URL (main ART-DECOR host). */
  public static final String ART_DECOR_DEFAULT_SERVER_BASE_URL =
      "https://art-decor.org/decor/services/";

  /**
   * <div class="en">Loads a content profile package configuration from the given file.</div>
   *
   * <p><div class="de">Lädt eine Inhaltsprofil Paket-Konfiguration aus der angegebenen Datei.</div>
   *
   * @param config the config
   * @return the content profile package config
   * @throws ConfigurationException the configuration exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ContentProfilePackageConfig loadContentProfilePackageConfig(File config)
      throws ConfigurationException, IOException {
    try (FileInputStream is = new FileInputStream(config)) {
      return loadContentProfilePackageConfig(is);
    }
  }

  /**
   * <div class="en">Loads a content profile package configuration from the given stream.</div>
   *
   * <p><div class="de">Lädt eine Inhaltsprofil Paket-Konfigurationaus dem angegebenen Stream.</div>
   *
   * @param inputStream the config
   * @return the content profile package config
   * @throws ConfigurationException the configuration exception
   */
  public ContentProfilePackageConfig loadContentProfilePackageConfig(InputStream inputStream)
      throws ConfigurationException {
    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    ContentProfilePackageConfig contentProfilePackageConfig = new ContentProfilePackageConfig();
    try {
      contentProfilePackageConfig =
          CustomizedYaml.getCustomizedYaml().loadAs(reader, contentProfilePackageConfig.getClass());
    } catch (RuntimeException e) {
      e.printStackTrace();
      throw new ConfigurationException(e.getMessage());
    }

    return contentProfilePackageConfig;
  }

  /**
   * <div class="en">Loads a content profile package configuration from the given file (the given
   * filename must contain the relative or full path to access the file).</div>
   *
   * <p><div class="de">Lädt eine Inhaltsprofil Paket-Konfiguration aus der angegebenen Datei (der
   * angegebene Dateiname muss den relativen oder vollständigen Pfad enthalten, um auf die Datei
   * zuzugreifen).</div>
   *
   * @param fileName the file name
   * @return the content profile package config
   * @throws ConfigurationException the configuration exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ContentProfilePackageConfig loadContentProfilePackageConfig(String fileName)
      throws ConfigurationException, IOException {
    return loadContentProfilePackageConfig(new File(fileName));
  }

  /**
   * <div class="en">Saves a content profile package configuration in YAML format into the given
   * File.</div>
   *
   * <p><div class="de">Speichert eine Inhaltsprofil-Paket Konfiguration im YAML-Format in der
   * angegebenen Datei.</div>
   *
   * @param config the config
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void saveContentProfilePackageConfig(ContentProfilePackageConfig config, File file)
      throws IOException {
    saveContentProfilePackageConfig(config, new FileOutputStream(file));
  }

  /**
   * <div class="en">Saves a content profile package configuration in YAML format into the given
   * output stream.</div>
   *
   * <p><div class="de">Speichert eine Inhaltsprofil-Paket Konfiguration im YAML-Format in der
   * angegebenen Datei.</div>
   *
   * @param config The config
   * @param outputStream The outputStream
   * @throws IOException if an I/O exception has occurred.
   */
  public void saveContentProfilePackageConfig(
      ContentProfilePackageConfig config, OutputStream outputStream) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
    writer.write(CustomizedYaml.getCustomizedYaml().dumpAsMap(config));
    writer.flush();
    writer.close();
  }
}
