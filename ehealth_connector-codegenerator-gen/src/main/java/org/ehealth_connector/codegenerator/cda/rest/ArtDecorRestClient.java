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
package org.ehealth_connector.codegenerator.cda.rest;

import java.io.InputStream;

/**
 *
 * TODO: This class acts as “downloader” of all used templates by a given
 * Document Template. It recursively goes through all includes and contains of
 * an ART-DECOR template.
 *
 */
public class ArtDecorRestClient {

	/**
	 * This method gets the XML as InputStream for the given template from ART
	 * DECOR and will be recursively called for each include or contains
	 * statement within that template that references to another template.
	 *
	 * @param baseUrl
	 *            the base url
	 * @param prefix
	 *            the prefix
	 * @param templateId
	 *            the template id
	 * @return the art decor template
	 */
	public InputStream getArtDecorTemplate(String baseUrl, String prefix, String templateId) {
		// TODO
		return null;
	}

}
