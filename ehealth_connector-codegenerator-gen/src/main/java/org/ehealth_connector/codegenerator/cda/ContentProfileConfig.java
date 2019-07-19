/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
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
package org.ehealth_connector.codegenerator.cda;

import javax.annotation.Generated;

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

		/** The art decor base url. */
		private String artDecorBaseUrl;

		/** The art decor prefix. */
		private String artDecorPrefix;

		/** The art decor doc template id. */
		private String artDecorDocTemplateId;

		/** The target namespace. */
		private String targetNamespace;

		/** The target dir. */
		private String targetDir;

		/**
		 * Instantiates a new builder.
		 */
		private Builder() {
		}

		/**
		 * Builds the.
		 *
		 * @return the content profile config
		 */
		public ContentProfileConfig build() {
			return new ContentProfileConfig(this);
		}

		/**
		 * With art decor base url.
		 *
		 * @param artDecorBaseUrl
		 *            the art decor base url
		 * @return the builder
		 */
		public Builder withArtDecorBaseUrl(String artDecorBaseUrl) {
			this.artDecorBaseUrl = artDecorBaseUrl;
			return this;
		}

		/**
		 * With art decor doc template id.
		 *
		 * @param artDecorDocTemplateId
		 *            the art decor doc template id
		 * @return the builder
		 */
		public Builder withArtDecorDocTemplateId(String artDecorDocTemplateId) {
			this.artDecorDocTemplateId = artDecorDocTemplateId;
			return this;
		}

		/**
		 * With art decor prefix.
		 *
		 * @param artDecorPrefix
		 *            the art decor prefix
		 * @return the builder
		 */
		public Builder withArtDecorPrefix(String artDecorPrefix) {
			this.artDecorPrefix = artDecorPrefix;
			return this;
		}

		/**
		 * With target dir.
		 *
		 * @param targetDir
		 *            the target dir
		 * @return the builder
		 */
		public Builder withTargetDir(String targetDir) {
			this.targetDir = targetDir;
			return this;
		}

		/**
		 * With target namespace.
		 *
		 * @param targetNamespace
		 *            the target namespace
		 * @return the builder
		 */
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

	/** The art decor prefix. */
	private String artDecorPrefix;

	/** The art decor doc template id. */
	private String artDecorDocTemplateId;

	/** The target namespace. */
	private String targetNamespace;

	/** The target dir. */
	private String targetDir;

	/**
	 * Instantiates a new content profile config. Default constructor.
	 */
	public ContentProfileConfig() {
	}

	/**
	 * Instantiates a new content profile config.
	 *
	 * @param builder
	 *            the builder
	 */
	@Generated("SparkTools")
	private ContentProfileConfig(Builder builder) {
		this.artDecorBaseUrl = builder.artDecorBaseUrl;
		this.artDecorPrefix = builder.artDecorPrefix;
		this.artDecorDocTemplateId = builder.artDecorDocTemplateId;
		this.targetNamespace = builder.targetNamespace;
		this.targetDir = builder.targetDir;
	}

	/**
	 * <div class="en">Gets the ART-DECOR base URL. e.g.:
	 * https://art-decor.org/decor/services/. ART-DECOR prefix and template id
	 * will be added to this base URL to correctly access the templates.</div>
	 * <div class="de">Ruft die ART-DECOR-Basis-URL ab. Beispiel:
	 * https://art-decor.org/decor/services/. ART-DECOR Präfix und die Template
	 * ID werden zu dieser Basis-URL hinzugefügt, um den korrekten Zugriff auf
	 * die Templates zu ermöglichen.</div>
	 *
	 * @return the art decor base url
	 */
	public String getArtDecorBaseUrl() {
		return artDecorBaseUrl;
	}

	/**
	 * <div class="en">Gets the ART-DECOR document template id.</div>
	 * <div class="de">Ruft die ART-DECOR document template ID ab.</div>
	 *
	 *
	 * @return the art decor doc template id
	 */
	public String getArtDecorDocTemplateId() {
		return artDecorDocTemplateId;
	}

	/**
	 * <div class="en">Gets the ART-DECOR prefix.</div> <div class="de">Ruft den
	 * ART-DECOR Präfix ab.</div>
	 *
	 * @return the art decor prefix
	 */
	public String getArtDecorPrefix() {
		return artDecorPrefix;
	}

	/**
	 * <div class="en">Gets the target dir. That's where the Java class files
	 * will be generated to.</div> <div class="de">Ruft das Zielverzeichnis ab.
	 * Dorthin werden die Java-Klassendateien generiert.</div>
	 *
	 * @return the target dir
	 */
	public String getTargetDir() {
		return targetDir;
	}

	/**
	 * <div class="en">Gets the target namespace. That's the namespace to put in
	 * the generated Java class files.</div> <div class="de">Ruft den Ziel
	 * namespace ab. Dies ist der Namespace, der in die generierten
	 * Java-Klassendateien eingefügt werden soll.</div>
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
	 * <div class="de">Legt die ART-DECOR-Basis-URL fest. Beispiel:
	 * https://art-decor.org/decor/services/. ART-DECOR Präfix und die Template
	 * ID werden zu dieser Basis-URL hinzugefügt, um den korrekten Zugriff auf
	 * die Templates zu ermöglichen.</div>
	 *
	 * @param artDecorBaseUrl
	 *            the new art decor base url
	 */
	public void setArtDecorBaseUrl(String artDecorBaseUrl) {
		this.artDecorBaseUrl = artDecorBaseUrl;
	}

	/**
	 * <div class="en">Sets the ART-DECOR document template id.</div>
	 * <div class="de">Legt die ART-DECOR document template ID fest.</div>
	 *
	 * @param artDecorDocTemplateId
	 *            the new art decor doc template id
	 */
	public void setArtDecorDocTemplateId(String artDecorDocTemplateId) {
		this.artDecorDocTemplateId = artDecorDocTemplateId;
	}

	/**
	 * <div class="en">Sets the ART-DECOR prefix.</div> <div class="de">Legt den
	 * ART-DECOR Präfix fest.</div>
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
	 * will be generated to.</div> <div class="de">Legt das Zielverzeichnis
	 * fest. Dorthin werden die Java-Klassendateien generiert.</div>
	 *
	 * @param targetDir
	 *            the new target dir
	 */
	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	/**
	 * <div class="en">Sets the target namespace. That's the namespace to put in
	 * the generated Java class files.</div> <div class="de">Legt den Ziel
	 * namespace fest. Dies ist der Namespace, der in die generierten
	 * Java-Klassendateien eingefügt werden soll.</div>
	 *
	 * @param targetNamespace
	 *            the new target namespace
	 */
	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

}
