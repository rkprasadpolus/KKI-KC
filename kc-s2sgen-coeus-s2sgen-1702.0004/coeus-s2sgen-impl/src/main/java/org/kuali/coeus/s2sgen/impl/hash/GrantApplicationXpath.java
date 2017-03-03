/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.s2sgen.impl.hash;

import org.kuali.coeus.s2sgen.impl.util.XPathExecutor;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.TransformerException;

/**
 * Grant application Xpath class.
 */
public class GrantApplicationXpath
{

    private static final String HASH_ALGORITHM = "glob:hashAlgorithm";
    private static final String SHA_1 = "SHA";

    public static final String GS_HEADER_XPATH = "/*[namespace-uri()='http://apply.grants.gov/system/MetaGrantApplication' and local-name()='GrantApplication']/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='GrantSubmissionHeader']";
    public static final String OPPORTUNITY_ID_XPATH = GS_HEADER_XPATH
        + "/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='OpportunityID']";
    public static final String CFDA_NUMBER_XPATH = GS_HEADER_XPATH
        + "/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='CFDANumber']";
    public static final String OPPORTUNITY_TITLE_XPATH = GS_HEADER_XPATH
        + "/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='OpportunityTitle']";
    public static final String CLOSING_DATE_XPATH = GS_HEADER_XPATH
        + "/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='ClosingDate']";
    public static final String AGENCY_NAME_XPATH = GS_HEADER_XPATH
        + "/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='AgencyName']";
    public static final String COMPETITION_ID_XPATH = GS_HEADER_XPATH
        + "/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='CompetitionID']";
    public static final String SUBMISSION_TITLE_XPATH = GS_HEADER_XPATH
        + "/*[namespace-uri()='http://apply.grants.gov/system/Header-V1.0' and local-name()='SubmissionTitle']";
    public static final String HASHVALUE = "glob:HashValue";
    public static final String FORMS_XPATH = "//*[local-name(.) = 'Forms' and namespace-uri(.) = 'http://apply.grants.gov/system/MetaGrantApplication']";
    public static final String HEADER_XPATH = "//*[local-name(.) = 'GrantSubmissionHeader' and namespace-uri(.) = 'http://apply.grants.gov/system/Header-V1.0']";
    public static final String HASH_XPATH = "//*[local-name(.) = 'GrantSubmissionHeader' and namespace-uri(.) = 'http://apply.grants.gov/system/Header-V1.0']/*[local-name(.) = 'HashValue' and namespace-uri(.) = 'http://apply.grants.gov/system/Global-V1.0']";
    public static final String GLOBAL_NS = "http://apply.grants.gov/system/Global-V1.0";

    private XPathExecutor executor;

    public GrantApplicationXpath(String xmlDoc)
        throws Exception
    {
        setExecutor(createExecutor(xmlDoc));

    }

    /**
     * @return the XPathExecutor.
     */
    public XPathExecutor getExecutor()
    {
        return executor;
    }

    /**
     * @param executor
     *            the XPathExecutor.
     */
    public void setExecutor(XPathExecutor executor)
    {
        this.executor = executor;
    }

    private XPathExecutor createExecutor(String xmlDoc)
        throws Exception
    {
        return new XPathExecutor(xmlDoc);
    }

    /**
     * Get the <code>&lt;grant:Forms&gt;</code> Node.
     * 
     * @return A DOM Node representing the <code>&lt;grant:Forms&gt;</code>
     *         tag in the XML document.
     */
    public Node getFormsNode()
        throws TransformerException
    {
        return getExecutor().getNode(FORMS_XPATH);
    }

    /**
     * Get the <code>&lt;header:GrantSubmissionHeader&gt;</code> Node.
     * 
     * @return A DOM Node representing the
     *         <code>&lt;header:GrantSubmissionHeader&gt;</code> tag in the
     *         XML document.
     */
    public Node getHeaderNode()
        throws TransformerException
    {
        return getExecutor().getNode(HEADER_XPATH);
    }

    /**
     * Get the <code>&lt;glob:HashValue&gt;</code> Node. If the Node does not
     * exist, create one and add it to the Document.
     * 
     * @return A DOM Node representing the <code>&lt;glob:HashValue&gt;</code>
     *         tag in the XML document.
     */
    public Node getHashNode()
        throws TransformerException
    {
        Node hashNode = getExecutor().getNode(HASH_XPATH);
        if (hashNode == null)
        {
            hashNode = getExecutor().getDoc().createElementNS(GLOBAL_NS,
                HASHVALUE);
            Attr algorithm = getExecutor().getDoc().createAttributeNS(GLOBAL_NS, HASH_ALGORITHM);
            algorithm.setValue(SHA_1);
            ((Element) hashNode).setAttributeNodeNS(algorithm);
            hashNode.appendChild(getExecutor().getDoc().createTextNode(""));
            Node header = getHeaderNode();
            header.insertBefore(hashNode, header.getFirstChild());
        }
        return hashNode;
    }

    /**
     * Get the value in the <code>&lt;glob:HashValue&gt;</code> element.
     * 
     * @return The value that is currently in the
     *         <code>&lt;glob:HashValue&gt;</code> element or
     *         <code>null</code> if the element does not exist or is empty.
     *
     */
    public String getHeaderHashValue()
        throws Exception
    {
        return getExecutor().execute(HASH_XPATH);
    }

    /**
     * @return Returns the agencyName.
     */
    public String getAgencyName()
        throws Exception
    {
        return getExecutor().execute(AGENCY_NAME_XPATH);
    }

    /**
     * @return the string of the CFDA Number.
     */
    public String getCfdaNumber()
        throws Exception
    {
        return getExecutor().execute(CFDA_NUMBER_XPATH);
    }

    /**
     * @return the string of the closing date.
     */
    public String getClosingDate()
        throws Exception
    {
        return getExecutor().execute(CLOSING_DATE_XPATH);
    }

    /**
     * @return the string of the opportunity ID.
     */
    public String getOpportunityId()
        throws Exception
    {
        return getExecutor().execute(OPPORTUNITY_ID_XPATH);
    }

    /**
     * @return the string of the opportunity title.
     *
     */
    public String getOpportunityTitle()
        throws Exception
    {
        return getExecutor().execute(OPPORTUNITY_TITLE_XPATH);
    }

    /**
     * @return the string of the competition ID.
     *
     */
    public String getCompetitionId()
        throws Exception
    {
        return getExecutor().execute(COMPETITION_ID_XPATH);
    }

    /**
     * @return returns the submissionTitle.
     *
     */
    public String getSubmissionTitle()
        throws Exception
    {
        return getExecutor().execute(SUBMISSION_TITLE_XPATH);
    }
}
