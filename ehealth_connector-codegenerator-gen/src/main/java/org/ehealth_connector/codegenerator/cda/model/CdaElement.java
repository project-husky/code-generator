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

public class CdaElement {

	private ArrayList<CdaAttribute> cdaAttributeList = new ArrayList<CdaAttribute>();

	private ArrayList<CdaElement> childrenCdaElementList = new ArrayList<CdaElement>();

	private HashMap<CdaTemplate, ProcessModes> cdaTemplateList = new HashMap<CdaTemplate, ProcessModes>();

	private String dataType;

	private String description;

	private int maxOccurs;

	private int minOccurs;

	private String xmlName;

	private String javaName;

	private CdaElement parentCdaElement;

	private CdaTemplate template;

	private String targetXmlElement;

	public CdaElement(CdaElement parentCdaElement) {
		this.parentCdaElement = parentCdaElement;
	}

	public void addAttribute(CdaAttribute cdaAttribute) {
		cdaAttributeList.add(cdaAttribute);
	}

	public void addCdaTemplate(CdaTemplate cdaTemplate, ProcessModes processMode) {
		cdaTemplateList.put(cdaTemplate, processMode);
	}

	public void addChild(CdaElement cdaElement) {
		if (cdaElement != null)
			childrenCdaElementList.add(cdaElement);
	}

	public ArrayList<CdaAttribute> getCdaAttributeList() {
		return cdaAttributeList;
	}

	public HashMap<CdaTemplate, ProcessModes> getCdaTemplateList() {
		return cdaTemplateList;
	}

	public ArrayList<CdaElement> getChildrenCdaElementList() {
		return childrenCdaElementList;
	}

	public String getDataType() {
		return dataType;
	}

	public String getDescription() {
		return description;
	}

	public String getFullJavaName() {
		String retVal = getJavaName();
		CdaElement parent = getParentCdaElement();
		while (parent != null) {
			retVal = parent.getJavaName() + "." + retVal;
			parent = parent.getParentCdaElement();
		}
		return retVal;
	}

	public String getFullXmlName() {
		String retVal = getXmlName();
		CdaElement parent = getParentCdaElement();
		while (parent != null) {
			retVal = parent.getXmlName() + "." + retVal;
			parent = parent.getParentCdaElement();
		}
		return retVal;
	}

	public String getJavaName() {
		return javaName;
	}

	public int getMaxOccurs() {
		return maxOccurs;
	}

	public int getMinOccurs() {
		return minOccurs;
	}

	public CdaElement getParentCdaElement() {
		return parentCdaElement;
	}

	public String getTargetXmlElement() {
		return targetXmlElement;
	}

	public CdaTemplate getTemplate() {
		return template;
	}

	public String getXmlName() {
		return xmlName;
	}

	public void setCdaAttributeList(ArrayList<CdaAttribute> cdaAttributeList) {
		this.cdaAttributeList = cdaAttributeList;
	}

	public void setChildrenCdaElementList(ArrayList<CdaElement> childrenCdaElementList) {
		this.childrenCdaElementList = childrenCdaElementList;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public void setMaxOccurs(int maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}

	public void setParentCdaElement(CdaElement parentCdaElement) {
		this.parentCdaElement = parentCdaElement;
		if (parentCdaElement == this)
			System.out.println("Stop here");
	}

	public void setTargetXmlElement(String targetXmlElement) {
		this.targetXmlElement = targetXmlElement;
	}

	public void setTemplate(CdaTemplate template) {
		this.template = template;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}
}
