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
package org.kuali.kra.institutionalproposal.service.impl;


import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.common.impl.fiscalyear.FiscalYearMonthServiceImpl;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;

import java.util.ArrayList;

public class InstitutionalProposalJavaFunctionKrmsTermServiceImplTest {

    class MockIP extends InstitutionalProposal {

        @Override
        protected void calculateFiscalMonthAndYearFields() {

        }
    }

    class MockIpTermService extends InstitutionalProposalJavaFunctionKrmsTermServiceImpl {


    }

    class MockFiscalYearMonthServiceImpl extends FiscalYearMonthServiceImpl {
        Integer month;
        Integer year;

        public void setCurrentFiscalMonth(Integer month) {
            this.month = month;
        }

        public void setCurrentFiscalYear(Integer year) {
            this.year = year;
        }

        public Integer getCurrentFiscalMonth() {
            return month;
        }

        public Integer getCurrentFiscalYear() {
            return year;
        }

    }

    @Test
    public void testIsCurrentFiscalMonth() {
        InstitutionalProposalJavaFunctionKrmsTermServiceImpl ipTermService = new InstitutionalProposalJavaFunctionKrmsTermServiceImpl();
        MockFiscalYearMonthServiceImpl fiscalYearMonthService = new MockFiscalYearMonthServiceImpl();
        fiscalYearMonthService.setCurrentFiscalYear(2016);
        fiscalYearMonthService.setCurrentFiscalMonth(8);
        ipTermService.setFiscalYearMonthService(fiscalYearMonthService);
        MockIP ip = new MockIP();
        ip.setFiscalYear("2016");
        ip.setFiscalMonth("08");
        Assert.assertTrue(ipTermService.isCurrentFiscalMonth(ip));

        ip.setFiscalYear("2018");
        ip.setFiscalMonth("8");
        Assert.assertFalse(ipTermService.isCurrentFiscalMonth(ip));

        ip.setFiscalYear(null);
        ip.setFiscalMonth(null);
        Assert.assertFalse(ipTermService.isCurrentFiscalMonth(ip));

        ip.setFiscalYear("2018");
        ip.setFiscalMonth(null);
        Assert.assertFalse(ipTermService.isCurrentFiscalMonth(ip));
    }


    @Test
    public void testHasSpecialReviewOfType() {

        MockIP ip = new MockIP();
        InstitutionalProposalSpecialReview specialReview = new InstitutionalProposalSpecialReview();
        specialReview.setSpecialReviewTypeCode("1");
        SpecialReviewType specialReviewType = new SpecialReviewType();
        specialReviewType.setSpecialReviewTypeCode("1");
        specialReview.setSpecialReviewType(specialReviewType);
        ip.getSpecialReviews().add(specialReview);

        InstitutionalProposalJavaFunctionKrmsTermServiceImpl ipTermService = new InstitutionalProposalJavaFunctionKrmsTermServiceImpl();
        Assert.assertTrue(ipTermService.hasSpecialReviewOfType(ip, "1"));

        specialReview = new InstitutionalProposalSpecialReview();
        specialReviewType = new SpecialReviewType();
        specialReviewType.setDescription("1");
        specialReview.setSpecialReviewType(specialReviewType);
        ip.getSpecialReviews().add(specialReview);

        Assert.assertTrue(ipTermService.hasSpecialReviewOfType(ip, "1"));

        specialReview = new InstitutionalProposalSpecialReview();
        specialReviewType = new SpecialReviewType();
        specialReview.setSpecialReviewTypeCode("1");
        specialReviewType.setSpecialReviewTypeCode("1");
        specialReviewType.setDescription("1");
        specialReview.setSpecialReviewType(specialReviewType);
        ip.getSpecialReviews().add(specialReview);

        Assert.assertFalse(ipTermService.hasSpecialReviewOfType(ip, "2"));

        ip.setSpecialReviews(new ArrayList<>());
        Assert.assertFalse(ipTermService.hasSpecialReviewOfType(ip, "2"));

    }
}
