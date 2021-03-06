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
update krcr_parm_t set PARM_DESC_TXT = 'This parameter turns on or off the ability for a user to enter obligated amount, anticipated amount, obligated start date, obligated end date, execution date, and modification date as a cumulative value via the Award document or the Hierarchy panel on the Time and Money document. When parameter is set to Y, data can be entered as either change amount or as cumulative amount. When parameter is sent to N, only change values can be entered.' where APPL_ID = 'KC' and NMSPC_CD = 'KC-AWARD' and CMPNT_CD = 'Document' and PARM_NM = 'MAKE_AWD_CUM_ANTICIPATED_OBL_EDITABLE';
