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
package org.kuali.coeus.instprop.impl.api;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rest.SearchResults;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;

public class InstitutionalProposalSummaryControllerTest {
	
	private static final String PERSON_ID123 = "personId123";
	private InstitutionalProposalController institutionalProposalSummaryController;
	private Award award1;
	private Award award2;
	private InstitutionalProposalBoLite proposalLite1;
	private InstitutionalProposalBoLite proposalLite2;
	private InstitutionalProposal proposal1;
	private InstitutionalProposal proposal2;
	private InstitutionalProposalPerson pi;
	private KcPerson piPerson;
	private Sponsor sponsor;
	private List<InstitutionalProposal> proposals;
	
	@Before
	public void setup() {
		sponsor = new Sponsor();
		sponsor.setSponsorCode("000001");
		sponsor.setAcronym("TSC");
		sponsor.setSponsorName("TestSponsorCode");
		
		piPerson = new KcPerson() {
			String personId;
			public String getPersonId() {
				return personId;
			}
			public void setPersonId(String personId) {
				this.personId = personId;
			}
			public String getAddressLine1() {
				return "123 Nowhere";
			}
			public String getAddressLine2() {
				return null;
			}
			public String getAddressLine3() {
				return null;
			}
			public String getCity() {
				return "Nowhere";
			}
			public String getState() {
				return "NA";
			}
			public String getPostalCode() {
				return "55555";
			}
			public String getUserName() {
				return "Foobar";
			}
			public String getEmailAddress() {
				return "noarealemail@nowhere.com";
			}
		};
		piPerson.setPersonId(PERSON_ID123);
		pi = new InstitutionalProposalPerson() {
			public String getProjectRole() {
				return "Principal Investigator";
			}
			public String getEmailAddress() {
				return "test@nowhere.com";
			}
		};
		pi.setPerson(piPerson);
		pi.setFullName("Foobar");
		pi.setRoleCode("PI");
		
		award1 = new Award();
		award1.setAwardId(1L);
		award1.setAwardNumber("000001-00001");
		award1.setSequenceNumber(1);
		
		award2 = new Award();
		award2.setAwardId(1L);
		award2.setAwardNumber("000001-00001");
		award2.setSequenceNumber(1);
		
		proposalLite1 = new InstitutionalProposalBoLite();
		proposalLite1.setProposalId(1L);
		proposalLite1.setProposalNumber("123");
		proposalLite1.setSequenceNumber(1);
		
		proposalLite2 = new InstitutionalProposalBoLite();
		proposalLite2.setProposalId(2L);
		proposalLite2.setProposalNumber("345");
		proposalLite2.setSequenceNumber(2);
		
		proposal1 = buildTestProposal(proposalLite1);
		proposal2 = buildTestProposal(proposalLite2);
		
		proposals = new ArrayList<>();
		proposals.add(proposal1);
		proposals.add(proposal2);
		
	}

	InstitutionalProposal buildTestProposal(InstitutionalProposalBoLite proposalLite) {
		InstitutionalProposal proposal1;
		proposal1 = new InstitutionalProposal() {
			protected void calculateFiscalMonthAndYearFields() { }
		};
		proposal1.getProjectPersons().add(pi);
		proposal1.setSponsor(sponsor);
		AwardFundingProposal fp1 = new AwardFundingProposal();
		fp1.setProposal(proposalLite);
		fp1.setAward(award1);
		proposal1.getAwardFundingProposals().add(fp1);
		AwardFundingProposal fp2 = new AwardFundingProposal();
		fp2.setProposal(proposalLite);
		fp2.setAward(award2);
		proposal1.getAwardFundingProposals().add(fp2);
		return proposal1;
	}
	
	@Test
	public void testInstPropSummary() {
		institutionalProposalSummaryController = new InstitutionalProposalController() {

			@Override
			protected void assertUserHasReadAccess() {
				//no op
			}

			@Override
			SearchResults<InstitutionalProposal> getProposals(Date updatedSince, Integer page, Integer numberPerPage) {
				SearchResults<InstitutionalProposal> result = new SearchResults<>();
				result.setResults(proposals);
				result.setTotalResults(2);
				return result;
			}
		};
		
		InstitutionalProposalResults result = institutionalProposalSummaryController.getInstitutionalProposalSummary(null, null, null);
		assertEquals(2, result.getTotalFound().intValue());
		assertEquals(2, result.getCount().intValue());
		assertEquals(2, result.getInstitutionalProposals().size());
		assertEquals(proposal1.getProposalNumber(), ((List<InstitutionalProposalSummaryDto>)result.getInstitutionalProposals()).get(0).getProposalNumber());
	}	
}
