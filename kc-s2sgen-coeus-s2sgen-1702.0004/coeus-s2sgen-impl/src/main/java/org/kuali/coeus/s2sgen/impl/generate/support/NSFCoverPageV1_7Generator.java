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

import gov.grants.apply.forms.nsfCoverPage17V17.NSFCoverPage17Document;
import gov.grants.apply.forms.nsfCoverPage17V17.NSFCoverPage17Document.NSFCoverPage17;
import gov.grants.apply.forms.nsfCoverPage17V17.NSFCoverPage17Document.NSFCoverPage17.OtherInfo;
import gov.grants.apply.forms.nsfCoverPage17V17.NSFCoverPage17Document.NSFCoverPage17.PIInfo;
import gov.grants.apply.forms.nsfCoverPage17V17.NSFCoverPage17Document.NSFCoverPage17.FundingMechanism;
import gov.grants.apply.forms.nsfCoverPage17V17.NSFCoverPage17Document.NSFCoverPage17.NSFUnitConsideration;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin1Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@FormGenerator("NSFCoverPageV1_7Generator")
public class NSFCoverPageV1_7Generator extends NSFCoverPageBaseGenerator {

    private static final int MENTORING_PLAN = 147;
    private static final int DATA_MANAGEMENT_PLAN = 146;
    private static final int LOBBYING_ACTIVITIES_QUESTION = 11;
    private static final int GOALI = -1;
    private static final int RAISE = -2;

    @Value("http://apply.grants.gov/forms/NSF_CoverPage_1_7-V1.7")
    private String namespace;

    @Value("NSF_CoverPage_1_7-V1.7")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NSF_CoverPage-V1.7.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.nsfCoverPage17V17")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    private NSFCoverPage17Document getNSFCoverPage17() {
        NSFCoverPage17Document nsfCoverPage17Document = NSFCoverPage17Document.Factory.newInstance();
        NSFCoverPage17Document.NSFCoverPage17 nsfCoverPage17 = NSFCoverPage17.Factory.newInstance();
        nsfCoverPage17.setFormVersion(FormVersion.v1_7.getVersion());
        setFundingOpportunityNumber(nsfCoverPage17);

        if (pdDoc.getDevelopmentProposal().getS2sOpportunity() != null && pdDoc.getDevelopmentProposal().getS2sOpportunity().getClosingDate() != null) {
            nsfCoverPage17.setDueDate(pdDoc.getDevelopmentProposal().getS2sOpportunity().getClosingDate());
        }
        nsfCoverPage17.setNSFUnitConsideration(getNSFUnitConsideration());
        setOtherInfo(nsfCoverPage17);
        AttachmentGroupMin1Max100DataType attachmentGroup = AttachmentGroupMin1Max100DataType.Factory.newInstance();
        attachmentGroup.setAttachedFileArray(getAttachedFileDataTypes());
        if (attachmentGroup.getAttachedFileArray().length > 0) {
            nsfCoverPage17.setSingleCopyDocuments(attachmentGroup);
        }
        final AttachedFileDataType dataManagementPlan = getAttachedNarrativeFile(DATA_MANAGEMENT_PLAN);
        if (dataManagementPlan != null) {
            nsfCoverPage17.setDataManagementPlan(dataManagementPlan);
        }

        final AttachedFileDataType mentoringPlan = getAttachedNarrativeFile(MENTORING_PLAN);
        if (mentoringPlan != null) {
            nsfCoverPage17.setMentoringPlan(mentoringPlan);
        }

        final AttachedFileDataType goali = getAttachedNarrativeFile(GOALI);
        if (goali != null) {
            nsfCoverPage17.setGOALILetter(goali);
        }

