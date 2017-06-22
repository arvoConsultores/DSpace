/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.09 at 01:52:56 PM BST 
//

package org.orcid.jaxb.model.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}application-orcid"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}application-name"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}application-website"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}approval-date"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}scope-paths"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( propOrder = { "applicationOrcid", "applicationName", "applicationWebsite", "approvalDate", "scopePaths", "applicationGroupOrcid", "applicationGroupName"  })
@XmlRootElement(name = "application-summary")
public class ApplicationSummary implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "application-orcid", required = true)
    protected ApplicationOrcid applicationOrcid;
    @XmlElement(name = "application-name", required = true)
    protected ApplicationName applicationName;
    @XmlElement(name = "application-group-orcid", required = true)
    private ApplicationOrcid applicationGroupOrcid;
    @XmlElement(name = "application-group-name", required = true)
    private ApplicationName applicationGroupName;
    @XmlElement(name = "application-website", required = true)
    protected ApplicationWebsite applicationWebsite;
    @XmlElement(name = "approval-date", required = true)
    protected ApprovalDate approvalDate;
    @XmlElement(name = "scope-paths", required = true)
    protected ScopePaths scopePaths;

    /**
     * Gets the value of the applicationOrcid property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationOrcid }
     *     
     */
    public ApplicationOrcid getApplicationOrcid() {
        return applicationOrcid;
    }

    /**
     * Sets the value of the applicationOrcid property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationOrcid }
     *     
     */
    public void setApplicationOrcid(ApplicationOrcid value) {
        this.applicationOrcid = value;
    }

    /**
     * Gets the value of the applicationName property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationName }
     *     
     */
    public ApplicationName getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the value of the applicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationName }
     *     
     */
    public void setApplicationName(ApplicationName value) {
        this.applicationName = value;
    }

    /**
     * Gets the value of the applicationWebsite property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationWebsite }
     *     
     */
    public ApplicationWebsite getApplicationWebsite() {
        return applicationWebsite;
    }

    /**
     * Sets the value of the applicationWebsite property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationWebsite }
     *     
     */
    public void setApplicationWebsite(ApplicationWebsite value) {
        this.applicationWebsite = value;
    }

    /**
     * Gets the value of the approvalDate property.
     * 
     * @return
     *     possible object is
     *     {@link ApprovalDate }
     *     
     */
    public ApprovalDate getApprovalDate() {
        return approvalDate;
    }

    /**
     * Sets the value of the approvalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovalDate }
     *     
     */
    public void setApprovalDate(ApprovalDate value) {
        this.approvalDate = value;
    }

    /**
     * Gets the value of the scopePaths property.
     * 
     * @return
     *     possible object is
     *     {@link ScopePaths }
     *     
     */
    public ScopePaths getScopePaths() {
        return scopePaths;
    }

    /**
     * Sets the value of the scopePaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScopePaths }
     *     
     */
    public void setScopePaths(ScopePaths value) {
        this.scopePaths = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationSummary)) {
            return false;
        }

        ApplicationSummary that = (ApplicationSummary) o;

        if (applicationName != null ? !applicationName.equals(that.applicationName) : that.applicationName != null) {
            return false;
        }
        if (applicationOrcid != null ? !applicationOrcid.equals(that.applicationOrcid) : that.applicationOrcid != null) {
            return false;
        }
        if (applicationWebsite != null ? !applicationWebsite.equals(that.applicationWebsite) : that.applicationWebsite != null) {
            return false;
        }
        if (approvalDate != null ? !approvalDate.equals(that.approvalDate) : that.approvalDate != null) {
            return false;
        }
        if (scopePaths != null ? !scopePaths.equals(that.scopePaths) : that.scopePaths != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = applicationOrcid != null ? applicationOrcid.hashCode() : 0;
        result = 31 * result + (applicationName != null ? applicationName.hashCode() : 0);
        result = 31 * result + (applicationWebsite != null ? applicationWebsite.hashCode() : 0);
        result = 31 * result + (approvalDate != null ? approvalDate.hashCode() : 0);
        result = 31 * result + (scopePaths != null ? scopePaths.hashCode() : 0);
        return result;
    }

    public ApplicationOrcid getApplicationGroupOrcid() {
        return applicationGroupOrcid;
    }

    public void setApplicationGroupOrcid(ApplicationOrcid applicationGroupOrcid) {
        this.applicationGroupOrcid = applicationGroupOrcid;
    }

    public ApplicationName getApplicationGroupName() {
        return applicationGroupName;
    }

    public void setApplicationGroupName(ApplicationName applicationGroupName) {
        this.applicationGroupName = applicationGroupName;
    }
}
