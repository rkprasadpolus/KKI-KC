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
package org.kuali.coeus.common.impl.person.signature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.kuali.coeus.common.framework.person.signature.*;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;

public abstract class PersonSignatureServiceImpl implements PersonSignatureService {
    
    private static final String CORRESPONDENCE_SIGNATURE_TYPE = "CORRESPONDENCE_SIGNATURE_TYPE";
    private static final String CORRESPONDENCE_SIGNATURE_TAG = "CORRESPONDENCE_SIGNATURE_TAG";
    private static final float ADDITIONAL_SPACE_BETWEEN_TAG_AND_IMAGE = 120f;
    private static final String PERSON_SIGNATURE_ACTIVE = "signatureActive";
    private static final String PERSON_SIGNATURE_PERSON_ID = "personId";
    private static final String DEFAULT_SIGNATURE = "defaultSignature";
    protected static final String MODULE_CODE = "moduleCode";
    
    private static final Log LOG = LogFactory.getLog(PersonSignatureServiceImpl.class);

    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;

    /**
     * Configure signature type required
     * D - Always use Default signature
     * S - Always use signed in user signature, if not found use the default signature
     * N - No signature is required
     */
    public enum SignatureTypes {
        DEFAULT_SIGNATURE("D"), 
        SIGNED_IN_USER_SIGNATURE("S"), 
        NO_SIGNATURE_REQURIED("N");

        private String signatureType;

        SignatureTypes(String signatureType) {
            this.signatureType = signatureType;
        }

        public String getSignatureType() {
            return signatureType;
        }

        public static SignatureTypes getSignatureMode(String signatureType) {
            for (SignatureTypes sType : values() ){
                if (sType.signatureType.equals(signatureType)) {
                    return sType;
                }
            }
            return null;
        }
        
    }    

    @Override
    public byte[] applySignature(byte[] pdfBytes) throws IOException {
        byte[] pdfFileData = pdfBytes;
        ByteArrayOutputStream byteArrayOutputStream = getOriginalPdfDocumentAsOutputsStream(pdfBytes);
        byteArrayOutputStream = identifyModeAndApplySignature(byteArrayOutputStream);
        if(Objects.nonNull(byteArrayOutputStream)) {
            pdfFileData = byteArrayOutputStream.toByteArray();
        }
        return pdfFileData;
    }
    
    /**
     * This method is to identify signature mode and invoke appropriate method
     * If signature is not available, return the original.
     * to sign the document.
     */
    protected ByteArrayOutputStream identifyModeAndApplySignature(ByteArrayOutputStream originalByteArrayOutputStream) throws IOException {
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        String signatureTypeParam = getSignatureTypeParameter();
        SignatureTypes signatureType = SignatureTypes.NO_SIGNATURE_REQURIED;
        if(Objects.nonNull(signatureTypeParam)) {
            signatureType = SignatureTypes.getSignatureMode(signatureTypeParam);
        }
        
        if(signatureType != null) {
            switch(signatureType) {
                case DEFAULT_SIGNATURE :
                    outputStream = printDefaultSignature(outputStream);
                    break;
                case SIGNED_IN_USER_SIGNATURE :
                    String personId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
                    outputStream = printLoggedInUserSignature(personId, outputStream);
                    break;
                case NO_SIGNATURE_REQURIED :
                    break;
            }
        }else {
            LOG.warn("Invalid signature type defined in parameter");
        }
        
        return outputStream;
    }
    
    /**
     * This method is to print default module authorized signature.
     * Original document is returned if signature is not available.
     */
    protected ByteArrayOutputStream printDefaultSignature(ByteArrayOutputStream originalByteArrayOutputStream) throws IOException {
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        PersonSignature authorizedSignature = getDefaultSignature();
        if(ObjectUtils.isNotNull(authorizedSignature)) {
            if(Objects.nonNull(authorizedSignature.getAttachmentContent())) {
                outputStream = applyAutographInDocument(authorizedSignature, outputStream);
            }
        }
        return outputStream;
    }

    /**
     * This method is to print logged in user signature.
     * If logged in user signature is not available, get the default signature for applicable module.
     * Original document is returned if signature is not available.
     */
    protected ByteArrayOutputStream printLoggedInUserSignature(String personId, ByteArrayOutputStream originalByteArrayOutputStream) throws IOException{
        PersonSignature userSignature = getLoggedinPersonSignature(personId);
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        if(ObjectUtils.isNull(userSignature)) {
            userSignature = getDefaultSignature();
        }
        if(ObjectUtils.isNotNull(userSignature)) {
            if(Objects.nonNull(userSignature.getAttachmentContent())) {
                outputStream = applyAutographInDocument(userSignature, outputStream);
            }
        }
        return outputStream;
    }
    
    /**
     * This method is to apply signature in the document.
     */
    protected ByteArrayOutputStream applyAutographInDocument(PersonSignature personSignature, ByteArrayOutputStream originalByteArrayOutputStream) {
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        try {
            if (personSignature.getAttachmentContent() != null) {
                outputStream = scanAndApplyAutographInEachPage(personSignature.getAttachmentContent(), outputStream);
            }
        }catch (IOException ex) {
                LOG.error(ex.getMessage(), ex);
        }
        return outputStream;
    }
    
