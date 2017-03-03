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
package org.kuali.coeus.propdev.impl.auth.perm;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.ArrayList;
import java.util.List;


public class ProposalDevelopmentPermissionsServiceTest {

    public static final String LOGGED_IN_USER_ID = "101";
    public static final String POSTDOC = "Postdoc";
    public static final String GRADUATE_STUDENT = "GraduateStudent";
    public static boolean CUSTOM_DATA_ENFORCED = true;
    public static boolean SPONSOR_REQUIRED = true;
    public static boolean EXEMPT_ADDRESSBOOK_MULTI_PI_CERT = true;
    public static boolean EXEMPT_ADDRESSBOOK_KP_CERT = true;

    String USER_ID = "100";

    class ProposalDevelopmentPermissionsServiceTestImpl extends ProposalDevelopmentPermissionsServiceImpl {

        @Override
        protected List<String> getExemptKeyPersonRoles() {
            List<String> EXEMPT_ROLES = new ArrayList<>();
            EXEMPT_ROLES.add(POSTDOC);
            return EXEMPT_ROLES;
        }

        @Override
        public boolean isPiCoiKeyPersonsForcedToDiscloseWithCustomData(DevelopmentProposal developmentProposal) {
            return CUSTOM_DATA_ENFORCED;
        }

        @Override
        public boolean doesSponsorRequireKeyPersonCertification(ProposalPerson proposalPerson) {
            return SPONSOR_REQUIRED;
        }

        @Override
        protected boolean isCoiDisclosureStatusFeatureEnabled() {
            return true;
        }
        
        @Override
        public boolean isRolodexCertificationEnabled() {
        	return true;
        }
        
        @Override
        public boolean isAddressBookMultiPiCertificationExempt(ProposalPerson proposalPerson) {
        	return EXEMPT_ADDRESSBOOK_MULTI_PI_CERT;
        }
        
        @Override
        public boolean isKeyPersonRoleExempt(ProposalPerson proposalPerson) {
        	return EXEMPT_ADDRESSBOOK_KP_CERT;
        }

    }

    class ProposalPersonTestImpl extends ProposalPerson {
        @Override
        public KcPerson getPerson() {
           return new KcPerson();
        }
    }

