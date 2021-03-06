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

spool 520_oracle_rice_client_upgrade.sql.log
@./kc/embedded_client_scripts/V520_034__KRC_RICE_01_2.2.1-2.2.2_2013-03-18_B000.sql
@./kc/embedded_client_scripts/V520_035__KRC_RICE_02_2.2.1-2.2.2_CLEANUP-2013-03-18_B000.sql
commit;
