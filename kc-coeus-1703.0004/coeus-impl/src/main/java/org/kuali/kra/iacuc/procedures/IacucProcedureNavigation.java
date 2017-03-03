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
package org.kuali.kra.iacuc.procedures;

/**
 * This class represents different possible enumerations of iacuc procedure details navigation
 */
public enum IacucProcedureNavigation {
    PROCEDURES("procedures", "updateProcedures"),
    PERSONNEL("personnel", "updatePersonnel"),
    LOCATION("location", "updateLocation"),
    SUMMARY("summary", "updateSummary");

    private final String displayName;
    private final String methodName;
    
    IacucProcedureNavigation(String displayName, String methodName){
        this.displayName = displayName;
        this.methodName = methodName;
    }
    
    public String getDisplayName(){
        return displayName;
    }
    
    public String getMethodName(){
        return methodName;
    }

}