        final AttachedFileDataType raise = getAttachedNarrativeFile(RAISE);
        if (raise != null) {
            nsfCoverPage17.setRAISEEmails(raise);
        }
        nsfCoverPage17Document.setNSFCoverPage17(nsfCoverPage17);
        return nsfCoverPage17Document;
    }


    private void setFundingOpportunityNumber(NSFCoverPage17Document.NSFCoverPage17 nsfCoverPage17) {
        nsfCoverPage17.setFundingOpportunityNumber(StringUtils.substring(pdDoc.getDevelopmentProposal().getProgramAnnouncementNumber(), 0, PROGRAM_ANNOUNCEMENT_NUMBER_MAX_LENGTH));
    }

    private void setOtherInfo(NSFCoverPage17Document.NSFCoverPage17 nsfCoverPage17) {
        OtherInfo otherInfo = OtherInfo.Factory.newInstance();

        PIInfo pInfo = PIInfo.Factory.newInstance();
        final List<? extends AnswerContract> questionnaireAnswers = getPropDevQuestionAnswerService().getQuestionnaireAnswers(pdDoc.getDevelopmentProposal().getProposalNumber(), getNamespace(), getFormName());
        int unansweredQuestions = questionnaireAnswers.size();

        for (AnswerContract questionnaireAnswer : questionnaireAnswers) {
            String answer = questionnaireAnswer.getAnswer();
            int questionId = questionnaireAnswer.getQuestionNumber();
            if (answer != null) {
                switch (questionId) {
                    case QUESTION_CURRENT_PI:
                        pInfo.setIsCurrentPI(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        unansweredQuestions--;
                        break;
                    case QUESTION_BEGIN_INVESTIGATOR:
                        otherInfo.setIsBeginInvestigator(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        unansweredQuestions--;
                        break;
                    case QUESTION_ACCOMPLISHMENT_RENEWAL:
                        otherInfo.setIsAccomplishmentRenewal(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        unansweredQuestions--;
                        break;
                    case FUNDING_MECHANISM_QUESTION:
                        setFundingMechanism(nsfCoverPage17, answer);
                        unansweredQuestions--;
                        break;
                    case LOBBYING_ACTIVITIES_QUESTION:
                        otherInfo.setIsDisclosureLobbyingActivities(answer.equals(YnqConstant.YES.code()) ? YesNoDataType.Y_YES : YesNoDataType.N_NO);
                        unansweredQuestions--;
                        break;
                    default:
                        break;
                }
            }
        }
        if (unansweredQuestions != 0) {
            nsfCoverPage17.setPIInfo(null);
            nsfCoverPage17.setOtherInfo(null);
        }
        else {
            nsfCoverPage17.setPIInfo(pInfo);
            nsfCoverPage17.setOtherInfo(otherInfo);
        }
    }

    private void setFundingMechanism(NSFCoverPage17 nsfCoverPage17, String answer) {
        FundingMechanism.Enum fundingMechanism;
        if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.CONFERENCE.toString())) {
            fundingMechanism = FundingMechanism.CONFERENCE;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.RAPID.toString())) {
            fundingMechanism = FundingMechanism.RAPID;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.EAGER.toString())) {
            fundingMechanism = FundingMechanism.EAGER;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.EQUIPMENT.toString())) {
            fundingMechanism = FundingMechanism.EQUIPMENT;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.RAISE.toString())) {
            fundingMechanism = FundingMechanism.RAISE;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.FELLOWSHIP.toString())) {
            fundingMechanism = FundingMechanism.FELLOWSHIP;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.IDEAS_LAB.toString())) {
            fundingMechanism = FundingMechanism.IDEAS_LAB;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.GOALI.toString())) {
            fundingMechanism = FundingMechanism.GOALI;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.FASED.toString())) {
            fundingMechanism = FundingMechanism.FASED;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.TRAVEL.toString())) {
            fundingMechanism = FundingMechanism.TRAVEL;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.CENTER_RESEARCH_INFRASTRUCTURE.toString())) {
            fundingMechanism = FundingMechanism.CENTER_RESEARCH_INFRASTRUCTURE;
        } else if (StringUtils.equalsIgnoreCase(answer, FundingMechanism.RESEARCH.toString())) {
            fundingMechanism = FundingMechanism.RESEARCH;
        }
        else {
            fundingMechanism = null;
        }
        nsfCoverPage17.setFundingMechanism(fundingMechanism);
    }

    private NSFUnitConsideration getNSFUnitConsideration() {
        NSFUnitConsideration nsfConsideration = NSFUnitConsideration.Factory.newInstance();
        nsfConsideration.setDivisionCode(pdDoc.getDevelopmentProposal().getAgencyDivisionCode());
        nsfConsideration.setProgramCode(pdDoc.getDevelopmentProposal().getAgencyProgramCode());
        return nsfConsideration;
    }

    private AttachedFileDataType[] getAttachedFileDataTypes() {
        List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<>();
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null) {
                int narrativeTypeCode = Integer.parseInt(narrative.getNarrativeType().getCode());
                if (narrativeTypeCode == PERSONAL_DATA || narrativeTypeCode == PROPRIETARY_INFORMATION || narrativeTypeCode == SINGLE_COPY_DOCUMENT) {
                    AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                    if (attachedFileDataType != null) {
                        attachedFileDataTypeList.add(attachedFileDataType);
                    }
                }
            }
        }
        return attachedFileDataTypeList.toArray(new AttachedFileDataType[attachedFileDataTypeList.size()]);
    }

    private AttachedFileDataType getAttachedNarrativeFile(int narrativeCode) {
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null && Integer.parseInt(narrative.getNarrativeType().getCode()) == narrativeCode) {
                AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    return attachedFileDataType;
                }
            }
        }
        return null;
    }

    @Override
    public NSFCoverPage17Document getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getNSFCoverPage17();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
