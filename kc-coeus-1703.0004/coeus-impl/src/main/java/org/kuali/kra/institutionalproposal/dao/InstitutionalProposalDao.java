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
package org.kuali.kra.institutionalproposal.dao;

import org.kuali.coeus.sys.framework.rest.SearchResults;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Data access for institutional proposals
 */
public interface InstitutionalProposalDao {
    /**
     * Retrieves the proposal id for the given award
     * @param award the award to find the proposal id for
     * @return the proposal id, or null if nothing can be found
     */
    public Long getProposalId(Award award);

    public SearchResults<InstitutionalProposal> retrievePopulatedInstitutionalProposalByCriteria(Map<String, Object> fieldValues, Date updatedSince, Integer pageNum, Integer numPerPage);
    
    /**
     * 
     * @param startDate optional, can be null
     * @param endDate optional, can be null
     * @return a list of proposals between the dates if provided
     */
    List<InstitutionalProposal> getProposalsModifiedBetween(Date startDate, Date endDate);
}
