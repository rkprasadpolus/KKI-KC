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


\. ./rice/bootstrap/V1601_001__BO_permissions.sql
\. ./rice/bootstrap/V1601_002__budget_BO_permissions.sql
\. ./rice/bootstrap/V1601_004__neg_unrecovered_f_a.sql
\. ./rice/bootstrap/V1601_008__RESKC-767.sql
\. ./rice/bootstrap/V1601_009__RESKC-1116.sql
\. ./rice/bootstrap/V1601_010__update_editable_param.sql
commit;
