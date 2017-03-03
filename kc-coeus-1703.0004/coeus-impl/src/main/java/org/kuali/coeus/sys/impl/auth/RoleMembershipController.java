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
package org.kuali.coeus.sys.impl.auth;

import com.codiform.moo.curry.Translate;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.auth.RoleMembershipDto;
import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudMapBasedRestController;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.entry;
import static org.kuali.coeus.sys.framework.util.CollectionUtils.entriesToMap;

public class RoleMembershipController extends SimpleCrudMapBasedRestController {

	private static final String NAMESPACE_NAME_DELIMETER = ":";
	private static final String QUALIFIER_NAME_VALUE_DELIMETER = ":";

	@Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

	@RequestMapping(method= RequestMethod.GET, value="api/v1/roles/{roleId}/principals/{memberId}")
	public @ResponseBody List<RoleMembershipDto> getRoleMembershipsForMemberId(@PathVariable String roleId, @PathVariable String memberId, @RequestParam(value="qualification", required=false) String[] qualification) {
		assertMethodSupported(RequestMethod.GET);

		if (StringUtils.isBlank(roleId)) {
			throw new ResourceNotFoundException("roleId is blank");
		}

		if (StringUtils.isBlank(memberId)) {
			throw new ResourceNotFoundException("memberId is blank");
		}

		final Map<String, String> qualificationMap = withPrincipalQualifier(memberId, getQualifierMap(qualification != null ? Arrays.asList(qualification) : Collections.emptyList()));

		final List<RoleMembership> roleMembers = roleService.getRoleMembers(Collections.singletonList(getActualRoleId(roleId)), qualificationMap).stream()
				.filter(m -> m.getType() == MemberType.PRINCIPAL)
				.filter(m -> m.getMemberId().equals(memberId))
				.collect(Collectors.toList());

		if (roleMembers.isEmpty()) {
			throw new ResourceNotFoundException("no results");
		}

        List<RoleMembershipDto> roleMembershipDtoList = Translate.to(RoleMembershipDto.class).fromEach(roleMembers);
        Map<String, Person> personMap = new HashMap<>();
        roleMembershipDtoList.stream().forEach(roleMembershipDto -> populatePersonData(roleMembershipDto, personMap));

        return roleMembershipDtoList;

	}

    @RequestMapping(method= RequestMethod.GET, value="api/v1/roles/{roleId}/principals/")
    public @ResponseBody List<RoleMembershipDto> getRoleMemberships(@PathVariable String roleId, @RequestParam(value="qualification", required=false) String[] qualification) {
		assertMethodSupported(RequestMethod.GET);

		if (StringUtils.isBlank(roleId)) {
            throw new ResourceNotFoundException("roleId is blank");
        }

        final Map<String, String> qualificationMap = getQualifierMap(qualification != null ? Arrays.asList(qualification) : Collections.emptyList());

        final List<RoleMembership> roleMembers = roleService.getRoleMembers(Collections.singletonList(getActualRoleId(roleId)), qualificationMap).stream()
                .filter(m -> m.getType() == MemberType.PRINCIPAL)
                .collect(Collectors.toList());

        if (roleMembers.isEmpty()) {
            throw new ResourceNotFoundException("no results");
        }

        List<RoleMembershipDto> roleMembershipDtoList = Translate.to(RoleMembershipDto.class).fromEach(roleMembers);

        Map<String, Person> personMap = new HashMap<>();
        roleMembershipDtoList.stream().forEach(roleMembershipDto -> populatePersonData(roleMembershipDto,personMap));

        return roleMembershipDtoList;
    }

    protected void populatePersonData(RoleMembershipDto roleMembershipDto, Map<String, Person> personMap) {
        final Person person;
        if (!personMap.containsKey(roleMembershipDto.getMemberId())) {
            person = getPersonService().getPerson(roleMembershipDto.getMemberId());
            personMap.put(roleMembershipDto.getMemberId(), person);
        } else {
            person = personMap.get(roleMembershipDto.getMemberId());
        }

        if (person != null) {
            roleMembershipDto.setFullName((person.getFirstName() + " " + person.getLastName()).trim());
            roleMembershipDto.setEmail(person.getEmailAddress());
        }
    }

    /**
	 * Some roles like the user role require a principal qualifier to make a match.
	 */
	protected Map<String, String> withPrincipalQualifier(String memberId, Map<String, String> qualificationMap) {
		final HashMap<String, String> map = new HashMap<>(qualificationMap);
		map.put(KimConstants.AttributeConstants.PRINCIPAL_ID, memberId);
		return map;
	}

	protected Map<String, String> getQualifierMap(List<String> qualification) {
		return qualification.stream().map(qualifier -> {
			final String[] q = qualifier.split(QUALIFIER_NAME_VALUE_DELIMETER);
			return entry(q[0], q[1]);
		}).collect(entriesToMap());
	}

	protected String getActualRoleId(String roleId) {
		final String actualRoleId;
		if (roleId.contains(NAMESPACE_NAME_DELIMETER)) {
			final String[] namespaceAndName = roleId.split(NAMESPACE_NAME_DELIMETER);
			final String namespace = namespaceAndName[0];
			final String name = namespaceAndName[1];
			if (StringUtils.isBlank(namespace)) {
				throw new ResourceNotFoundException("blank namespace from roleId");
			}

			if (StringUtils.isBlank(name)) {
				throw new ResourceNotFoundException("blank name from roleId");
			}

			actualRoleId = roleService.getRoleIdByNamespaceCodeAndName(namespace, name);
		} else {
			actualRoleId = roleId;
		}

		if (StringUtils.isBlank(actualRoleId)) {
			throw new ResourceNotFoundException("roleId not found");
		}
		return actualRoleId;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
