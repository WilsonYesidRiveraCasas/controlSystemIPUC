
package com.ipuc.web.form;

import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.list.CivilStateFormat;
import com.ipuc.web.list.IdentificationTypeFormat;
import com.ipuc.web.list.MinisterStateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jogger.http.Request;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class MinisterRegisterForm {
    
    private static Logger log = LoggerFactory.getLogger(MinisterRegisterForm.class);
    
    private String t_identification;
    
    private String n_identificacion;
    
    private String p_name;
    
    private String s_name;
    
    private String p_lastname;
    
    private String s_lastname;
    
    private Date d_birth;
    
    private String p_birth;
    
    private String m_status;
    
    private String n_phone;
    
    private String mail;
    
    private String pass;
    
    private String status;
    
    private Date n_date;
    
    public static MinisterRegisterForm parse(Request request) throws BadRequestException {
        MinisterRegisterForm register = getRegister(request);
        return register;
    }
    
    private static MinisterRegisterForm getRegister(Request request) throws BadRequestException {
        try {
            JSONObject json = new JSONObject(request.getBody().asString());
            MinisterRegisterForm register = new MinisterRegisterForm();
            register.setT_identification(validateIdentificationType(json.getString("t_identification")));
            register.setN_identificacion(json.getString("n_identification"));
            register.setP_name(json.getString("p_name"));
            register.setS_name(json.getString("s_name"));
            register.setP_lastname(json.getString("p_lastname"));
            register.setS_lastname(json.getString("s_lastname"));
            register.setD_birth(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("d_birth")));
            register.setP_birth(json.getString("p_birth"));
            register.setM_status(validateCivilState(json.getString("m_status")));
            register.setN_phone(json.getString("n_phone"));
            register.setMail(json.getString("mail"));
            register.setPass(json.getString("pass"));
            register.setStatus(validateMinisterState(json.getString("status")));
            register.setN_date(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("n_date")));
            
            return register;
        } catch(Exception e) {
            log.error("Error parsing MinisterRegisterForm. Message: " + e.getMessage());
            throw new BadRequestException("\"Error parsing MinisterRegisterForm\"");
        }
    }
    
    private static String validateIdentificationType(String identificationTypeCode) throws BadRequestException {
        boolean isValid = IdentificationTypeFormat.isValidCode(identificationTypeCode);
        if(!isValid) {
            throw new BadRequestException("Identification type invalid");
        }
        return identificationTypeCode;        
    }
    
    private static String validateCivilState(String civilState) throws BadRequestException {
        boolean isValid = CivilStateFormat.isValidStateCivil(civilState);
        if(!isValid) {
            throw new BadRequestException("Civil state invalid");
        }
        return civilState;
    }
    
    private static String validateMinisterState(String codeMinisterState) throws BadRequestException {
        boolean isValid = MinisterStateFormat.isValidState(codeMinisterState);
        if(!isValid) {
            throw new BadRequestException("Minister state invalid");
        }
        return codeMinisterState;
    }

    public String getT_identification() {
        return t_identification;
    }

    public void setT_identification(String t_identification) {
        this.t_identification = t_identification;
    }

    public String getN_identificacion() {
        return n_identificacion;
    }

    public void setN_identificacion(String n_identificacion) {
        this.n_identificacion = n_identificacion;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getP_lastname() {
        return p_lastname;
    }

    public void setP_lastname(String p_lastname) {
        this.p_lastname = p_lastname;
    }

    public String getS_lastname() {
        return s_lastname;
    }

    public void setS_lastname(String s_lastname) {
        this.s_lastname = s_lastname;
    }

    public Date getD_birth() {
        return d_birth;
    }

    public void setD_birth(Date d_birth) {
        this.d_birth = d_birth;
    }

    public String getP_birth() {
        return p_birth;
    }

    public void setP_birth(String p_birth) {
        this.p_birth = p_birth;
    }

    public String getM_status() {
        return m_status;
    }

    public void setM_status(String m_status) {
        this.m_status = m_status;
    }

    public String getN_phone() {
        return n_phone;
    }

    public void setN_phone(String n_phone) {
        this.n_phone = n_phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getN_date() {
        return n_date;
    }

    public void setN_date(Date n_date) {
        this.n_date = n_date;
    }
    
    
}
