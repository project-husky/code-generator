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
package org.ehealth_connector.codegenerator.cda;

public class CdaAttribute {

	private String dataType;

	private String description;

	private String name;

	private String value;

	private String valueSetId;

	private CdaElement cdaElement;

	private CdaTemplate cdaTemplate;

	public CdaAttribute() {
	}

	public CdaElement getCdaElement() {
		return cdaElement;
	}

	public CdaTemplate getCdaTemplate() {
		return cdaTemplate;
	}

	public String getDataType() {
		return dataType;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getValueSetId() {
		return valueSetId;
	}

	public void setCdaElement(CdaElement cdaElement) {
		this.cdaElement = cdaElement;
	}

	public void setCdaTemplate(CdaTemplate cdaTemplate) {
		this.cdaTemplate = cdaTemplate;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setValueSetId(String valueSetId) {
		this.valueSetId = valueSetId;
	}

}
