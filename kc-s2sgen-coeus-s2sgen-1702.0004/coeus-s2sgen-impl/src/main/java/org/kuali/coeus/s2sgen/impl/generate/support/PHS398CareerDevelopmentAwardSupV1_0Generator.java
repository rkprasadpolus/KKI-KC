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

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.CitizenshipDataType.Enum;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.ApplicationType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.ApplicationType.TypeOfApplication;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10.PHS398CareerDevelopmentAwardSupDocument.PHS398CareerDevelopmentAwardSup.CareerDevelopmentAwardAttachments.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.attr.CitizenshipType;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@FormGenerator("PHS398CareerDevelopmentAwardSupV1_0Generator")
public class PHS398CareerDevelopmentAwardSupV1_0Generator extends
		PHS398CareerDevelopmentAwardSupBaseGenerator {
	private static final String PROPOSAL_TYPE_TASK_ORDER = "6";

    @Value("http://apply.grants.gov/forms/PHS398_CareerDevelopmentAwardSup-V1.0")
    private String namespace;

    @Value("PHS398_CareerDevelopmentAwardSup-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/PHS398_CareerDevelopmentAwardSup-V1.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398CareerDevelopmentAwardSupV10")
    private String packageName;

    @Value("200")
    private int sortIndex;

	private PHS398CareerDevelopmentAwardSupDocument getPHS398CareerDevelopmentAwardSup() {
		PHS398CareerDevelopmentAwardSupDocument phs398CareerDevelopmentAwardSupDocument = PHS398CareerDevelopmentAwardSupDocument.Factory.newInstance();
		PHS398CareerDevelopmentAwardSup phs398CareerDevelopmentAwardSup = PHS398CareerDevelopmentAwardSup.Factory.newInstance();
		phs398CareerDevelopmentAwardSup.setFormVersion(FormVersion.v1_0.getVersion());
		phs398CareerDevelopmentAwardSup.setApplicationType(getApplicationType());
		phs398CareerDevelopmentAwardSup.setCitizenship(getCitizenshipDataType());
		phs398CareerDevelopmentAwardSup.setCareerDevelopmentAwardAttachments(getCareerDevelopmentAwardAttachments());
		phs398CareerDevelopmentAwardSupDocument.setPHS398CareerDevelopmentAwardSup(phs398CareerDevelopmentAwardSup);
		return phs398CareerDevelopmentAwardSupDocument;
	}
	
	private Enum getCitizenshipDataType() {
		return pdDoc.getDevelopmentProposal().getProposalPersons().stream()
				.filter(ProposalPersonContract::isInvestigator)
				.map(proposalPerson -> s2SProposalPersonService.getCitizenship(proposalPerson))
				.map(citizenShip -> {
					if(CitizenshipType.NON_US_CITIZEN_WITH_TEMPORARY_VISA.equals(citizenShip)) {
						return CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA;
					} else if(CitizenshipType.PERMANENT_RESIDENT_OF_US.equals(citizenShip)) {
						return CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
					} else if(CitizenshipType.US_CITIZEN_OR_NONCITIZEN_NATIONAL.equals(citizenShip)) {
						return CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL;
					} else {
						return null;
					}
				}).findFirst().orElse(null);
	}

	private ApplicationType getApplicationType() {
		ApplicationType applicationType = ApplicationType.Factory.newInstance();
		if (pdDoc.getDevelopmentProposal().getProposalType().getCode() != null
				&& !pdDoc.getDevelopmentProposal().getProposalType().getCode()
						.equals(PROPOSAL_TYPE_TASK_ORDER)) {
			// Check !=6 to ensure that if proposalType='TASK ORDER", it must
			// not set. THis is because the enum has no
			// entry for TASK ORDER
			applicationType.setTypeOfApplication(TypeOfApplication.Enum
					.forInt(Integer.parseInt(pdDoc.getDevelopmentProposal()
							.getProposalType().getCode())));
		} else {
			applicationType.setTypeOfApplication(TypeOfApplication.NEW);
		}
		return applicationType;
	}

	private CareerDevelopmentAwardAttachments getCareerDevelopmentAwardAttachments() {
		CareerDevelopmentAwardAttachments careerDevelopmentAwardAttachments = CareerDevelopmentAwardAttachments.Factory.newInstance();
		AttachmentGroupMin0Max100DataType attachmentGroupMin0Max100DataType = AttachmentGroupMin0Max100DataType.Factory.newInstance();
		List<AttachedFileDataType> attachedFileList = new ArrayList<>();
		AttachedFileDataType attachedFileDataType;
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
			int narrativeType = Integer.parseInt(narrative.getNarrativeType().getCode());
			switch (narrativeType) {
			case NARRATIVE_TYPE_INTRODUCTION_TO_APPLICATION:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				IntroductionToApplication introductionToApplication = IntroductionToApplication.Factory.newInstance();
				introductionToApplication.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setIntroductionToApplication(introductionToApplication);
				break;
			case NARRATIVE_TYPE_SPECIFIC_AIMS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SpecificAims specificAims = SpecificAims.Factory.newInstance();
				specificAims.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setSpecificAims(specificAims);
				break;
			case NARRATIVE_TYPE_BACKGROUND_SIGNIFICANCE:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				BackgroundAndSignificance backgroundAndSignificance = BackgroundAndSignificance.Factory.newInstance();
				backgroundAndSignificance.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setBackgroundAndSignificance(backgroundAndSignificance);
				break;
			case NARRATIVE_TYPE_RESEARCH_DESIGN_METHODS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResearchDesignAndMethods researchDesignAndMethods = ResearchDesignAndMethods.Factory.newInstance();
				researchDesignAndMethods.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setResearchDesignAndMethods(researchDesignAndMethods);
				break;
			case NARRATIVE_TYPE_INCLUSION_ENROLLMENT_REPORT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionEnrollmentReport inclusionEnrollmentReport = InclusionEnrollmentReport.Factory.newInstance();
				inclusionEnrollmentReport.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInclusionEnrollmentReport(inclusionEnrollmentReport);
				break;
			case NARRATIVE_TYPE_PROGRESS_REPORT_PUBLICATION_LIST:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProgressReportPublicationList progressReportPublicationList = ProgressReportPublicationList.Factory.newInstance();
				progressReportPublicationList.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setProgressReportPublicationList(progressReportPublicationList);
				break;
			case NARRATIVE_TYPE_PROTECTION_OF_HUMAN_SUBJECTS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ProtectionOfHumanSubjects protectionOfHumanSubjects = ProtectionOfHumanSubjects.Factory.newInstance();
				protectionOfHumanSubjects.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setProtectionOfHumanSubjects(protectionOfHumanSubjects);
				break;
			case NARRATIVE_TYPE_INCLUSION_OF_WOMEN_AND_MINORITIES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfWomenAndMinorities inclusionOfWomenAndMinorities = InclusionOfWomenAndMinorities.Factory.newInstance();
				inclusionOfWomenAndMinorities.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInclusionOfWomenAndMinorities(inclusionOfWomenAndMinorities);
				break;
			case NARRATIVE_TYPE_TARGETED_PLANNED_ENROLLMENT_TABLE:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				TargetedPlannedEnrollment targetedPlannedEnrollment = TargetedPlannedEnrollment.Factory.newInstance();
				targetedPlannedEnrollment.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setTargetedPlannedEnrollment(targetedPlannedEnrollment);
				break;
			case NARRATIVE_TYPE_INCLUSION_OF_CHILDREN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InclusionOfChildren inclusionOfChildren = InclusionOfChildren.Factory.newInstance();
				inclusionOfChildren.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInclusionOfChildren(inclusionOfChildren);
				break;
			case NARRATIVE_TYPE_VERTEBRATE_ANIMALS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				VertebrateAnimals vertebrateAnimals = VertebrateAnimals.Factory.newInstance();
				vertebrateAnimals.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setVertebrateAnimals(vertebrateAnimals);
				break;
			case NARRATIVE_TYPE_SELECT_AGENT_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				SelectAgentResearch selectAgentResearch = SelectAgentResearch.Factory.newInstance();
				selectAgentResearch.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setSelectAgentResearch(selectAgentResearch);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_PRELIM_STUDIES_PROGREP:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				PreliminaryStudiesProgressReport preliminaryStudiesProgressReport = PreliminaryStudiesProgressReport.Factory.newInstance();
				preliminaryStudiesProgressReport.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setPreliminaryStudiesProgressReport(preliminaryStudiesProgressReport);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_CONSORTIUM_CONTRACT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ConsortiumContractualArrangements consortiumContractualArrangements = ConsortiumContractualArrangements.Factory.newInstance();
				consortiumContractualArrangements.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setConsortiumContractualArrangements(consortiumContractualArrangements);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_RESOURCE_SHARING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResourceSharingPlans resourceSharingPlans = ResourceSharingPlans.Factory.newInstance();
				resourceSharingPlans.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setResourceSharingPlans(resourceSharingPlans);
				break;
			case NARRATIVE_TYPE_CANDIDATE_BACKGROUND:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CandidateBackground candidateBackground = CandidateBackground.Factory.newInstance();
				candidateBackground.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setCandidateBackground(candidateBackground);
				break;
			case NARRATIVE_TYPE_CAREER_GOALS_AND_OBJECTIVES:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CareerGoalsAndObjectives careerGoalsAndObjectives = CareerGoalsAndObjectives.Factory.newInstance();
				careerGoalsAndObjectives.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setCareerGoalsAndObjectives(careerGoalsAndObjectives);
				break;
			case NARRATIVE_TYPE_CAREER_DEVELOPMENT_AND_TRAINING:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				CareerDevelopmentAndTrainingActivities careerDevelopmentAndTrainingActivities = CareerDevelopmentAndTrainingActivities.Factory.newInstance();
				careerDevelopmentAndTrainingActivities.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setCareerDevelopmentAndTrainingActivities(careerDevelopmentAndTrainingActivities);
				break;
			case NARRATIVE_TYPE_RESPONSIBLE_CONDUCT_OF_RESEARCH:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				ResponsibleConductOfResearch responsibleConductOfResearch = ResponsibleConductOfResearch.Factory.newInstance();
				responsibleConductOfResearch.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setResponsibleConductOfResearch(responsibleConductOfResearch);
				break;
			case NARRATIVE_TYPE_PHS398_MENTORING_PLAN:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				MentoringPlan mentoringPlan = MentoringPlan.Factory.newInstance();
				mentoringPlan.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setMentoringPlan(mentoringPlan);
				break;
			case NARRATIVE_TYPE_PHS398_MENTOR_STATEMENTS_LETTERS:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				StatementsOfSupport statementsOfSupport = StatementsOfSupport.Factory.newInstance();
				statementsOfSupport.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setStatementsOfSupport(statementsOfSupport);
				break;
			case NARRATIVE_TYPE_PSH398_INSTITUTIONAL_ENVIRONMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InsitutionalEnvironment insitutionalEnvironment = InsitutionalEnvironment.Factory.newInstance();
				insitutionalEnvironment.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInsitutionalEnvironment(insitutionalEnvironment);
				break;
			case NARRATIVE_TYPE_PHS398_INSTITUTIONAL_COMMITMENT:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				InstitutionalCommitment institutionalCommitment = InstitutionalCommitment.Factory.newInstance();
				institutionalCommitment.setAttFile(attachedFileDataType);
				careerDevelopmentAwardAttachments.setInstitutionalCommitment(institutionalCommitment);
				break;
			case NARRATIVE_TYPE_PHS_CAREER_APPENDIX:
	            attachedFileDataType = getAttachedFileType(narrative);
	            if(attachedFileDataType == null){
	                continue;
	            }
				attachedFileList.add(attachedFileDataType);
				break;
			}
		}
		attachmentGroupMin0Max100DataType.setAttachedFileArray(attachedFileList.toArray(new AttachedFileDataType[0]));
		careerDevelopmentAwardAttachments.setAppendix(attachmentGroupMin0Max100DataType);
		return careerDevelopmentAwardAttachments;
	}

	@Override
	public PHS398CareerDevelopmentAwardSupDocument getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHS398CareerDevelopmentAwardSup();
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
