package org.ehealth_connector.codegenerator.java;

/**
 * Utilities for the runtime.
 *
 * @author Quentin Ligier
 */
public class RuntimeUtils {

    /**
     * This class is not instantiable.
     */
    private RuntimeUtils() {
    }

    /**
     * Gets the full path to the ehealth_connector-codegenerator-gen subpackage.
     *
     * @return the full path.
     */
    public static String getCodeGeneratorGenPath() {
        String path = System.getProperty("user.dir");
        if (!path.endsWith("ehealth_connector-codegenerator-gen")) {
            path += "/ehealth_connector-codegenerator-gen";
        }
        return path;
    }
}
