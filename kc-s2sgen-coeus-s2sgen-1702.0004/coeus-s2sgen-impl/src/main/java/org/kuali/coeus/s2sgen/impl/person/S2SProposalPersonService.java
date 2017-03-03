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
package org.kuali.coeus.s2sgen.impl.person;

import org.kuali.coeus.common.api.person.attr.CitizenshipType;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;

import java.util.List;

public interface S2SProposalPersonService {

    /**
     *
     * This method limits the number of key persons to n, returns list of key persons.
     *
     * @param keyPersons
     *            list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     * @param n
     *            number of key persons that are considered as not extra persons
     * @return list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     */
    List<ProposalPersonContract> getNKeyPersons(List<? extends ProposalPersonContract> keyPersons, int n);

    /**
     *
     * This method is to get PrincipalInvestigator from person list
     *
     * @param pdDoc
     *            Proposal development document.
     * @return ProposalPerson PrincipalInvestigator for the proposal.
     */
    ProposalPersonContract getPrincipalInvestigator(
            ProposalDevelopmentDocumentContract pdDoc);

    /**
     * Finds all the co-investigators associated with the provided pdDoc.
     * @return List of Co-Investigators {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}.
     */

    List<ProposalPersonContract> getCoInvestigators(ProposalDevelopmentDocumentContract pdDoc);

    /**
     * Finds all the key Person associated with the provided pdDoc.
     * @return List of Key Persons {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}.
     */

    List<ProposalPersonContract> getKeyPersons (ProposalDevelopmentDocumentContract pdDoc);

    /**
     *
     * This method is used to get the citizenship from either warehouse or from person custom element
     */
    CitizenshipType getCitizenship(ProposalPersonContract proposalPerson);
}
