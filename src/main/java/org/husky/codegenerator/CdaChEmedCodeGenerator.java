/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator;

import org.husky.codegenerator.cda.ArtDecor2JavaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
     * Base path where to find the config files for the generator (YAML and JSON files).
     */
    private static final String CONFIG_FILE_BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/cda/";

    /**
     * The logger.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(CdaChEmedCodeGenerator.class);

    /**
     * The main entry for the ART-DECOR to Java Code Generator.
     *
     * @param args Command line arguments. A single value is expected.
     */
    public static void main(final String[] args) {
        LOG.info("Update CDA-CH-EMED classes and enums");

        if (args.length != 1) {
            printUsage();
            return;
        }

        final String javaSourceDirString = args[0] + "/husky-emed/husky-emed-cda/";
        final File javaSourceDir = new File(javaSourceDirString);
        if (!javaSourceDir.exists()) {
            LOG.error("Java source directory does not exist ({})", javaSourceDirString);
            printUsage();
            return;
        } else {
            if (!javaSourceDir.isDirectory()) {
                LOG.error("Java source is not a directory ({})", javaSourceDirString);
                printUsage();
                return;
            }
        }

        final File packageConfig = new File(CONFIG_FILE_BASE_PATH, CDACHEMED_PACKAGE_CONFIG);
        if (!packageConfig.exists() || !packageConfig.isFile()) {
            LOG.error("The package config file doesn't exist ({})", packageConfig.getAbsolutePath());
            printUsage();
        }

        ArtDecor2JavaGenerator.generate(javaSourceDir, packageConfig);
    }

    /**
     * Prints the application usage.
     */
    private static void printUsage() {
        LOG.info("Usage:");
        LOG.info("CdaChEmedCodeGenerator <javaSourceDir>");
        LOG.info("  javaSourceDir: This parameter must be the path to the Husky project " +
                "directory (e.g. D:/Java/husky)");
        LOG.info("");
        LOG.info("Example:");
        LOG.info("CdaChEmedCodeGenerator D:/Java/husky");
    }
}
