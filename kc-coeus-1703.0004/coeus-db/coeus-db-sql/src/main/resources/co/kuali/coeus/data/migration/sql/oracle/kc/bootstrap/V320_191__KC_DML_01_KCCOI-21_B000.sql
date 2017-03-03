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

INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('203', 'Closed', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('202', 'Void - Disclosure not rquired', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('100', 'Pending', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('101', 'PI Reviewed', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('102', 'OSP-Review in Progress', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('103', 'Other Review in Progress', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('200', 'No Conflict', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('201', 'Resolved', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('300', 'Unresolved', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('104', 'Pending Annual Disclosure', SYSDATE, 'admin', SYS_GUID());
INSERT INTO COI_DISCLOSURE_STATUS (COI_DISCLOSURE_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES('204', 'Superseded by Award', SYSDATE, 'admin', SYS_GUID());
