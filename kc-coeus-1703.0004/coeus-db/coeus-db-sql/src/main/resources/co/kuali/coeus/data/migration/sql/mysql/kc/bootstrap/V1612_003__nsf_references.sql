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

update EPS_PROPOSAL t set t.NSF_CODE = (select u.NSF_SEQUENCE_NUMBER from NSF_CODES u where u.NSF_CODE = t.NSF_CODE);
update AWARD t set t.NSF_CODE = (select u.NSF_SEQUENCE_NUMBER from NSF_CODES u where u.NSF_CODE = t.NSF_CODE);
update PROPOSAL t set t.NSF_CODE = (select u.NSF_SEQUENCE_NUMBER from NSF_CODES u where u.NSF_CODE = t.NSF_CODE);

alter table EPS_PROPOSAL CHANGE COLUMN NSF_CODE NSF_SEQUENCE_NUMBER decimal(12,0);

ALTER TABLE EPS_PROPOSAL
  ADD CONSTRAINT FK_EPS_PROPOSAL_NSF
FOREIGN KEY (NSF_SEQUENCE_NUMBER)
REFERENCES NSF_CODES (NSF_SEQUENCE_NUMBER);

alter table AWARD CHANGE COLUMN NSF_CODE NSF_SEQUENCE_NUMBER decimal(12,0);

ALTER TABLE AWARD
  ADD CONSTRAINT FK_AWARD_NSF
FOREIGN KEY (NSF_SEQUENCE_NUMBER)
REFERENCES NSF_CODES (NSF_SEQUENCE_NUMBER);

alter table PROPOSAL CHANGE COLUMN NSF_CODE NSF_SEQUENCE_NUMBER decimal(12,0);

ALTER TABLE PROPOSAL
  ADD CONSTRAINT FK_PROPOSAL_NSF
FOREIGN KEY (NSF_SEQUENCE_NUMBER)
REFERENCES NSF_CODES (NSF_SEQUENCE_NUMBER);

