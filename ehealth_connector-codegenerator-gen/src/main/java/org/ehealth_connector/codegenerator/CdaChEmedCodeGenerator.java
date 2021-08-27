package org.ehealth_connector.codegenerator;

import org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator;
import org.ehealth_connector.codegenerator.java.RuntimeUtils;
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
    private static final String CONFIG_FILE_BASE_PATH = RuntimeUtils.getCodeGeneratorGenPath()
            + "/src/updateValueSets/resources/valuesets/";

    /**
     * The logger.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(CdaChEmedCodeGenerator.class);

    /**
     * <div class="en">The main entry for the ART-DECOR to Java Code Generator.</div>
     *
     * <p><div class="de">Hauptzugang zum ART-DECOR to Java Code Generator.</div>
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
        final File javaSourceDir;
        if (javaSourceDirString != null) {
            javaSourceDir = new File(javaSourceDirString);
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
        } else {
            LOG.error("Java source directory is null");
            printUsage();
            return;
        }

        final File packageConfig = new File(CONFIG_FILE_BASE_PATH, CDACHEMED_PACKAGE_CONFIG);
        if (!packageConfig.exists() || !packageConfig.isFile()) {
            LOG.error("The package config file doesn't exist ({})", packageConfig.getAbsolutePath());
            printUsage();
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
        LOG.info("  javaSourceDir: This parameter must be the path to the eHealthConnector-Suisse project " +
                "directory (e.g. D:/Java/ehealtconnector-suisse)");
        LOG.info("");
        LOG.info("Example:");
        LOG.info("CdaChEmedCodeGenerator D:/Java/ehealtconnector-suisse");
    }
}
