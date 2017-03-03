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
package org.kuali.coeus.s2sgen.impl.print;

import java.util.List;

/**
 * 
 * This class provides the API for KC Printing. It will take any KC
 * <code>Printable</code> and return the printable PDF form of that Printable
 * in an OutputStream which can be decorated how the implementing print consumer
 * requires.
 * 
 */
public interface S2SPrintingService {

	/**
	 *
	 * This method invokes the KC printable architecture for reports,
	 * notifications, docs and bos. It will take raw KC XML from bo/docs and
	 * perform the XSLT to generate XML-FO, and will render the Printable XML-FO
	 * as a PDF OutputStream.
	 */
	S2SFile print(S2SPrintable printableArtifact);

	/**
	 *
	 * This method invokes the KC printable architecture for reports,
	 * notifications, docs and bos. It will take raw KC XML from bo/docs and
	 * perform the XSLT to generate XML-FO, and will render the {@link java.util.List} of
	 * Printable XML-FO as a PDF OutputStream.
	 */
	S2SFile print(List<S2SPrintable> printableArtifactList);

	byte[] mergePdfBytes(List<byte[]> pdfBytesList, List<String> bookmarksList, boolean headerFooterRequired);
}
