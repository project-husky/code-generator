package org.projecthusky.codegenerator;

import org.projecthusky.codegenerator.valuesets.UpdateValueSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * husky-codegenerator
 *
 * @author Quentin Ligier
 **/
public class SwissEmedCodeGenerator {

    /**
     * CDA-CH-EMED value set package config, current
     */
    private static final String EMED_VALUE_SET_PACKAGE_CONFIG = System.getProperty("user.dir") + "/src/main/resources" +
            "/valuesets/SwissFhirEmed.yaml";

    /**
     * The logger.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(SwissEmedCodeGenerator.class);

    /**
     * The main entry for the ART-DECOR to Java Code Generator.
     *
     * @param args Command line arguments. A single value is expected.
     */
    public static void main(final String[] args) {
        LOG.info("Update CH-EMED-EPR enums");

        if (args.length != 1) {
            printUsage();
            return;
        }

        final String javaSourceDirString = args[0];
        final File javaSourceDir = new File(javaSourceDirString);
        if (!javaSourceDir.exists()) {
            LOG.error("Java source directory does not exist ({})", javaSourceDirString);
            printUsage();
            return;
        } else if (!javaSourceDir.isDirectory()) {
            LOG.error("Java source is not a directory ({})", javaSourceDirString);
            printUsage();
            return;
        }
        final var cdaSourceDirString = javaSourceDirString + "/husky-fhir/husky-fhir-emed-ch/";
        final File cdaSourceDir = new File(cdaSourceDirString);
        if (!cdaSourceDir.exists()) {
            LOG.error("Java source directory does not exist ({})", cdaSourceDirString);
            printUsage();
            return;
        } else if (!cdaSourceDir.isDirectory()) {
            LOG.error("Java source is not a directory ({})", cdaSourceDirString);
            printUsage();
            return;
        }

        final File packageConfig = new File(EMED_VALUE_SET_PACKAGE_CONFIG);
        if (!packageConfig.exists() || !packageConfig.isFile()) {
            LOG.error("The package config file doesn't exist ({})", packageConfig.getAbsolutePath());
            printUsage();
        }

        UpdateValueSets.updateValueSets(javaSourceDir, packageConfig, true);
    }

    /**
     * Prints the application usage.
     */
    private static void printUsage() {
        LOG.info("Usage:");
        LOG.info("SwissEmedCodeGenerator <javaSourceDir>");
        LOG.info("  javaSourceDir: This parameter must be the path to the Husky project " +
                         "directory (e.g. D:/Java/husky)");
        LOG.info("");
        LOG.info("Example:");
        LOG.info("SwissEmedCodeGenerator D:/Java/husky");
    }
}
