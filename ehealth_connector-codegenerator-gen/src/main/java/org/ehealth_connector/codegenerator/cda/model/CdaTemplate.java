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
package org.ehealth_connector.codegenerator.cda.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.ehealth_connector.codegenerator.cda.enums.ProcessModes;

/**
 * <div class="en">This class contains all necessary template information to
 * generate the resulting Java Classes for an ART-DECOR CDA Template.</div>
 *
 * <div class="de">Diese Klasse enthält alle erforderlichen Template
 * Informationen, um die resultierenden Java-Klassen für eine
 * ART-DECOR-CDA-Vorlage zu generieren.</div>
 *
 */
public class CdaTemplate {

	/** The list of all attributes attached to this template. */
	private ArrayList<CdaAttribute> cdaAttributeList = new ArrayList<CdaAttribute>();

	/** The list of all elements declared in this template. */
	private ArrayList<CdaElement> cdaElementList = new ArrayList<CdaElement>();

	/** The list of all templates declared in this template. */
	private HashMap<CdaTemplate, ProcessModes> cdaTemplateList = new HashMap<CdaTemplate, ProcessModes>();

	/** The data type of this template. */
	private String dataType;

	/** The description for this template. */
	private String description;

	/** The id (OID) of this template. */
	private String id;

	/** The name of this template. */
	private String name;

	/**
	 * <div class="en">Adds the given attribute.</div>
	 *
	 * <div class="de">Fügt das angegebene Attribut hinzu.</div>
	 *
	 * @param cdaAttribute
	 *            the cda attribute
	 */
	public void addCdaAttribute(CdaAttribute cdaAttribute) {
		cdaAttributeList.add(cdaAttribute);
	}

	/**
	 * <div class="en">Adds the given element.</div>
	 *
	 * <div class="de">Fügt das angegebene Element hinzu.</div>
	 *
	 * @param cdaElement
	 *            the cda element
	 */
	public void addCdaElement(CdaElement cdaElement) {
		if (cdaElementList == null) {
			cdaElementList = new ArrayList<CdaElement>();
		}
		cdaElementList.add(cdaElement);
	}

	/**
	 * <div class="en">Adds the given template.</div>
	 *
	 * <div class="de">Fügt das angegebene Template hinzu.</div>
	 *
	 * @param cdaTemplate
	 *            the cda template
	 * @param processMode
	 *            the process mode
	 */
	public void addCdaTemplate(CdaTemplate cdaTemplate, ProcessModes processMode) {
		cdaTemplateList.put(cdaTemplate, processMode);
	}

	/**
	 * <div class="en">Gets the attribute list.</div>
	 *
	 * <div class="de">Ruft die Attributliste ab.</div>
	 *
	 * @return the cda attribute list
	 */
	public ArrayList<CdaAttribute> getCdaAttributeList() {
		return cdaAttributeList;
	}

	/**
	 * <div class="en">Gets the element list.</div>
	 *
	 * <div class="de">Ruft die Elementliste ab.</div>
	 *
	 * @return the cda element list
	 */
	public ArrayList<CdaElement> getCdaElementList() {
		return cdaElementList;
	}

	/**
	 * <div class="en">Gets the template list.</div>
	 *
	 * <div class="de">Ruft die Templateliste ab.</div>
	 *
	 * @return the cda template list
	 */
	public HashMap<CdaTemplate, ProcessModes> getCdaTemplateList() {
		return cdaTemplateList;
	}

	/**
	 * <div class="en">Gets the data type of this template.</div>
	 *
	 * <div class="de">Ruft den Datentyp dieser Vorlage ab.</div>
	 *
	 * @return the data type
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <div class="en">Gets the description of this template.</div>
	 *
	 * <div class="de">Ruft die Beschreibung dieser Vorlage ab.</div>
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <div class="en">Gets the id (OID) of this template.</div>
	 *
	 * <div class="de">Ruft die ID (OID) dieser Vorlage ab.</div>
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * <div class="en">Gets the name of this template.</div>
	 *
	 * <div class="de">Ruft den Namen dieser Vorlage ab.</div>
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <div class="en">Sets the data type of this template.</div>
	 *
	 * <div class="de">Legt den Datentyp dieser Vorlage fest.</div>
	 *
	 * @param dataType
	 *            the new data type
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * <div class="en">Sets the description of this template.</div>
	 *
	 * <div class="de">Legt die Beschreibung Vorlage fest.</div>
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * <div class="en">Sets the id (OID) of this template.</div>
	 *
	 * <div class="de">Legt die ID (OID) dieser Vorlage fest.</div>
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <div class="en">Sets the name of this template.</div>
	 *
	 * <div class="de">Legt den Namen dieser Vorlage fest.</div>
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
