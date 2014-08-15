
package com.ipuc.web.controller;

import static com.ipuc.web.controller.Login.COOKIE_N_IDENTIFICATION;
import com.ipuc.web.helper.ResponseFormat;
import org.jogger.http.Cookie;
import org.jogger.http.Request;
import org.jogger.http.Response;

/**
 *
 * @author wilson-rivera
 */
public class Index {
    
    public void loginForm(Request request, Response response) {
        Cookie n_identification = request.getCookie(COOKIE_N_IDENTIFICATION);
        if(n_identification != null) {
            response.redirect("/register");
        } else{
            response.contentType(ResponseFormat.HTML.getContentType()).render("login.ftl");
        }
    }

}
