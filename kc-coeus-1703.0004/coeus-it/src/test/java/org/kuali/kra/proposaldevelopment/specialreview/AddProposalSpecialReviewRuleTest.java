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
package org.kuali.kra.proposaldevelopment.specialreview;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.kra.common.specialreview.rules.AddSpecialReviewRuleTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

public class AddProposalSpecialReviewRuleTest extends AddSpecialReviewRuleTestBase<ProposalSpecialReview> {
    
    @Override
    public Document getDocument() throws WorkflowException {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        Document document = KRADServiceLocatorWeb.getDocumentService().getNewDocument(ProposalDevelopmentDocument.class);
        return document;
    }

    @Override
    public ProposalSpecialReview getSpecialReview() {
        return new ProposalSpecialReview();
    }

}