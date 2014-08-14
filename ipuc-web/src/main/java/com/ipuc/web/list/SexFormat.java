
package com.ipuc.web.list;

/**
 *
 * @author wilson-rivera
 */
public enum SexFormat {
    
    M("M", "Masculino"),
    F("F", "Femenino");
    
    private String codeSex;
    
    private String sex;

    private SexFormat(String codeSex, String sex) {
        this.codeSex = codeSex;
        this.sex = sex;
    }
    
    public static boolean isValidSex(String codeSex) {
        SexFormat sex = SexFormat.valueOf(codeSex);
        return sex != null;
    }

    public String getCodeSex() {
        return codeSex;
    }

    public String getSex() {
        return sex;
    }
    
    
}
