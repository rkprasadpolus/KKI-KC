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

spool 1609_oracle_kc_upgrade.sql.log
@./kc/bootstrap/V1609_001__subaward_modification_type2.sql
@./kc/bootstrap/V1609_002__training_module_code.sql
@./kc/bootstrap/V1609_004__fdp_form_update.sql
@./kc/bootstrap/V1609_007__training_module.sql
@./kc/bootstrap/V1609_008__ffata_reporting.sql
@./kc/bootstrap/V1609_010__fdp_form_update.sql
commit;
