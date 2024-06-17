/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator;

import org.projecthusky.codegenerator.valuesets.UpdateValueSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Entry point of the Swiss EPR code generator.
 *
 * @author Quentin Ligier
 * @see <a href="https://github.com/project-husky/code-generator/wiki/Swiss-EPR-code-generator">Updating the Swiss EPR
 * metadata</a>
 */
public class SwissEprCodeGenerator {

    /**
     * As published on April 1, 2021: https://www.e-health-suisse.ch/technik-semantik/semantische-interoperabilitaet/metadaten.html
     */
    private static final String SWISS_EPR_VALUE_SET_PACKAGE_CONFIG = "SwissEprValueSetPackageConfig-20210401.yaml";

    /**
     * Base path where to find the config files for the generator (YAML and JSON files).
     */
    private static final String CONFIG_FILE_BASE_PATH = System.getProperty("user.dir") + "/src/main/resources/valuesets/";

    /**
     * The logger.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(SwissEprCodeGenerator.class);

    /**
     * The updateValueSets entry for the value set generator.
     *
     * @param args command line arguments. A single value is expected.
     */
    public static void main(final String[] args) {
        LOG.info("Update Swiss EPR enums");

        if (args.length != 1) {
            printUsage();
            return;
        }
        final String javaSourceDirString = args[0];

        final File javaSourceDir;
        if (javaSourceDirString != null) {
            javaSourceDir = new File(javaSourceDirString);
            if (!javaSourceDir.exists()) {
                LOG.error("Java source directory does not exist ({})", javaSourceDir.getAbsolutePath());
                printUsage();
                return;
            } else {
                if (!javaSourceDir.isDirectory()) {
                    LOG.error("Java source is not a directory ({})", javaSourceDir.getAbsolutePath());
                    printUsage();
                    return;
                }
            }
        } else {
            LOG.error("Java source directory is null");
            printUsage();
            return;
        }

        final File packageConfig = new File(CONFIG_FILE_BASE_PATH, SWISS_EPR_VALUE_SET_PACKAGE_CONFIG);
        if (!packageConfig.exists() || !packageConfig.isFile()) {
            LOG.error("The package config file doesn't exist ({})", packageConfig.getAbsolutePath());
            printUsage();
        }

        UpdateValueSets.updateValueSets(javaSourceDir, packageConfig, false);
    }

    /**
     * Prints the application usage.
     */
    private static void printUsage() {
        LOG.info("Usage:");
        LOG.info("SwissEprCodeGenerator <javaSourceDir>");
        LOG.info("  javaSourceDir: This parameter must be the full path to the Husky project " +
                "directory (e.g. D:/Java/husky)");
        LOG.info("");
        LOG.info("Example:");
        LOG.info("SwissEprCodeGenerator D:/Java/husky");
    }
}
