/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.cda.config;

import java.io.Serial;

/**
 * <div class="en">Exception in case the content profile configuration is
 * invalid.</div>
 *
 * <div class="de">Ausnahme für den Fall, dass die Konfiguration des
 * Inhaltsprofils ungültig ist.<div>
 */
public class ConfigurationException extends Exception {

    /** The generated constant serialVersionUID. */
    @Serial
    private static final long serialVersionUID = -836975789021093767L;

    /**
     * <div class="en">Default constructor to instantiate the object. A
     * predefined text message will be used.</div>
     *
     * <div class="de">Standardkonstruktor zum Instanziieren des Objekts. Eine
     * vordefinierte Textnachricht wird verwendet.<div>
     */
    public ConfigurationException() {
        super("The content profile configuration cannot be loaded. Please check the config file/stream.");
    }

    /**
     * <div class="en">Instantiates a new configuration exception by using the
     * given text message.</div>
     *
     * <div class="de">Instanziiert eine neue Konfigurationsausnahme unter
     * Verwendung der angegebenen Textnachricht.<div>
     *
     * @param message
     *            the message
     */
    public ConfigurationException(String message) {
        super(message);
    }
}
