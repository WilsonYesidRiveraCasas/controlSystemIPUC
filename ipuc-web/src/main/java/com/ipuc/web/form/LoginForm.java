
package com.ipuc.web.form;

import com.ipuc.web.exception.BadRequestException;
import org.jogger.http.Request;
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
            login.setN_identification(json.getString("n_identification"));
            login.setPass(json.getString("pass"));
            
            return login;
        } catch(Exception e) {
            log.error("Error parsing MinisterRegisterForm. Message: " + e.getMessage());
            throw new BadRequestException("\"Error parsing MinisterRegisterForm\"");
        }
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
