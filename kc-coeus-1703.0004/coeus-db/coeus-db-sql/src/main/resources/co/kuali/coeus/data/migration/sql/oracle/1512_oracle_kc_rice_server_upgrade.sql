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

spool 1512_oracle_kc_rice_server_upgrade.sql.log
@./rice/bootstrap/V1512_001__RESKC-979.sql
@./rice/bootstrap/V1512_004__invoice_inquiry.sql
@./rice/bootstrap/V1512_005__RESOPS-195-AlphaSortNarrativeType.sql
@./rice/bootstrap/V1512_007__REVERT_term_spec.sql
commit;
