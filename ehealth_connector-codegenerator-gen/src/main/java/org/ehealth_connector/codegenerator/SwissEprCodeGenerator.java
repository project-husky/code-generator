package org.ehealth_connector.codegenerator;

import org.ehealth_connector.codegenerator.java.RuntimeUtils;
import org.ehealth_connector.codegenerator.valuesets.UpdateValueSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Entry point of the Swiss EPR code generator.
 *
 * @see <a href="https://gitlab.com/ehealth-connector/api/-/wikis/Updating-the-Swiss-EPR-metadata">Updating the Swiss EPR metadata</a>
 * @author Quentin Ligier
 */
public class SwissEprCodeGenerator {

    /**
     * As published on April 1, 2021: https://www.e-health-suisse.ch/technik-semantik/semantische-interoperabilitaet/metadaten.html
     */
    private static final String SWISS_EPR_VALUE_SET_PACKAGE_CONFIG = "SwissEprValueSetPackageConfig-20210401.yaml";

    /**
     * Base path where to find the config files for the generator (YAML and JSON files).
     */
    private static final String CONFIG_FILE_BASE_PATH = RuntimeUtils.getCodeGeneratorGenPath()
            + "/src/updateValueSets/resources/valuesets/";

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

        UpdateValueSets.updateValueSets(javaSourceDir, packageConfig);
    }

    /**
     * Prints the application usage.
     */
    private static void printUsage() {
        LOG.info("Usage:");
        LOG.info("SwissEprCodeGenerator <javaSourceDir>");
        LOG.info("  javaSourceDir: This parameter must be the full path to the eHealthConnector-Suisse project " +
                "directory (e.g. D:/Java/ehealtconnector-suisse)");
        LOG.info("");
        LOG.info("Example:");
        LOG.info("SwissEprCodeGenerator D:/Java/ehealtconnector-suisse");
    }
}
