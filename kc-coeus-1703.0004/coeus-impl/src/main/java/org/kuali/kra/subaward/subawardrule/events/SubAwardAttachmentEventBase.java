/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.subaward.subawardrule.events;



import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.subaward.subawardrule.AddSubAwardAttachmentRule;
import org.kuali.kra.subaward.subawardrule.events.SubAwardAttachmentEvent;
import org.kuali.kra.subaward.bo.SubAwardAttachments;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

public class SubAwardAttachmentEventBase extends KcDocumentEventBase implements SubAwardAttachmentEvent {
    
    private SubAwardAttachments attachment;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(SubAwardAttachmentEventBase.class);
    
    protected SubAwardAttachmentEventBase(String description, String errorPathPrefix, SubAwardDocument document, SubAwardAttachments attachment) {
        super(description, errorPathPrefix, document);
        this.attachment = attachment;
        logEvent();
    }
    
    protected SubAwardAttachmentEventBase( String description, String errorPathPrefix, SubAwardDocument document ) {
        super( description, errorPathPrefix, document );
        logEvent();
    }

    @Override
    protected void logEvent() {
        if( LOG.isDebugEnabled() )
            LOG.debug(getDescription());
    }

    public SubAwardAttachments getSubAwardAttachments() {
       return attachment;
    }

    public void setSubAwardAttachments(SubAwardAttachments attachment) {
        this.attachment = attachment;
    }

    
    public Class getRuleInterfaceClass() {
       return AddSubAwardAttachmentRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return false;
    }

}
