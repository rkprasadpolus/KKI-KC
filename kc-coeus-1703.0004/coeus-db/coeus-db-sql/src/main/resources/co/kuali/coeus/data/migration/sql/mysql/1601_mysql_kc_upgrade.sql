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


\. ./kc/bootstrap/V1601_003__RESOPS-581.sql
\. ./kc/bootstrap/V1601_005__RestAuditLog.sql
\. ./kc/bootstrap/V1601_006__fix_create_ip_link.sql
\. ./kc/bootstrap/V1601_007__support_new_pd_attachment.sql
\. ./kc/bootstrap/V1601_012__person_editable_fields.sql
\. ./kc/bootstrap/V1601_013__reject_notification.sql
\. ./kc/bootstrap/V1601_014__approve_notification.sql
\. ./kc/bootstrap/V1601_015__disable_new_notifications.sql
commit;
