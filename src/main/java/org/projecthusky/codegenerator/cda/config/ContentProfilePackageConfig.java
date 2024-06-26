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

import java.util.ArrayList;

import javax.annotation.processing.Generated;

/**
 * <div class="en">The Class ContentProfilePackageConfig contains all relevant
 * information (the configuration) for a set of content profiles to be converted
 * vom ART-DECOR to Java.</div>
 *
 * <div class="de">Die Klasse ContentProfilePackageConfig enthält alle
 * relevanten Informationen (die Konfiguration) für Set von Inhaltsprofilen
 * (Austauschformat), die von ART-DECOR nach Java konvertiert werden
 * sollen.</div>
 */
public class ContentProfilePackageConfig {
    /**
     * Builder to build {@link ContentProfilePackageConfig}.
     */
    @Generated("SparkTools")
    public static final class Builder {

        /** The content profile config list. */
        private ArrayList<ContentProfileConfig> contentProfileConfigList;

        /** The description. */
        private String description;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
        }

        /**
         * Builds the ContentProfilePackageConfig.
         *
         * @return the content profile package config
         */
        public ContentProfilePackageConfig build() {
            return new ContentProfilePackageConfig(this);
        }

        /**
         * With content profile config list.
         *
         * @param contentProfileConfigList
         *            the content profile config list
         * @return the builder
         */
        public Builder withContentProfileConfigList(
                ArrayList<ContentProfileConfig> contentProfileConfigList) {
            this.contentProfileConfigList = contentProfileConfigList;
            return this;
        }

        /**
         * With description.
         *
         * @param description
         *            the description
         * @return the builder
         */
        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }
    }

    /**
     * Creates builder to build {@link ContentProfilePackageConfig}.
     *
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /** The content profile config list. */
    private ArrayList<ContentProfileConfig> contentProfileConfigList;

    /** The description. */
    private String description;

    /**
     * Instantiates a new content profile package config. Default constructor.
     */
    public ContentProfilePackageConfig() {
    }

    /**
     * Instantiates a new content profile package config.
     *
     * @param builder
     *            the builder
     */
    @Generated("SparkTools")
    private ContentProfilePackageConfig(Builder builder) {
        this.description = builder.description;
        this.contentProfileConfigList = builder.contentProfileConfigList;
    }

    /**
     * <div class="en">Adds a content profile configuration.</div>
     *
     * <div class="de">Fügt eine Inhaltsprofil-Konfiguration hinzu.</div>
     *
     * @param value
     *            the value
     */
    public void addContentProfileConfig(ContentProfileConfig value) {
        if (this.contentProfileConfigList == null) {
            this.contentProfileConfigList = new ArrayList<ContentProfileConfig>();
        }
        this.contentProfileConfigList.add(value);

    }

    /**
     * <div class="en">Clears the content profile config list.</div>
     *
     * <div class="de">Löscht die Liste der Inhaltsprofil-Konfigurationen.</div>
     */
    public void clearMappingIdentificatorList() {
        this.contentProfileConfigList = new ArrayList<ContentProfileConfig>();
    }

    /**
     * <div class="en">Gets the content profile config list.</div>
     *
     * <div class="de">Ruft die Inhaltsprofil-Konfigurationsliste ab.</div>
     *
     * @return the content profile config list
     */
    public ArrayList<ContentProfileConfig> getContentProfileConfigList() {
        if (this.contentProfileConfigList == null) {
            this.contentProfileConfigList = new ArrayList<ContentProfileConfig>();
        }
        return this.contentProfileConfigList;
    }

    /**
     * <div class="en">Gets the description of the content profile package. It
     * is for information, only.</div>
     *
     * <div class="de">Ruft die Beschreibung des Inhaltsprofilpakets ab. Diese
     * dient nur zur Information.</div>
     *
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * <div class="en">Sets the list of content profile configs.</div>
     *
     * <div class="de">Legt die Liste der Inhaltsprofil-Konfigurationen
     * fest.</div>
     *
     * @param contentProfileConfigList
     *            the new content profile config list
     */
    public void setContentProfileConfigList(
            ArrayList<ContentProfileConfig> contentProfileConfigList) {
        this.contentProfileConfigList = contentProfileConfigList;
    }

    /**
     * <div class="en">Sets the description of the content profile package. It
     * is for information, only.</div>
     *
     * <div class="de">Legt die Beschreibung des Inhaltsprofilpakets fest. Diese
     * dient nur zur Information.</div>
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
