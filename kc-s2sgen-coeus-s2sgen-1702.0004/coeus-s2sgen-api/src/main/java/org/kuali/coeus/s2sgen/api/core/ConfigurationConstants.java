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
package org.kuali.coeus.s2sgen.api.core;

/**
 * Configuration constants used by the s2s subsystem.  The constants in this class should only
 * refer to configuration names used by the {@link org.kuali.coeus.propdev.api.s2s.S2SConfigurationService}.
 */
public final class ConfigurationConstants {

    //referenced from other modules
    public static final String IRB_PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED = "irb.protocol.development.proposal.linking.enabled";
    public static final String IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER = "iacuc.protocol.proposal.development.linking.enabled";
    public static final String DHHS_AGREEMENT = "DHHS_AGREEMENT";
    public static final String PROPOSAL_TYPE_CODE_NEW = "PROPOSAL_TYPE_CODE_NEW";
    public static final String PROPOSAL_TYPE_CODE_RESUBMISSION = "PROPOSAL_TYPE_CODE_RESUBMISSION";
    public static final String PROPOSAL_TYPE_CODE_RENEWAL = "PROPOSAL_TYPE_CODE_RENEWAL";
    public static final String PROPOSAL_TYPE_CODE_CONTINUATION = "PROPOSAL_TYPE_CODE_CONTINUATION";
    public static final String PROPOSAL_TYPE_CODE_REVISION = "PROPOSAL_TYPE_CODE_REVISION";
    public static final String ACTIVITY_TYPE_CODE_CONSTRUCTION = "ACTIVITY_TYPE_CODE_CONSTRUCTION";
    public static final String PROPOSAL_TYPE_CODE_PRE_PROPOSAL = "PROPOSAL_TYPE_CODE_PRE_PROPOSAL";
    public static final String PROPOSAL_TYPE_CODE_NEW_CHANGE_CORRECTED = "PROPOSAL_TYPE_CODE_NEW_CHANGE_CORRECTED";
    public static final String PROPOSAL_TYPE_CODE_SUPPLEMENT_CHANGE_CORRECTED = "PROPOSAL_TYPE_CODE_SUPPLEMENT_CHANGE_CORRECTED";
    public static final String PROPOSAL_TYPE_CODE_RENEWAL_CHANGE_CORRECTED = "PROPOSAL_TYPE_CODE_RENEWAL_CHANGE_CORRECTED";
    public static final String PROPOSAL_TYPE_CODE_RESUBMISSION_CHANGE_CORRECTED = "PROPOSAL_TYPE_CODE_RESUBMISSION_CHANGE_CORRECTED";
    public static final String MULTI_CAMPUS_ENABLED = "MULTI_CAMPUS_ENABLED";