    /**
     * This method is to scan for signature tag in each page and apply the signature
     * at desired location.
     */
    protected ByteArrayOutputStream scanAndApplyAutographInEachPage(byte[] imageData, ByteArrayOutputStream originalByteArrayOutputStream) throws IOException {

        byte[] pdfFileData = originalByteArrayOutputStream.toByteArray();
        final PDDocument originalDocument = getPdfDocument(pdfFileData);
        final List<PrintTextLocator.PDFTextLocation> locations = new PrintTextLocator(originalDocument, new HashSet<>(getSignatureTagParameter())).doSearch();
        final PDDocument signatureDocument = new PDDocument();

        IntStream.rangeClosed(1, originalDocument.getDocumentCatalog().getPages().getCount()).forEach(pageNumber -> {
            final PDPage signaturePage = new PDPage();
            locations.stream().filter(location -> location.isFound() && pageNumber == location.getPage()).forEach(location -> {
                try {
                    final PDImageXObject signatureImage = JPEGFactory.createFromImage(signatureDocument, getBufferedImage(imageData));
                    final PDPageContentStream stream = new PDPageContentStream(signatureDocument, signaturePage, PDPageContentStream.AppendMode.APPEND, true, false);
                    stream.drawImage(signatureImage, location.getX(), location.getY() - signatureImage.getHeight() - ADDITIONAL_SPACE_BETWEEN_TAG_AND_IMAGE);
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            signatureDocument.addPage(signaturePage);
        });

        final Overlay overlay = new Overlay();
        overlay.setDefaultOverlayPDF(signatureDocument);
        overlay.setInputPDF(originalDocument);
        final PDDocument overlayed = overlay.overlay(Collections.emptyMap());

        overlayed.save(originalByteArrayOutputStream);
        overlayed.close();
        originalDocument.close();
        signatureDocument.close();
        return originalByteArrayOutputStream;
    }
    
    private PDDocument getPdfDocument(byte[] pdfFileData) throws IOException {
        InputStream is = new ByteArrayInputStream(pdfFileData);
        return PDDocument.load(is);
    }
    
    private ByteArrayOutputStream getOriginalPdfDocumentAsOutputsStream(byte[] pdfFileData) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream is = new ByteArrayInputStream(pdfFileData);
        PDDocument originalDocument = PDDocument.load(is);
        originalDocument.save(outputStream);
        originalDocument.close();
        return outputStream;
    }
    
    /**
     * This method is to get buffered image from image data.
     */
    protected BufferedImage getBufferedImage(byte[] imageData) throws IOException {
        InputStream in = new ByteArrayInputStream(imageData);
        return ImageIO.read(in);
    }
    
    /**
     * This method is to get the default signature for module.
     */
    protected PersonSignature getDefaultSignature() {
        List<PersonSignatureModule> moduleSignatures = getAuthorizedDefaultSignatory();
        PersonSignature authorizedSignature = null;
        if(!moduleSignatures.isEmpty()) {
            authorizedSignature = moduleSignatures.get(0).getPersonSignature();
        }
        return authorizedSignature;
    }
    
    /**
     * This method is to get logged in person signature
     * Check whether user is authorized to sign in given module.
     */
    protected PersonSignature getLoggedinPersonSignature(String personId) {
        Map<String, Object> fieldValues = new HashMap<>();
        boolean isAuthorized = false;
        fieldValues.put(PERSON_SIGNATURE_PERSON_ID, personId);
        fieldValues.put(PERSON_SIGNATURE_ACTIVE, Boolean.TRUE);
        PersonSignature personSignature = getBusinessObjectService().findByPrimaryKey(PersonSignature.class, fieldValues);
        if(ObjectUtils.isNotNull(personSignature)) {
            for(PersonSignatureModule personSignatureModule : personSignature.getPersonSignatureModules()) {
                if(personSignatureModule.getModuleCode().equalsIgnoreCase(getModuleCodeHook()) && personSignatureModule.isSignatureActive()) {
                    isAuthorized = true;
                    break;
                }
            }
        }
        return isAuthorized ? personSignature : null;
    }
    
    
    /**
     * This method is to get authorized default signatory for a given module.
     * get all active signatures.
     */
    protected List<PersonSignatureModule> getAuthorizedDefaultSignatory() {    
        Map<String, Object> matchingKey = new HashMap<>();
        matchingKey.put(MODULE_CODE, getModuleCodeHook());
        matchingKey.put(PERSON_SIGNATURE_ACTIVE, Boolean.TRUE);
        matchingKey.put(DEFAULT_SIGNATURE, Boolean.TRUE);
        return (List<PersonSignatureModule>)getBusinessObjectService().findMatching(PersonSignatureModule.class, matchingKey);
    }  
    
    protected String getSignatureTypeParameter() {
        return getParameterService().getParameterValueAsString(getModuleNameSpaceHook(), ParameterConstants.DOCUMENT_COMPONENT, CORRESPONDENCE_SIGNATURE_TYPE);
    }

    protected Collection<String> getSignatureTagParameter() {
        return getParameterService().getParameterValuesAsString(getModuleNameSpaceHook(), ParameterConstants.DOCUMENT_COMPONENT, CORRESPONDENCE_SIGNATURE_TAG);
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected abstract String getModuleCodeHook();
    protected abstract String getModuleNameSpaceHook();

}
