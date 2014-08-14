
package com.ipuc.web.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public enum IdentificationTypeFormat {
    
    CC("CC", "Cédula de ciudadanía"),
    CE("CE", "Cédula de extranjería"),
    DNI("DNI", "DNI - Documento Nacional de Identidad"),
    PASS("PASS", "Pasaporte"),
    RC("RC", "Registro civil"),
    TI("TI","Tarjeta de identidad");
    
    private String codeTipoIdenti;
    
    private String nombreTipoIdenti;
    
    private IdentificationTypeFormat(String codeTipoIdenti, String nombreTipoIdenti) {
        this.codeTipoIdenti = codeTipoIdenti;
        this.nombreTipoIdenti = nombreTipoIdenti;
    }
    
    public static List<IdentificationTypeFormat> getIdentificationTypes() {
        
        List<IdentificationTypeFormat> types = new ArrayList<IdentificationTypeFormat>();
        
        types.add(CC);
        types.add(CE);
        types.add(DNI);
        types.add(PASS);
        types.add(RC);
        types.add(TI);
        
        return types;
    }
    
    public static boolean isValidCode(String code) {
        IdentificationTypeFormat validType = IdentificationTypeFormat.valueOf(code);
        return validType != null;
    }

    public String getCodeTipoIdenti() {
        return codeTipoIdenti;
    }

    public String getNombreTipoIdenti() {
        return nombreTipoIdenti;
    }
    
    
}
