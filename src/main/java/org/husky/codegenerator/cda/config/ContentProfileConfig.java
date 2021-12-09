/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.cda.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.husky.common.utils.Util;

// TODO: Auto-generated Javadoc
/**
 * <div class="en">The Class ContentProfileConfig is intended to contains all
 * relevant information (the configuration) for a given content profile to be
 * converted vom ART-DECOR to Java.</div>
 *
 * <div class="de">Die Klasse ContentProfileConfig ist dafür gedacht, alle
 * relevanten Informationen (die Konfiguration) für ein bestimmtes Inhaltsprofil
 * zusammenzufassen, das von ART-DECOR nach Java konvertiert werden soll.</div>
 */
public class ContentProfileConfig {

    /**
     * Builder to build {@link ContentProfileConfig}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String artDecorBaseUrl;
        private Map<String, String> artDecorDocTemplateMap;
        private Map<String, String> artDecorProjectMap;
        private Map<String, String> enums;
        private String targetDir;
        private String targetNamespace;

        private Builder() {
        }

        public ContentProfileConfig build() {
            return new ContentProfileConfig(this);
        }

        public Builder withArtDecorBaseUrl(String artDecorBaseUrl) {
            this.artDecorBaseUrl = artDecorBaseUrl;
            return this;
        }

        public Builder withArtDecorDocTemplateMap(HashMap<String, String> artDecorDocTemplateMap) {
            this.artDecorDocTemplateMap = artDecorDocTemplateMap;
            return this;
        }

        public Builder withArtDecorProjectMap(HashMap<String, String> artDecorProjectMap) {
            this.artDecorProjectMap = artDecorProjectMap;
            return this;
        }

        public Builder withEnums(HashMap<String, String> enums) {
            this.enums = enums;
            return this;
        }

        public Builder withTargetDir(String targetDir) {
            this.targetDir = targetDir;
            return this;
        }

        public Builder withTargetNamespace(String targetNamespace) {
            this.targetNamespace = targetNamespace;
            return this;
        }
    }

    /**
     * Creates builder to build {@link ContentProfileConfig}.
     *
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /** The art decor base url. */
    private String artDecorBaseUrl;

    /** The art decor doc template id list. */
    private Map<String, String> artDecorDocTemplateMap;

    /** The art decor project references. */
    private Map<String, String> artDecorProjectMap;

    /** The enums mapping to use. The key is the name of the value set, the value is the fully qualified class name. */
    private Map<String, String> enums;

    /** The art decor main prefix. */
    private String artDecorMainPrefix;

    /** The art decor main base url. */
    private String artDecorMainBaseUrl;

    /** The target dir. */
    private String targetDir = Util.getTempDirectory() + "/Ad2Java";

    /** The target namespace. */
    private String targetNamespace = "org.husky.cda.ad2java";

    /**
     * Instantiates a new content profile config. Default constructor.
     */
    public ContentProfileConfig() {
        this.artDecorDocTemplateMap = new HashMap<>();
        this.artDecorProjectMap = new HashMap<>();
        this.enums = new HashMap<>();
    }

    @Generated("SparkTools")
    private ContentProfileConfig(Builder builder) {
        this.artDecorBaseUrl = builder.artDecorBaseUrl;
        this.artDecorDocTemplateMap = builder.artDecorDocTemplateMap;
        this.artDecorProjectMap = builder.artDecorProjectMap;
        this.enums = builder.enums;
        this.targetDir = builder.targetDir;
        this.targetNamespace = builder.targetNamespace;
    }

    /**
     * Instantiates a new content profile config.
     *
     * @param artDecorBaseUrl
     *            the art decor base url
     * @param targetNamespace
     *            the target namespace
     * @param targetDir
     *            the target dir
     */
    public ContentProfileConfig(URL artDecorBaseUrl, String targetNamespace, String targetDir) {
        this.artDecorDocTemplateMap = new HashMap<>();
        this.artDecorProjectMap = new HashMap<>();
        this.enums = new HashMap<>();
        this.artDecorBaseUrl = artDecorBaseUrl.toString();
        this.targetNamespace = targetNamespace;
        this.targetDir = targetDir;
    }

