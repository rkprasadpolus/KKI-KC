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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.HierarchyPersonnelSummary;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.hierarchy.ProposalBudgetHierarchyService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyAttachment;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcDataObject;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.OrderByField;
import org.kuali.rice.core.api.criteria.OrderDirection;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyKeyConstants.*;

@Component("proposalHierarchyService")
@Transactional
public class ProposalHierarchyServiceImpl implements ProposalHierarchyService {

    private static final Log LOG = LogFactory.getLog(ProposalHierarchyServiceImpl.class);

    private static final String HIERARCHY_REJECTED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.rejected";
    private static final String HIERARCHY_ENROUTE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.enroute";
    private static final String HIERARCHY_CANCEL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.cancel";
    private static final String HIERARCHY_DISAPPROVE_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.disapprove";
    private static final String HIERARCHY_PROCESSED_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.processed";
    private static final String HIERARCHY_FINAL_APPSTATUS="message.proposalDevelopment.workflow.applicationStatus.final";

    private static final String PROPOSAL_ROUTING_RETURNED_ANNOTATION = "message.proposalDevelopment.workflow.annotation.rejected";

    private static final String PROPOSAL_DEVELOPMENT_DOCUMENT_TYPE = "ProposalDevelopmentDocument";
    private static final String DOCUMENT_NEXTVALUES = "documentNextvalues";
    private static final String HIERARCHY_STATUS = "hierarchyStatus";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String CODE = "code";
    private static final String COMPLETE = "C";
    private static final String NARRATIVE_TYPE = "narrativeType";
    private static final String HIERARCHY_PROPOSAL_NUMBER = "hierarchyProposalNumber";
    private static final String NARRATIVE_TYPE_CODE = "narrativeTypeCode";
    private static final String HIERARCHY_UNIT_SYNC = "HIERARCHY_UNIT_SYNC";

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kcAuthorizationService;
    @Autowired
    @Qualifier("proposalHierarchyDao")
    private ProposalHierarchyDao proposalHierarchyDao;
    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService legacyNarrativeService;
    @Autowired
    @Qualifier("proposalPersonBiographyService")
    private ProposalPersonBiographyService proposalPersonBiographyService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;
    @Autowired
    @Qualifier("kcDocumentRejectionService")
    private KcDocumentRejectionService kcDocumentRejectionService;

    @Autowired
    @Qualifier("kradWorkflowDocumentService")
    private WorkflowDocumentService kradWorkflowDocumentService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("pessimisticLockService")
    private PessimisticLockService pessimisticLockService;
    
    @Autowired
    @Qualifier("proposalBudgetHierarchyService")
    private ProposalBudgetHierarchyService proposalBudgetHierarchyService;

    @Autowired
    @Qualifier("keyPersonnelService")
    private KeyPersonnelService keyPersonnelService;