    @Test
    public void testCanLoggedInPiCertify() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson pi = new ProposalPersonTestImpl();
        pi.setPersonId(USER_ID);
        pi.setProposalPersonRoleId(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
        boolean canCertify = permissionService.canCertify(USER_ID, pi, false);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanProxyCertifyForRolodex() throws Exception {
        class ProposalRolodexPersonTestImpl extends ProposalPerson {
            @Override
            public KcPerson getPerson() {
                return null;
            }
        }

        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson proxy = new ProposalRolodexPersonTestImpl();
        proxy.setPersonId(USER_ID);
        proxy.setProposalPersonRoleId(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        boolean canCertify = permissionService.canCertify(USER_ID, proxy, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanCoiCertify() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson coi = new ProposalPersonTestImpl();
        coi.setPersonId(USER_ID);
        coi.setProposalPersonRoleId(PropAwardPersonRole.CO_INVESTIGATOR);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = false;
        boolean canCertify = permissionService.canCertify(USER_ID, coi, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanMultiPiCertify() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson mpi = new ProposalPersonTestImpl();
        mpi.setPersonId(USER_ID);
        mpi.setProposalPersonRoleId(PropAwardPersonRole.MULTI_PI);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = false;
        boolean canCertify = permissionService.canCertify(USER_ID, mpi, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanProxyCertifyForPi() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson mpi = new ProposalPersonTestImpl();
        mpi.setPersonId(USER_ID);
        mpi.setProposalPersonRoleId(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        boolean canCertify = permissionService.canCertify(LOGGED_IN_USER_ID, mpi, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanProxyCertifyForCoI() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson mpi = new ProposalPersonTestImpl();
        mpi.setPersonId(USER_ID);
        mpi.setProposalPersonRoleId(PropAwardPersonRole.CO_INVESTIGATOR);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        boolean canCertify = permissionService.canCertify(LOGGED_IN_USER_ID, mpi, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanProxyCertifyForMultiPi() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson mpi = new ProposalPersonTestImpl();
        mpi.setPersonId(USER_ID);
        mpi.setProposalPersonRoleId(PropAwardPersonRole.MULTI_PI);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        boolean canCertify = permissionService.canCertify(LOGGED_IN_USER_ID, mpi, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanExemptKeyPersonCertify() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson kp = new ProposalPersonTestImpl();
        kp.setPersonId(USER_ID);
        kp.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        kp.setProjectRole(POSTDOC);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = false;
        boolean canCertify = permissionService.canCertify(USER_ID, kp, canProxyCertify);
        Assert.assertFalse(canCertify);
    }

    @Test
    public void testKeyPersonProxyCertifyWhenCustomDataEnforced() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson kp = new ProposalPersonTestImpl();
        kp.setPersonId(USER_ID);
        kp.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        kp.setProjectRole(GRADUATE_STUDENT);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        CUSTOM_DATA_ENFORCED = true;
        boolean canCertify = permissionService.canCertify(LOGGED_IN_USER_ID, kp, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testExemptKeyPersonProxyCertifyWhenCustomDataEnforced() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson kp = new ProposalPersonTestImpl();
        kp.setPersonId(USER_ID);
        kp.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        kp.setProjectRole(GRADUATE_STUDENT);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        CUSTOM_DATA_ENFORCED = true;
        boolean canCertify = permissionService.canCertify(LOGGED_IN_USER_ID, kp, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testKeyPersonProxyCertifyWhenSponsorRequired() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson kp = new ProposalPersonTestImpl();
        kp.setPersonId(USER_ID);
        kp.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        kp.setProjectRole(GRADUATE_STUDENT);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        CUSTOM_DATA_ENFORCED = false;
        SPONSOR_REQUIRED = true;
        boolean canCertify = permissionService.canCertify(LOGGED_IN_USER_ID, kp, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void testCanViewerCertifyKeyPerson() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson kp = new ProposalPersonTestImpl();
        kp.setPersonId(USER_ID);
        kp.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        kp.setProjectRole(GRADUATE_STUDENT);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = false;
        CUSTOM_DATA_ENFORCED = false;
        SPONSOR_REQUIRED = true;
        boolean canCertify = permissionService.canCertify(LOGGED_IN_USER_ID, kp, canProxyCertify);
        Assert.assertFalse(canCertify);
    }

    @Test
    public void testCanKPCertifyWhenSponsorAndCustomDataNotRequiredAndNotExempt() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson kp = new ProposalPersonTestImpl();
        kp.setPersonId(USER_ID);
        kp.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        kp.setProjectRole(GRADUATE_STUDENT);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = false;
        CUSTOM_DATA_ENFORCED = false;
        SPONSOR_REQUIRED = false;
        boolean canCertify = permissionService.canCertify(USER_ID, kp, canProxyCertify);
        Assert.assertFalse(canCertify);
    }
    
    @Test
    public void testCanCertifyAddressBookPersonWithMultiPiRoleAndNotExempt() throws Exception {
        class ProposalRolodexPersonTestImpl extends ProposalPerson {
            @Override
            public KcPerson getPerson() {
                return null;
            }
        }

        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson proxy = new ProposalRolodexPersonTestImpl();
        proxy.setPersonId(USER_ID);
        proxy.setProposalPersonRoleId(PropAwardPersonRole.MULTI_PI);
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        EXEMPT_ADDRESSBOOK_MULTI_PI_CERT=false;
        boolean canCertify = permissionService.canCertify(USER_ID, proxy, canProxyCertify);
        Assert.assertTrue(canCertify);
    }

    @Test
    public void doesRoleExemptKeyPersonRequireCertification() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson keyPerson = new ProposalPersonTestImpl();
        keyPerson.setPersonId(USER_ID);
        keyPerson.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        keyPerson.setProjectRole(POSTDOC);
        EXEMPT_ADDRESSBOOK_KP_CERT = true;
        boolean needsToCertify = permissionService.doesPersonRequireCertification(keyPerson);
        Assert.assertFalse(needsToCertify);
    }

    @Test
    public void doesNonRoleExemptKeyPersonRequireCertification() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson keyPerson = new ProposalPersonTestImpl();
        keyPerson.setPersonId(USER_ID);
        keyPerson.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        keyPerson.setProjectRole(GRADUATE_STUDENT);
        boolean needsToCertify = permissionService.doesPersonRequireCertification(keyPerson);
        Assert.assertTrue(needsToCertify);
    }

    @Test
    public void doesCustomDataEnforcedKeyPersonRequireCertification() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson keyPerson = new ProposalPersonTestImpl();
        keyPerson.setPersonId(USER_ID);
        keyPerson.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        keyPerson.setProjectRole(GRADUATE_STUDENT);
        CUSTOM_DATA_ENFORCED = true;
        boolean needsToCertify = permissionService.doesPersonRequireCertification(keyPerson);
        Assert.assertTrue(needsToCertify);
    }

    @Test
    public void doesSponsorEnforcedKeyPersonRequireCertification() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson keyPerson = new ProposalPersonTestImpl();
        keyPerson.setPersonId(USER_ID);
        keyPerson.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        keyPerson.setProjectRole(GRADUATE_STUDENT);
        CUSTOM_DATA_ENFORCED = false;
        SPONSOR_REQUIRED = true;
        boolean needsToCertify = permissionService.doesPersonRequireCertification(keyPerson);
        Assert.assertTrue(needsToCertify);
    }

    @Test
    public void doesNonKeyPersonRequireCertification() throws Exception {
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson keyPerson = new ProposalPersonTestImpl();
        keyPerson.setPersonId(USER_ID);
        keyPerson.setProposalPersonRoleId(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
        boolean needsToCertify = permissionService.doesPersonRequireCertification(keyPerson);
        Assert.assertTrue(needsToCertify);
    }

    @Test
    public void doesRolodexRequireCertification() throws Exception {
        class ProposalRolodexPersonTestImpl extends ProposalPerson {
            @Override
            public KcPerson getPerson() {
                return null;
            }
        }
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson keyPerson = new ProposalRolodexPersonTestImpl();
        keyPerson.setPersonId(USER_ID);
        keyPerson.setProposalPersonRoleId(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR);
        EXEMPT_ADDRESSBOOK_MULTI_PI_CERT = false;
        boolean needsToCertify = permissionService.doesPersonRequireCertification(keyPerson);
        Assert.assertTrue(needsToCertify);
    }
    
    @Test
    public void testCanCertifyAddressBookPersonWithKeyPersonRoleAndNotExempt() throws Exception {
        class ProposalRolodexPersonTestImpl extends ProposalPerson {
            @Override
            public KcPerson getPerson() {
                return null;
            }
        }
        ProposalDevelopmentPermissionsServiceTestImpl permissionService = new ProposalDevelopmentPermissionsServiceTestImpl();
        ProposalPerson keyPerson = new ProposalRolodexPersonTestImpl();
        keyPerson.setPersonId(USER_ID);
        keyPerson.setProposalPersonRoleId(PropAwardPersonRole.KEY_PERSON);
        EXEMPT_ADDRESSBOOK_KP_CERT = false;
        boolean isLoggedInUserPi = false;
        boolean canProxyCertify = true;
        boolean needsToCertify = permissionService.canCertify(USER_ID, keyPerson, canProxyCertify);
        Assert.assertTrue(needsToCertify);
    }
    
}
