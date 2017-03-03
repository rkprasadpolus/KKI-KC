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
package org.kuali.coeus.propdev.impl.budget;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.Account;
import org.kuali.coeus.common.budget.framework.core.ValidSourceAccountsCostShareType;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.impl.gv.GlobalVariableServiceImpl;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ProposalBudgetServiceTest {

    public static final String COST_SHARE_FIELD = "costShareField";

    @Test
	public void test_getFinalBudgetVersion_noBudgetDoc()
			throws WorkflowException {
		final ProposalDevelopmentDocument proposalDevelopmentDocument = new ProposalDevelopmentDocument();
        ProposalBudgetService proposalBudgetService = new ProposalBudgetServiceImpl();
		proposalDevelopmentDocument.getDevelopmentProposal().setFinalBudget(null);

		ProposalDevelopmentBudgetExt assertBudgetDocument = proposalBudgetService
				.getFinalBudgetVersion(proposalDevelopmentDocument);
		assertNull(assertBudgetDocument);
	}

	@Test
	public void test_getFinalBudgetVersion_no_docHeaderId()
			throws WorkflowException {
        ProposalBudgetService proposalBudgetService = new ProposalBudgetServiceImpl();
        final ProposalDevelopmentDocument proposalDevelopmentDocument = new ProposalDevelopmentDocument();
		ProposalDevelopmentBudgetExt finalBudget = new ProposalDevelopmentBudgetExt();
		proposalDevelopmentDocument.getDevelopmentProposal().setFinalBudget(
				finalBudget);

		ProposalDevelopmentBudgetExt assertBudgetDocument = proposalBudgetService
				.getFinalBudgetVersion(proposalDevelopmentDocument);
		assertNotNull(assertBudgetDocument);
	}

	@Test
	public void test_getFinalBudgetVersion_withoutFinalBudgetVersion()
			throws WorkflowException {
        ProposalBudgetService proposalBudgetService = new ProposalBudgetServiceImpl();
        final BudgetVersionOverview budgetVersionOverview = new BudgetVersionOverview();
		budgetVersionOverview.setDocumentNumber("1234");
		final ProposalDevelopmentDocument proposalDevelopmentDocument = new ProposalDevelopmentDocument();


		ProposalDevelopmentBudgetExt assertBudgetDocument = proposalBudgetService
				.getFinalBudgetVersion(proposalDevelopmentDocument);
		assertNull(assertBudgetDocument);
	}

	@Test(expected = NullPointerException.class)
	public void test_getFinalBudgetVersion_no_argument()
			throws WorkflowException {
        ProposalBudgetService proposalBudgetService = new ProposalBudgetServiceImpl();
        proposalBudgetService.getFinalBudgetVersion(null);
	}

    @Test
    public void testIsValidSourceAccountCostShareType() {
        ProposalBudgetServiceImpl proposalBudgetServiceImpl = new ProposalBudgetServiceImpl() {

            public Collection<ValidSourceAccountsCostShareType> getMatchingValidSourceAccountsCostShareTypes() {
                List<ValidSourceAccountsCostShareType> validSourceAccountsCostShareTypeList = new ArrayList<>();
                final ValidSourceAccountsCostShareType validSourceAccountsCostShareType1 = new ValidSourceAccountsCostShareType();
                validSourceAccountsCostShareType1.setActive(true);
                validSourceAccountsCostShareType1.setCostShareTypeCode(1);
                final Account account1 = new Account();
                account1.setAccountNumber("2345");
                validSourceAccountsCostShareType1.setAccount(account1);

                final ValidSourceAccountsCostShareType validSourceAccountsCostShareType2 = new ValidSourceAccountsCostShareType();
                validSourceAccountsCostShareType2.setActive(true);
                validSourceAccountsCostShareType2.setCostShareTypeCode(2);
                final Account account2 = new Account();
                account2.setAccountNumber("3456");
                validSourceAccountsCostShareType1.setAccount(account1);

                validSourceAccountsCostShareTypeList.add(validSourceAccountsCostShareType1);
                return validSourceAccountsCostShareTypeList;
            }

            public boolean isCostShareTypeSourceAccountValidationEnabled() {
                return true;
            }
        };

        proposalBudgetServiceImpl.setGlobalVariableService(new GlobalVariableServiceImpl());
        BudgetCostShare budgetCostShare1 = new BudgetCostShare();
        budgetCostShare1.setCostShareTypeCode(1);
        budgetCostShare1.setSourceAccount("2345");
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("W", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("E", budgetCostShare1, COST_SHARE_FIELD));

        budgetCostShare1.setSourceAccount("1234");
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("W", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getWarningMessages().size() == 1);
        proposalBudgetServiceImpl.getGlobalVariableService().setMessageMap(new MessageMap());
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("N", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertFalse(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("E", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getErrorMessages().size() == 1);

        budgetCostShare1 = new BudgetCostShare();
        budgetCostShare1.setCostShareTypeCode(1);
        budgetCostShare1.setSourceAccount("3456");
        Assert.assertFalse(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("E", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getErrorMessages().size() == 1);
        proposalBudgetServiceImpl.getGlobalVariableService().setMessageMap(new MessageMap());
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("W", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getWarningMessages().size() == 1);
        proposalBudgetServiceImpl.getGlobalVariableService().setMessageMap(new MessageMap());

        budgetCostShare1 = new BudgetCostShare();
        budgetCostShare1.setCostShareTypeCode(2);
        budgetCostShare1.setSourceAccount("9999");
        Assert.assertFalse(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("E", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getErrorMessages().size() == 1);
        proposalBudgetServiceImpl.getGlobalVariableService().setMessageMap(new MessageMap());

        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("W", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getWarningMessages().size() == 1);
        proposalBudgetServiceImpl.getGlobalVariableService().setMessageMap(new MessageMap());
    }

    @Test
    public void testIsValidSourceAccountCostShareTypeNoValidEntries() {

        ProposalBudgetServiceImpl proposalBudgetServiceImpl = new ProposalBudgetServiceImpl() {
            public Collection<ValidSourceAccountsCostShareType> getMatchingValidSourceAccountsCostShareTypes() {
                List<ValidSourceAccountsCostShareType> validSourceAccountsCostShareTypeList = new ArrayList<>();
                return validSourceAccountsCostShareTypeList;
            }

            public boolean isCostShareTypeSourceAccountValidationEnabled() {
                return true;
            }
        };

        final GlobalVariableServiceImpl globalVariableService = new GlobalVariableServiceImpl();
        globalVariableService.setMessageMap(new MessageMap());
        proposalBudgetServiceImpl.setGlobalVariableService(globalVariableService);
        BudgetCostShare budgetCostShare1 = new BudgetCostShare();
        budgetCostShare1.setCostShareTypeCode(1);
        budgetCostShare1.setSourceAccount("2345");
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("W", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getWarningMessages().size() == 0);
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("E", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getWarningMessages().size() == 0);
        budgetCostShare1.setSourceAccount("1234");
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("W", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getWarningMessages().size() == 0);
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("N", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.isValidSourceAccountCostShareType("E", budgetCostShare1, COST_SHARE_FIELD));
        Assert.assertTrue(proposalBudgetServiceImpl.getGlobalVariableService().getMessageMap().getErrorMessages().size() == 0);
    }

}
