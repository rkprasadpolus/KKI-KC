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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class PHS398ChecklistBaseGeneratorTest extends S2STestBase {

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        ProposalType type = new ProposalType();
        type.setCode("2");
        document.getDevelopmentProposal().setProposalType(type);
        ProposalYnq proposalYnq = new ProposalYnq();
        proposalYnq.setAnswer("Y");
        proposalYnq.setQuestionId("22");
        proposalYnq.setExplanation("David,Blain");

        Ynq ynq = new Ynq();
        ynq.setQuestionId("22");
        ynq.setGroupName("groupName");
        ynq.setDescription("description");
        ynq.setEffectiveDate(new Date(1));
        ynq.setNoOfAnswers(1);
        ynq.setQuestionType("A");
        ynq.setStatus("A");
        proposalYnq.setYnq(ynq);
        proposalYnq.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
        saveBO(ynq);


        List<ProposalYnq> ynqList = new ArrayList<ProposalYnq>();
        ynqList.add(proposalYnq);
        document.getDevelopmentProposal().setProposalYnqs(ynqList);
    }
}
