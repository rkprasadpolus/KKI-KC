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
package org.kuali.kra.bo;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

@Entity
@Table(name = "NSF_CODES")
public class NsfCode extends KcPersistableBusinessObjectBase {

    @PortableSequenceGenerator(name = "SEQUENCE_NSF_CODES")
    @GeneratedValue(generator = "SEQUENCE_NSF_CODES")
    @Id
    @Column(name = "NSF_SEQUENCE_NUMBER")
    private Integer nsfSequenceNumber;

    @Column(name = "NSF_CODE")
    private String nsfCode;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "DESCRIPTION")
    private String description;

    public Integer getNsfSequenceNumber() {
        return nsfSequenceNumber;
    }

    public void setNsfSequenceNumber(Integer nsfSequenceNumber) {
        this.nsfSequenceNumber = nsfSequenceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNsfCode() {
        return nsfCode;
    }

    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
