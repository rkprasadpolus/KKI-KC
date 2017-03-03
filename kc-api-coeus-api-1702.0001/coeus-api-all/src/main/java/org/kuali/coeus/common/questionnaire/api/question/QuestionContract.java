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
package org.kuali.coeus.common.questionnaire.api.question;

import org.kuali.coeus.common.questionnaire.api.core.QuestionTypeContract;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

import java.util.List;

public interface QuestionContract extends IdentifiableNumeric {

    String getDocumentNumber();
    Integer getQuestionSeqId();
    Integer getSequenceNumber();
    String getSequenceStatus();
    String getQuestion();
    String getStatus();
    String getLookupClass();
    String getLookupReturn();
    Integer getDisplayedAnswers();
    Integer getMaxAnswers();
    Integer getAnswerMaxLength();

    QuestionCategoryContract getQuestionCategory();

    QuestionTypeContract getQuestionType();

    List<? extends QuestionExplanationContract> getQuestionExplanations();
}
