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
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '-1', 'INVALID', 'Y', '50', 'RED', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '100', 'Pending/In Progress', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '101', 'Submitted To IRB', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '102', 'Specific Minor Revisions Required', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '103', 'Defered', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '104', 'Substantive Revisions Required', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '200', 'Approved', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '304', 'Withdrawn', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '305', 'Expired', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
INSERT INTO SEQ_WATERMARK_ID VALUES(NULL)
/
INSERT INTO WATERMARK (WATERMARK_ID, STATUS_CODE, WATERMARK_TEXT, WATERMARK_STATUS, FONT_SIZE, FONT_COLOUR, UPDATE_TIMESTAMP, VER_NBR, OBJ_ID, UPDATE_USER, WATERMARK_TYPE)
VALUES ((SELECT (MAX(ID)) FROM SEQ_WATERMARK_ID), '306', 'Withdrawn', 'Y', '50', 'DARKGRAY', NOW(), 0, UUID(),'admin','TEXT')
/
commit
/
DELIMITER ;
