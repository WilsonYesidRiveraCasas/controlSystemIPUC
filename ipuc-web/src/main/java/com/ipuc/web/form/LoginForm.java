
package com.ipuc.web.form;

import com.elibom.jogger.http.Request;
import com.ipuc.web.exception.BadRequestException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class LoginForm {
    
    private static Logger log = LoggerFactory.getLogger(LoginForm.class);
    
    private String n_identification;
    
    private String pass;
    
    public static LoginForm parse(Request request) throws BadRequestException {
        LoginForm login = getLogin(request);
        return login;
    }
    
    private static LoginForm getLogin(Request request) throws BadRequestException {
        try {
            JSONObject json = new JSONObject(request.getBody().asString());
            LoginForm login = new LoginForm();
            login.setN_identification(validaNumIdentificacion(json.getString("n_identification")));
            login.setPass(validaPassword(json.getString("pass")));
            
            return login;
        } catch(JSONException e) {
            log.error("Error parsing MinisterRegisterForm. Message: " + e.getMessage());
            throw new BadRequestException("Error manejando la petición. Inténtelo de nuevo y si persiste contáctese con el administrador");
        }
    }
    
    private static String validaNumIdentificacion(String numIdentificacion) throws BadRequestException {
        if(numIdentificacion == null || numIdentificacion.isEmpty()) {
            throw new BadRequestException("El número de indentificación es requerido");
        }
        
        if(numIdentificacion.length() > 50) {
            throw new BadRequestException("El número de identificación excede los 50 caracteres");
        }
        return numIdentificacion;
    }
    
    private static String validaPassword(String password) throws BadRequestException {
        if(password == null || password.isEmpty()) {
            throw new BadRequestException("La contraseña es requerida");
        }
        
        if(password.length() > 100) {
            throw new BadRequestException("La contraseña es muy larga");
        }
        
        return password;
    }

    public String getN_identification() {
        return n_identification;
    }

    public void setN_identification(String n_identification) {
        this.n_identification = n_identification;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
