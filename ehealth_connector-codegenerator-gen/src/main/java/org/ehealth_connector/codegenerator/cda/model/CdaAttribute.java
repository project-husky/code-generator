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

import org.ehealth_connector.common.Code;

/**
 * <div class="en">This class contains all necessary attribte information to
 * generate the resulting Java Classes for an ART-DECOR CDA Template.</div>
 *
 * <div class="de">Diese Klasse enthält alle erforderlichen Attribut
 * Informationen, um die resultierenden Java-Klassen für eine
 * ART-DECOR-CDA-Vorlage zu generieren..</div>
 *
 */
public class CdaAttribute {

	/** The data type of this attribute. */
	private String dataType;

	/** The description of this attribute. */
	private String description;

	/** The name of this attribute. */
	private String name = null;

	/** The value of this attribute. */
	private String value;

	/** The value set id of this attribute. */
	private String valueSetId;

	/** The code of this attribute. */
	private Code code = null;

	/** The list code of codes of this attribute. */
	private ArrayList<Code> codes = null;

	/** The element, in which this attribute is declared. */
	private CdaElement cdaElement;

	/** The template, in which this attribute is declared. */
	private CdaTemplate cdaTemplate;

	/** True, if it is a vocabulary attribute. False otherwise. */
	private boolean isVocab;

	/**
	 * <div class="en">Instantiates a new attribute. Default constructor.</div>
	 *
	 * <div class="de">Instanziiert ein neues Attribut.
	 * Standardkonstruktor.</div>
	 *
	 */
	public CdaAttribute() {
	}

	/**
	 * <div class="en">Adds the code of this attribute.</div>
	 *
	 * <div class="de">Fügt den Code dieses Attributs hinzu.</div>
	 *
	 * @param code
	 *            the new code
	 */
	public void addCode(Code code) {
		if (this.codes == null)
			this.codes = new ArrayList<Code>();
		this.codes.add(code);
	}

	/**
	 * <div class="en">Gets the element, in which this attribute is
	 * declared.</div>
	 *
	 * <div class="de">Ruft das Element ab, in dem dieses Attribut deklariert
	 * ist.</div>
	 *
	 * @return the cda element
	 */
	public CdaElement getCdaElement() {
		return cdaElement;
	}

	/**
	 * <div class="en">Gets the template, in which this attribute is
	 * declared.</div>
	 *
	 * <div class="de">Ruft das Template ab, in dem dieses Attribut deklariert
	 * ist.</div>
	 *
	 * @return the cda template
	 */
	public CdaTemplate getCdaTemplate() {
		return cdaTemplate;
	}

	/**
	 * <div class="en">Gets the code of this attribute.</div>
	 *
	 * <div class="de">Ruft den Code dieses Attributs ab.</div>
	 *
	 * @return the code
	 */
	public Code getCode() {
		return code;
	}

	/**
	 * <div class="en">Gets the code of this attribute.</div>
	 *
	 * <div class="de">Ruft den Code dieses Attributs ab.</div>
	 *
	 * @return the code
	 */
	public ArrayList<Code> getCodeList() {
		return codes;
	}

	/**
	 * <div class="en">Gets the data type of this attribute.</div>
	 *
	 * <div class="de">Ruft den Datentyp dieses Attributs ab.</div>
	 *
	 * @return the data type
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <div class="en">Gets the description of this attribute.</div>
	 *
	 * <div class="de">Ruft die Beschreibung dieses Attributs ab.</div>
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <div class="en">Gets the name of this attribute.</div>
	 *
	 * <div class="de">Ruft den Namen dieses Attributs ab.</div>
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <div class="en">Gets the value of this attribute.</div>
	 *
	 * <div class="de">Ruft den Wert dieses Attributs ab.</div>
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <div class="en">Gets the value set id of this attribute.</div>
	 *
	 * <div class="de">Ruft Value-Set ID dieses Attributs ab.</div>
	 *
	 * @return the value set id
	 */
	public String getValueSetId() {
		return valueSetId;
	}

	/**
	 * <div class="en">True, if it is a vocabulary attribute. False
	 * otherwise.</div>
	 *
	 * <div class="de">True, wenn es ein vocabulary Attribut ist. Sonst
	 * false.</div>
	 *
	 * @return true, if is vocab
	 */
	public boolean isVocab() {
		return isVocab;
	}

	/**
	 * <div class="en">Sets the element, in which this attribute is
	 * declared.</div>
	 *
	 * <div class="de">Legt das Element fest, in dem dieses Attribut deklariert
	 * ist.</div>
	 *
	 * @param cdaElement
	 *            the new cda element
	 */
	public void setCdaElement(CdaElement cdaElement) {
		this.cdaElement = cdaElement;
	}

	/**
	 * <div class="en">Sets the template, in which this attribute is
	 * declared.</div>
	 *
	 * <div class="de">Legt das Template fest, in dem dieses Attribut deklariert
	 * ist.</div>
	 *
	 * @param cdaTemplate
	 *            the new cda template
	 */
	public void setCdaTemplate(CdaTemplate cdaTemplate) {
		this.cdaTemplate = cdaTemplate;
	}

	/**
	 * <div class="en">Sets the code of this attribute.</div>
	 *
	 * <div class="de">Legt den Code dieses Attributs fest.</div>
	 *
	 * @param code
	 *            the new code
	 */
	public void setCode(Code code) {
		this.code = code;
	}

	/**
	 * <div class="en">Sets the data type of this attribute.</div>
	 *
	 * <div class="de">Legt den Datentyp dieses Attributs fest.</div>
	 *
	 * @param dataType
	 *            the new data type
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * <div class="en">Sets the description of this attribute.</div>
	 *
	 * <div class="de">Legt die Beschreibung dieses Attributs fest.</div>
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * <div class="en">Sets the name of this attribute.</div>
	 *
	 * <div class="de">Legt den Namen dieses Attributs fest.</div>
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <div class="en">Sets the value of this attribute.</div>
	 *
	 * <div class="de">Legt den Wert dieses Attributs fest.</div>
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * <div class="en">Sets the value set id of this attribute.</div>
	 *
	 * <div class="de">Legt die Value-Set ID dieses Attributs fest.</div>
	 *
	 * @param valueSetId
	 *            the new value set id
	 */
	public void setValueSetId(String valueSetId) {
		this.valueSetId = valueSetId;
	}

	/**
	 * <div class="en">Sets the vocab flag: True, if it is a vocabulary
	 * attribute. False otherwise.</div>
	 *
	 * <div class="de">Setzt das Vokabular-Flag: True, wenn es ein vocabulary
	 * Attribut ist. Sonst false.</div>
	 *
	 *
	 *
	 * @param isVocab
	 *            the new vocab
	 */
	public void setVocab(boolean isVocab) {
		this.isVocab = isVocab;
	}

}
