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

import gov.grants.apply.forms.rrKeyPersonV11.PersonProfileDataType;
import gov.grants.apply.forms.rrKeyPersonV11.PersonProfileDataType.Profile;
import gov.grants.apply.forms.rrKeyPersonV11.PersonProfileDataType.Profile.OtherProjectRoleCategory;
import gov.grants.apply.forms.rrKeyPersonV11.ProjectRoleDataType;
import gov.grants.apply.forms.rrKeyPersonV11.RRKeyPersonDocument;
import gov.grants.apply.forms.rrKeyPersonV11.RRKeyPersonDocument.RRKeyPerson;
import gov.grants.apply.forms.rrKeyPersonV11.RRKeyPersonDocument.RRKeyPerson.AdditionalProfilesAttached;
import gov.grants.apply.forms.rrKeyPersonV11.RRKeyPersonDocument.RRKeyPerson.BioSketchsAttached;
import gov.grants.apply.forms.rrKeyPersonV11.RRKeyPersonDocument.RRKeyPerson.SupportsAttached;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.util.FieldValueConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@FormGenerator("RRKeyPersonV1_1Generator")
public class RRKeyPersonV1_1Generator extends RRKeyPersonBase {

	RolodexContract rolodex;

    @Value("http://apply.grants.gov/forms/RR_KeyPerson-V1.1")
    private String namespace;

    @Value("RR_KeyPerson-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/RR_KeyPerson-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrKeyPersonV11")
    private String packageName;

    @Value("150")
    private int sortIndex;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    private RRKeyPersonDocument getRRKeyPerson() {
        RRKeyPersonDocument rrKeyPersonDocument = RRKeyPersonDocument.Factory.newInstance();
        RRKeyPerson rrKeyPerson = RRKeyPerson.Factory.newInstance();
        rrKeyPerson.setFormVersion(FormVersion.v1_1.getVersion());
        rrKeyPerson.setPDPI(getPersonProfilePI());
        rrKeyPerson.setKeyPersonArray(getPersonProfileKeyPerson());
        saveKeyPersonAttachmentsToProposal();
        if (extraPersons.size() > 0) {
    		AttachedFileDataType attachedFileDataType;
            BioSketchsAttached bioSketchAttached = BioSketchsAttached.Factory.newInstance();
            for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                if (narrative.getNarrativeType().getCode() != null) {
                    switch(Integer.parseInt(narrative.getNarrativeType().getCode())){
                        case(BIOSKETCH_DOC_TYPE):
                            attachedFileDataType = getAttachedFileType(narrative);
                        if (attachedFileDataType != null) {
                            bioSketchAttached.setBioSketchAttached(attachedFileDataType);
                        }
                        break;
                        case(CURRENTPENDING_DOC_TYPE):
                            attachedFileDataType = getAttachedFileType(narrative);
                            if (attachedFileDataType != null) {
                                SupportsAttached supportsAttached = SupportsAttached.Factory.newInstance();
                                supportsAttached.setSupportAttached(attachedFileDataType);
                                rrKeyPerson.setSupportsAttached(supportsAttached);
                            }
                        break;
                        case(PROFILE_TYPE):
                            attachedFileDataType = getAttachedFileType(narrative);
                            if(attachedFileDataType != null){
                                AdditionalProfilesAttached additionalProfilesAttached = AdditionalProfilesAttached.Factory.newInstance();
                                additionalProfilesAttached.setAdditionalProfileAttached(attachedFileDataType);
                                rrKeyPerson.setAdditionalProfilesAttached(additionalProfilesAttached);
                            }
                        break;
                    }
                }
            }
            rrKeyPerson.setBioSketchsAttached(bioSketchAttached);
        }

