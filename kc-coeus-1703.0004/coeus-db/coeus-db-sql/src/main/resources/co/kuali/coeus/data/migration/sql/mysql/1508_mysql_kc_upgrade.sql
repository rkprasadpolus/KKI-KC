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


\. ./kc/bootstrap/V1508_001__RESKC_654.sql
\. ./kc/bootstrap/V1508_002__add_index.sql
\. ./kc/bootstrap/V1508_004__FixAwardBudgetData.sql
\. ./kc/bootstrap/V1508_006__add_index.sql
\. ./kc/bootstrap/V1508_008__financial_rest_apis.sql
\. ./kc/bootstrap/V1508_009__RESKC-147.sql
commit;
