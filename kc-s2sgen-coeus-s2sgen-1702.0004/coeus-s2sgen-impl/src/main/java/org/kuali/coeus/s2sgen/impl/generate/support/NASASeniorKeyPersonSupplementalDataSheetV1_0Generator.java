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

import gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.CoItypeDataType;
import gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument;
import gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet;
import gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPersonAttachment;
import gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPersonAttachment.NASASeniorKeyPersonSupplementalDataSheetAtt;
import gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPersonAttachment.NASASeniorKeyPersonSupplementalDataSheetAtt.SeniorKeyPerson;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.attachmentsV10.AttachmentGroupMin0Max100DataType;
import gov.grants.apply.system.globalLibraryV20.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.person.S2SProposalPersonService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.budget.S2SBudgetCalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.*;
import java.util.stream.Collectors;

@FormGenerator("NASASeniorKeyPersonSupplementalDataSheetV1_0Generator")
public class NASASeniorKeyPersonSupplementalDataSheetV1_0Generator extends
		S2SBaseFormGenerator {

    @Value("http://apply.grants.gov/forms/NASA_SeniorKeyPersonSupplementalDataSheet-V1.0")
    private String namespace;

    @Value("NASA_SeniorKeyPersonSupplementalDataSheet-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/NASA_SeniorKeyPersonSupplementalDataSheet-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.NASASeniorKeyPersonSupplementalDataSheetV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

    @Autowired
    @Qualifier("s2SProposalPersonService")
	private S2SProposalPersonService s2SProposalPersonService;

    @Autowired
    @Qualifier("s2SBudgetCalculatorService")
	private S2SBudgetCalculatorService s2sBudgetCalculatorService;

    @Autowired
    @Qualifier("rolodexService")
	private RolodexService rolodexService;

    @Autowired
    @Qualifier("sponsorService")
    private SponsorService sponsorService;

	private static final String COLLABORATOR = "COLLABORATOR";
	private static final String COUNTRY_CODE_USA = "USA";
	private static final String COUNTRY_CODE_PRI = "PRI";
	private static final String COUNTRY_CODE_VIR = "VIR";
	private static final String CO_INVESTIGATOR = "COI";
	private static final String ROLE_COLLABORATOR = "COLLABORATOR";

	private static final String ATTACHED_ATTACHMENT_1 = "Attached Attachment 1";
	private static final String ATTACHED_ATTACHMENT_2 = "Attached Attachment 2";
	private static final String ATTACHED_ATTACHMENT_3 = "Attached Attachment 3";
	private static final String ATTACHED_ATTACHMENT_4 = "Attached Attachment 4";

	private static final String ATTACHMENT_TYPE_BUDGET_DETAILS = "3";
	private static final String ATTACHMENT_TYPE_STATEMENT_OF_COMMITMENT_DOC = "4";
	protected static final int MAX_KEY_PERSON_COUNT = 8;

	private List<ProposalPersonContract> extraPersons = new ArrayList<>();

	private NASASeniorKeyPersonSupplementalDataSheetDocument getNasaSeniorKeyPersonSupplementalDataSheetDocument() {
		NASASeniorKeyPersonSupplementalDataSheetDocument nasaSeniorKPDoc = NASASeniorKeyPersonSupplementalDataSheetDocument.Factory
				.newInstance();
		NASASeniorKeyPersonSupplementalDataSheet nasaSeniorKPDataSheet = nasaSeniorKPDoc.addNewNASASeniorKeyPersonSupplementalDataSheet();
		nasaSeniorKPDataSheet.setFormVersion(FormVersion.v1_0.getVersion());
		populateInvestigators(nasaSeniorKPDataSheet);
		if (extraPersons != null && extraPersons.size() > 0) {
			int extraPersonNumber = extraPersons.size();
			int attPersons;

			List<NASASeniorKeyPersonSupplementalDataSheetAtt> nasaSeniorKPAttList = new ArrayList<>();
			for (int begin = 0, index = 0; begin < extraPersonNumber; begin = begin + 8, index++) {
				NASASeniorKeyPersonSupplementalDataSheetAtt nasaSeniorKPAtt = NASASeniorKeyPersonSupplementalDataSheetAtt.Factory
						.newInstance();

				if (extraPersonNumber - begin > 8) {
					attPersons = begin + 8;
				} else {
					attPersons = extraPersonNumber;
				}
				List<SeniorKeyPerson> seniorKeyPersonList = new ArrayList<>();
				for (int cnt = begin; cnt < attPersons; cnt++) {
					seniorKeyPersonList.add(getExtraPerson(extraPersons.get(cnt)));
				}
				SeniorKeyPerson seniorKeyPersons[] = new SeniorKeyPerson[0];
				seniorKeyPersons = seniorKeyPersonList
						.toArray(seniorKeyPersons);
				nasaSeniorKPAtt.setSeniorKeyPersonArray(seniorKeyPersons);
				nasaSeniorKPAttList.add(nasaSeniorKPAtt);

				if (begin == 0) {
					nasaSeniorKPDataSheet.setAttachment1(ATTACHED_ATTACHMENT_1);
				} else if (begin == 8) {
					nasaSeniorKPDataSheet.setAttachment2(ATTACHED_ATTACHMENT_2);
				} else if (begin == 16) {
					nasaSeniorKPDataSheet.setAttachment3(ATTACHED_ATTACHMENT_3);
				} else if (begin == 24) {
					nasaSeniorKPDataSheet.setAttachment4(ATTACHED_ATTACHMENT_4);
				}
			}
			SeniorKeyPersonAttachment seniorKeyPersonAttachment = SeniorKeyPersonAttachment.Factory
					.newInstance();
			NASASeniorKeyPersonSupplementalDataSheetAtt[]  nasaSeniorKPAtts =  
			                                        new NASASeniorKeyPersonSupplementalDataSheetAtt[0];
			nasaSeniorKPAtts = nasaSeniorKPAttList.toArray(nasaSeniorKPAtts);
			seniorKeyPersonAttachment
					.setNASASeniorKeyPersonSupplementalDataSheetAttArray(nasaSeniorKPAtts);
			nasaSeniorKPDataSheet
					.setSeniorKeyPersonAttachment(seniorKeyPersonAttachment);
		}
		return nasaSeniorKPDoc;
	}

	private void populateInvestigators(NASASeniorKeyPersonSupplementalDataSheet nasaSeniorKPDataSheet) {
		List<? extends ProposalPersonContract> proposalPersonns = pdDoc.getDevelopmentProposal()
				.getProposalPersons();
		Collections.sort(proposalPersonns, new ProposalPersonComparator());
		List<ProposalPersonContract> keyPersons = pdDoc.getDevelopmentProposal()
				.getProposalPersons().stream()
				.filter(proposalPerson -> proposalPerson.getProposalPersonRoleId() != null
						&& (CO_INVESTIGATOR.equalsIgnoreCase(proposalPerson.getProposalPersonRoleId())
						|| ROLE_COLLABORATOR.equalsIgnoreCase(proposalPerson.getProjectRole())))
				.collect(Collectors.toCollection(LinkedList::new));

		if(keyPersons.isEmpty()){
		    nasaSeniorKPDataSheet.addNewSeniorKeyPerson();
		    return;
		}
		
		List<ProposalPersonContract> nKeyPersons = s2SProposalPersonService.getNKeyPersons(
				keyPersons, MAX_KEY_PERSON_COUNT);

		extraPersons = keyPersons.stream()
				.filter(kp -> !nKeyPersons.contains(kp))
				.collect(Collectors.toList());
		List<gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPerson> seniorKeyPersonList = new LinkedList<>();
		for (ProposalPersonContract proposalPerson : nKeyPersons) {
			seniorKeyPersonList.add(getPerson(proposalPerson));
		}

		gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPerson[] seniorKeyPersonsArray = new gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPerson[0];
		nasaSeniorKPDataSheet.setSeniorKeyPersonArray(seniorKeyPersonList.toArray(seniorKeyPersonsArray));
	}

	private gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPerson getPerson(
			ProposalPersonContract proposalPerson) {
		gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPerson seniorKeyPerson = gov.grants.apply.forms.nasaSeniorKeyPersonSupplementalDataSheetV10.NASASeniorKeyPersonSupplementalDataSheetDocument.NASASeniorKeyPersonSupplementalDataSheet.SeniorKeyPerson.Factory
				.newInstance();
		seniorKeyPerson.setInternationalParticipation(YesNoDataType.N_NO); // default
		seniorKeyPerson.setUSGovernmentParticipation(YesNoDataType.N_NO); // default
		int sponsortType = -1;

		if (proposalPerson.getProposalPersonRoleId() != null
				&& proposalPerson.getProposalPersonRoleId()
						.equals(COLLABORATOR)) {
			seniorKeyPerson.setNASACoItype(CoItypeDataType.COLLABORATOR);
		}

		if (s2sBudgetCalculatorService.isPersonNonMITPerson(proposalPerson)) {
			if (proposalPerson.getRolodexId() != null) {
				RolodexContract rolodex = rolodexService.getRolodex(proposalPerson.getRolodexId());
				if (rolodex != null) {
                    final SponsorContract rolodexSponsor = sponsorService.getSponsor(rolodex.getSponsorCode());
                    final SponsorContract proposalSponsor = pdDoc.getDevelopmentProposal().getSponsor();
                    if (rolodexSponsor != null && rolodexSponsor.getSponsorCode().equals(rolodexSponsor.getSponsorCode())) {
                        if (rolodexSponsor.getSponsorType().getCode() != null
								    && rolodexSponsor.getSponsorType().getCode().equals(
								            proposalSponsor.getSponsorType().getCode())) {
							sponsortType = Integer.parseInt(rolodexSponsor.getSponsorType().getCode());
							if (sponsortType == 0) {
								seniorKeyPerson
										.setUSGovernmentParticipation(YesNoDataType.Y_YES);
							} else if (sponsortType > 9) {
								seniorKeyPerson
										.setInternationalParticipation(YesNoDataType.Y_YES);
							}
						}
                    }
					// if no sponsor in rolodex is found, then use person's
					// country
					if (sponsortType == -1
							&& rolodex.getCountryCode() != null
							&& ((rolodex.getCountryCode().equals(COUNTRY_CODE_USA)
									|| rolodex.getCountryCode().equals(COUNTRY_CODE_PRI) 
									|| rolodex.getCountryCode().equals(COUNTRY_CODE_VIR)))) {
						seniorKeyPerson
								.setNASACoItype(CoItypeDataType.CO_I_INSTITUTIONAL_PI);
					} else {
						seniorKeyPerson
								.setNASACoItype(CoItypeDataType.CO_I_CO_PI_NON_U_S_ORGANIZATION_ONLY);
						seniorKeyPerson
								.setInternationalParticipation(YesNoDataType.Y_YES);
					}
				}
			}
		} else {
			seniorKeyPerson.setNASACoItype(CoItypeDataType.CO_I);
		}
		if (proposalPerson.getProposalPersonRoleId() != null
                && proposalPerson.isKeyPerson()
                && proposalPerson.getProjectRole().equalsIgnoreCase(COLLABORATOR)) {
            seniorKeyPerson.setNASACoItype(CoItypeDataType.COLLABORATOR);
        }		

		// set total dollar amount requested. There is no budget for rolodex
		// person.
		// seniorKeyPerson.setFederalAgencyDollar(null);
		// Above logic same as in Coeus

		seniorKeyPerson.setSeniorKeyPersonName(globLibV20Generator
				.getHumanNameDataType(proposalPerson));

		AttachedFileDataType commitmentAttachment = getPernonnelAttachments(
				pdDoc, proposalPerson.getPersonId(), proposalPerson
						.getRolodexId(),
				ATTACHMENT_TYPE_STATEMENT_OF_COMMITMENT_DOC);
		if (commitmentAttachment != null) {
			AttachmentGroupMin0Max100DataType attachmentGroup = AttachmentGroupMin0Max100DataType.Factory
					.newInstance();
			AttachedFileDataType[] commitmentAttachmentArray = new AttachedFileDataType[1];
			commitmentAttachmentArray[0] = commitmentAttachment;
			attachmentGroup.setAttachedFileArray(commitmentAttachmentArray);
			seniorKeyPerson.setStatementofCommitment(attachmentGroup);
		}

		AttachedFileDataType budgetAttachment = getPernonnelAttachments(pdDoc,
				proposalPerson.getPersonId(), proposalPerson.getRolodexId(),
				ATTACHMENT_TYPE_BUDGET_DETAILS);
		if (budgetAttachment != null) {
			AttachmentGroupMin0Max100DataType attachmentGroup = AttachmentGroupMin0Max100DataType.Factory
					.newInstance();
			AttachedFileDataType[] budgetAttachmentArray = new AttachedFileDataType[1];
			budgetAttachmentArray[0] = budgetAttachment;
			attachmentGroup.setAttachedFileArray(budgetAttachmentArray);
			seniorKeyPerson.setBudgetDetails(attachmentGroup);
		}
		return seniorKeyPerson;
	}

	private SeniorKeyPerson getExtraPerson(ProposalPersonContract proposalPerson) {
		SeniorKeyPerson seniorKeyPerson = SeniorKeyPerson.Factory.newInstance();
		seniorKeyPerson.setInternationalParticipation(YesNoDataType.N_NO); // default
		seniorKeyPerson.setUSGovernmentParticipation(YesNoDataType.N_NO); // default
		int sponsortType = -1;

		if (proposalPerson.getProposalPersonRoleId() != null
				&& proposalPerson.getProposalPersonRoleId()
						.equals(COLLABORATOR)) {
			seniorKeyPerson.setNASACoItype(CoItypeDataType.COLLABORATOR);
		}
		if (s2sBudgetCalculatorService.isPersonNonMITPerson(proposalPerson)) {
			if (proposalPerson.getRolodexId() != null) {
				RolodexContract rolodex = rolodexService.getRolodex(proposalPerson.getRolodexId());
				if (rolodex != null) {
					if (rolodex.getSponsorCode() != null
							&& rolodex.getSponsorCode().equals(
									pdDoc.getDevelopmentProposal()
											.getSponsor().getSponsorCode())) {

                        final SponsorContract sponsor = sponsorService.getSponsor(rolodex.getSponsorCode());
                        if (sponsor != null
								&& sponsor.getSponsorType().getCode() != null
								&& Integer.parseInt(sponsor
										.getSponsorType().getCode()) == Integer
										.parseInt(pdDoc
												.getDevelopmentProposal()
												.getSponsor()
												.getSponsorType().getCode())) {
							sponsortType = Integer.parseInt(sponsor.getSponsorType().getCode());
							if (sponsortType == 0) {
								seniorKeyPerson
										.setUSGovernmentParticipation(YesNoDataType.Y_YES);
							} else if (sponsortType > 9) {
								seniorKeyPerson
										.setInternationalParticipation(YesNoDataType.Y_YES);
							}
						}
					}
					// if no sponsor in rolodex is found, then use person's
					// country
					if (sponsortType == -1
							&& rolodex.getCountryCode() != null
							&& ((rolodex.getCountryCode().equals(
									COUNTRY_CODE_USA)
									|| rolodex.getCountryCode().equals(
											COUNTRY_CODE_PRI) || rolodex
									.getCountryCode().equals(COUNTRY_CODE_VIR)))) {
						seniorKeyPerson
								.setNASACoItype(CoItypeDataType.CO_I_INSTITUTIONAL_PI);
					} else {
						seniorKeyPerson
								.setNASACoItype(CoItypeDataType.CO_I_CO_PI_NON_U_S_ORGANIZATION_ONLY);
						seniorKeyPerson
								.setInternationalParticipation(YesNoDataType.Y_YES);
					}
				}
			}
		} else {
			seniorKeyPerson.setNASACoItype(CoItypeDataType.CO_I);
		}
		// set total dollar amount requested. There is no budget for rolodex
		// person.
		// seniorKeyPerson.setFederalAgencyDollar(null);
		// Above logic same as in Coeus

		seniorKeyPerson.setSeniorKeyPersonName(globLibV20Generator
				.getHumanNameDataType(proposalPerson));

		AttachedFileDataType commitmentAttachment = getPernonnelAttachments(
				pdDoc, proposalPerson.getPersonId(), proposalPerson
						.getRolodexId(),
				ATTACHMENT_TYPE_STATEMENT_OF_COMMITMENT_DOC);
		if (commitmentAttachment != null) {
			AttachmentGroupMin0Max100DataType attachmentGroup = AttachmentGroupMin0Max100DataType.Factory
					.newInstance();
			AttachedFileDataType[] commitmentAttachmentArray = new AttachedFileDataType[1];
			commitmentAttachmentArray[0] = commitmentAttachment;
			attachmentGroup.setAttachedFileArray(commitmentAttachmentArray);
			seniorKeyPerson.setStatementofCommitment(attachmentGroup);
		}

		AttachedFileDataType budgetAttachment = getPernonnelAttachments(pdDoc,
				proposalPerson.getPersonId(), proposalPerson.getRolodexId(),
				ATTACHMENT_TYPE_BUDGET_DETAILS);
		if (budgetAttachment != null) {
			AttachmentGroupMin0Max100DataType attachmentGroup = AttachmentGroupMin0Max100DataType.Factory
					.newInstance();
			AttachedFileDataType[] budgetAttachmentArray = new AttachedFileDataType[1];
			budgetAttachmentArray[0] = budgetAttachment;
			attachmentGroup.setAttachedFileArray(budgetAttachmentArray);
			seniorKeyPerson.setBudgetDetails(attachmentGroup);
		}
		return seniorKeyPerson;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link NASASeniorKeyPersonSupplementalDataSheetDocument} by populating
	 * data from the given {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	@Override
	public NASASeniorKeyPersonSupplementalDataSheetDocument getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getNasaSeniorKeyPersonSupplementalDataSheetDocument();
	}

    public S2SProposalPersonService getS2SProposalPersonService() {
        return s2SProposalPersonService;
    }

    public void setS2SProposalPersonService(S2SProposalPersonService s2SProposalPersonService) {
        this.s2SProposalPersonService = s2SProposalPersonService;
    }

    public S2SBudgetCalculatorService getS2sBudgetCalculatorService() {
        return s2sBudgetCalculatorService;
    }

    public void setS2sBudgetCalculatorService(S2SBudgetCalculatorService s2sBudgetCalculatorService) {
        this.s2sBudgetCalculatorService = s2sBudgetCalculatorService;
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public SponsorService getSponsorService() {
        return sponsorService;
    }

    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
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
