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
 * <div class="en">This class contains all necessary element information to
 * generate the resulting Java Classes for an ART-DECOR CDA Template.</div>
 *
 * <div class="de">Diese Klasse enthält alle erforderlichen Element
 * Informationen, um die resultierenden Java-Klassen für eine
 * ART-DECOR-CDA-Vorlage zu generieren.</div>
 *
 */
public class CdaElement {

	/** The list of all attributes attached to this element. */
	private ArrayList<CdaAttribute> cdaAttributeList = new ArrayList<CdaAttribute>();

	/** The list of all elements declared in this element. */
	private ArrayList<CdaElement> childrenCdaElementList = new ArrayList<CdaElement>();

	/** The list of all templates declared in this element. */
	private HashMap<CdaTemplate, ProcessModes> cdaTemplateList = new HashMap<CdaTemplate, ProcessModes>();

	/** The data type of this element. */
	private String dataType;

	/** The description of this element. */
	private String description;

	/** The max occurs cardinality of this element. */
	private int maxOccurs;

	/** The min occurs cardinality of this element. */
	private int minOccurs;

	/** The XML name of this element. */
	private String xmlName;

	/** The Java name of this element. */
	private String javaName;

	/** The parent element. */
	private CdaElement parentCdaElement;

	/** The template in which this element is declared. */
	private CdaTemplate template;

	/**
	 * <div class="en">Instantiates a new element in the given parent element.
	 * For the root element, null is passed as argument.</div>
	 *
	 * <div class="de">Instanziiert ein neues Element im angegebenen
	 * übergeordneten Element. Für das Root-Element wird null als Argument
	 * übergeben.</div>
	 *
	 * @param parentCdaElement
	 *            the parent cda element
	 */
	public CdaElement(CdaElement parentCdaElement) {
		this.parentCdaElement = parentCdaElement;
	}

	/**
	 * /** <div class="en">Adds the given attribute.</div>
	 *
	 * <div class="de">Fügt das angegebene Attribut hinzu.</div>
	 *
	 * @param cdaAttribute
	 *            the cda attribute
	 */
	public void addAttribute(CdaAttribute cdaAttribute) {
		cdaAttributeList.add(cdaAttribute);
	}

