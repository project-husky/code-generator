/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */
package org.ehealth_connector.codegenerator.cda.config;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Generated;

import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;

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
		private String artDecorDocTemplateId;
		private String artDecorPrefix;
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

		public Builder withArtDecorDocTemplateId(String artDecorDocTemplateId) {
			this.artDecorDocTemplateId = artDecorDocTemplateId;
			return this;
		}

		public Builder withArtDecorPrefix(String artDecorPrefix) {
			this.artDecorPrefix = artDecorPrefix;
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

	/** The art decor doc template id. */
	private String artDecorDocTemplateId;

	/** The art decor prefix. */
	private String artDecorPrefix;

	/** The target dir. */
	private String targetDir = Util.getTempDirectory() + FileUtil.getPlatformSpecificPathSeparator()
			+ "Ad2Java";

	/** The target namespace. */
	private String targetNamespace = "org.ehealth_connector.cda.ad2java";

	/**
	 * Instantiates a new content profile config. Default constructor.
	 */
	public ContentProfileConfig() {
	}

	@Generated("SparkTools")
	private ContentProfileConfig(Builder builder) {
		this.artDecorBaseUrl = builder.artDecorBaseUrl;
		this.artDecorDocTemplateId = builder.artDecorDocTemplateId;
		this.artDecorPrefix = builder.artDecorPrefix;
		this.targetDir = builder.targetDir;
		this.targetNamespace = builder.targetNamespace;
	}

	/**
	 * Instantiates a new content profile config.
	 *
	 * @param artDecorBaseUrl
	 *            the art decor base url
	 * @param artDecorPrefix
	 *            the art decor prefix
	 * @param artDecorDocTemplateId
	 *            the art decor doc template id
	 * @param targetNamespace
	 *            the target namespace
	 * @param targetDir
	 *            the target dir
	 */
	public ContentProfileConfig(URL artDecorBaseUrl, String artDecorPrefix,
			String artDecorDocTemplateId, String targetNamespace, String targetDir) {
		this.artDecorBaseUrl = artDecorBaseUrl.toString();
		this.artDecorPrefix = artDecorPrefix;
		this.artDecorDocTemplateId = artDecorDocTemplateId;
		this.targetNamespace = targetNamespace;
		this.targetDir = targetDir;
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
		return artDecorBaseUrl.toString();
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
			retVal = new URL(artDecorBaseUrl);
		} catch (Exception e) {
		}
		return retVal;
	}

	/**
	 * <div class="en">Gets the ART-DECOR document template id.</div>
	 *
	 * <div class="de">Ruft die ART-DECOR document template ID ab.</div>
	 *
	 *
	 * @return the art decor doc template id
	 */
	public String getArtDecorDocTemplateId() {
		return artDecorDocTemplateId;
	}

	/**
	 * <div class="en">Gets the ART-DECOR prefix.</div>
	 *
	 * <div class="de">Ruft den ART-DECOR Präfix ab.</div>
	 *
	 * @return the art decor prefix
	 */
	public String getArtDecorPrefix() {
		return artDecorPrefix;
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
		return targetDir;
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
		return targetNamespace;
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
	 * <div class="en">Sets the ART-DECOR document template id.</div>
	 *
	 * <div class="de">Legt die ART-DECOR document template ID fest.</div>
	 *
	 * @param artDecorDocTemplateId
	 *            the new art decor doc template id
	 */
	public void setArtDecorDocTemplateId(String artDecorDocTemplateId) {
		this.artDecorDocTemplateId = artDecorDocTemplateId;
	}

	/**
	 * <div class="en">Sets the ART-DECOR prefix.</div>
	 *
	 * <div class="de">Legt den ART-DECOR Präfix fest.</div>
	 *
	 *
	 * @param artDecorPrefix
	 *            the new art decor prefix
	 */
	public void setArtDecorPrefix(String artDecorPrefix) {
		this.artDecorPrefix = artDecorPrefix;
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
