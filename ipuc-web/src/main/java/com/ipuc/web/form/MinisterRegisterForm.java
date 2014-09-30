
package com.ipuc.web.form;

import com.ipuc.base.util.RegexValidator;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.list.IdentificationTypeFormat;
import com.ipuc.web.util.Preconditions;
import org.jogger.http.Request;
import org.json.JSONException;
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
    
    private String correo;
    
    public static MinisterRegisterForm parse(Request request) throws BadRequestException {
        MinisterRegisterForm register = getRegister(request);
        return register;
    }
    
    private static MinisterRegisterForm getRegister(Request request) throws BadRequestException {
        try {
            JSONObject json = new JSONObject(request.getBody().asString());
            MinisterRegisterForm register = new MinisterRegisterForm();
            register.setT_identification(validateIdentificationType(json.getString("tipo_identi")));
            register.setN_identificacion(validateNumIdentification(json.getString("num_identi")));
            register.setCorreo(validateCorreo(json.getString("correo")));            
            return register;
        } catch(IllegalArgumentException e) {
            log.error("Error parsing request register pastor. " + e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch(JSONException e) {
            log.error("Error parsing MinisterRegisterForm. Message: " + e.getMessage());
            throw new BadRequestException("Error manejando la petición. Inténtelo de nuevo y si persiste contáctese con el administrador");
        }
    }
    
    private static String validateNumIdentification(String numIdentificacion) throws BadRequestException {
        
        Preconditions.notEmpty(numIdentificacion, "El número de identificación es requerido");
        
        if(numIdentificacion.length() > 50) {
            throw new BadRequestException("El número de identificación excede los 50 caracteres");
        }
        
        return numIdentificacion;
    }
    
    private static String validateCorreo(String correo) throws BadRequestException {
        Preconditions.notEmpty(correo, "El correo es requerido");
        boolean is_valid = RegexValidator.isValidateEmail(correo);
        if(!is_valid) {
            throw new BadRequestException("El correo es inválido");
        }
        return correo;
    }
    
    private static String validateIdentificationType(String identificationTypeCode) throws BadRequestException {
        
        Preconditions.notEmpty(identificationTypeCode, "Tipo de identificación requerido");
        
        boolean isValid = IdentificationTypeFormat.isValidCode(identificationTypeCode);
        if(!isValid) {
            throw new BadRequestException("Tipo de identificación es inválido");
        }
        return identificationTypeCode;        
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
   
}
