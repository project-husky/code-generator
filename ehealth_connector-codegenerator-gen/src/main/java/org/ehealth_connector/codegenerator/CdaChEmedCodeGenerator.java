package org.ehealth_connector.codegenerator;

import org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point of the CDA-CH-EMED code generator.
 *
 * @see <a href="https://gitlab.com/ehealth-connector/api/-/wikis/ART-DECOR-to-Java-Code-Generator">ART DECOR to Java Code Generator</a>
 * @author Quentin Ligier
 */
public class CdaChEmedCodeGenerator {

    /**
     * CDA-CH-EMED package config, v0.97
     */
    private static final String CDACHEMED_PACKAGE_CONFIG = "ContentProfilePackageConfigCdaChEmedV097.yml";

    /**
     * The logger.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(CdaChEmedCodeGenerator.class);
}
