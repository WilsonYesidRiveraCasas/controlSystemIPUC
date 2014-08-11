
package com.ipuc.web.controller;

import com.ipuc.web.helper.ResponseFormat;
import org.jogger.http.Request;
import org.jogger.http.Response;

/**
 *
 * @author wilson-rivera
 */
public class Index {
    
    
    public void index(Request request, Response response) {
        response.contentType(ResponseFormat.HTML.getContentType()).render("registerPastor.html");                
    }
    
}
