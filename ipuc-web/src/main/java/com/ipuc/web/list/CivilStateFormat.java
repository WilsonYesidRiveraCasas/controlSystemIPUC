
package com.ipuc.web.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public enum CivilStateFormat {
    
    C("C", "Casado"),
    S("S", "Soltero"),
    V("V", "Viudo");
    
    private String codeStateCivil;
    
    private String stateCivil;

    private CivilStateFormat(String codeStateCivil, String stateCivil) {
        this.codeStateCivil = codeStateCivil;
        this.stateCivil = stateCivil;
    }

    public static List<CivilStateFormat> getStatesCivil() {
        List<CivilStateFormat> states = new ArrayList<CivilStateFormat>();
        states.add(C);
        states.add(S);
        states.add(V);
        
        return states;
    }
    
    public static boolean isValidStateCivil(String codeState) {
        CivilStateFormat state = CivilStateFormat.valueOf(codeState);
        return state != null;        
    }
    
    public String getCodeStateCivil() {
        return codeStateCivil;
    }

    public String getStateCivil() {
        return stateCivil;
    }

    
}
