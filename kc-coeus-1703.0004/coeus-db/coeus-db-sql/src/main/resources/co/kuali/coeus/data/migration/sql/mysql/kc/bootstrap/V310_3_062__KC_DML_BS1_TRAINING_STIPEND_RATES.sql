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

delete from TRAINING_STIPEND_RATES;

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Undergraduates',0,7980,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Undergraduates',1,11172,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Predoctoral',0,21180,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',0,37740,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',1,39756,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',2,42624,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',3,44304,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',4,45960,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',5,47940,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',6,49836,NOW(),NOW(),'admin','1',UUID());

INSERT INTO SEQ_TRAINING_STIPEND_RATES_ID VALUES (NULL);

insert into TRAINING_STIPEND_RATES (TRAINING_STIPEND_RATES_ID,CAREER_LEVEL,EXPERIENCE_LEVEL,STIPEND_RATE,EFFECTIVE_DATE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values (LAST_INSERT_ID(),'Postdoctoral',7,52068,NOW(),NOW(),'admin','1',UUID());

commit;