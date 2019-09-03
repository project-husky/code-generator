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

public class CdaTemplate {

	private ArrayList<CdaAttribute> cdaAttributeList = new ArrayList<CdaAttribute>();

	private ArrayList<CdaElement> cdaElementList = new ArrayList<CdaElement>();

	private HashMap<CdaTemplate, ProcessModes> cdaTemplateList = new HashMap<CdaTemplate, ProcessModes>();

	private String dataType;

	private String description;

	private String id;

	private String name;

	// private CdaElement rootCdaElement;

	public void addCdaAttribute(CdaAttribute cdaAttribute) {
		cdaAttributeList.add(cdaAttribute);
	}

	public void addCdaElement(CdaElement cdaElement) {
		if (cdaElementList == null) {
			cdaElementList = new ArrayList<CdaElement>();
			// rootCdaElement = cdaElement;
		}
		cdaElementList.add(cdaElement);
	}

	public void addCdaTemplate(CdaTemplate cdaTemplate, ProcessModes processMode) {
		cdaTemplateList.put(cdaTemplate, processMode);
	}

	public ArrayList<CdaAttribute> getCdaAttributeList() {
		return cdaAttributeList;
	}

	public ArrayList<CdaElement> getCdaElementList() {
		return cdaElementList;
	}

	public HashMap<CdaTemplate, ProcessModes> getCdaTemplateList() {
		return cdaTemplateList;
	}

	public String getDataType() {
		return dataType;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// public CdaElement getRootCdaElement() {
	// return rootCdaElement;
	// }

	public void setCdaAttributeList(ArrayList<CdaAttribute> cdaAttributeList) {
		this.cdaAttributeList = cdaAttributeList;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
