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
import org.husky.codegenerator.valuesets.UpdateValueSets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Entry point of the CDA-CH-EMED code generator.
 *
 * @see <a href="https://github.com/project-husky/code-generator/wiki/CDA-CH-EMED-code-generator">CDA-CH-EMED Code Generator</a>
 * @author Quentin Ligier
 */
public class CdaChEmedCodeGenerator {

    /**
     * CDA-CH-EMED package config, v0.97
     */
    private static final String CDACHEMED_PACKAGE_CONFIG = System.getProperty("user.dir") + "/src/main/resources/cda/ContentProfilePackageConfigCdaChEmedV097.yml";

    /**
     * CDA-CH-EMED value set package config, current
     */
    private static final String CDACHEMED_VALUE_SET_PACKAGE_CONFIG = System.getProperty("user.dir") + "/src/main/resources/valuesets/CdaChEmedValueSetPackageConfig-current.yaml";

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
        final var cdaSourceDirString = javaSourceDirString + "/husky-emed/husky-emed-ch-cda/";
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

        final File packageConfig = new File(CDACHEMED_PACKAGE_CONFIG);
        if (!packageConfig.exists() || !packageConfig.isFile()) {
            LOG.error("The package config file doesn't exist ({})", packageConfig.getAbsolutePath());
            printUsage();
        }

        final File packageConfig2 = new File(CDACHEMED_VALUE_SET_PACKAGE_CONFIG);
        if (!packageConfig2.exists() || !packageConfig2.isFile()) {
            LOG.error("The package config file doesn't exist ({})", packageConfig2.getAbsolutePath());
            printUsage();
        }

        ArtDecor2JavaGenerator.generate(cdaSourceDir, packageConfig);
        UpdateValueSets.updateValueSets(javaSourceDir, packageConfig2);
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
