--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2015 Kuali, Inc.
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
--
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

update krim_role_t set kim_typ_id = '1' where nmspc_cd = 'KC-SYS' and role_nm = 'KC Superuser';

delete from krim_role_mbr_attr_data_t 
	where kim_typ_id = (select kim_typ_id from krim_typ_t where nm = 'UnitHierarchy' and nmspc_cd = 'KC-SYS') 
	and role_mbr_id in (select role_mbr_id from krim_role_mbr_t where role_id = (select role_id from krim_role_t where nmspc_cd = 'KC-SYS' and role_nm = 'KC Superuser'));
