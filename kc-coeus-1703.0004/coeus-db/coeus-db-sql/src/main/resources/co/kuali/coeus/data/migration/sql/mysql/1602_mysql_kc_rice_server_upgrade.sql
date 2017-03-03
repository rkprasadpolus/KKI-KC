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


\. ./rice/bootstrap/V1602_001__kc_post_processor.sql
\. ./rice/bootstrap/V1602_003__new_breakupinterval_rate_calc_param.sql
\. ./rice/bootstrap/V1602_005__cleanup_procedures.sql
\. ./rice/bootstrap/V1602_006__enable_new_breakupinterval_rate_calc.sql
\. ./rice/bootstrap/V1602_007__awardFunctions.sql
\. ./rice/bootstrap/V1602_008__KRMS_Functions.sql
\. ./rice/bootstrap/V1602_009__rename_bo_class.sql
\. ./rice/bootstrap/V1602_010__coi_admin_bo_permissions.sql
\. ./rice/bootstrap/V1602_011__CustomData_KRMS_Function.sql
commit;
