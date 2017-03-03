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
package org.kuali.kra.award.home.fundingproposal;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardCommentFactory;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


class CostSharingDataFeedCommand extends ProposalDataFeedCommandBase {
    private static final String COST_SHARE_COMMENT_PATTERN = "Added Cost Shares from Proposal Number %s";
    
    public CostSharingDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }

    @Override
    void performDataFeed() {
        if (mergeType != FundingProposalMergeType.NOCHANGE) {
            int costshareCount = 0;
            if (mergeType == FundingProposalMergeType.REPLACE) {
                award.setAwardCostShares(new ArrayList<>());
            }
            List<InstitutionalProposalCostShare> costShares = proposal.getInstitutionalProposalCostShares();
            for (InstitutionalProposalCostShare ipCostShare : costShares) {
                award.add(copyCostShare(ipCostShare));
                costshareCount++;
            }

            if (costshareCount > 0) {
                addCostShareComment(proposal);
            }


        }
    }

    protected void addCostShareComment(InstitutionalProposal proposal) {
        if ((mergeType == FundingProposalMergeType.NEWAWARD || mergeType == FundingProposalMergeType.REPLACE)
                && !Objects.isNull(this.proposal.getCostShareComment())
                && !StringUtils.isEmpty(this.proposal.getCostShareComment().getComments())) {
            if (mergeType == FundingProposalMergeType.REPLACE) {
                award.getAwardCostShareComment().setComments(StringUtils.EMPTY);
            }
            this.award.getAwardCostShareComment().setComments(this.proposal.getCostShareComment().getComments());
        } else {
            String newComment = String.format(COST_SHARE_COMMENT_PATTERN, proposal.getProposalNumber());
            appendComments(findOrCreateCommentOfSpecifiedType(new AwardCommentFactory().createCostShareComment()), newComment);
        }
    }

    private AwardCostShare copyCostShare(InstitutionalProposalCostShare ipCostShare) {
        AwardCostShare awardCostShare = new AwardCostShare();
        awardCostShare.setCommitmentAmount(ipCostShare.getAmount());
        awardCostShare.setCostSharePercentage(ipCostShare.getCostSharePercentage());
        awardCostShare.setCostShareType(ipCostShare.getCostShareType());
        awardCostShare.setCostShareTypeCode(ipCostShare.getCostShareTypeCode());
        awardCostShare.setSource(ipCostShare.getSourceAccount());
        awardCostShare.setProjectPeriod(ipCostShare.getProjectPeriod());
        awardCostShare.setUnitNumber(ipCostShare.getUnitNumber());
        awardCostShare.setUnit(ipCostShare.getUnit());
        return awardCostShare;
    }
}
