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
package org.kuali.coeus.s2sgen.impl.generate.support;


import gov.grants.apply.forms.attachmentsV11.AttachmentsDocument;
import gov.grants.apply.forms.attachmentsV11.AttachmentsDocument.Attachments;
import gov.grants.apply.forms.attachmentsV11.AttachmentsDocument.Attachments.*;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@FormGenerator("AttachmentsV1_1Generator")
public class AttachmentsV1_1Generator extends S2SBaseFormGenerator {

    private static final String NARRATIVECODE_ATTACHMENTS = "61";

    @Value("http://apply.grants.gov/forms/Attachments-V1.1")
    private String namespace;

    @Value("Attachments-V1.1")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/stylesheet/Attachments-V1.1.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.attachmentsV11")
    private String packageName;

    @Value("240")
    private int sortIndex;


    private AttachmentsDocument getAttachment() {

        AttachmentsDocument attachmentsDocument = AttachmentsDocument.Factory.newInstance();
        Attachments attachments = Attachments.Factory.newInstance();
        attachments.setFormVersion(FormVersion.v1_1.getVersion());
        ATT1 att1 = ATT1.Factory.newInstance();
        ATT2 att2 = ATT2.Factory.newInstance();
        ATT3 att3 = ATT3.Factory.newInstance();
        ATT4 att4 = ATT4.Factory.newInstance();
        ATT5 att5 = ATT5.Factory.newInstance();
        ATT6 att6 = ATT6.Factory.newInstance();
        ATT7 att7 = ATT7.Factory.newInstance();
        ATT8 att8 = ATT8.Factory.newInstance();
        ATT9 att9 = ATT9.Factory.newInstance();
        ATT10 att10 = ATT10.Factory.newInstance();
        ATT11 att11 = ATT11.Factory.newInstance();
        ATT12 att12 = ATT12.Factory.newInstance();
        ATT13 att13 = ATT13.Factory.newInstance();
        ATT14 att14 = ATT14.Factory.newInstance();
        ATT15 att15 = ATT15.Factory.newInstance();
        int countAttachments = 0;
        for (NarrativeContract narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeType() != null && NARRATIVECODE_ATTACHMENTS.equals(narrative.getNarrativeType().getCode())) {
                final AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
                if (attachedFileDataType != null) {
                    countAttachments++;
                    switch (countAttachments) {
                        case 1: {
                            att1.setATT1File(attachedFileDataType);
                            attachments.setATT1(att1);
                            break;
                        }
                        case 2: {
                            att2.setATT2File(attachedFileDataType);
                            attachments.setATT2(att2);
                            break;
                        }
                        case 3: {
                            att3.setATT3File(attachedFileDataType);
                            attachments.setATT3(att3);
                            break;
                        }
                        case 4: {
                            att4.setATT4File(attachedFileDataType);
                            attachments.setATT4(att4);
                            break;
                        }
                        case 5: {
                            att5.setATT5File(attachedFileDataType);
                            attachments.setATT5(att5);
                            break;
                        }
                        case 6: {
                            att6.setATT6File(attachedFileDataType);
                            attachments.setATT6(att6);
                            break;
                        }
                        case 7: {
                            att7.setATT7File(attachedFileDataType);
                            attachments.setATT7(att7);
                            break;
                        }
                        case 8: {
                            att8.setATT8File(attachedFileDataType);
                            attachments.setATT8(att8);
                            break;
                        }
                        case 9: {
                            att9.setATT9File(attachedFileDataType);
                            attachments.setATT9(att9);
                            break;
                        }
                        case 10: {
                            att10.setATT10File(attachedFileDataType);
                            attachments.setATT10(att10);
                            break;
                        }
                        case 11: {
                            att11.setATT11File(attachedFileDataType);
                            attachments.setATT11(att11);
                            break;
                        }
                        case 12: {
                            att12.setATT12File(attachedFileDataType);
                            attachments.setATT12(att12);
                            break;
                        }
                        case 13: {
                            att13.setATT13File(attachedFileDataType);
                            attachments.setATT13(att13);
                            break;
                        }
                        case 14: {
                            att14.setATT14File(attachedFileDataType);
                            attachments.setATT14(att14);
                            break;
                        }
                        case 15: {
                            att15.setATT15File(attachedFileDataType);
                            attachments.setATT15(att15);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            }
        }
        attachmentsDocument.setAttachments(attachments);
        return attachmentsDocument;
    }

    @Override
    public AttachmentsDocument getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {

        this.pdDoc = proposalDevelopmentDocument;
        return getAttachment();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