        rrKeyPersonDocument.setRRKeyPerson(rrKeyPerson);
        return rrKeyPersonDocument;
    }

    private PersonProfileDataType getPersonProfilePI() {

        PersonProfileDataType personProfileDataType = PersonProfileDataType.Factory.newInstance();
        Profile profile = Profile.Factory.newInstance();
        ProposalPersonContract PI = s2SProposalPersonService.getPrincipalInvestigator(pdDoc);
        if (PI != null) {
            if (PI.getPersonId() != null) {
                pIPersonOrRolodexId = PI.getPersonId();
                rolodex = null;
            }
            else if (PI.getRolodexId() != null) {
                pIPersonOrRolodexId = PI.getRolodexId().toString();
                rolodex = rolodexService.getRolodex(Integer.valueOf(pIPersonOrRolodexId));
            }
            profile.setName(globLibV20Generator.getHumanNameDataType(PI));
            if (PI.getDirectoryTitle() != null) {
                if (PI.getDirectoryTitle().length() > DIRECTORY_TITLE_MAX_LENGTH) {
                    profile.setTitle(PI.getDirectoryTitle().substring(0, DIRECTORY_TITLE_MAX_LENGTH));
                }
                else {
                    profile.setTitle(PI.getDirectoryTitle());
                }
            }
            profile.setAddress(globLibV20Generator.getAddressDataType(PI));
            profile.setPhone(PI.getOfficePhone());
            if (StringUtils.isNotEmpty(PI.getFaxNumber())) {
                profile.setFax(PI.getFaxNumber());
            }
            profile.setEmail(PI.getEmailAddress());
            if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
                if (rolodex != null)
                    profile.setOrganizationName(rolodex.getOrganization());
                else
                    profile.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
            }
            if(PI.getHomeUnit() != null) {
                KcPersonContract kcPerson = PI.getPerson();
                String departmentName =  kcPerson.getOrganizationIdentifier();
                profile.setDepartmentName(StringUtils.substring(departmentName, 0, DEPARTMENT_NAME_MAX_LENGTH));
            }
            else
            {
                DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
                profile.setDepartmentName(StringUtils.substring(developmentProposal.getOwnedByUnit().getUnitName(), 0, DEPARTMENT_NAME_MAX_LENGTH));
            }
            String divisionName = PI.getDivision();
            if (divisionName != null) {
                profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
            }
            if (PI.getEraCommonsUserName() != null) {
                profile.setCredential(PI.getEraCommonsUserName());
            } else {
                if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                	getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + PI.getFullName(), AuditError.GG_LINK));
                }
            }
            profile.setProjectRole(ProjectRoleDataType.PD_PI);

            PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
                    .newInstance();
            AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(),
                    BIOSKETCH_TYPE);
            personBioSketch.setBioSketchAttached(bioSketchAttachment);
            profile.setBioSketchsAttached(personBioSketch);

            AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(),
                    CURRENT_PENDING_TYPE);
            if (supportAttachment != null) {
                PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
                        .newInstance();
                supportsAttached.setSupportAttached(supportAttachment);
                profile.setSupportsAttached(supportsAttached);
            }
            personProfileDataType.setProfile(profile);
        }
        return personProfileDataType;
    }

    private PersonProfileDataType[] getPersonProfileKeyPerson() {

        List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<>();
        List<? extends ProposalPersonContract> keyPersons = pdDoc.getDevelopmentProposal().getProposalPersons();
        Collections.sort(keyPersons, new ProposalPersonComparator());
        List<ProposalPersonContract> nKeyPersons = s2SProposalPersonService.getNKeyPersons(keyPersons, MAX_KEY_PERSON_COUNT);
        extraPersons = keyPersons.stream()
                .filter(kp -> !nKeyPersons.contains(kp))
                .collect(Collectors.toList());

        if (nKeyPersons.size() > 0) {
            for (ProposalPersonContract keyPerson : nKeyPersons) {
                if (pIPersonOrRolodexId != null) {
                    // Don't add PI to keyperson list
                    if (keyPerson.getPersonId() != null && keyPerson.getPersonId().equals(pIPersonOrRolodexId)) {
                        continue;
                    }
                    else if ((keyPerson.getRolodexId() != null) && pIPersonOrRolodexId.equals(keyPerson.getRolodexId().toString())) {
                        continue;
                    }
                }
                if (keyPerson.getPersonId() != null) {
                    pIPersonOrRolodexId = keyPerson.getPersonId();
                    rolodex = null;
                }
                else if (keyPerson.getRolodexId() != null) {
                    pIPersonOrRolodexId = keyPerson.getRolodexId().toString();
                    rolodex = rolodexService.getRolodex(Integer.valueOf(pIPersonOrRolodexId));
                }
                Profile profileKeyPerson = Profile.Factory.newInstance();
                profileKeyPerson.setName(globLibV20Generator.getHumanNameDataType(keyPerson));
                if (keyPerson.getDirectoryTitle() != null) {
                    profileKeyPerson.setTitle(keyPerson.getDirectoryTitle());
                }
                profileKeyPerson.setAddress(globLibV20Generator.getAddressDataType(keyPerson));
                profileKeyPerson.setPhone(keyPerson.getOfficePhone());
                if (StringUtils.isNotEmpty(keyPerson.getFaxNumber())) {
                    profileKeyPerson.setFax(keyPerson.getFaxNumber());
                }
                profileKeyPerson.setEmail(keyPerson.getEmailAddress());
                if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
                    if (rolodex != null)
                        profileKeyPerson.setOrganizationName(rolodex.getOrganization());
                    else
                        profileKeyPerson.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
                }
                if(keyPerson.getHomeUnit() != null) {
                    KcPersonContract kcPerson = keyPerson.getPerson();
                    String departmentName =  kcPerson.getOrganizationIdentifier();
                    profileKeyPerson.setDepartmentName(StringUtils.substring(departmentName, 0, DEPARTMENT_NAME_MAX_LENGTH));
                }
                else
                {
                    DevelopmentProposalContract developmentProposal = pdDoc.getDevelopmentProposal();
                    profileKeyPerson.setDepartmentName(StringUtils.substring(developmentProposal.getOwnedByUnit().getUnitName(), 0, DEPARTMENT_NAME_MAX_LENGTH));
                }
                String divisionName = keyPerson.getDivision();
                if (divisionName != null) {
                    profileKeyPerson.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
                }
                if (keyPerson.getEraCommonsUserName() != null) {
                    profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
                } else {
                    if (getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())) {
                        if (keyPerson.isMultiplePi()) {
                            getAuditErrors().add(new AuditError(AuditError.NO_FIELD_ERROR_KEY, ERROR_ERA_COMMON_USER_NAME + keyPerson.getFullName(),
                                    AuditError.GG_LINK));
                        }
                    }
                }
                if (keyPerson.isMultiplePi() || keyPerson.isCoInvestigator()) {
                	if(getSponsorHierarchyService().isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal().getSponsor().getSponsorCode())){
                	    if (keyPerson.isMultiplePi()) {
                	        profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                	    } else {
                	        profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                	    }
                	}else{
                		profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
                	}
                } else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
                    OtherProjectRoleCategory otherProjectRole = OtherProjectRoleCategory.Factory.newInstance();
                    String otherRole;
                    if (keyPerson.getProjectRole() != null) {
                        if (keyPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                            otherRole = keyPerson.getProjectRole().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                        }
                        else {
                            otherRole = keyPerson.getProjectRole();
                        }
                    }
                    else {
                        otherRole = FieldValueConstants.VALUE_UNKNOWN;
                    }
                    otherProjectRole.setStringValue(otherRole);
                    profileKeyPerson.setOtherProjectRoleCategory(otherProjectRole);
                }

                PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory
                        .newInstance();
                AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, keyPerson.getPersonId(), keyPerson
                        .getRolodexId(), BIOSKETCH_TYPE);
                personBioSketch.setBioSketchAttached(bioSketchAttachment);
                profileKeyPerson.setBioSketchsAttached(personBioSketch);

                AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, keyPerson.getPersonId(), keyPerson
                        .getRolodexId(), CURRENT_PENDING_TYPE);
                if (supportAttachment != null) {
                    PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory
                            .newInstance();
                    supportsAttached.setSupportAttached(supportAttachment);
                    profileKeyPerson.setSupportsAttached(supportsAttached);
                }

                PersonProfileDataType personProfileDataTypeKeyperson = PersonProfileDataType.Factory.newInstance();
                personProfileDataTypeKeyperson.setProfile(profileKeyPerson);
                personProfileDataTypeList.add(personProfileDataTypeKeyperson);
            }
        }
        PersonProfileDataType[] personProfileDataTypeArray = new PersonProfileDataType[0];
        personProfileDataTypeArray = personProfileDataTypeList.toArray(personProfileDataTypeArray);
        return personProfileDataTypeArray;
    }

    @Override
    public RRKeyPersonDocument getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRKeyPerson();
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
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
