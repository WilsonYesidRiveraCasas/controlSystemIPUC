
package com.ipuc.web.controller;

import com.ipuc.base.auth.Auth;
import com.ipuc.base.auth.AuthManager;
import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.web.annotation.Secured;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.form.LoginForm;
import com.ipuc.web.helper.ResponseFormat;
import com.ipuc.web.util.Random;
import org.jogger.http.Cookie;
import org.jogger.http.Request;
import org.jogger.http.Response;

/**
 *
 * @author wilson-rivera
 */
public class Login {
    
    public static final String COOKIE_N_IDENTIFICATION = "ipuc.n_identification";

    public static final String COOKIE_SESSION_ID = "ipuc.session_id";
    
    private PastorManager pastorManager;
    
    private AuthManager authManager;
    
    public void login(Request request, Response response) throws BadRequestException, Exception {
        String contentType = ResponseFormat.JSON.getContentType();
        LoginForm form = LoginForm.parse(request);
        
        if(isValidPastor(form.getN_identification(), form.getPass())) {
            createSession(response, form.getN_identification());
            response.contentType(contentType).write("{}");
        } else {
            response.unauthorized().contentType(contentType).write("{\"status\" : \"invalid\"}");
        }
    }
    
    @Secured
    public void logout(Request request, Response response) throws Exception{
       
        Cookie n_identification = request.getCookie(COOKIE_N_IDENTIFICATION);
        authManager.delete(n_identification.getValue());
        response.removeCookie(request.getCookie(COOKIE_SESSION_ID));
        response.removeCookie(n_identification);
        response.redirect("/");
    }
    
    private void createSession(Response response, String n_identification) throws Exception {
        String session_id = generateSessionId();
        createAuth(n_identification, session_id);
        setCookiesToResponse(response, n_identification, session_id);
    }
    
    private String generateSessionId() {
        return Random.generateString(35);
    }
    
    private void createAuth(String n_identification, String session_id) throws Exception {
        Auth auth = new Auth();
        auth.setNumeroIdentificacion(n_identification);
        auth.setIdSesion(session_id);
        
        authManager.create(auth);
    }
    
    private static void setCookiesToResponse(Response response, String n_identification, String session_id) {
        response.setCookie(new Cookie(COOKIE_N_IDENTIFICATION, n_identification, Auth.TIME_LIFE, "/"));
        response.setCookie(new Cookie(COOKIE_SESSION_ID, session_id, Auth.TIME_LIFE, "/"));
    }
        
    private boolean isValidPastor(String num_identification, String pass) throws Exception {
        Pastor pastor = pastorManager.find(num_identification);
        if(pastor == null) {
            return false;
        }
        return pastor.getPassword().equals(pass);       
    }

    public void setPastorManager(PastorManager pastorManager) {
        this.pastorManager = pastorManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }   
    
}
