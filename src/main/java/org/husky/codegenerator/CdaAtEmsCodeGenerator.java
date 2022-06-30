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

import java.io.File;

import org.husky.codegenerator.cda.ArtDecor2JavaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point of the CDA-EMS code generator.
 *
 * @author Anna Jungwirth
 */
public class CdaAtEmsCodeGenerator {

    /**
	 * CDA-EMS package config, v2020
	 */
	private static final String CDACHEMED_PACKAGE_CONFIG = "src/main/resources/cda/ContentProfilePackageConfigCdaAtEmsV2020.yml";

    /**
     * The logger.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(CdaAtEmsCodeGenerator.class);

    /**
     * The main entry for the ART-DECOR to Java Code Generator.
     *
     * @param args Command line arguments. A single value is expected.
     */
    public static void main(final String[] args) {
		LOG.info("Update CDA-EMS classes and enums");

        if (args.length != 1) {
			printUsage(null);
            return;
        }

        final String javaSourceDirString = args[0];
        final File javaSourceDir = new File(javaSourceDirString);
        if (!javaSourceDir.exists()) {
            LOG.error("Java source directory does not exist ({})", javaSourceDirString);
			printUsage(javaSourceDirString);
            return;
        } else if (!javaSourceDir.isDirectory()) {
            LOG.error("Java source is not a directory ({})", javaSourceDirString);
			printUsage(javaSourceDirString);
            return;
        }
		final var cdaSourceDirString = javaSourceDirString + "/husky-cda/husky-ems/";
        final File cdaSourceDir = new File(cdaSourceDirString);
        if (!cdaSourceDir.exists()) {
            LOG.error("Java source directory does not exist ({})", cdaSourceDirString);
			printUsage(javaSourceDirString);
            return;
        } else if (!cdaSourceDir.isDirectory()) {
            LOG.error("Java source is not a directory ({})", cdaSourceDirString);
			printUsage(javaSourceDirString);
            return;
        }

        final File packageConfig = new File(CDACHEMED_PACKAGE_CONFIG);
        if (!packageConfig.exists() || !packageConfig.isFile()) {
            LOG.error("The package config file doesn't exist ({})", packageConfig.getAbsolutePath());
			printUsage(javaSourceDirString);
        }

        ArtDecor2JavaGenerator.generate(cdaSourceDir, packageConfig);
    }

    /**
     * Prints the application usage.
     */
	private static void printUsage(String javaSourceDir) {
        LOG.info("Usage:");
		LOG.info("CdaAtEmsCodeGenerator <javaSourceDir>");
        LOG.info("  javaSourceDir: This parameter must be the path to the Husky project " +
				"directory: {}", javaSourceDir);
    }
}
