
package com.ipuc.web.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public enum CreyenteStateFormat {
    
    A("A", "Activo"),
    D("D", "Disciplina"),
    F("F", "Fallecido");
    
    private String codeState;
    
    private String state;

    private CreyenteStateFormat(String codeState, String state) {
        this.codeState = codeState;
        this.state = state;
    }
    
    public static List<CreyenteStateFormat> getStates() {
        List<CreyenteStateFormat> states = new ArrayList<CreyenteStateFormat>();
        states.add(A);
        states.add(D);
        states.add(F);
        
        return states;        
    }
    
    public static boolean isValidState(String codeState) {
        CreyenteStateFormat states = CreyenteStateFormat.valueOf(codeState);
        return states != null;
    }
    
    public String getCodeState() {
        return codeState;
    }

    public String getState() {
        return state;
    }
    
}
