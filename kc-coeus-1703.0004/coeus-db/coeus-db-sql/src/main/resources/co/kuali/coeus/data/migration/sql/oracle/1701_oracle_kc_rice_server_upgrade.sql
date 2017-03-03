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

spool 1701_oracle_kc_rice_server_upgrade.sql.log
@./rice/bootstrap/V1701_001__return.sql
@./rice/bootstrap/V1701_005__ArgValuesConfigParam.sql
@./rice/bootstrap/V1701_008__cost_share_type_flag.sql
@./rice/bootstrap/V1701_013__cost_share_account.sql
@./rice/bootstrap/V1701_018__cost_share_account_perms.sql
@./rice/bootstrap/V1701_019__cost_share_validation_flag.sql
@./rice/bootstrap/V1701_020__award_budget_summary.sql
commit;
