
package com.ipuc.web.controller;

import static com.ipuc.web.controller.Login.COOKIE_N_IDENTIFICATION;
import com.ipuc.web.helper.ResponseFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
            Map<String, Object> info = new HashMap<String, Object>();
            info.put("fecha", getYear());
            response.contentType(ResponseFormat.HTML.getContentType()).render("login.ftl", info);
        }
    }
    
    private int getYear() {
        String formato="yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(new Date()));
    }

}