    //s2s specific
    public static final String TUITION_COST_ELEMENTS = "TUITION_COST_ELEMENTS";
    public static final String STIPEND_COST_ELEMENTS = "STIPEND_COST_ELEMENTS";
    public static final String S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION = "S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION";
    public static final String TUITION_POSTDOC_DEG_COST_ELEMENTS = "TUITION_POSTDOC_DEG_COST_ELEMENTS";
    public static final String TUITION_OTHER_COST_ELEMENTS = "TUITION_OTHER_COST_ELEMENTS";
    public static final String TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS = "TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS";
    public static final String TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS = "TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS";
    public static final String TUITION_UNDERGRAD_COST_ELEMENTS = "TUITION_UNDERGRAD_COST_ELEMENTS";
    public static final String TUITION_POSTDOC_NONDEG_COST_ELEMENTS = "TUITION_POSTDOC_NONDEG_COST_ELEMENTS";
    public static final String SUBCONTRACT_COST_ELEMENTS = "SUBCONTRACT_COST_ELEMENTS";
    public static final String TRAINING_REL_COST_ELEMENTS = "TRAINING_REL_COST_ELEMENTS";
    public static final String TRAINEE_TRAVEL_COST_ELEMENTS = "TRAINEE_TRAVEL_COST_ELEMENTS";
    public static final String PROPOSAL_CONTACT_TYPE = "PROPOSAL_CONTACT_TYPE";
    public static final String S2SBUDGET_BUDGET_CATEGORY_TYPE_PERSONNEL = "s2sBudgetBudgetCategoryTypePersonnel";
    public static final String S2SBUDGET_FILTER_CATEGORY_TYPE_PERSONNEL = "s2sBudgetFilterCategoryTypePersonnel";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_SALARIES_MS = "s2sBudgetRateClassTypeSalariesMs";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_LAB_ALLOCATION_SALARIES = "s2sBudgetRateClassTypeLabAllocationSalaries";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_EMPLOYEE_BENEFITS = "s2sBudgetRateClassTypeEmployeeBenefits";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_VACATION = "s2sBudgetRateClassTypeVacation";
    public static final String S2SBUDGET_RATE_TYPE_ADMINISTRATIVE_SALARIES = "s2sBudgetRateTypeAdministrativeSalaries";
    public static final String S2SBUDGET_RATE_TYPE_SUPPORT_STAFF_SALARIES = "s2sBudgetRateTypeSupportStaffSalaries";
    public static final String S2SBUDGET_RATE_CLASS_CODE_EMPLOYEE_BENEFITS = "s2sBudgetRateClassCodeEmployeeBenefits";
    public static final String S2SBUDGET_RATE_CLASS_CODE_VACATION = "s2sBudgetRateClassCodeVacation";
    public static final String S2SBUDGET_PERIOD_TYPE_ACADEMIC_MONTHS = "s2sBudgetPeriodTypeAcademicMonths";
    public static final String S2SBUDGET_PERIOD_TYPE_CALENDAR_MONTHS = "s2sBudgetPeriodTypeCalendarMonths";
    public static final String S2SBUDGET_PERIOD_TYPE_SUMMER_MONTHS = "s2sBudgetPeriodTypeSummerMonths";
    public static final String S2SBUDGET_PERIOD_TYPE_CYCLE_MONTHS = "s2sBudgetPeriodTypeCycleMonths";
    public static final String S2SBUDGET_TARGET_CATEGORY_CODE_EQUIPMENT_COST = "s2sBudgetTargetCategoryCodeEquipmentCost";
    public static final String S2SBUDGET_BUDGET_CATEGORY_CODE_PERSONNEL = "s2sBudgetCategoryCodePersonnel";
    public static final String S2SBUDGET_CATEGORY_01_GRADUATES = "s2sBudgetCategory01Graduates";
    public static final String S2SBUDGET_CATEGORY_01_POSTDOCS = "s2sBudgetCategory01Postdocs";
    public static final String S2SBUDGET_CATEGORY_01_UNDERGRADS = "s2sBudgetCategory01Undergrads";
    public static final String S2SBUDGET_CATEGORY_01_SECRETARIAL = "s2sBudgetCategory01Secretarial";
    public static final String S2SBUDGET_CATEGORY_01_OTHER = "s2sBudgetCategory01Other";
    public static final String S2SBUDGET_CATEGORY_01_OTHER_PROFS = "s2sBudgetCategory01OtherProfs";
    public static final String S2SBUDGET_MATERIALS_AND_SUPPLIES_CATEGORY = "s2sBudgetMaterialsAndSuppliesCategory";
    public static final String S2SBUDGET_CONSULTANT_COSTS_CATEGORY = "s2sBudgetConsultantCostsCategory";
    public static final String S2SBUDGET_PUBLICATION_COSTS_CATEGORY = "s2sBudgetPublicationCostsCategory";
    public static final String S2SBUDGET_COMPUTER_SERVICES_CATEGORY = "s2sBudgetComputerServicesCategory";
    public static final String S2SBUDGET_ALTERATIONS_CATEGORY = "s2sBudgetAlterationsCategory";
    public static final String S2SBUDGET_SUBCONTRACT_CATEGORY = "s2sBudgetSubcontractCategory";
    public static final String S2SBUDGET_EQUIPMENT_RENTAL_CATEGORY = "s2sBudgetEquipmentRentalCategory";
    public static final String S2SBUDGET_DOMESTIC_TRAVEL_CATEGORY = "s2sBudgetDomesticTravelCategory";
    public static final String S2SBUDGET_FOREIGN_TRAVEL_CATEGORY = "s2sBudgetForeignTravelCategory";
    public static final String S2SBUDGET_PARTICIPANT_STIPENDS_CATEGORY = "s2sBudgetParticipantStipendsCategory";
    public static final String S2SBUDGET_PARTICIPANT_TRAVEL_CATEGORY = "s2sBudgetParticipantTravelCategory";
    public static final String S2SBUDGET_PARTICIPANT_TUITION_CATEGORY = "s2sBudgetParticipantTutionCategory";
    public static final String S2SBUDGET_PARTICIPANT_SUBSISTENCE_CATEGORY = "s2sBudgetParticipantSubsistenceCategory";
    public static final String S2SBUDGET_PARTICIPANT_OTHER_CATEGORY = "s2sBudgetParticipantOtherCategory";
    public static final String S2SBUDGET_OTHER_DIRECT_COSTS_CATEGORY = "s2sBudgetOtherDirectCostsCategory";
    public static final String S2SBUDGET_APPOINTMENT_TYPE_SUM_EMPLOYEE = "s2sBudgetAppointmentTypeSumEmployee";
    public static final String S2SBUDGET_APPOINTMENT_TYPE_TMP_EMPLOYEE = "s2sBudgetAppointmentTypeTmpEmployee";
    public static final String BUDGET_CATEGORY_TYPE_PARTICIPANT_SUPPORT = "budgetCategoryType.participantSupport";
    public static final String HIERARCHY_LEVEL = "HIERARCHY_LEVEL";


    //printing
    public static final String PRINT_XML_DIRECTORY = "print.xml.directory";
    public static final String PRINT_LOGGING_ENABLE = "print.logging.enable";
    public static final String PRINT_PDF_LOGGING_ENABLE = "print.pdf.logging.enable";
    public static final String PRINT_LOGGING_DIRECTORY = "print.logging.directory";
    public static final String APPLICATION_URL_KEY = "application.url";

    private ConfigurationConstants() {
        throw new UnsupportedOperationException("do not call");
    }
}
