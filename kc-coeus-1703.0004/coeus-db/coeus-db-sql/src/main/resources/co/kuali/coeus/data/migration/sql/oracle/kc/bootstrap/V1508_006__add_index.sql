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
-- along with this program.  If not, see <http://www.gnu.org/lincenses/>.
--

create index IACUC_PROTOCOL_ONLN_RVWS_IDX1 on
  IACUC_PROTOCOL_ONLN_RVWS(PROTOCOL_REVIEWER_FK);

create index IACUC_PROTOCOL_ONLN_RVWS_IDX2 on
  IACUC_PROTOCOL_ONLN_RVWS(REVIEW_DETERM_RECOM_CD);

create index IACUC_PROTOCOL_ONLN_RVWS_IDX3 on
  IACUC_PROTOCOL_ONLN_RVWS(PROTOCOL_ONLN_RVW_STATUS_CODE);

create index IACUC_PROTOCOL_ONLN_RVWS_IDX4 on
  IACUC_PROTOCOL_ONLN_RVWS(SUBMISSION_ID_FK);

create index IACUC_PROTOCOL_ONLN_RVWS_IDX5 on
  IACUC_PROTOCOL_ONLN_RVWS(DOCUMENT_NUMBER);

create index IACUC_PROTOCOL_ONLN_RVWS_IDX6 on
  IACUC_PROTOCOL_ONLN_RVWS(PROTOCOL_ID);

ALTER TABLE IACUC_PROTOCOL_ONLN_RVWS
ADD CONSTRAINT FK2_IACUC_PROTOCOL_ONLN_RVWS FOREIGN KEY (PROTOCOL_REVIEWER_FK)
REFERENCES IACUC_PROTOCOL_REVIEWERS (PROTOCOL_REVIEWER_ID);

ALTER TABLE IACUC_PROTOCOL_ONLN_RVWS
ADD CONSTRAINT FK5_IACUC_PROTOCOL_ONLN_RVWS FOREIGN KEY (REVIEW_DETERM_RECOM_CD)
REFERENCES iacuc_proto_olr_dt_rec_type (REVIEW_DETERM_RECOM_CD);

ALTER TABLE IACUC_PROTOCOL_ONLN_RVWS
ADD CONSTRAINT FK1_IACUC_PROTOCOL_ONLN_RVWS FOREIGN KEY (PROTOCOL_ONLN_RVW_STATUS_CODE)
REFERENCES iacuc_protocol_olr_status (STATUS_CODE);

ALTER TABLE IACUC_PROTOCOL_ONLN_RVWS
ADD CONSTRAINT FK3_IACUC_PROTOCOL_ONLN_RVWS FOREIGN KEY (SUBMISSION_ID_FK)
REFERENCES IACUC_PROTOCOL_SUBMISSION (IACUC_PROTOCOL_SUBMISSION_ID);

ALTER TABLE IACUC_PROTOCOL_ONLN_RVWS
ADD CONSTRAINT FK4_IACUC_PROTOCOL_ONLN_RVWS FOREIGN KEY (PROTOCOL_ID)
REFERENCES IACUC_PROTOCOL (PROTOCOL_ID);

create index PROTOCOL_ONLN_RVWS_IDX6 on
  PROTOCOL_ONLN_RVWS(PROTOCOL_ID);