    /**
     * Adds a project to the list.
     *
     * @param prefix
     *            the ART-DECOR prefix
     * @param url
     *            the ART-DECOR base url
     * @throws MalformedURLException
     *             the malformed URL exception
     */
    public void addProject(String prefix, String url) throws MalformedURLException {
        if (this.artDecorProjectMap == null)
            this.artDecorProjectMap = new HashMap<>();
        addProject(prefix, new URL(url));
    }

    /**
     * Adds a project to the list. You are required to add the main ART-DECOR
     * project first! (The main ART-DECOR project is the where the document
     * template(s) are located).
     *
     * @param prefix
     *            the ART-DECOR prefix
     * @param url
     *            the ART-DECOR base url
     */
    public void addProject(String prefix, URL url) {
        if (this.artDecorProjectMap == null)
            this.artDecorProjectMap = new HashMap<>();
        this.artDecorProjectMap.put(prefix, url.toString());
        if (this.artDecorMainPrefix == null)
            this.artDecorMainPrefix = prefix;
        if (this.artDecorMainBaseUrl == null)
            this.artDecorMainBaseUrl = url.toString();
    }

    /**
     * Adds a template Id to the list.
     *
     * @param templateId
     *            the template id
     * @param effectiveTime
     *            the effective time
     */
    public void addTemplateId(String templateId, String effectiveTime) {
        if (this.artDecorDocTemplateMap == null)
            this.artDecorDocTemplateMap = new HashMap<>();
        this.artDecorDocTemplateMap.put(templateId, effectiveTime);
    }

    /**
     * <div class="en">Gets the ART-DECOR base URL. e.g.:
     * https://art-decor.org/decor/services/. ART-DECOR prefix and template id
     * will be added to this base URL to correctly access the templates.</div>
     *
     * <div class="de">Ruft die ART-DECOR-Basis-URL ab. Beispiel:
     * https://art-decor.org/decor/services/. ART-DECOR Präfix und die Template
     * ID werden zu dieser Basis-URL hinzugefügt, um den korrekten Zugriff auf
     * die Templates zu ermöglichen.</div>
     *
     * @return the art decor base url as string
     */
    public String getArtDecorBaseUrl() {
        return this.artDecorBaseUrl;
    }

    /**
     * <div class="en">Gets the ART-DECOR base URL. e.g.:
     * https://art-decor.org/decor/services/. ART-DECOR prefix and template id
     * will be added to this base URL to correctly access the templates.</div>
     *
     * <div class="de">Ruft die ART-DECOR-Basis-URL ab. Beispiel:
     * https://art-decor.org/decor/services/. ART-DECOR Präfix und die Template
     * ID werden zu dieser Basis-URL hinzugefügt, um den korrekten Zugriff auf
     * die Templates zu ermöglichen.</div>
     *
     * @return the art decor base url as URL
     */
    public URL getArtDecorBaseUrlAsUrl() {
        URL retVal = null;
        try {
            retVal = new URL(this.artDecorBaseUrl);
        } catch (Exception e) {
        }
        return retVal;
    }

    /**
     * <div class="en">Gets the ART-DECOR document template id list.</div>
     *
     * <div class="de">Ruft die Liste der ART-DECOR document template IDs
     * ab.</div>
     *
     *
     * @return the art decor doc template id
     */
    public Map<String, String> getArtDecorDocTemplateMap() {
        if (this.artDecorDocTemplateMap == null)
            this.artDecorDocTemplateMap = new HashMap<>();
        return this.artDecorDocTemplateMap;
    }

    /**
     * Gets the art decor main base url.
     *
     * @return the art decor main base url
     */
    public String getArtDecorMainBaseUrl() {
        return this.artDecorMainBaseUrl;
    }

    /**
     * Gets the art decor main prefix.
     *
     * @return the art decor main prefix
     */
    public String getArtDecorMainPrefix() {
        return this.artDecorMainPrefix;
    }

    /**
     * <div class="en">Gets the ART-DECOR project list.</div>
     *
     * <div class="de">Ruft die Liste der ART-DECOR Projekte ab.</div>
     *
     * @return the art decor project list
     */
    public Map<String, String> getArtDecorProjectMap() {
        if (this.artDecorProjectMap == null)
            this.artDecorProjectMap = new HashMap<>();
        return this.artDecorProjectMap;
    }

    public Map<String, String> getEnums() {
        if (this.enums == null)
            this.enums = new HashMap<>();
        return this.enums;
    }

