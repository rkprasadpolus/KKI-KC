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

set define off
set sqlblanklines on

spool 1609_oracle_kc_rice_server_upgrade.sql.log
@./rice/bootstrap/V1609_003__fdp_flags.sql
@./rice/bootstrap/V1609_005__add_subaward_component.sql
@./rice/bootstrap/V1609_006__fdp_update_param_desc.sql
@./rice/bootstrap/V1609_009__ffata_reporting_flag.sql
@./rice/bootstrap/V1609_011__pd_approval_dialog_flag.sql
@./rice/bootstrap/V1609_012__pd_approval_narrative_type.sql
commit;
