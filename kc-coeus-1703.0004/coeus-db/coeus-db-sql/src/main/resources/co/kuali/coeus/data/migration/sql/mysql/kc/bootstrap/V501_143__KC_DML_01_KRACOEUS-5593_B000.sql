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

DELIMITER /
insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,SYSTEM_GENERATED,ALLOW_MULTIPLE,UPDATE_TIMESTAMP,UPDATE_USER,NARRATIVE_TYPE_GROUP,VER_NBR,OBJ_ID) 
values (135,'SF424V21_Areas_Affected','N','N',NOW(),'admin','P',1,UUID())
/
insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,SYSTEM_GENERATED,ALLOW_MULTIPLE,UPDATE_TIMESTAMP,UPDATE_USER,NARRATIVE_TYPE_GROUP,VER_NBR,OBJ_ID) 
values (136,'SF424V21_Debt_Explanation','N','N',NOW(),'admin','P',1,UUID())
/
insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,SYSTEM_GENERATED,ALLOW_MULTIPLE,UPDATE_TIMESTAMP,UPDATE_USER,NARRATIVE_TYPE_GROUP,VER_NBR,OBJ_ID) 
values (137,'SF424V21_Additional_Project_Title','N','N',NOW(),'admin','P',1,UUID()) 
/
insert into NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,SYSTEM_GENERATED,ALLOW_MULTIPLE,UPDATE_TIMESTAMP,UPDATE_USER,NARRATIVE_TYPE_GROUP,VER_NBR,OBJ_ID) 
values (138,'SF424V21_Additional_Congressional_Districts','N','N',NOW(),'admin','P',1,UUID())
/
INSERT INTO SEQ_VALID_NARR_FORMS_ID VALUES(NULL)
/
insert into VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID) 
values ((SELECT (MAX(ID)) FROM SEQ_VALID_NARR_FORMS_ID),'SF424-V2.1',135,null,NOW(),'admin',UUID())
/
INSERT INTO SEQ_VALID_NARR_FORMS_ID VALUES(NULL)
/
insert into VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID) 
values ((SELECT (MAX(ID)) FROM SEQ_VALID_NARR_FORMS_ID),'SF424-V2.1',136,null,NOW(),'admin',UUID())
/
INSERT INTO SEQ_VALID_NARR_FORMS_ID VALUES(NULL)
/
insert into VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID) 
values ((SELECT (MAX(ID)) FROM SEQ_VALID_NARR_FORMS_ID),'SF424-V2.1',137,null,NOW(),'admin',UUID())
/
INSERT INTO SEQ_VALID_NARR_FORMS_ID VALUES(NULL)
/
insert into VALID_NARR_FORMS (VALID_NARR_FORMS_ID,FORM_NAME,NARRATIVE_TYPE_CODE,MANDATORY,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID) 
values ((SELECT (MAX(ID)) FROM SEQ_VALID_NARR_FORMS_ID),'SF424-V2.1',138,null,NOW(),'admin',UUID())
/

DELIMITER ;
