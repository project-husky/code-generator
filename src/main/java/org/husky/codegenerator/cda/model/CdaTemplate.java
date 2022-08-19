/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.cda.model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
    private final List<CdaAttribute> cdaAttributeList = new ArrayList<>();

    /** The list of all elements declared in this template. */
    private List<CdaElement> cdaElementList = new ArrayList<>();

    /** The data type of this template. */
    private String dataType;

    /** The description for this template. */
    private String description;

    /** The id (OID) of this template. */
    private String id;

    /** The name of this template. */
    private String name;

    /** The version label. */
    private String versionLabel;

    /** The effective date. */
    private String effectiveDate;

    /** The template project. */
    private String project;

    /** The template status. */
    private String status;

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
            cdaElementList = new ArrayList<>();
        }
        cdaElementList.add(cdaElement);
    }

    /**
     * <div class="en">Gets the attribute list.</div>
     *
     * <div class="de">Ruft die Attributliste ab.</div>
     *
     * @return the cda attribute list
     */
    public List<CdaAttribute> getCdaAttributeList() {
        return cdaAttributeList;
    }

    /**
     * <div class="en">Gets the element list.</div>
     *
     * <div class="de">Ruft die Elementliste ab.</div>
     *
     * @return the cda element list
     */
    public List<CdaElement> getCdaElementList() {
        return cdaElementList;
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
     * <div class="en">Gets the version label of this template.</div>
     *
     * @return the version label
     */
    public String getVersionLabel() {
        return versionLabel;
    }

    /**
     * <div class="en">Gets the effective date of this template.</div>
     *
     * @return the effective date
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * <div class="en">Gets the status of this template.</div>
     *
     * @return the status
     */
    public String getStatus() {
        return status;
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

    /**
     * <div class="en">Sets the version label of this template.</div>
     *
     * @param versionLabel
     *            the new version label
     */
    public void setVersionLabel(String versionLabel) {
        this.versionLabel = versionLabel;
    }

    /**
     * <div class="en">Sets the effective date of this template.</div>
     *
     * @param effectiveDate
     *            the new effective date
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * <div class="en">Sets the status of this template.</div>
     *
     * @param status
     *            the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(final String project) {
        this.project = project;
    }

    public String getTemplateUrl() {
        return "https://art-decor.org/art-decor/decor-templates--" +
                this.project +
                "?id=" +
                this.id +
                "&amp;effectiveTime=" +
                URLEncoder.encode(this.effectiveDate, StandardCharsets.UTF_8);
    }
}
