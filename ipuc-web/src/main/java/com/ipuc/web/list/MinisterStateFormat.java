
package com.ipuc.web.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public enum MinisterStateFormat {
    
    A("A", "Activo"),
    U("U", "Uso del buen retiro"),
    D("D", "Disciplina"),
    E("E", "Destituido"),
    R("R", "Entregó el ministerio"),
    F("F", "Fallecido");
    
    private String codeState;
    
    private String state;
    
    MinisterStateFormat(String codeState, String state) {    
        this.codeState = codeState;
        this.state = state;
    }
    
    public static List<MinisterStateFormat> getStates() {
        List<MinisterStateFormat> states = new ArrayList<MinisterStateFormat>();
        states.add(A);
        states.add(U);
        states.add(D);
        states.add(E);
        states.add(R);
        states.add(F);
        
        return states;        
    }
    
    public static boolean isValidState(String codeState) {
        MinisterStateFormat states = MinisterStateFormat.valueOf(codeState);
        return states != null;
    }

    public String getCodeState() {
        return codeState;
    }

    public String getState() {
        return state;
    }
    
    
}
