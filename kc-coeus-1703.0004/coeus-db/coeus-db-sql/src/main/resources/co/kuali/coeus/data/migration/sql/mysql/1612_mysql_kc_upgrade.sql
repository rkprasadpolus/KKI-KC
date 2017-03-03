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


\. ./kc/bootstrap/V1612_002__RESKC-1441_credit_split.sql
\. ./kc/bootstrap/V1612_003__nsf_references.sql
\. ./kc/bootstrap/V1612_004__add_nsf_codes.sql
\. ./kc/bootstrap/V1612_007__add_unit_cost_share.sql
\. ./kc/bootstrap/V1612_008__fy_based_ip.sql
\. ./kc/bootstrap/V1612_009__alter_duration.sql
commit;
