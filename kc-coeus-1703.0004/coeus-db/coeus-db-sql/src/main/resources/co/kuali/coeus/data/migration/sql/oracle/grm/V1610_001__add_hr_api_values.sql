--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
SELECT 'Y','98','S',SYSDATE,'Sabbatical Leave',SYS_GUID(),1 FROM dual
WHERE NOT EXISTS (select EMP_STAT_CD from KRIM_EMP_STAT_T where EMP_STAT_CD = 'S');

INSERT INTO CITIZENSHIP_TYPE_T (CITIZENSHIP_TYPE_CODE, DESCRIPTION, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
SELECT 5, 'OTHER', 'Y', SYSDATE, 'admin', 1, SYS_GUID() FROM dual
WHERE NOT EXISTS (select CITIZENSHIP_TYPE_CODE from CITIZENSHIP_TYPE_T where CITIZENSHIP_TYPE_CODE = 5);
