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

import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document;
import gov.grants.apply.forms.rrOtherProjectInfo13V13.RROtherProjectInfo13Document.RROtherProjectInfo13.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.ynq.YnqConstant;
import org.kuali.coeus.common.questionnaire.api.answer.AnswerHeaderContract;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewContract;
import org.kuali.coeus.propdev.api.specialreview.ProposalSpecialReviewExemptionContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


@FormGenerator("RROtherProjectInfo_1_3V1_3Generator")
public class RROtherProjectInfo_1_3V1_3Generator extends
		RROtherProjectInfoBaseGenerator {
	private static final Integer HISTORIC_DESTIONATION_YNQ = 125;

	List<? extends AnswerHeaderContract> answerHeaders;

    @Value("http://apply.grants.gov/forms/RR_OtherProjectInfo_1_3-V1.3")
    private String namespace;

    @Value("RR_OtherProjectInfo_1_3-V1.3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_OtherProjectInfo_1_3-V1.3.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrOtherProjectInfo13V13")
    private String packageName;

    @Value("140")
    private int sortIndex;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

	private RROtherProjectInfo13Document getRROtherProjectInfo() {
		RROtherProjectInfo13Document rrOtherProjectInfoDocument = RROtherProjectInfo13Document.Factory
				.newInstance();
		RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo = RROtherProjectInfo13Document.RROtherProjectInfo13.Factory
				.newInstance();
		rrOtherProjectInfo.setFormVersion(FormVersion.v1_3.getVersion());
		answerHeaders = getPropDevQuestionAnswerService().getQuestionnaireAnswerHeaders(pdDoc.getDevelopmentProposal().getProposalNumber());
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.N_NO);
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO);
		OrganizationContract organization = pdDoc.getDevelopmentProposal()
				.getApplicantOrganization().getOrganization();
		setHumanSubjAndVertebrateAnimals(rrOtherProjectInfo, organization);
		setProprietaryInformationIndicator(rrOtherProjectInfo);
		setEnvironmentalImpactDetails(rrOtherProjectInfo);
		setHistoricDestionation(rrOtherProjectInfo);
		setInternationalActivities(rrOtherProjectInfo);
		setAttachments(rrOtherProjectInfo);
		rrOtherProjectInfoDocument.setRROtherProjectInfo13(rrOtherProjectInfo);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rrOtherProjectInfoDocument.toString().getBytes());            
        sortAttachments(byteArrayInputStream);
		return rrOtherProjectInfoDocument;
	}

	private void setHistoricDestionation(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
	    String historicDestinationAnswer = getAnswer(HISTORIC_DESTIONATION_YNQ, answerHeaders);
	    if (historicDestinationAnswer != null && !historicDestinationAnswer.equals(NOT_ANSWERED)) {
	        YesNoDataType.Enum answer = YnqConstant.YES.code().equals(historicDestinationAnswer) ? YesNoDataType.Y_YES
	                : YesNoDataType.N_NO;
	        String answerExplanation = getChildQuestionAnswer(HISTORIC_DESTIONATION_YNQ, EXPLANATION, answerHeaders);
	        rrOtherProjectInfo.setHistoricDesignation(answer);
	        if (answerExplanation != null) {
	            if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
	                rrOtherProjectInfo
						    .setHistoricDesignationExplanation(answerExplanation
						            .trim()
						            .substring(0, EXPLANATION_MAX_LENGTH));
	            } else {
	                rrOtherProjectInfo
						    .setHistoricDesignationExplanation(answerExplanation
						            .trim());
	            }
	        } else if (YnqConstant.YES.code().equals(historicDestinationAnswer)) {
	            rrOtherProjectInfo.setHistoricDesignationExplanation(null);
	        }
	    } else {
	        rrOtherProjectInfo.setHistoricDesignation(null);
	    }
	}

	private void setProprietaryInformationIndicator(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		YesNoDataType.Enum answer;
		String propertyInformationAnswer = getAnswer(PROPRIETARY_INFORMATION_INDICATOR, answerHeaders);
		if (propertyInformationAnswer != null && !propertyInformationAnswer.equals(NOT_ANSWERED)) {
    		answer = YnqConstant.YES.code().equals(
                    propertyInformationAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
    		rrOtherProjectInfo.setProprietaryInformationIndicator(answer);
		} else {
		    rrOtherProjectInfo.setProprietaryInformationIndicator(null);
		}
	}

	private void setEnvironmentalImpactDetails(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		EnvironmentalImpact environmentalImpact = EnvironmentalImpact.Factory
				.newInstance();
		setEnvironmentalImpactIndicatorAndExplanation(environmentalImpact);
		setEnvironmentalExemption(environmentalImpact);
		rrOtherProjectInfo.setEnvironmentalImpact(environmentalImpact);
	}

	private void setEnvironmentalImpactIndicatorAndExplanation(
			EnvironmentalImpact environmentalImpact) {
		String answerExplanation;
		String environmentalImpactAnswer = getAnswer(ENVIRONMENTAL_IMPACT_YNQ, answerHeaders);
		if (environmentalImpactAnswer != null && !environmentalImpactAnswer.equals(NOT_ANSWERED)) {
    		YesNoDataType.Enum answer = YnqConstant.YES.code()
    				.equals(environmentalImpactAnswer) ? YesNoDataType.Y_YES
    				: YesNoDataType.N_NO;
    		answerExplanation =  getChildQuestionAnswer(ENVIRONMENTAL_IMPACT_YNQ, EXPLANATION, answerHeaders);
    		environmentalImpact.setEnvironmentalImpactIndicator(answer);
    		if (answerExplanation != null) {
    			if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
    				environmentalImpact
    						.setEnvironmentalImpactExplanation(answerExplanation
    								.trim()
    								.substring(0, EXPLANATION_MAX_LENGTH));
    			} else {
    				environmentalImpact
    						.setEnvironmentalImpactExplanation(answerExplanation
    								.trim());
    			}
    		} else if (YnqConstant.YES.code().equals(environmentalImpactAnswer)) {
    		    environmentalImpact.setEnvironmentalImpactExplanation(null);
    		}
		} else {
		    environmentalImpact.setEnvironmentalImpactIndicator(null);
		}
	}

	private void setHumanSubjAndVertebrateAnimals(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo,
			OrganizationContract organization) {
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.N_NO); 
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.N_NO);
		pdDoc.getDevelopmentProposal().getPropSpecialReviews().stream()
				.filter(proposalSpecialReview -> proposalSpecialReview.getSpecialReviewType() != null)
				.forEach(proposalSpecialReview -> {
			if (Integer.valueOf(proposalSpecialReview
					.getSpecialReviewType().getCode()) == HUMAN_SUBJECT_SUPPLEMENT) {
				setHumaSubjectSupplementDetails(rrOtherProjectInfo,
						organization, proposalSpecialReview);
			} else if (Integer.valueOf(proposalSpecialReview
					.getSpecialReviewType().getCode()) == VERTEBRATE_ANIMALS_SUPPLEMENT) {
				setVertebrateAnimalsSupplementDetails(rrOtherProjectInfo,
						organization, proposalSpecialReview);
			}
		});
	}

	private void setEnvironmentalExemption(
			EnvironmentalImpact environmentalImpact) {
		YesNoDataType.Enum answer;
		String answerExplanation;		
		answerExplanation = getChildQuestionAnswer(ENVIRONMENTAL_EXEMPTION_YNQ, EXPLANATION, answerHeaders);
		String ynqAnswer = getAnswer(ENVIRONMENTAL_EXEMPTION_YNQ, answerHeaders);
		if (YnqConstant.YES.code().equals(ynqAnswer)) {
			answer = YesNoDataType.Y_YES;
		} else {
			answer = YesNoDataType.N_NO;
		}
		EnvironmentalImpact.EnvironmentalExemption environmentalExemption = EnvironmentalImpact.EnvironmentalExemption.Factory
        .newInstance();
		if (ynqAnswer != null && !ynqAnswer.equals(NOT_ANSWERED)) {
    		if (!YnqConstant.NA.code().equals(ynqAnswer)) {
    			// Answer not equal to X (not-applicable)			
    			environmentalExemption.setEnvironmentalExemptionIndicator(answer);
    			if (answerExplanation != null) {
    				if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
    					environmentalExemption
    							.setEnvironmentalExemptionExplanation(answerExplanation
    									.trim().substring(0,EXPLANATION_MAX_LENGTH));
    				} else {
    					environmentalExemption.setEnvironmentalExemptionExplanation(answerExplanation.trim());
    				}
    			} else if (YnqConstant.YES.code().equals(ynqAnswer)) {
    			    environmentalExemption.setEnvironmentalExemptionExplanation(null);
    			}
    		    environmentalImpact.setEnvironmentalExemption(environmentalExemption);
    
    		}
		} else {
		    environmentalExemption.setEnvironmentalExemptionIndicator(null);
        }    		
	}

	private void setInternationalActivities(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		InternationalActivities internationalActivities = InternationalActivities.Factory
				.newInstance();
		YesNoDataType.Enum answer;
		String answerExplanation;
		String internationalActivitiesAnswer = getAnswer(INTERNATIONAL_ACTIVITIES_YNQ, answerHeaders);
		if (internationalActivitiesAnswer != null && !internationalActivitiesAnswer.equals(NOT_ANSWERED)) {
    		answer = YnqConstant.YES.code().equals(
                    internationalActivitiesAnswer) ? YesNoDataType.Y_YES : YesNoDataType.N_NO;
    		answerExplanation = getAnswer(INTERNATIONAL_ACTIVITIES_EXPL, answerHeaders);
    		internationalActivities.setInternationalActivitiesIndicator(answer);
    		if (answerExplanation != null && !answerExplanation.equals(NOT_ANSWERED)) {
    			if (answerExplanation.trim().length() > EXPLANATION_MAX_LENGTH) {
    				internationalActivities
    						.setActivitiesPartnershipsCountries(answerExplanation
    								.trim()
    								.substring(0, EXPLANATION_MAX_LENGTH));
    			} else {
    				internationalActivities
    						.setActivitiesPartnershipsCountries(answerExplanation
    								.trim());
    			}
    			if (getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION, answerHeaders) != null) {
    			    internationalActivities.setInternationalActivitiesExplanation(getChildQuestionAnswer(INTERNATIONAL_ACTIVITIES_YNQ, EXPLANATION, answerHeaders));
    			}
    		} else if (YnqConstant.YES.code().equals(internationalActivitiesAnswer)) {
    		    internationalActivities.setActivitiesPartnershipsCountries(answerExplanation);
    		}
		} else {
		    internationalActivities.setInternationalActivitiesIndicator(null);
		}
		rrOtherProjectInfo.setInternationalActivities(internationalActivities);
	}

	private void setVertebrateAnimalsSupplementDetails(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo,
			OrganizationContract organization,
			ProposalSpecialReviewContract proposalSpecialReview) {
		rrOtherProjectInfo.setVertebrateAnimalsIndicator(YesNoDataType.Y_YES);
		VertebrateAnimalsSupplement vertebrateAnimalsSupplement = VertebrateAnimalsSupplement.Factory
				.newInstance();
		setVertebrateAnimalsIACUCReviewDetails(proposalSpecialReview,
				vertebrateAnimalsSupplement);
		if (organization != null
				&& organization.getAnimalWelfareAssurance() != null) {
			vertebrateAnimalsSupplement.setAssuranceNumber(organization
					.getAnimalWelfareAssurance());
		}
		rrOtherProjectInfo
				.setVertebrateAnimalsSupplement(vertebrateAnimalsSupplement);
	}

	private void setHumaSubjectSupplementDetails(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo,
			OrganizationContract organization,
			ProposalSpecialReviewContract proposalSpecialReview) {
		rrOtherProjectInfo.
		setHumanSubjectsIndicator(YesNoDataType.Y_YES);
		HumanSubjectsSupplement humanSubjectsSupplement = HumanSubjectsSupplement.Factory
				.newInstance();
		HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers = HumanSubjectsSupplement.ExemptionNumbers.Factory
				.newInstance();

		if (proposalSpecialReview.getApprovalType() != null) {
			
			
			setExemptions(proposalSpecialReview, humanSubjectsSupplement,
					exemptionNumbers);
			setHumanSubjectIRBReviewIndicator(proposalSpecialReview,
					humanSubjectsSupplement);
		}
		Boolean paramValue= s2SConfigurationService.getValueAsBoolean(ConfigurationConstants.IRB_PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED);
		 if(paramValue){
			if (proposalSpecialReview.getSpecialReviewExemptions() != null && !proposalSpecialReview.getSpecialReviewExemptions().isEmpty()) {
				humanSubjectsSupplement.setExemptFedReg(YesNoDataType.Y_YES);				
				List<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum> exemptionNumberList = new ArrayList<>();
				HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum exemptionNumberEnum;
				for (ProposalSpecialReviewExemptionContract exemption : proposalSpecialReview.getSpecialReviewExemptions()) {
					exemptionNumberEnum = HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum
							.forInt(Integer.parseInt(exemption.getExemptionType().getCode()));
					exemptionNumberList.add(exemptionNumberEnum);	
			
		}
				exemptionNumbers
				.setExemptionNumberArray(exemptionNumberList
						.toArray(new HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum[1]));
		humanSubjectsSupplement.setExemptionNumbers(exemptionNumbers);} else {
			if (proposalSpecialReview.getApprovalDate() != null) {
				humanSubjectsSupplement
				.setHumanSubjectIRBReviewIndicator(YesNoDataType.N_NO);
			}else{
				humanSubjectsSupplement
				.setHumanSubjectIRBReviewIndicator(YesNoDataType.Y_YES);
			}
		}
			}
		if (organization != null && organization.getHumanSubAssurance() != null) {
			humanSubjectsSupplement.setHumanSubjectAssuranceNumber(organization
					.getHumanSubAssurance().substring(3));
		}
		if (humanSubjectsSupplement != null) {
			rrOtherProjectInfo
					.setHumanSubjectsSupplement(humanSubjectsSupplement);
		}
	}

	private void setVertebrateAnimalsIACUCReviewDetails(
			ProposalSpecialReviewContract proposalSpecialReview,
			VertebrateAnimalsSupplement vertebrateAnimalsSupplement) {
		if (SPECIAL_REVIEW_ANIMAL_USAGE.equals(proposalSpecialReview
				.getApprovalType().getCode())) {
			vertebrateAnimalsSupplement
					.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
		} else {
			vertebrateAnimalsSupplement
					.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
			if (proposalSpecialReview.getApprovalDate() != null) {
				vertebrateAnimalsSupplement
						.setVertebrateAnimalsIACUCApprovalDateReviewDate(s2SDateTimeService
								.convertDateToCalendar(proposalSpecialReview
										.getApprovalDate()));
			}
		}
		if (s2SConfigurationService.getValueAsBoolean(ConfigurationConstants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER)) {
		    if (proposalSpecialReview.getApprovalDate() == null) {
		        vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.Y_YES);
		    } else {
		        vertebrateAnimalsSupplement.setVertebrateAnimalsIACUCReviewIndicator(YesNoDataType.N_NO);
		    }
		}
	}

	private void setExemptions(ProposalSpecialReviewContract proposalSpecialReview,
			HumanSubjectsSupplement humanSubjectsSupplement,
			HumanSubjectsSupplement.ExemptionNumbers exemptionNumbers) {
	
		if (Integer.parseInt(proposalSpecialReview.getApprovalType().getCode()) == APPROVAL_TYPE_EXCEMPT) {
			if (proposalSpecialReview.getSpecialReviewExemptions() != null) {
				List<HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum> exemptionNumberList = new ArrayList<>();
				HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum exemptionNumberEnum;
				for (ProposalSpecialReviewExemptionContract exemption : proposalSpecialReview.getSpecialReviewExemptions()) {
					exemptionNumberEnum = HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum
							.forInt(Integer.parseInt(exemption.getExemptionType().getCode()));
					exemptionNumberList.add(exemptionNumberEnum);
				}
				exemptionNumbers
						.setExemptionNumberArray(exemptionNumberList
								.toArray(new HumanSubjectsSupplement.ExemptionNumbers.ExemptionNumber.Enum[1]));
				humanSubjectsSupplement.setExemptionNumbers(exemptionNumbers);
			}
			humanSubjectsSupplement.setExemptFedReg(YesNoDataType.Y_YES);
		} 
		else{
		    humanSubjectsSupplement.setExemptFedReg(YesNoDataType.N_NO);
		}
		
	}

	private void setHumanSubjectIRBReviewIndicator(
			ProposalSpecialReviewContract proposalSpecialReview,
			HumanSubjectsSupplement humanSubjectsSupplement) {
        if (SPECIAL_REVIEW_HUMAN_SUBJECTS.equals(proposalSpecialReview
                .getApprovalType().getCode())) {
            humanSubjectsSupplement
                    .setHumanSubjectIRBReviewIndicator(YesNoDataType.Y_YES);
        } else {
            humanSubjectsSupplement
                    .setHumanSubjectIRBReviewIndicator(YesNoDataType.N_NO);
            if (proposalSpecialReview.getApprovalDate() != null) {
                humanSubjectsSupplement
                        .setHumanSubjectIRBReviewDate(s2SDateTimeService
                                .convertDateToCalendar(proposalSpecialReview
                                        .getApprovalDate()));
            }
        }
    }

	private void setAttachments(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
        Boolean isOtherAttachmentsExists = false;
        AttachedFileDataType attachedFileDataType;
        ProjectNarrativeAttachments projectNarrativeAttachments = ProjectNarrativeAttachments.Factory.newInstance();
        AbstractAttachments abstractAttachments = AbstractAttachments.Factory.newInstance();
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType().getCode() != null) {
                switch(Integer.parseInt(narrative.getNarrativeType().getCode())){
                    case(EQUIPMENT_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){
                            EquipmentAttachments equipmentAttachments = EquipmentAttachments.Factory.newInstance();
                            equipmentAttachments.setEquipmentAttachment(attachedFileDataType);
                            rrOtherProjectInfo.setEquipmentAttachments(equipmentAttachments);
                        }
                    break;
                    case(FACILITIES_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){
                            FacilitiesAttachments facilitiesAttachments = FacilitiesAttachments.Factory.newInstance();
                            facilitiesAttachments.setFacilitiesAttachment(attachedFileDataType);
                            rrOtherProjectInfo.setFacilitiesAttachments(facilitiesAttachments);
                        }
                    break;
                    case(NARRATIVE_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){
                            projectNarrativeAttachments.setProjectNarrativeAttachment(attachedFileDataType);
                        }
                    break;
                    case(BIBLIOGRAPHY_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){
                            BibliographyAttachments bibliographyAttachments = BibliographyAttachments.Factory.newInstance();
                            bibliographyAttachments.setBibliographyAttachment(attachedFileDataType);
                            rrOtherProjectInfo.setBibliographyAttachments(bibliographyAttachments);
                        }
                    break;
                    case(ABSTRACT_PROJECT_SUMMARY_ATTACHMENT):
                        attachedFileDataType = getAttachedFileType(narrative);
                        if(attachedFileDataType != null){
                            abstractAttachments.setAbstractAttachment(attachedFileDataType);
                        }
                    break;
                    case(OTHER_ATTACHMENT):
                        isOtherAttachmentsExists = true;
                    break;
                }
            }
        }
        rrOtherProjectInfo.setProjectNarrativeAttachments(projectNarrativeAttachments);
        rrOtherProjectInfo.setAbstractAttachments(abstractAttachments);
        if (isOtherAttachmentsExists) {
            setOtherAttachments(rrOtherProjectInfo);
        }
	}

	private void setOtherAttachments(
			RROtherProjectInfo13Document.RROtherProjectInfo13 rrOtherProjectInfo) {
		OtherAttachments otherAttachments = OtherAttachments.Factory
				.newInstance();
		otherAttachments.setOtherAttachmentArray(getAttachedFileDataTypes());
		rrOtherProjectInfo.setOtherAttachments(otherAttachments);
	}


	private AttachedFileDataType[] getAttachedFileDataTypes() {
		AttachedFileDataType attachedFileDataType;
		List<AttachedFileDataType> attachedFileDataTypeList = new ArrayList<>();
		DevelopmentProposalContract developmentProposal = pdDoc
				.getDevelopmentProposal();
		for (NarrativeContract narrative : developmentProposal.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& (Integer.parseInt(narrative.getNarrativeType().getCode()) == OTHER_ATTACHMENT)) {
				attachedFileDataType= getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					attachedFileDataTypeList.add(attachedFileDataType);
				}
			}
		}
		return attachedFileDataTypeList.toArray(new AttachedFileDataType[0]);
	}

	@Override
	public RROtherProjectInfo13Document getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getRROtherProjectInfo();
	}

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
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