	/**
	 * /** <div class="en">Adds the given template.</div>
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
	 * /** <div class="en">Adds the given element.</div>
	 *
	 * <div class="de">Fügt das angegebene Element hinzu.</div>
	 *
	 *
	 * @param cdaElement
	 *            the cda element
	 */
	public void addChild(CdaElement cdaElement) {
		if (cdaElement != null)
			childrenCdaElementList.add(cdaElement);
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
	 * <div class="en">Gets the element list.</div>
	 *
	 * <div class="de">Ruft die Elementliste ab.</div>
	 *
	 * @return the children cda element list
	 */
	public ArrayList<CdaElement> getChildrenCdaElementList() {
		return childrenCdaElementList;
	}

	/**
	 * <div class="en">Gets the data type of this element.</div>
	 *
	 * <div class="de">Ruft den Datentyp dieses Elements ab.</div>
	 *
	 * @return the data type
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <div class="en">Gets the description of this element.</div>
	 *
	 * <div class="de">Ruft die Beschreibung dieses Elements ab.</div>
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <div class="en">Gets the full Java name of this element. This ios for
	 * debugging purposes, only. It concatenates all eleement names in the
	 * element tree.</div>
	 *
	 * <div class="de">Ruft den vollständigen Java-Namen dieses Elements ab.
	 * Dies ist nur zu Debug-Zwecken. Es verkettet alle Elementnamen im
	 * Elementbaum.</div>
	 *
	 * @return the full java name
	 */
	public String getFullJavaName() {
		String retVal = getJavaName();
		CdaElement parent = getParentCdaElement();
		while (parent != null) {
			retVal = parent.getJavaName() + "." + retVal;
			parent = parent.getParentCdaElement();
		}
		return retVal;
	}

	/**
	 * <div class="en">Gets the full XML name of this element. This ios for
	 * debugging purposes, only. It concatenates all eleement names in the
	 * element tree.</div>
	 *
	 * <div class="de">Ruft den vollständigen XML namen dieses Elements ab. Dies
	 * ist nur zu Debug-Zwecken. Es verkettet alle Elementnamen im
	 * Elementbaum.</div>
	 *
	 * @return the full xml name
	 */
	public String getFullXmlName() {
		String retVal = getXmlName();
		CdaElement parent = getParentCdaElement();
		while (parent != null) {
			retVal = parent.getXmlName() + "." + retVal;
			parent = parent.getParentCdaElement();
		}
		return retVal;
	}

	/**
	 * <div class="en">Gets the Java name of this element.</div>
	 *
	 * <div class="de">Ruft den Java-Namen dieses Elements ab.</div>
	 *
	 * @return the java name
	 */
	public String getJavaName() {
		return javaName;
	}

	/**
	 * <div class="en">Gets the max occurs cardinality of this element.</div>
	 *
	 * <div class="de">Ruft die Kardinalität (max. Vorkommen) dieses Elements
	 * ab.</div>
	 *
	 * @return the max occurs
	 */
	public int getMaxOccurs() {
		return maxOccurs;
	}

	/**
	 * <div class="en">Gets the min occurs cardinality of this element.</div>
	 *
	 * <div class="de">Ruft die Kardinalität (min. Vorkommen) dieses Elements
	 * ab.</div>
	 *
	 * @return the min occurs
	 */
	public int getMinOccurs() {
		return minOccurs;
	}

	/**
	 * <div class="en">Gets the parent element.</div>
	 *
	 * <div class="de">Ruft das übergeordnete Element ab.</div>
	 *
	 * @return the parent cda element
	 */
	public CdaElement getParentCdaElement() {
		return parentCdaElement;
	}

	/**
	 * <div class="en">Gets the template in which this element is
	 * declared.</div>
	 *
	 * <div class="de">Ruft die Vorlage ab, in der dieses Element deklariert
	 * ist.</div>
	 *
	 * @return the template
	 */
	public CdaTemplate getTemplate() {
		return template;
	}

	/**
	 * <div class="en">Gets the XML name of this element.</div>
	 *
	 * <div class="de">Ruft den XML-Namen dieses Elements ab.</div>
	 *
	 * Gets the xml name.
	 *
	 * @return the xml name
	 */
	public String getXmlName() {
		return xmlName;
	}

	/**
	 * <div class="en">Sets the attribute list.</div>
	 *
	 * <div class="de">Legt die Attributliste fest.</div>
	 *
	 * @param cdaAttributeList
	 *            the new cda attribute list
	 */
	public void setCdaAttributeList(ArrayList<CdaAttribute> cdaAttributeList) {
		this.cdaAttributeList = cdaAttributeList;
	}

	/**
	 * <div class="en">Sets the element list.</div>
	 *
	 * <div class="de">Legt die Elementliste fest.</div>
	 *
	 * @param childrenCdaElementList
	 *            the new children cda element list
	 */
	public void setChildrenCdaElementList(ArrayList<CdaElement> childrenCdaElementList) {
		this.childrenCdaElementList = childrenCdaElementList;
	}

	/**
	 * <div class="en">Sets the data type of this element.</div>
	 *
	 * <div class="de">Legt den Datentyp dieses Elements fest.</div>
	 *
	 * @param dataType
	 *            the new data type
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * <div class="en">Sets the description of this element.</div>
	 *
	 * <div class="de">Legt die Beschreibung dieses Elements fest.</div>
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * <div class="en">Sets the Java name of this element.</div>
	 *
	 * <div class="de">Legt den Java Namen dieses Elements fest.</div>
	 *
	 * @param javaName
	 *            the new java name
	 */
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	/**
	 * <div class="en">Sets the max occurs cardinality of this element.</div>
	 *
	 * <div class="de">Legt die Kardinalität (max. Vorkommen) dieses Elements
	 * fest.</div>
	 *
	 * @param maxOccurs
	 *            the new max occurs
	 */
	public void setMaxOccurs(int maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	/**
	 * <div class="en">Sets the min occurs cardinality of this element.</div>
	 *
	 * <div class="de">Legt die Kardinalität (min. Vorkommen) dieses Elements
	 * fest.</div>
	 *
	 * @param minOccurs
	 *            the new min occurs
	 */
	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}

	/**
	 * <div class="en">Sets the parent element.</div>
	 *
	 * <div class="de">Legt das übergeordnete Element fest.</div>
	 *
	 * @param parentCdaElement
	 *            the new parent cda element
	 */
	public void setParentCdaElement(CdaElement parentCdaElement) {
		this.parentCdaElement = parentCdaElement;
	}

	/**
	 * <div class="en">Sets the template in which this element is
	 * declared.</div>
	 *
	 * <div class="de">Legt die Vorlage fest, in der dieses Element deklariert
	 * ist.</div>
	 *
	 * @param template
	 *            the new template
	 */
	public void setTemplate(CdaTemplate template) {
		this.template = template;
	}

	/**
	 * <div class="en">Sets the XML name of this element.</div>
	 *
	 * <div class="de">Legt den XML Namen dieses Elements fest.</div>
	 *
	 * @param xmlName
	 *            the new xml name
	 */
	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}
}