    /**
     * <div class="en">Gets the target dir. That's where the Java class files
     * will be generated to.</div>
     *
     * <div class="de">Ruft das Zielverzeichnis ab. Dorthin werden die
     * Java-Klassendateien generiert.</div>
     *
     * @return the target dir
     */
    public String getTargetDir() {
        return this.targetDir;
    }

    /**
     * <div class="en">Gets the target namespace. That's the namespace to put in
     * the generated Java class files.</div>
     *
     * <div class="de">Ruft den Ziel namespace ab. Dies ist der Namespace, der
     * in die generierten Java-Klassendateien eingefügt werden soll.</div>
     *
     * @return the target namespace
     */
    public String getTargetNamespace() {
        return this.targetNamespace;
    }

    /**
     * <div class="en">Sets the ART-DECOR base URL. e.g.:
     * https://art-decor.org/decor/services/ ART-DECOR. prefix and template id
     * will be added to this base URL to correctly access the templates.</div>
     *
     * <div class="de">Legt die ART-DECOR-Basis-URL fest. Beispiel:
     * https://art-decor.org/decor/services/. ART-DECOR Präfix und die Template
     * ID werden zu dieser Basis-URL hinzugefügt, um den korrekten Zugriff auf
     * die Templates zu ermöglichen.</div>
     *
     * @param artDecorBaseUrl
     *            the new art decor base url
     * @throws MalformedURLException
     *             the malformed URL exception
     */
    public void setArtDecorBaseUrl(String artDecorBaseUrl) throws MalformedURLException {
        URL url = new URL(artDecorBaseUrl);
        this.artDecorBaseUrl = url.toString();
    }

    /**
     * <div class="en">Sets the ART-DECOR base URL. e.g.:
     * https://art-decor.org/decor/services/ ART-DECOR. prefix and template id
     * will be added to this base URL to correctly access the templates.</div>
     *
     * <div class="de">Legt die ART-DECOR-Basis-URL fest. Beispiel:
     * https://art-decor.org/decor/services/. ART-DECOR Präfix und die Template
     * ID werden zu dieser Basis-URL hinzugefügt, um den korrekten Zugriff auf
     * die Templates zu ermöglichen.</div>
     *
     * @param artDecorBaseUrl
     *            the new art decor base url
     */
    public void setArtDecorBaseUrl(URL artDecorBaseUrl) {
        this.artDecorBaseUrl = artDecorBaseUrl.toString();
    }

    /**
     * Sets the art decor doc template map.
     *
     * @param artDecorDocTemplateMap
     *            the art decor doc template map
     */
    public void setArtDecorDocTemplateMap(Map<String, String> artDecorDocTemplateMap) {
        if (this.artDecorDocTemplateMap == null)
            this.artDecorDocTemplateMap = new HashMap<>();
        this.artDecorDocTemplateMap = artDecorDocTemplateMap;
    }

    public void setArtDecorMainBaseUrl(String artDecorMainBaseUrl) {
        this.artDecorMainBaseUrl = artDecorMainBaseUrl;
    }

    public void setArtDecorMainPrefix(String artDecorMainPrefix) {
        this.artDecorMainPrefix = artDecorMainPrefix;
    }

    /**
     * <div class="en">Sets the ART-DECOR project list.</div>
     *
     * <div class="de">Legt die Liste der ART-DECOR Projekte fest.</div>
     *
     * @param artDecorProjectMap
     *            the art decor project map
     */
    public void setArtDecorProjectMap(Map<String, String> artDecorProjectMap) {
        if (this.artDecorProjectMap == null)
            this.artDecorProjectMap = new HashMap<>();
        this.artDecorProjectMap = artDecorProjectMap;
    }

    public void setEnums(Map<String, String> enums) {
        this.enums = enums;
    }

    /**
     * <div class="en">Sets the target dir. That's where the Java class files
     * will be generated to.</div>
     *
     * <div class="de">Legt das Zielverzeichnis fest. Dorthin werden die
     * Java-Klassendateien generiert.</div>
     *
     * @param targetDir
     *            the new target dir
     */
    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    /**
     * <div class="en">Sets the target namespace. That's the namespace to put in
     * the generated Java class files.</div>
     *
     * <div class="de">Legt den Ziel namespace fest. Dies ist der Namespace, der
     * in die generierten Java-Klassendateien eingefügt werden soll.</div>
     *
     * @param targetNamespace
     *            the new target namespace
     */
    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }

}
