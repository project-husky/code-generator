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

import java.util.ArrayList;

import javax.annotation.Generated;

public class CdaElement {

	/**
	 * Builder to build {@link CdaElement}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String dataType;
		private String description;
		private String name;
		private String targetXmlElement;

		private Builder() {
		}

		public CdaElement build() {
			return new CdaElement(this);
		}

		public Builder withDataType(String dataType) {
			this.dataType = dataType;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withTargetXmlElement(String targetXmlElement) {
			this.targetXmlElement = targetXmlElement;
			return this;
		}
	}

	/**
	 * Creates builder to build {@link CdaElement}.
	 *
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	private CdaElement parentCdaElement;

	private ArrayList<CdaElement> childrenCdaElementList = new ArrayList<CdaElement>();

	private String dataType;

	private String description;

	private String name;

	private String targetXmlElement;

	@Generated("SparkTools")
	private CdaElement(Builder builder) {
		this.dataType = builder.dataType;
		this.description = builder.description;
		this.name = builder.name;
		this.targetXmlElement = builder.targetXmlElement;
	}

	public CdaElement(CdaElement parentCdaElement) {
		this.parentCdaElement = parentCdaElement;
	}

	public void addChild(CdaElement cdaElement) {
		if (cdaElement != null)
			childrenCdaElementList.add(cdaElement);
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

	public String getName() {
		return name;
	}

	public CdaElement getParentCdaElement() {
		return parentCdaElement;
	}

	public String getTargetXmlElement() {
		return targetXmlElement;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setParentCdaElement(CdaElement parentCdaElement) {
		this.parentCdaElement = parentCdaElement;
	}

	public void setTargetXmlElement(String targetXmlElement) {
		this.targetXmlElement = targetXmlElement;
	}

	// private String name;
	// private String elementName;
	// private String hl7ElementName;
	// private String hl7XmlType;
	// private String hl7SchemaType;
	// private String simpleType;
	//
	// private int minOccurs = 1;
	//
	// private int maxOccurs = 1;
	//
	// private String assertXPath;
	//
	// private String reportXPath;
	// private String desc;
	// private List<CdaElement> components = null;
	// public HashMap<String, String> fixedContents = new HashMap<String,
	// String>();
	//
	// public CdaElement(String name) {
	// this.elementName = name;
	// this.setName(name.replace("hl7:", ""));
	// }
	//
	// public void addComponent(CdaElement component) {
	// if (components == null)
	// components = new ArrayList<CdaElement>();
	// components.add(component);
	// }
	//
	// public void addFixedContent(String attrName, String attrValue) {
	// fixedContents.put(attrName, attrValue);
	// }
	//
	// public String getAssertXPath() {
	// return assertXPath;
	// }
	//
	// public String getDesc() {
	// return desc;
	// }
	//
	// public String getElementName() {
	// return elementName;
	// }
	//
	// public String getHl7ElementName() {
	// return hl7ElementName;
	// }
	//
	// public String getHl7SchemaType() {
	// return hl7SchemaType;
	// }
	//
	// public String getHl7XmlType() {
	// return hl7XmlType;
	// }
	//
	// public int getMaxOccurs() {
	// return maxOccurs;
	// }
	//
	// public int getMinOccurs() {
	// return minOccurs;
	// }
	//
	// public String getName() {
	// return name;
	// }
	//
	// public String getReportXPath() {
	// return reportXPath;
	// }
	//
	// public String getSimpleType() {
	// return simpleType;
	// }
	//
	// public List<CdaElement> listComponents() {
	// return components;
	// }
	//
	// public void setAssertXPath(String assertXPath) {
	// this.assertXPath = assertXPath;
	// }
	//
	// public void setDesc(String desc) {
	// this.desc = desc;
	// }
	//
	// public void setElementName(String elementName) {
	// this.elementName = elementName;
	// }
	//
	// public void setHl7ElementName(String hl7ElementName) {
	// this.hl7ElementName = hl7ElementName;
	// }
	//
	// public void setHl7SchemaType(String hl7SchemaType) {
	// this.hl7SchemaType = hl7SchemaType;
	// }
	//
	// public void setHl7XmlType(String hl7Type) {
	// this.hl7XmlType = hl7Type;
	// switch (hl7Type) {
	//
	// // TODO This needs to become dynamic
	// // case "Cdachlrep_header_author":
	// //
	// setHl7SchemaType("org.ehealth_connector.cda.schema.POCDMT000040Author");
	// // break;
	// // // TODO This needs to become dynamic
	// // case "Chpalm_entry_laboratoryresultsvalidator":
	// //
	// setHl7SchemaType("org.ehealth_connector.cda.schema.POCDMT000040Authenticator");
	// // break;
	// //
	// // case "AD":
	// // setHl7SchemaType("org.ehealth_connector.cda.schema.AD");
	// // setSimpleType(AddressBaseType.class);
	// // break;
	// // case "CE":
	// // setHl7SchemaType("org.ehealth_connector.cda.schema.CE");
	// // setSimpleType(CodeBaseType.class);
	// // break;
	// // case "II":
	// // setHl7SchemaType("org.ehealth_connector.cda.schema.II");
	// // setSimpleType(IdentificatorBaseType.class);
	// // break;
	// // case "TEL":
	// // setHl7SchemaType("org.ehealth_connector.cda.schema.TEL");
	// // setSimpleType(TelecomBaseType.class);
	// // break;
	// // case "TS.CH.TZ":
	// // setHl7SchemaType("org.ehealth_connector.cda.schema.TS");
	// // setSimpleType(java.time.LocalDateTime.class);
	// // break;
	// default:
	// throw new NotImplementedException(
	// "Base Class name not implemented, yet. elementName: " + elementName);
	// // if ("".contentEquals(hl7Type)) {
	// // setHl7SchemaType("org.ehealth_connector.cda.schema.ST");
	// // setSimpleType("String");
	// // } else {
	// // // setHl7SchemaType("TODO-setHl7Type-Default");
	// // setSimpleType(hl7Type);
	// // }
	// // break;
	// }
	//
	// }
	//
	// public void setMaxOccurs(int maxOccurs) {
	// this.maxOccurs = maxOccurs;
	// }
	//
	// public void setMinOccurs(int minOccurs) {
	// this.minOccurs = minOccurs;
	// }
	//
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public void setReportXPath(String reportXPath) {
	// this.reportXPath = reportXPath;
	// }
	//
	// private void setSimpleType(String type) {
	//
	// // TODO handle unknown types...
	// if (type == null)
	// type = "String";
	// if ("".contentEquals(type))
	// type = "String";
	//
	// this.simpleType = type;
	//
	// }
	//
	// public void setSimpleType(Type simpleType) {
	// this.simpleType = simpleType.getTypeName();
	// }

}