    //Setters for dependency injection
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }
    public void setProposalHierarchyDao(ProposalHierarchyDao proposalHierarchyDao) {
        this.proposalHierarchyDao = proposalHierarchyDao;
    }
    public void setLegacyNarrativeService(LegacyNarrativeService narrativeService) {
        this.legacyNarrativeService = narrativeService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    @Override
    public String createHierarchy(DevelopmentProposal initialChild, String userId) {
        LOG.info(String.format("***Create Hierarchy using Proposal #%s", initialChild.getProposalNumber()));

        ProposalDevelopmentDocument newDoc = assembleDoc();

        // copy the initial information to the new parent proposal
        DevelopmentProposal hierarchy = newDoc.getDevelopmentProposal();
        copyInitialData(hierarchy, initialChild);

        // set hierarchy status
        setHierarchyStatus(initialChild.getProposalDocument(), newDoc);

        ProposalDevelopmentDocument hierarchyDoc = saveDocument(newDoc);

        copyOpportunity(initialChild, hierarchyDoc.getDevelopmentProposal());

        // add aggregator to the document
        kcAuthorizationService.addDocumentLevelRole(userId, RoleConstants.AGGREGATOR, hierarchyDoc);

        proposalBudgetHierarchyService.initializeBudget(hierarchyDoc.getDevelopmentProposal(), initialChild);
        
        //we are creating the attachments for the first time so do not sync personnel attachments at this time, just copy as is.
        linkChild(hierarchyDoc.getDevelopmentProposal(), initialChild, HierarchyBudgetTypeConstants.SubBudget.code(), false);
        setInitialPi(hierarchyDoc.getDevelopmentProposal(), initialChild);
        copyInitialAttachments(initialChild, hierarchyDoc.getDevelopmentProposal());

        addCreateDetails(newDoc);

        LOG.info(String.format("***Initial Child (#%s) linked to Parent (#%s)", initialChild.getProposalNumber(), hierarchyDoc.getDevelopmentProposal().getProposalNumber()));
        
        finalizeHierarchySync(hierarchyDoc.getDevelopmentProposal());
        
        // return the parent id
        LOG.info(String.format("***Hierarchy creation (#%s) complete", hierarchyDoc.getDevelopmentProposal().getProposalNumber()));
        return hierarchyDoc.getDevelopmentProposal().getProposalNumber();
    }

    private void addCreateDetails(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        proposalDevelopmentDocument.getDevelopmentProposal().setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        proposalDevelopmentDocument.getDevelopmentProposal().setCreateUser(getGlobalVariableService().getUserSession().getLoggedInUserPrincipalName());
    }

    protected ProposalDevelopmentDocument saveDocument(ProposalDevelopmentDocument newDoc) {
        ProposalDevelopmentDocument hierarchyDoc;
        // persist the document and add a budget
        try {
            hierarchyDoc = (ProposalDevelopmentDocument) documentService.saveDocument(newDoc);
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error saving new document: " + x);
        }
        return hierarchyDoc;
    }

    protected void setHierarchyStatus(ProposalDevelopmentDocument childDocument, ProposalDevelopmentDocument newDoc) {
        DevelopmentProposal hierarchy = newDoc.getDevelopmentProposal();
        hierarchy.setHierarchyStatus(HierarchyStatusConstants.Parent.code());
        String docDescription = childDocument.getDocumentHeader()
                .getDocumentDescription();
        newDoc.getDocumentHeader().setDocumentDescription(docDescription);
        newDoc.setDevelopmentProposal(hierarchy);
        hierarchy.setProposalDocument(newDoc);
    }

    protected ProposalDevelopmentDocument assembleDoc() {
        ProposalDevelopmentDocument newDoc;
        // manually assembling a new PDDoc here because the DocumentService will deny initiator permission without context
        // since a person with MAINTAIN_PROPOSAL_HIERARCHY permission is allowed to initiate IF they are creating a parent
        // we circumvent the initiator step altogether.
        try {
            WorkflowDocument workflowDocument = kradWorkflowDocumentService.createWorkflowDocument(PROPOSAL_DEVELOPMENT_DOCUMENT_TYPE, globalVariableService.getUserSession().getPerson());
            DocumentHeader documentHeader = new DocumentHeader();
            documentHeader.setWorkflowDocument(workflowDocument);
            documentHeader.setDocumentNumber(workflowDocument.getDocumentId());
            newDoc = new ProposalDevelopmentDocument();
            newDoc.setDocumentHeader(documentHeader);
            newDoc.setDocumentNumber(documentHeader.getDocumentNumber());
        }
        catch (WorkflowException x) {
            throw new ProposalHierarchyException("Error creating new document: " + x);
        }
        return newDoc;
    }

    @Override
    public void linkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal newChildProposal, String hierarchyBudgetTypeCode) throws ProposalHierarchyException {
            prepareHierarchySync(hierarchyProposal);
            linkChild(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode, true);
            finalizeHierarchySync(hierarchyProposal);
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateLinkToHierarchy(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if (hierarchyProposal == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_PROPOSAL_DOES_NOT_EXIST, Boolean.TRUE));
            return errors;
        } else {
                if (!hierarchyProposal.isParent()) {
                    errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_PROPOSAL_NOT_HIERARCHY_PARENT,
                                                                Boolean.TRUE, hierarchyProposal.getProposalNumber()));
                }
        }
        if (childProposal.isInHierarchy()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ProposalHierarchyKeyConstants.ERROR_NOT_HIERARCHY_CHILD, Boolean.TRUE, childProposal.getProposalNumber()));
        }
        errors.addAll(validateChildBudgetPeriods(hierarchyProposal,childProposal,true));
        errors.addAll(validateSponsor(childProposal, hierarchyProposal));
        errors.addAll(validateParent(hierarchyProposal));
        errors.addAll(validateIsParentLocked(hierarchyProposal));
        errors.addAll(validateIsAggregatorOnParent(hierarchyProposal));

        List<ProposalHierarchyErrorWarningDto> sponsorErrors = validateSponsor(childProposal, hierarchyProposal);

        errors.addAll(sponsorErrors);

        return errors;
    }

    @Override
    public DevelopmentProposal removeFromHierarchy(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        String hierarchyProposalNumber = childProposal.getHierarchyParentProposalNumber();
        DevelopmentProposal hierarchyProposal = getHierarchy(hierarchyProposalNumber);

        List<String> childProposalNumbers = proposalHierarchyDao.getHierarchyChildProposalNumbers(hierarchyProposalNumber);
        boolean isLast = childProposalNumbers.size()==1 && StringUtils.equals(childProposalNumbers.get(0), childProposal.getProposalNumber());
     
        childProposal.setHierarchyStatus(HierarchyStatusConstants.None.code());
        childProposal.setHierarchyParentProposalNumber(null);
        if (StringUtils.equalsIgnoreCase(hierarchyProposal.getHierarchyOriginatingChildProposalNumber(), childProposal.getProposalNumber()) && hierarchyProposal.getPrincipalInvestigator() != null) {
            hierarchyProposal.getPrincipalInvestigator().setHierarchyProposalNumber(null);
        }

        removeChildElements(hierarchyProposal, childProposal.getProposalNumber());
        removeAllChildPersonnelFromParent(hierarchyProposal, childProposal);

        if (isLast) {
            try {
                childProposal = dataObjectService.save(childProposal);
                Document doc = documentService.getByDocumentHeaderId(hierarchyProposal.getProposalDocument().getDocumentNumber());
                documentService.cancelDocument(doc, "Removed last child from Proposal Hierarchy");
                return childProposal;
            }
            catch (WorkflowException e) {
                throw new ProposalHierarchyException("Error cancelling empty parent proposal");
            }
        }
        else {
            //need to find out what the lowest number is
            //so we can make it the new primary child for budget syncs.
            String lowestProposalNumber = "";
            for( String proposalNumber : childProposalNumbers ) {
                if ( !StringUtils.equals(proposalNumber, childProposal.getProposalNumber() )) {
                    lowestProposalNumber = proposalNumber;
                    break;
                }
            }
            hierarchyProposal.setHierarchyOriginatingChildProposalNumber(lowestProposalNumber);
            dataObjectService.save(childProposal);
            dataObjectService.save(hierarchyProposal);
            return childProposal;
        }
    }

    protected void removeDeletedPersonnelFromParent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (Iterator<ProposalPerson> iterator = hierarchyProposal.getProposalPersons().iterator(); iterator.hasNext();) {
            ProposalPerson person = iterator.next();

            if (StringUtils.equals(person.getHierarchyProposalNumber(), childProposal.getProposalNumber()) &&
                childProposal.getProposalPersons().indexOf(person) == -1 &&
                    ((StringUtils.isNotBlank(person.getPersonId()) && !employeePersonInMultipleProposals(person.getPersonId(), hierarchyProposal)) ||
                            (person.getRolodexId() != null && !nonEmployeePersonInMultipleProposals(person.getRolodexId(), hierarchyProposal)))) {
                    //remove person from parent
                    iterator.remove();
                    // remove attachments also
                    getProposalPersonBiographyService().removePersonnelAttachmentForDeletedPerson(hierarchyProposal.getProposalDocument(), person);
            }
        }
    }

    protected void removeAllChildPersonnelFromParent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalPerson childPerson : childProposal.getProposalPersons()) {
            if ((StringUtils.isNotBlank(childPerson.getPersonId()) && !employeePersonInMultipleProposals(childPerson.getPersonId(), hierarchyProposal)) ||
                    (childPerson.getRolodexId() != null && !nonEmployeePersonInMultipleProposals(childPerson.getRolodexId(), hierarchyProposal))) {
                for (Iterator<ProposalPerson> parentIterator = hierarchyProposal.getProposalPersons().iterator(); parentIterator.hasNext(); ) {
                    ProposalPerson parentPerson = parentIterator.next();
                    if (StringUtils.equals(childPerson.getPersonId(), parentPerson.getPersonId())) {
                        //remove person from parent
                        parentIterator.remove();
                        // remove attachments also
                        getProposalPersonBiographyService().removePersonnelAttachmentForDeletedPerson(hierarchyProposal.getProposalDocument(), parentPerson);
                    }
                }
            }
        }
    }

    @Override
    public void synchronizeAllChildren(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        prepareHierarchySync(hierarchyProposal);
        synchronizeAll(hierarchyProposal);
        finalizeHierarchySync(hierarchyProposal);
        // because refresh reference does not work, having to retrieve and add to proposal
        // so it displays right.
        reinstateCollections(hierarchyProposal);
    }


    protected void reinstateCollections(DevelopmentProposal proposal) {
        reinstateDegreeInfo(proposal);
    }

    @Override
    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return getProposalHierarchyDao().getDevelopmentProposal(proposalNumber);
    }

    @Override
    public ProposalState getProposalState(String proposalNumber) {
        return getProposalHierarchyDao().getProposalState(proposalNumber);
    }

    @Override
    public void synchronizeAll(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        synchronizeAllChildProposals(hierarchyProposal);
    }

    @Override
    public void synchronizeChild(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getHierarchy(childProposal.getHierarchyParentProposalNumber());

        prepareHierarchySync(hierarchy);
        synchronizeChildProposal(hierarchy, childProposal, true, getHierarchyChildren(hierarchy.getProposalNumber()));
        finalizeHierarchySync(hierarchy);
    }
    
    @Override
    public DevelopmentProposal lookupParent(DevelopmentProposal childProposal) throws ProposalHierarchyException {
        return getHierarchy(childProposal.getHierarchyParentProposalNumber());
    }

    @Override
    public void linkChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, String hierarchyBudgetTypeCode, boolean syncPersonnelAttachments)
            throws ProposalHierarchyException {
        // set child to child status
        childProposal.setHierarchyStatus(HierarchyStatusConstants.Child.code());
        childProposal.setHierarchyParentProposalNumber(hierarchyProposal.getProposalNumber());
        childProposal.setHierarchyBudgetType(hierarchyBudgetTypeCode);
        // call synchronize

        synchronizeChildProposal(hierarchyProposal, childProposal, syncPersonnelAttachments, getHierarchyChildren(hierarchyProposal.getProposalNumber()));
    }

    protected void copyInitialData(DevelopmentProposal hierarchyProposal, DevelopmentProposal srcProposal)
            throws ProposalHierarchyException {
        // Required fields for saving document
        hierarchyProposal.setHierarchyOriginatingChildProposalNumber(srcProposal.getProposalNumber());
        hierarchyProposal.setSponsor(srcProposal.getSponsor());
        hierarchyProposal.setSponsorCode(srcProposal.getSponsorCode());
        hierarchyProposal.setProposalTypeCode(srcProposal.getProposalTypeCode());
        hierarchyProposal.setRequestedStartDateInitial(srcProposal.getRequestedStartDateInitial());
        hierarchyProposal.setRequestedEndDateInitial(srcProposal.getRequestedEndDateInitial());
        hierarchyProposal.setOwnedByUnit(srcProposal.getOwnedByUnit());
        hierarchyProposal.setOwnedByUnitNumber(srcProposal.getOwnedByUnitNumber());
        hierarchyProposal.setActivityType(srcProposal.getActivityType());
        hierarchyProposal.setActivityTypeCode(srcProposal.getActivityTypeCode());
        hierarchyProposal.setTitle(srcProposal.getTitle());

        // Sponsor & program information
        hierarchyProposal.setDeadlineDate(srcProposal.getDeadlineDate());
        hierarchyProposal.setDeadlineTime(srcProposal.getDeadlineTime());
        hierarchyProposal.setDeadlineType(srcProposal.getDeadlineType());
        hierarchyProposal.setAnticipatedAwardTypeCode(srcProposal.getAnticipatedAwardTypeCode());
        hierarchyProposal.setNoticeOfOpportunityCode(srcProposal.getNoticeOfOpportunityCode());
        hierarchyProposal.setCfdaNumber(srcProposal.getCfdaNumber());
        hierarchyProposal.setPrimeSponsorCode(srcProposal.getPrimeSponsorCode());
        hierarchyProposal.setNsfSequenceNumber(srcProposal.getNsfSequenceNumber());
        hierarchyProposal.setSponsorProposalNumber(srcProposal.getSponsorProposalNumber());
        hierarchyProposal.setAgencyDivisionCode(srcProposal.getAgencyDivisionCode());
        hierarchyProposal.setAgencyProgramCode(srcProposal.getAgencyProgramCode());
        hierarchyProposal.setSubcontracts(srcProposal.getSubcontracts());
        hierarchyProposal.setProgramAnnouncementNumber(srcProposal.getProgramAnnouncementNumber());
        hierarchyProposal.setProgramAnnouncementTitle(srcProposal.getProgramAnnouncementTitle());

        // Organization/location
        ProposalSite newSite;
        hierarchyProposal.getProposalSites().clear();
    
          for (ProposalSite site : srcProposal.getProposalSites()) {
            newSite = deepCopy(site);
            newSite.setDevelopmentProposal(null);
            for (CongressionalDistrict cd : newSite.getCongressionalDistricts()) {
                cd.setProposalSite(newSite);
            }
            hierarchyProposal.addProposalSite(newSite);
        }

        setDocumentNextValueForProposalSites(hierarchyProposal);

        // Delivery info
        hierarchyProposal.setMailBy(srcProposal.getMailBy());
        hierarchyProposal.setMailType(srcProposal.getMailType());
        hierarchyProposal.setMailAccountNumber(srcProposal.getMailAccountNumber());
        hierarchyProposal.setNumberOfCopies(srcProposal.getNumberOfCopies());
        hierarchyProposal.setMailingAddressId(srcProposal.getMailingAddressId());
        hierarchyProposal.setMailDescription(srcProposal.getMailDescription());
    }

    private void setDocumentNextValueForProposalSites(DevelopmentProposal hierarchyProposal) {
        List<DocumentNextvalue> documentNextValues = hierarchyProposal.getProposalDocument().getDocumentNextvalues();
        DocumentNextvalue documentNextvalue = new DocumentNextvalue();
        documentNextvalue.setPropertyName(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER);
        documentNextvalue.setNextValue(hierarchyProposal.getProposalSites().size() + 1);
        documentNextvalue.setDocumentKey(hierarchyProposal.getProposalDocument().getDocumentNumber());
        documentNextValues.add(documentNextvalue);
        hierarchyProposal.getProposalDocument().setDocumentNextvalues(documentNextValues);
    }

    protected void copyOpportunity(DevelopmentProposal srcProposal, DevelopmentProposal hierarchyProposal) {
        if (srcProposal.getS2sOpportunity() != null) {
            S2sOpportunity opportunity = deepCopy(srcProposal.getS2sOpportunity());
            opportunity.setDevelopmentProposal(hierarchyProposal);
            hierarchyProposal.setS2sOpportunity(opportunity);

            for (S2sOppForms form : opportunity.getS2sOppForms()) {
                form.getS2sOppFormsId().setProposalNumber(hierarchyProposal.getProposalNumber());
            }
        }
    }

    protected <T extends KcDataObject> T deepCopy(T oldObject) {
        return getDataObjectService().copyInstance(oldObject, CopyOption.RESET_OBJECT_ID, CopyOption.RESET_PK_FIELDS, CopyOption.RESET_VERSION_NUMBER);
    }

    /**
     * Synchronizes all child proposals to the parent.
     */
    @Override
    public boolean synchronizeAllChildProposals(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        boolean changed = false;

        // delete all multiple inst attachments right at the beginning
        deleteAllMultipleInternal(hierarchyProposal);
        final ProposalDevelopmentBudgetExt budget = proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal);
        proposalBudgetHierarchyService.removeMergeableChildBudgetElements(budget);

        finalizeHierarchySync(hierarchyProposal.getProposalDocument());

        final List<DevelopmentProposal> hierarchyChildren = getHierarchyChildren(hierarchyProposal.getProposalNumber());
        for (DevelopmentProposal childProposal : hierarchyChildren) {
            List<BudgetPeriod> oldBudgetPeriods = getOldBudgetPeriods(budget);
            ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
            childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
            
            removeChildElements(hierarchyProposal, childProposal.getProposalNumber());
            
            synchronizeKeywords(hierarchyProposal, childProposal);
            synchronizeSpecialReviews(hierarchyProposal, childProposal);
            synchronizePersons(hierarchyProposal, childProposal, principalInvestigator);
            synchronizeNarratives(hierarchyProposal, childProposal);
            // we deleted all internal at the beginning so just add now.
            addInternalAttachments(hierarchyProposal, childProposal);
            syncDegreeInfo(hierarchyProposal, childProposal);

            syncAllPersonnelAttachments(hierarchyProposal, childProposal);
            proposalBudgetHierarchyService.synchronizeChildBudget(hierarchyProposal, childProposal, oldBudgetPeriods);
            dataObjectService.save(childProposal);
            changed = true;
        }

        synchronizePersonUnits(hierarchyChildren, hierarchyProposal);

        return changed;
    }

    protected boolean isUnitSyncEnabled() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE,
                HIERARCHY_UNIT_SYNC);
    }

    protected void synchronizePersonUnits(List<DevelopmentProposal> hierarchyChildren, DevelopmentProposal parentProposal) {
        if(isUnitSyncEnabled()) {
            if (hierarchyChildren != null) {
                final Map<String, Set<String>> personUnits = hierarchyChildren.stream()
                        .flatMap(childProposal -> childProposal.getProposalPersons().stream())
                                .flatMap(proposalPerson -> proposalPerson.getUnits().stream())
                                .collect(Collectors.groupingBy(proposalPersonUnit -> proposalPersonUnit.getProposalPerson().getPersonId(),
                                        Collectors.mapping(ProposalPersonUnit::getUnitNumber, Collectors.toSet())));

                if (parentProposal.getProposalPersons() != null) {
                    parentProposal.getProposalPersons().stream()
                            .filter(parentPerson -> personUnits.containsKey(parentPerson.getPersonId()))
                            .forEach(parentPerson -> {
                                parentPerson.setUnits(new ArrayList<>());
                                personUnits.get(parentPerson.getPersonId())
                                        .forEach(unitNumber -> keyPersonnelService.addUnitToPerson(parentPerson, keyPersonnelService.createProposalPersonUnit(unitNumber, parentPerson)));
                    });
                }
            }
        }
    }


    protected void deleteAllMultipleInternal(DevelopmentProposal proposal) {
        List<Narrative> freshList = deleteAllMultipleTypeAttachments(proposal.getInstituteAttachments());
        proposal.setInstituteAttachments(freshList);
    }

    protected List<Narrative> deleteAllMultipleTypeAttachments(List<Narrative> attachments) {
        List<Narrative> freshList = new ArrayList<>();
        for (Narrative narrative : attachments) {
            if (narrative.getNarrativeType().isAllowMultiple()) {
                narrative.setDevelopmentProposal(null);
            } else {
                freshList.add(narrative);
            }
        }
        return freshList;
    }

    /**
     * Synchronizes the given child proposal to the parent.  
     * <p>
     * If a key proposal person appears in multiple child proposals and is removed from the given child
     * proposal, then this also aggregates that key person back to the parent proposal from a different child proposal, making sure that all the key persons
     * in all of the child proposals are represented in the parent proposal.
     */
    protected boolean synchronizeChildProposal(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal,
                                               boolean syncPersonnelAttachments, List<DevelopmentProposal> hierarchyChildren) throws ProposalHierarchyException {
        List<BudgetPeriod> oldBudgetPeriods = getOldBudgetPeriods(proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal));
        ProposalPerson principalInvestigator = hierarchyProposal.getPrincipalInvestigator();
        childProposal.setHierarchyLastSyncHashCode(computeHierarchyHashCode(childProposal));
        
        removeChildElements(hierarchyProposal, childProposal.getProposalNumber());

        synchronizeKeywords(hierarchyProposal, childProposal);
        synchronizeSpecialReviews(hierarchyProposal, childProposal);
        synchronizePersonsAndAggregate(hierarchyProposal, childProposal, principalInvestigator);
        syncDegreeInfo(hierarchyProposal, childProposal);

        proposalBudgetHierarchyService.synchronizeChildBudget(hierarchyProposal, childProposal, oldBudgetPeriods);

        if (syncPersonnelAttachments) {
            synchronizeNarratives(hierarchyProposal, childProposal);
            synchronizeInternalAttachments(hierarchyProposal, childProposal);
            syncAllPersonnelAttachments(hierarchyProposal, childProposal);
        }

        synchronizePersonUnits(hierarchyChildren, hierarchyProposal);
        dataObjectService.save(childProposal);

        return true;
    }

    @Override
    public void reinstateDegreeInfo(DevelopmentProposal proposal) {
        for (ProposalPerson person : proposal.getProposalPersons()) {
            List<ProposalPersonDegree> degrees = getProposalHierarchyDao().getDegreeInformation(proposal.getProposalNumber(), person);
            person.setProposalPersonDegrees(degrees);
        }
    }

    /**
     * Gets the old budget periods before removing them from the parent.
     */
    protected List<BudgetPeriod> getOldBudgetPeriods(Budget oldBudget) {
        List<BudgetPeriod> oldBudgetPeriods = new ArrayList<>();
        oldBudgetPeriods.addAll(oldBudget.getBudgetPeriods());
        return oldBudgetPeriods;
    }

    /**
     * Synchronizes the proposal science keywords from the child proposal to the parent proposal.
     */
    protected void synchronizeKeywords(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (PropScienceKeyword keyword : childProposal.getPropScienceKeywords()) {
            PropScienceKeyword newKeyword = new PropScienceKeyword(hierarchyProposal, getScienceKeyword(keyword.getScienceKeyword().getCode()));
            if (!doesOldKeyWordExist(hierarchyProposal.getPropScienceKeywords(),newKeyword)) {
                newKeyword.setHierarchyProposalNumber(childProposal.getProposalNumber());
                hierarchyProposal.addPropScienceKeyword(newKeyword);
            }

        }
    }

    protected boolean doesOldKeyWordExist(List<PropScienceKeyword> oldKeywords, PropScienceKeyword newKeyword) {
        for (PropScienceKeyword oldKeyWord : oldKeywords) {
           if (oldKeyWord.getScienceKeyword().getCode().equals(newKeyword.getScienceKeyword().getCode()) &&
                   oldKeyWord.getProposalNumber().equals(newKeyword.getProposalNumber())) {
               return true;
           }
        }
        return false;
    }

    protected ScienceKeyword getScienceKeyword(String code) {
        return getDataObjectService().findUnique(ScienceKeyword.class, QueryByCriteria.Builder.forAttribute(CODE, code).build());
    }

    /**
     * Synchronizes the proposal special reviews from the child proposal to the parent proposal.
     */
    protected void synchronizeSpecialReviews(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalSpecialReview review : childProposal.getPropSpecialReviews()) {
            ProposalSpecialReview newReview = deepCopy(review);
            newReview.setDevelopmentProposal(hierarchyProposal);
            newReview.setHierarchyProposalNumber(childProposal.getProposalNumber());
            hierarchyProposal.getPropSpecialReviews().add(newReview);
        }
    }
    
    /**
     * Synchronizes the proposal narratives from the child proposal to the parent proposal based on some rules.
     * 1. If the attachment is single type, then will get created in the parent if one does not already exist there and then subsequent changes must be
     * made on the parent and will not allow sync to child.
     * 2. Multiple Attachment Type: If you have many  attachments for the attachment type multiple,
     * then they all sync up and use the hierarchy proposal number to figure out how to sync.
     * These are never changed at the parent, though you could add one at the parent ( this will not have a hierarchy proposal number) that never
     * gets synced
     * 3. Any attachment can be added at the parent and then requires all updates on the parent. Child proposals not populated with this attachment.
     */
    protected void synchronizeNarratives(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        // delete everything from parent that has this child proposal number as
        // as hierarchy proposal number and multiple attachment type.
        deleteMultipleTypeAttachments(childProposal, childProposal.getNarratives());

        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getNarratives(), hierarchyProposal.getNarratives());
    }

    protected void synchronizeInternalAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        // delete everything from parent that has this child proposal number as
        // as hierarchy proposal number and multiple attachment type.
        deleteMultipleTypeAttachments(childProposal, childProposal.getInstituteAttachments());

        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getInstituteAttachments(), hierarchyProposal.getInstituteAttachments());
    }

    protected void addInternalAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        syncAttachmentsFromChild(hierarchyProposal, childProposal, childProposal.getInstituteAttachments(), hierarchyProposal.getInstituteAttachments());
    }

    protected void syncAttachmentsFromChild(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, List<Narrative> childAttachments,
                                            List<Narrative> hierarchyAttachments) {
        for (Narrative narrative : childAttachments) {
            narrative.refreshReferenceObject(NARRATIVE_TYPE);
            // if single type narrative
            if (!narrative.getNarrativeType().isAllowMultiple() && !doesParentHaveNarrativeType(hierarchyProposal, narrative.getNarrativeType())) {
                addNarrativeToParent(hierarchyProposal, childProposal, narrative, hierarchyAttachments);
                // not adding user rights here since it is not needed.
            }

            // if attachment type allows multiple
            if (narrative.getNarrativeType().isAllowMultiple()) {
                addNarrativeToParent(hierarchyProposal, childProposal, narrative, hierarchyAttachments);
            }
        }
    }

    protected void addNarrativeToParent(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, Narrative narrative,
                                            List<Narrative> hierarchyAttachments) {
        Narrative newNarrative = deepCopy(narrative);
        newNarrative.setNarrativeTypeCode(narrative.getNarrativeTypeCode());
        newNarrative.setNarrativeType(narrative.getNarrativeType());
        newNarrative.refreshReferenceObject(NARRATIVE_TYPE);
        newNarrative.setHierarchyProposalNumber(childProposal.getProposalNumber());
        newNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE);
        newNarrative.setModuleNumber(legacyNarrativeService.getNextModuleNumber(hierarchyProposal.getProposalDocument()));
        newNarrative.setDevelopmentProposal(hierarchyProposal);
        newNarrative.setNarrativeUserRights(null);
        newNarrative.setNarrativeAttachment(deepCopy(narrative.getNarrativeAttachment()));
        //need to null out the file data id so the setData method does not remove the attachment data from the attachment db
        newNarrative.getNarrativeAttachment().setFileDataId(null);
        newNarrative.getNarrativeAttachment().setData(narrative.getNarrativeAttachment().getData());
        hierarchyAttachments.add(newNarrative);
    }

    protected void deleteMultipleTypeAttachments(DevelopmentProposal childProposal, List<Narrative> attachments) {
        for (Narrative narrative : attachments) {
            narrative.refreshReferenceObject(NARRATIVE_TYPE);
            if (narrative.getNarrativeType().isAllowMultiple()) {
                deleteNarrativesFromParent(childProposal, narrative.getNarrativeType());
            }
        }
    }

     protected void deleteNarrativesFromParent(DevelopmentProposal childProposal, NarrativeType type) {
         Map<String, String> param = new HashMap<>();
         param.put(HIERARCHY_PROPOSAL_NUMBER, childProposal.getProposalNumber());
         param.put(NARRATIVE_TYPE_CODE, type.getCode());
         getDataObjectService().deleteMatching(Narrative.class, QueryByCriteria.Builder.andAttributes(param).build());

     }

     protected boolean doesParentHaveNarrativeType(DevelopmentProposal hierarchyProposal, NarrativeType narrativeType) {
        return getLegacyNarrativeService().doesProposalHaveNarrativeType(hierarchyProposal, narrativeType);
     }

    protected void syncAllPersonnelAttachments(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {

        final List<ProposalPersonBiography> newList = new ArrayList<>();

        // go over the child bios list and if person is not in multiple proposals add it
        for (ProposalPersonBiography srcPropPersonBio : childProposal.getPropPersonBios()) {
            // if the proposal is JUST being linked to a hierarchy and if this proposal has bios for
            // employee people that exist on the parent, ignore those bios.
            if (StringUtils.isNotBlank(srcPropPersonBio.getPersonId()) && (!isEmployeeBioInNewChildDuplicate(hierarchyProposal, srcPropPersonBio)
                    && (!employeePersonInMultipleProposals(srcPropPersonBio.getPersonId(), hierarchyProposal) || !hasBeenAdded(ListUtils.union(hierarchyProposal.getPropPersonBios(), newList), srcPropPersonBio)))) {
                ProposalPersonBiography destPropPersonBio;
                destPropPersonBio = getNewPropPersonBio(srcPropPersonBio, hierarchyProposal);
                destPropPersonBio.setDevelopmentProposal(hierarchyProposal);
                destPropPersonBio.setProposalNumber(hierarchyProposal.getProposalNumber());
                destPropPersonBio.setProposalPersonNumber(getEmployeeProposalPersonNumber(destPropPersonBio.getPersonId(), hierarchyProposal));
                destPropPersonBio.setVersionNumber(0L);
                newList.add(destPropPersonBio);
            } else if (srcPropPersonBio.getRolodexId() != null && (!isNonEmployeeBioInNewChildDuplicate(hierarchyProposal, srcPropPersonBio)
                    && (!nonEmployeePersonInMultipleProposals(srcPropPersonBio.getRolodexId(), hierarchyProposal) || !hasBeenAdded(ListUtils.union(hierarchyProposal.getPropPersonBios(), newList), srcPropPersonBio)))) {
                ProposalPersonBiography destPropPersonBio;
                destPropPersonBio = getNewPropPersonBio(srcPropPersonBio, hierarchyProposal);
                destPropPersonBio.setDevelopmentProposal(hierarchyProposal);
                destPropPersonBio.setProposalNumber(hierarchyProposal.getProposalNumber());
                destPropPersonBio.setProposalPersonNumber(getNonEmployeeProposalPersonNumber(destPropPersonBio.getRolodexId(), hierarchyProposal));
                destPropPersonBio.setVersionNumber(0L);
                newList.add(destPropPersonBio);
            }
        }
        hierarchyProposal.getPropPersonBios().addAll(newList);
    }

    protected boolean hasBeenAdded(List<ProposalPersonBiography> bios, ProposalPersonBiography srcPropPersonBio) {
        return bios.stream()
                .filter(bio -> (StringUtils.isNotBlank(srcPropPersonBio.getPersonId()) && srcPropPersonBio.getPersonId().equals(bio.getPersonId())
                                                    || srcPropPersonBio.getRolodexId() != null && srcPropPersonBio.getRolodexId().equals(bio.getRolodexId())))
                .anyMatch(bio -> bio.getDocumentTypeCode().equals(srcPropPersonBio.getDocumentTypeCode()));
    }

    protected ProposalPersonBiography getNewPropPersonBio(ProposalPersonBiography srcPropPersonBio, DevelopmentProposal hierarchyProposal) {
        ProposalPersonBiography proposalPersonBiography = new ProposalPersonBiography();
        proposalPersonBiography.setDescription(srcPropPersonBio.getDescription());
        proposalPersonBiography.setContentType(srcPropPersonBio.getContentType());
        proposalPersonBiography.setDocumentTypeCode(srcPropPersonBio.getDocumentTypeCode());
        proposalPersonBiography.setName(srcPropPersonBio.getName());
        proposalPersonBiography.setPersonId(srcPropPersonBio.getPersonId());
        proposalPersonBiography.setRolodexId(srcPropPersonBio.getRolodexId());
        proposalPersonBiography.setPositionNumber(srcPropPersonBio.getPositionNumber());
        proposalPersonBiography.setPropPerDocType(srcPropPersonBio.getPropPerDocType());
        proposalPersonBiography.setDevelopmentProposal(hierarchyProposal);
        proposalPersonBiography.setBiographyNumber(hierarchyProposal.getProposalDocument().getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));

        ProposalPersonBiographyAttachment attachment = new ProposalPersonBiographyAttachment();
        attachment.setType(srcPropPersonBio.getPersonnelAttachment().getType());
        attachment.setName(srcPropPersonBio.getPersonnelAttachment().getName());
        attachment.setFileDataId(srcPropPersonBio.getPersonnelAttachment().getFileDataId());
        attachment.setUploadUser(srcPropPersonBio.getPersonnelAttachment().getUploadUser());
        attachment.setUploadTimestamp(srcPropPersonBio.getPersonnelAttachment().getUploadTimestamp());

        proposalPersonBiography.setPersonnelAttachment(attachment);
        return  proposalPersonBiography;
    }

    protected boolean isEmployeeBioInNewChildDuplicate(DevelopmentProposal hierarchyProposal, ProposalPersonBiography srcPropPersonBio) {
        return StringUtils.isNotBlank(srcPropPersonBio.getPersonId()) && isEmployeePersonOnParent(hierarchyProposal, srcPropPersonBio.getPersonId(), srcPropPersonBio);
    }

    protected boolean isNonEmployeeBioInNewChildDuplicate(DevelopmentProposal hierarchyProposal, ProposalPersonBiography srcPropPersonBio) {
        return srcPropPersonBio.getRolodexId() != null && isNonEmployeePersonOnParent(hierarchyProposal, srcPropPersonBio.getRolodexId(), srcPropPersonBio);
    }

    protected Integer getEmployeeProposalPersonNumber(String personId, DevelopmentProposal hierarchyProposal) {
        for (ProposalPerson person : hierarchyProposal.getProposalPersons()) {
            if (StringUtils.isNotBlank(personId) && StringUtils.equalsIgnoreCase(person.getPersonId(), personId)) {
                return person.getProposalPersonNumber();
            }
        }
        return null;
    }

    protected Integer getNonEmployeeProposalPersonNumber(Integer rolodexId, DevelopmentProposal hierarchyProposal) {
        for (ProposalPerson person : hierarchyProposal.getProposalPersons()) {
            if (rolodexId != null && rolodexId.equals(person.getRolodexId())) {
                return person.getProposalPersonNumber();
            }
        }
        return null;
    }

    @Override
    public boolean employeePersonInMultipleProposals(String personId, DevelopmentProposal proposal) {
        if (proposal.isChild()) {
            return getProposalHierarchyDao().employeePersonInMultipleChildProposals(personId, proposal.getHierarchyParentProposalNumber());
        } else {
            return getProposalHierarchyDao().employeePersonInMultipleChildProposals(personId, proposal.getProposalNumber());
        }
    }

    @Override
    public boolean nonEmployeePersonInMultipleProposals(Integer rolodexId, DevelopmentProposal proposal) {
        if (rolodexId != null) {
            if (proposal.isChild()) {
                return getProposalHierarchyDao().nonEmployeePersonInMultipleChildProposals(rolodexId, proposal.getHierarchyParentProposalNumber());
            } else {
                return getProposalHierarchyDao().nonEmployeePersonInMultipleChildProposals(rolodexId, proposal.getProposalNumber());
            }
        }
        return false;
    }

    protected boolean isEmployeePersonOnParent(DevelopmentProposal proposal, String id, ProposalPersonBiography srcPropPersonBio) {
        List<ProposalPerson> persons = getProposalHierarchyDao().isEmployeePersonOnProposal(proposal.getProposalNumber(), id);
        // if person is on parent, then check if the person has been added by the same proposal linked to
        // the current bio. If latter is true, the srcBio can be added, if not, bio has been added by a different proposal
        // and needs to be maintained at the parent.
        return persons.size() > 0 && !StringUtils.equals(persons.get(0).getHierarchyProposalNumber(), srcPropPersonBio.getDevelopmentProposal().getProposalNumber());
    }

    protected boolean isNonEmployeePersonOnParent(DevelopmentProposal proposal, Integer id, ProposalPersonBiography srcPropPersonBio) {
        List<ProposalPerson> persons = getProposalHierarchyDao().isNonEmployeePersonOnProposal(proposal.getProposalNumber(), id);
        // if person is on parent, then check if the person has been added by the same proposal linked to
        // the current bio. If latter is true, the srcBio can be added, if not, bio has been added by a different proposal
        // and needs to be maintained at the parent.
        return persons.size() > 0 && !StringUtils.equals(persons.get(0).getHierarchyProposalNumber(), srcPropPersonBio.getDevelopmentProposal().getProposalNumber());
    }

 /**
  * Synchronizes the proposal persons from the child proposal to the parent proposal and then restores any proposal persons that were in the given child
  * proposal (and hence removed from the given parent proposal).
  * <p>
  * This first synchronizes the main proposal persons from the primary child proposal to the parent proposal and then runs the same algorithm on all other
  * children of the parent proposal.
  */
    protected void synchronizePersonsAndAggregate(DevelopmentProposal hierarchyProposal, DevelopmentProposal primaryChildProposal, 
            ProposalPerson principalInvestigator) {
        
        synchronizePersons(hierarchyProposal, primaryChildProposal, principalInvestigator);
        getHierarchyChildren(hierarchyProposal.getProposalNumber()).
                forEach(childProposal -> synchronizePersons(hierarchyProposal, childProposal, principalInvestigator));

    }

    /**
     * Synchronizes the proposal persons from the child proposal to the parent proposal.
     */
    protected void synchronizePersons(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal, ProposalPerson principalInvestigator) {
        for (ProposalPerson person : childProposal.getProposalPersons()) {

            int firstIndex = hierarchyProposal.getProposalPersons().indexOf(person);
            int lastIndex = hierarchyProposal.getProposalPersons().lastIndexOf(person);
            ProposalPerson firstInstance = (firstIndex == -1) ? null : hierarchyProposal.getProposalPersons().get(firstIndex);
            if (firstIndex == -1 || (firstIndex == lastIndex && !rolesAreSimilar(person, firstInstance))) {
                ProposalPerson newPerson;
                newPerson = deepCopy(person);
                newPerson.setCertifiedBy(null);
                newPerson.setCertifiedTime(null);
                newPerson.setCertifiedTimeStamp(null);
                newPerson.setCertifiedPersonName(null);
                newPerson.setDevelopmentProposal(hierarchyProposal);
                newPerson.getProposalPersonYnqs().clear();
                for (ProposalPersonUnit unit : newPerson.getUnits()) {
                    for(ProposalUnitCreditSplit creditSplit : unit.getCreditSplits()) {
                        creditSplit.setCredit(new ScaleTwoDecimal(0));
                    }
                    if (!childProposal.getHierarchyParentProposalNumber().equals(childProposal.getProposalNumber())) {
                        unit.setLeadUnit(false);
                    }
                }

                for (ProposalPersonCreditSplit creditSplit : newPerson.getCreditSplits()) {
                    creditSplit.setCredit(new ScaleTwoDecimal(0));
                }
                newPerson.setProposalPersonNumber(null);
                newPerson.setVersionNumber(null);
                newPerson.setHierarchyProposalNumber(childProposal.getProposalNumber());
            
                if (StringUtils.equalsIgnoreCase(person.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                    newPerson.setProposalPersonRoleId(Constants.CO_INVESTIGATOR_ROLE);
                }
                if (newPerson.equals(principalInvestigator) && (firstIndex == -1 || !firstInstance.isInvestigator())) {
                    newPerson.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
                }
                hierarchyProposal.addProposalPerson(newPerson);
            }

        }
        removeDeletedPersonnelFromParent(hierarchyProposal, childProposal);

    }

    protected void syncDegreeInfo(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
        for (ProposalPerson person : childProposal.getProposalPersons()) {
            int firstIndex = hierarchyProposal.getProposalPersons().indexOf(person);
            syncDegreeInfo(hierarchyProposal, firstIndex, person);
        }
    }

    protected void syncDegreeInfo(DevelopmentProposal hierarchyProposal, int indexOfPersonInParent, ProposalPerson childPerson) {
        ProposalPerson personInParent = hierarchyProposal.getProposalPersons().get(indexOfPersonInParent);
        // if this person is not null and this person is not new then add degrees.
        if(personInParent != null && personInParent.getVersionNumber() != null) {
            if ((StringUtils.isNotBlank(personInParent.getPersonId()) && !employeePersonInMultipleProposals(personInParent.getPersonId(), hierarchyProposal)) ||
                    (personInParent.getRolodexId() != null && !nonEmployeePersonInMultipleProposals(personInParent.getRolodexId(), hierarchyProposal))) {
                getProposalHierarchyDao().deleteDegreeInfo(hierarchyProposal.getProposalNumber(), personInParent.getProposalPersonNumber(), personInParent);
                for (ProposalPersonDegree degree : childPerson.getProposalPersonDegrees()) {
                    ProposalPersonDegree newDegree = new ProposalPersonDegree();
                    newDegree.setDegree(degree.getDegree());
                    newDegree.setDegreeCode(degree.getDegreeCode());
                    newDegree.setProposalPerson(personInParent);
                    newDegree.setDegreeType(degree.getDegreeType());
                    newDegree.setFieldOfStudy(degree.getFieldOfStudy());
                    newDegree.setGraduationYear(degree.getGraduationYear());
                    newDegree.setSchool(degree.getSchool());
                    newDegree.setSpecialization(degree.getSpecialization());
                    newDegree.setDegreeSequenceNumber(getDocument(hierarchyProposal).
                                getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
                    dataObjectService.save(newDegree);
                }
            }
        }
    }

    protected ProposalDevelopmentDocument getDocument(DevelopmentProposal proposal) {
        ProposalDevelopmentDocument doc;
        try {
            doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(proposal.getProposalDocument().getDocumentNumber());
        } catch (WorkflowException e) {
            throw new RuntimeException("Cannot find the proposal", e);
        }
        return doc;
    }

    @Override
    public DevelopmentProposal getHierarchy(String hierarchyProposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal hierarchy = getDevelopmentProposal(hierarchyProposalNumber);
        if (hierarchy == null || !hierarchy.isParent())
            throw new ProposalHierarchyException("Proposal " + hierarchyProposalNumber + " is not a hierarchy");
        return hierarchy;
    }

    @Override
    public boolean isSynchronized(DevelopmentProposal childProposal) {
        Integer hc1 = computeHierarchyHashCode(childProposal);
        Integer hc2 = childProposal.getHierarchyLastSyncHashCode();
        return Objects.equals(hc1, hc2);
    }
    
    protected boolean isBudgetSynchronized(DevelopmentProposal childProposal, ProposalDevelopmentBudgetExt childBudget) throws ProposalHierarchyException {
        if (Objects.equals(childProposal.getLastSyncedBudget(), childBudget)) {
            ObjectUtils.materializeAllSubObjects(childBudget);
            Integer hc1 = proposalBudgetHierarchyService.computeHierarchyHashCode(childBudget);
            Integer hc2 = childBudget.getHierarchyLastSyncHashCode();
            return Objects.equals(hc1, hc2);
        } else {
            return false;
        }
    }
    
    
    protected void setInitialPi(DevelopmentProposal hierarchy, DevelopmentProposal child) {
        ProposalPerson childPi = child.getPrincipalInvestigator();
        if (childPi != null) {
            final Optional<ProposalPersonUnit> childPiLeadUnit = childPi.getUnits().stream().filter(ProposalPersonUnit::isLeadUnit).findFirst();
            int index = hierarchy.getProposalPersons().indexOf(childPi);
            if (index > -1) {
                ProposalPerson hierarchyPi = hierarchy.getProposalPerson(index);
                hierarchyPi.setProposalPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
                if (childPiLeadUnit.isPresent()) {
                    Optional <ProposalPersonUnit> hierarchyPiLeadUnit = hierarchyPi.getUnits().stream().filter(unit -> unit.getUnitNumber().equals(childPiLeadUnit.get().getUnitNumber())).findFirst();
                    hierarchyPiLeadUnit.ifPresent(proposalPersonUnit -> proposalPersonUnit.setLeadUnit(true));
                }
            }
        }
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateChildBudgetPeriods(DevelopmentProposal hierarchyProposal,
            DevelopmentProposal childProposal, boolean allowEndDateChange) throws ProposalHierarchyException {
    	return proposalBudgetHierarchyService.validateChildBudgetPeriods(hierarchyProposal, childProposal, allowEndDateChange);
    }

    @Override
    public void synchronizeChildBudget(DevelopmentProposal hierarchyProposal, ProposalDevelopmentBudgetExt budget) {
    	prepareHierarchySync(hierarchyProposal);
    	proposalBudgetHierarchyService.synchronizeChildBudget(hierarchyProposal, budget);
    	finalizeHierarchySync(hierarchyProposal);
    }

    @Override
    public ProposalDevelopmentBudgetExt getSyncableBudget(DevelopmentProposal proposal) {
    	return proposalBudgetHierarchyService.getSyncableBudget(proposal);
    }

    @Override
	public boolean needToExtendProjectDate(DevelopmentProposal hierarchyProposal, DevelopmentProposal childProposal) {
		if (hierarchyProposal != null && !hierarchyProposal.getBudgets().isEmpty()) {
	    	ProposalDevelopmentBudgetExt parentBudget = proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal);
	    	Budget childBudget = getSyncableBudget(childProposal);
	    	if (childBudget != null && parentBudget != null) {
                BudgetPeriod lastParentPeriod = parentBudget.getBudgetPeriods().get(parentBudget.getBudgetPeriods().size() - 1);
                BudgetPeriod lastChildPeriod = childBudget.getBudgetPeriods().get(childBudget.getBudgetPeriods().size() - 1);
                return lastChildPeriod.getStartDate().after(lastParentPeriod.getEndDate());
            }
            return false;
		} else {
			return false;
		}
    }

    @Override
    public boolean needToExtendProjectDate(DevelopmentProposal hierarchyProposal) {
    	List<DevelopmentProposal> proposals = this.getHierarchyProposals(hierarchyProposal);
    	for (DevelopmentProposal proposal : proposals) {
    		if (needToExtendProjectDate(hierarchyProposal, proposal)) {
    			return true;
    		}
    	}
    	return false;
    }

    protected void removeChildElements(DevelopmentProposal parentProposal, String childProposalNumber) {
        List<ProposalSpecialReview> reviews = parentProposal.getPropSpecialReviews();
        for (int i=reviews.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, reviews.get(i).getHierarchyProposalNumber())) {
                reviews.remove(i);
            }
        }

        List<Narrative> narratives = parentProposal.getNarratives();
        for (int i=narratives.size()-1; i>=0; i--) {
            if (StringUtils.equals(childProposalNumber, narratives.get(i).getHierarchyProposalNumber())) {
                dataObjectService.delete(narratives.remove(i));
            }
        }

        List<ProposalPersonBiography> bios = parentProposal.getPropPersonBios();
        for (int i=bios.size()-1; i>=0; i--) {
            final ProposalPersonBiography bio = bios.get(i);
            final Optional<ProposalPerson> person = parentProposal.getProposalPersons().stream()
                    .filter(p -> p.getProposalPersonNumber().equals(bio.getProposalPersonNumber())).findAny();

            if (person.isPresent() && StringUtils.equals(childProposalNumber, person.get().getHierarchyProposalNumber())
                    && !personInMultipleProposals(person.get().getPersonId(), person.get().getRolodexId(), parentProposal)) {
                dataObjectService.delete(bios.remove(i));
            }
        }

        ProposalDevelopmentBudgetExt parentBudget = proposalBudgetHierarchyService.getHierarchyBudget(parentProposal);
        if (parentBudget != null) {
        	proposalBudgetHierarchyService.removeChildBudgetElements(parentProposal, parentBudget, childProposalNumber);
        }

    }

    protected boolean personInMultipleProposals(String personId, Integer rolodexId, DevelopmentProposal parentProposal) {
     if (StringUtils.isNotBlank(personId)) {
         return employeePersonInMultipleProposals(personId, parentProposal);
     } else if (rolodexId != null) {
         return nonEmployeePersonInMultipleProposals(rolodexId, parentProposal);
     }
        return false;
    }
    
    protected void prepareHierarchySync(DevelopmentProposal hierarchyProposal) {
        prepareHierarchySync(hierarchyProposal.getProposalDocument());
    }
    
    protected void prepareHierarchySync(ProposalDevelopmentDocument pdDoc) {
        pdDoc.refreshReferenceObject(DOCUMENT_NEXTVALUES);
    }
    
    protected void finalizeHierarchySync(ProposalDevelopmentDocument pdDoc) throws ProposalHierarchyException {
        try {
            documentService.saveDocument(pdDoc);
        }
        catch (WorkflowException e) {
            throw new ProposalHierarchyException(e);
        }
    }
    
    protected DevelopmentProposal finalizeHierarchySync(DevelopmentProposal hierarchyProposal) throws ProposalHierarchyException {
        DevelopmentProposal savedParentProposal = dataObjectService.save(hierarchyProposal);
        dataObjectService.save(proposalBudgetHierarchyService.getHierarchyBudget(hierarchyProposal));
        return savedParentProposal;
    }

    /*
    This is the first copy from lead child to parent, so copy EVERY NARRATIVE over. Narrative includes institute attachments.
     */
    protected void copyInitialAttachments(DevelopmentProposal srcProposal, DevelopmentProposal destProposal) {

        ProposalPerson srcPerson = null;
        ProposalPerson destPerson = null;

        for (ProposalPersonBiography srcPropPersonBio : srcProposal.getPropPersonBios()) {
            for (ProposalPerson person : srcProposal.getProposalPersons()) {
                if (person.getProposalPersonNumber().equals(srcPropPersonBio.getProposalPersonNumber())) {
                    srcPerson = person;
                    break;
                }
            }
            for (ProposalPerson person : destProposal.getProposalPersons()) {
                if (person.equals(srcPerson)) {
                    destPerson = person;
                    break;
                }
            }
            if (destPerson != null) {
                ProposalPersonBiography destPropPersonBio = getNewPropPersonBio(srcPropPersonBio, destProposal);
                destPropPersonBio.setDevelopmentProposal(destProposal);
                destPropPersonBio.setProposalNumber(destProposal.getProposalNumber());
                destPropPersonBio.setProposalPersonNumber(destPerson.getProposalPersonNumber());
                destPropPersonBio.setPersonId(destPerson.getPersonId());
                destPropPersonBio.setRolodexId(destPerson.getRolodexId());
                destProposal.getPropPersonBios().add(destPropPersonBio);
            }
        }

        for (Narrative srcNarrative : srcProposal.getNarratives()) {
            addNarrativeToParent(destProposal, srcProposal, srcNarrative, destProposal.getNarratives());
        }

        for(Narrative narrative : destProposal.getNarratives()) {
            narrative.setNarrativeUserRights(null);
        }

        for (Narrative attInternal : srcProposal.getInstituteAttachments()) {
            addNarrativeToParent(destProposal, srcProposal, attInternal, destProposal.getInstituteAttachments());
        }
    }

    /**
     * Creates a hash of the data pertinent to a hierarchy for comparison during hierarchy syncing. 
     */
    protected int computeHierarchyHashCode(DevelopmentProposal proposal) {
        int prime = 31;
        int result = 1;
        for (ProposalPerson person : proposal.getProposalPersons()) {
            result = prime * result + person.hashCode();
        }
        for (Narrative narrative : proposal.getNarratives()) {
            result = prime * result + narrative.hierarchyHashCode();
        }
        for (PropScienceKeyword keyword : proposal.getPropScienceKeywords()) {
            result = prime * result + keyword.getScienceKeyword().getCode().hashCode();
        }
        for (ProposalSpecialReview review : proposal.getPropSpecialReviews()) {
            result = prime * result + review.hierarchyHashCode();
        }
        return result;
    }

    @Override
    public List<DevelopmentProposal> getHierarchyChildren(String parentProposalNumber) {
        return proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber).stream()
                .map(this::getDevelopmentProposal)
                .collect(Collectors.toList());
    }
    
    @Override
    public WorkflowDocument getParentWorkflowDocument(ProposalDevelopmentDocument child) throws ProposalHierarchyException {
            return getParentDocument( child ).getDocumentHeader().getWorkflowDocument();
    }

    
    @Override
    public ProposalDevelopmentDocument getParentDocument(ProposalDevelopmentDocument child) throws ProposalHierarchyException {
        try {
            DevelopmentProposal parentProposal = getHierarchy(child.getDevelopmentProposal().getHierarchyParentProposalNumber());
            String parentDocumentNumber = parentProposal.getProposalDocument().getDocumentNumber();
            return (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId(parentDocumentNumber);
        } catch (WorkflowException e) {
            throw new ProposalHierarchyException( String.format("Could not lookup hierarchy workflow status for child:%s",child.getDocumentHeader().getDocumentNumber()),e);
        }
    }
    
    
    /**
     * Reject a proposal by sending it to the first node ( as named by PROPOSALDEVELOPMENTDOCUMENT_KEW_INITIAL_NODE_NAME )
     * @param proposalDoc The ProposalDevelopmentDocument that should be rejected.
     * @param principalId the principal we are rejecting the document as.
     * @param appDocStatus the application document status to apply ( does not apply if null )
     */
    protected void rejectProposal( ProposalDevelopmentDocument proposalDoc, String reason, String principalId, String appDocStatus )  {
        getKcDocumentRejectionService().reject(proposalDoc.getDocumentHeader().getWorkflowDocument(), reason, principalId, appDocStatus);
    }


    /**
     * Reject an entire proposal hierarchy.
     * @param hierarchyParent The hierarchy to reject
     * @param reason the reason to be applied to the annotation field.  The reason will be pre-pended with static text indicating if it was a child or the parent.
     * @param principalId the id of the principal that is rejecting the document.
     */
    protected void rejectProposalHierarchy(ProposalDevelopmentDocument hierarchyParent, String reason, String principalId ) throws ProposalHierarchyException {
        rejectProposal( hierarchyParent, renderMessage( PROPOSAL_ROUTING_RETURNED_ANNOTATION, reason ), principalId, renderMessage( HIERARCHY_REJECTED_APPSTATUS ) );
    }

    @Override
    public void rejectProposalDevelopmentDocument( String proposalNumber, String reason, String principalName, MultipartFile rejectFile )
    throws WorkflowException, ProposalHierarchyException {
        DevelopmentProposal pbo = getDevelopmentProposal(proposalNumber);
        ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(pbo.getProposalDocument().getDocumentNumber());
        if (!pbo.isInHierarchy()) {
            rejectProposal(pDoc, renderMessage(PROPOSAL_ROUTING_RETURNED_ANNOTATION, reason), principalName, renderMessage(HIERARCHY_REJECTED_APPSTATUS));
        } else if (pbo.isParent()) {
            rejectProposalHierarchy(pDoc, reason, principalName);
        } else {
            //it is a child or in some unknown state, either way we do not support rejecting it.
            throw new UnsupportedOperationException(String.format("Cannot return proposal %s it is a hierarchy child or ", proposalNumber));
        }

        createAndSaveActionNarrative(reason, "Proposal return attachment.", rejectFile, getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.RETURN_NARRATIVE_TYPE_CODE_PARAM),
                pDoc);

    }
    @Override
    public void createAndSaveActionNarrative(String reason, String title, MultipartFile file, String narrativeTypeCode, ProposalDevelopmentDocument pDoc) {
        if (file != null && !file.isEmpty()) {
            Narrative narrative = new Narrative();
            narrative.setName(file.getOriginalFilename());
            narrative.setComments(reason);
            try {
                narrative.init(file);
            } catch (Exception e) {
                throw new RuntimeException("Error Initializing narrative attachment file", e);
            }
            narrative.setNarrativeTypeCode(narrativeTypeCode);
            narrative.setModuleStatusCode(COMPLETE);
            narrative.setModuleTitle(title);
            narrative.setContactName(globalVariableService.getUserSession().getPrincipalName());
            narrative.setPhoneNumber(globalVariableService.getUserSession().getPerson().getPhoneNumber());
            narrative.setEmailAddress(globalVariableService.getUserSession().getPerson().getEmailAddress());
            getLegacyNarrativeService().prepareNarrative(pDoc, narrative);
            pDoc.getDevelopmentProposal().getInstituteAttachments().add(narrative);
            dataObjectService.save(pDoc);
        }
    }

    @Override
    public void calculateAndSetProposalAppDocStatus( ProposalDevelopmentDocument doc, DocumentRouteStatusChange dto  ) throws ProposalHierarchyException {
        String principalId = globalVariableService.getUserSession().getPrincipalId();
        if( StringUtils.equals( dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_ENROUTE_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_ENROUTE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
            updateAppDocStatus( doc, principalId, HIERARCHY_CANCEL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_DISAPPROVE_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_FINAL_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_FINAL_APPSTATUS );
        } else if ( StringUtils.equals(dto.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_PROCESSED_CD )) {
            updateAppDocStatus( doc, principalId, HIERARCHY_PROCESSED_APPSTATUS ) ;
        } 
    }
    
    protected void updateAppDocStatus( ProposalDevelopmentDocument doc, String principalId, String newStatus ) throws ProposalHierarchyException {
        try {
            WorkflowDocument wdoc = WorkflowDocumentFactory.loadDocument(principalId, doc.getDocumentHeader().getWorkflowDocument().getDocumentId() );
            wdoc.setApplicationDocumentStatus(renderMessage( newStatus ));
        }
        catch (Exception e) {
            throw new ProposalHierarchyException( String.format( "Exception encountrered while attempting to update App Doc Status of proposal %s ( document #%s )", doc.getDevelopmentProposal().getProposalNumber(), doc.getDocumentNumber() ), e);
        }
    }
    
    protected boolean rolesAreSimilar(ProposalPerson person1, ProposalPerson person2) {
        boolean isInvestigator1 = StringUtils.equals(person1.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person1.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        boolean isInvestigator2 = StringUtils.equals(person2.getProposalPersonRoleId(), Constants.PRINCIPAL_INVESTIGATOR_ROLE)
                || StringUtils.equals(person2.getProposalPersonRoleId(), Constants.CO_INVESTIGATOR_ROLE);
        return isInvestigator1 == isInvestigator2;
    }

    @Override
    public List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries(String parentProposalNumber) throws ProposalHierarchyException {
        List<HierarchyPersonnelSummary> summaries = new ArrayList<>();
        
        List<String> proposalNumbers = new ArrayList<>();
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(parentProposalNumber));
        Collections.sort(proposalNumbers);
        
        for (String proposalNumber : proposalNumbers) {
            HierarchyPersonnelSummary summary = new HierarchyPersonnelSummary();
            
            final DevelopmentProposal childProposal = getDevelopmentProposal(proposalNumber);
            final List<Budget> hierarchyBudgets = getProposalBudgetHierarchyService().getHierarchyBudgets(childProposal).stream().sorted().collect(Collectors.toList());

            summary.setProposalNumber(proposalNumber);
            summary.setHierarchyBudgets(hierarchyBudgets);
            summaries.add(summary);
        }
        
        return summaries;
    }
    
    @Override
    public List<HierarchyProposalSummary> getHierarchyProposalSummaries(String proposalNumber) throws ProposalHierarchyException {
        DevelopmentProposal proposal = getDevelopmentProposal(proposalNumber);
        List<HierarchyProposalSummary> summaries = new ArrayList<>();

        List<String> proposalNumbers = new ArrayList<>();
        if (proposal.isParent()) {
            proposalNumbers.add(proposalNumber);
        }
        else if (proposal.isChild()) {
            proposalNumbers.add(proposal.getHierarchyParentProposalNumber());
        }
        else {
            throw new ProposalHierarchyException("Proposal " + proposalNumber + " is not a member of a hierarchy.");            
        }
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(proposalNumbers.get(0)));

        HierarchyProposalSummary summary;
        for (String number : proposalNumbers) {
            summary = new HierarchyProposalSummary();
            summary.setProposalNumber(number);
            if (!StringUtils.equals(number, proposalNumbers.get(0))) {
                summary = getProposalSummary(number);
            }
            summaries.add(summary);
        }
        return summaries;
    }

    @Override
    public HierarchyProposalSummary getProposalSummary(String proposalNumber) throws ProposalHierarchyException {
        HierarchyProposalSummary summary = new HierarchyProposalSummary();
        summary.setProposalNumber(proposalNumber);
        DevelopmentProposal childProposal = getDevelopmentProposal(proposalNumber);
        summary.setSynced(isSynchronized(childProposal));
        ProposalDevelopmentBudgetExt budget = getSyncableBudget(childProposal);
        summary.setBudgetSynced(isBudgetSynchronized(childProposal, budget));
        return summary;
    }

    @Override
    public List<DevelopmentProposal> getHierarchyProposals(DevelopmentProposal proposal) {
        List<DevelopmentProposal> hierachyProposals = new ArrayList<>();

        List<String> proposalNumbers = new ArrayList<>();
        if (proposal.isParent()) {
            proposalNumbers.add(proposal.getProposalNumber());
        }
        else if (proposal.isChild()) {
            proposalNumbers.add(proposal.getHierarchyParentProposalNumber());
        }
        else {
            return hierachyProposals;
        }
        proposalNumbers.addAll(proposalHierarchyDao.getHierarchyChildProposalNumbers(proposalNumbers.get(0)));

        hierachyProposals.addAll(getDataObjectService().findMatching(DevelopmentProposal.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap(PROPOSAL_NUMBER, proposalNumbers))
                .setOrderByFields(OrderByField.Builder.create(HIERARCHY_STATUS, OrderDirection.DESCENDING).build()).build()).getResults());

        return hierachyProposals;
    }

    @Override
    public boolean validateRemovePermissions(DevelopmentProposal childProposal, String principalId) {
        boolean valid = kcAuthorizationService.hasPermission(principalId, childProposal.getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        try {
            valid &= kcAuthorizationService.hasPermission(principalId, getHierarchy(childProposal.getHierarchyParentProposalNumber()).getProposalDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY);
        }
        catch (ProposalHierarchyException e) {
            valid = false;
        }
        return valid;
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateChildForRemoval(DevelopmentProposal child) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        try {
            DevelopmentProposal hierarchy = lookupParent(child);
            if (hasCompleteBudget(hierarchy)) {
                errors.add( new ProposalHierarchyErrorWarningDto(ERROR_REMOVE_PARENT_BUDGET_COMPLETE, Boolean.TRUE));
            }
        }
        catch (ProposalHierarchyException e) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_UNEXPECTED, Boolean.TRUE, e.getMessage()));
        }
        return errors;
    }

    protected String renderMessage( String key, String... params ) {
       String msg = kualiConfigurationService.getPropertyValueAsString(key);
       for (int i = 0; i < params.length; i++) {
           msg = replace(msg, "{" + i + "}", params[i]);
       }
       return msg;
       
    }
    protected KcDocumentRejectionService getKcDocumentRejectionService() {
        return kcDocumentRejectionService;
    }
    protected DocumentService getDocumentService() {
        return documentService;
    }
    protected KcAuthorizationService getKcAuthorizationService() {
        return kcAuthorizationService;
    }
    protected ProposalHierarchyDao getProposalHierarchyDao() {
        return proposalHierarchyDao;
    }
    protected LegacyNarrativeService getLegacyNarrativeService() {
        return legacyNarrativeService;
    }
    protected ProposalPersonBiographyService getProposalPersonBiographyService() {
        return proposalPersonBiographyService;
    }

    public void setProposalPersonBiographyService(ProposalPersonBiographyService proposalPersonBiographyService) {
        this.proposalPersonBiographyService = proposalPersonBiographyService;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    protected ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }


    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateChildForSync (DevelopmentProposal child, DevelopmentProposal hierarchy, boolean allowEndDateChange) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if (child.getPrincipalInvestigator() == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_SYNC_NO_PRINCIPLE_INVESTIGATOR, Boolean.TRUE, child.getProposalNumber()));
        }
        errors.addAll(validateSponsor(child, hierarchy));
        errors.addAll(validateIsParentLocked(hierarchy));
        errors.addAll(validateParent(hierarchy));
        return errors;
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateChildCandidate(DevelopmentProposal proposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();

        if (proposal.isInHierarchy()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_ALREADY_MEMBER, Boolean.TRUE));
        }
        if (proposal.getBudgets().isEmpty()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NO_BUDGET_VERSION, Boolean.TRUE));
        }
        else {
            if (!hasFinalBudget(proposal)) {
                errors.add(new ProposalHierarchyErrorWarningDto(WARNING_LINK_NO_FINAL_BUDGET, Boolean.FALSE, proposal.getProposalNumber()));
            }
        }
        if (proposal.getPrincipalInvestigator() == null) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NO_PRINCIPLE_INVESTIGATOR, Boolean.TRUE));
        }
        return errors;
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateChildCandidateForHierarchy(DevelopmentProposal hierarchy, DevelopmentProposal child, boolean allowEndDateChange) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();

        if (!StringUtils.equalsIgnoreCase(hierarchy.getSponsorCode(), child.getSponsorCode())) {
            errors.add(new ProposalHierarchyErrorWarningDto(WARNING_LINK_DIFFERENT_SPONSOR, Boolean.FALSE));
        }

        return errors;
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateParent(DevelopmentProposal proposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if (!proposal.isParent()) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_NOT_PARENT, Boolean.TRUE));
        }
        if (hasCompleteBudget(proposal)) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_LINK_PARENT_BUDGET_COMPLETE, Boolean.TRUE));
        }
        return errors;
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateSponsor(DevelopmentProposal childProposal, DevelopmentProposal parentProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if(!StringUtils.equals(childProposal.getSponsorCode(), parentProposal.getSponsorCode())) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_DIFFERENT_SPONSORS, Boolean.FALSE));
        }
        return errors;
    }

    protected List<ProposalHierarchyErrorWarningDto> validateIsAggregatorOnParent(DevelopmentProposal parentProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if(!getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(), parentProposal.getDocument(), PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY)) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_NOT_PARENT_AGGREGATOR, Boolean.TRUE, parentProposal.getProposalNumber()));
        }
        return errors;
    }

    @Override
    public List<ProposalHierarchyErrorWarningDto> validateIsAggregatorOnChild(DevelopmentProposal childProposal) {
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
        if(!getKcAuthorizationService().hasPermission(getGlobalVariableService().getUserSession().getPrincipalId(),childProposal.getDocument(),PermissionConstants.MAINTAIN_PROPOSAL_HIERARCHY)) {
            errors.add(new ProposalHierarchyErrorWarningDto(ERROR_NOT_CHILD_AGGREGATOR, Boolean.TRUE, childProposal.getProposalNumber()));
        }
        return errors;
    }

    protected List<ProposalHierarchyErrorWarningDto> validateIsParentLocked(DevelopmentProposal parentProposal){
        List<ProposalHierarchyErrorWarningDto> errors = new ArrayList<>();
            if (!getPessimisticLockService().getPessimisticLocksForDocument(parentProposal.getDocument().getDocumentNumber()).isEmpty()) {
                errors.add(new ProposalHierarchyErrorWarningDto(ERROR_PARENT_LOCK, Boolean.TRUE, parentProposal.getProposalNumber()));
            }
        return errors;
    }

    private boolean hasFinalBudget(DevelopmentProposal proposal) {
    	return proposal.getFinalBudget() != null;
    }

    private boolean hasCompleteBudget(DevelopmentProposal developmentProposal) {
        boolean retval = false;
        String completeCode = getParameterService().getParameterValueAsString(Budget.class, Constants.BUDGET_STATUS_COMPLETE_CODE);

        for (ProposalDevelopmentBudgetExt version : developmentProposal.getBudgets()) {
            if (!(version.getBudgetStatus() == null ) && version.getBudgetStatus().equalsIgnoreCase(completeCode)) {
                retval = true;
                break;
            }
        }
        return retval;
    }

	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}
	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	public void setKradWorkflowDocumentService(
			WorkflowDocumentService kradWorkflowDocumentService) {
		this.kradWorkflowDocumentService = kradWorkflowDocumentService;
	}
	public void setKcDocumentRejectionService(
			KcDocumentRejectionService kcDocumentRejectionService) {
		this.kcDocumentRejectionService = kcDocumentRejectionService;
	}

    public PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }
	protected ProposalBudgetHierarchyService getProposalBudgetHierarchyService() {
		return proposalBudgetHierarchyService;
	}
	public void setProposalBudgetHierarchyService(
			ProposalBudgetHierarchyService proposalBudgetHierarchyService) {
		this.proposalBudgetHierarchyService = proposalBudgetHierarchyService;
	}

}
