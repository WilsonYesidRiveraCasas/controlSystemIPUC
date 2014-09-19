package com.ipuc.web.exception;

import com.ipuc.web.helper.ResponseFormat;
import java.util.HashMap;
import java.util.Map;
import org.jogger.exception.NotFoundException;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wilson Rivera
 */
public class ExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    public void handle(Exception e, Request request, Response response) throws Exception {
        log.warn("Handling " + e.getMessage() + " in " + request.getPath() + " request");

        if(e instanceof NotFoundException) {            
            renderNotFound(request, response, e);            
        } else if(e instanceof ConflictException) {
            renderConflictException(request, response, e);
        } else if(e instanceof BadRequestException) {
            renderBadRequestException(response);
        } /*else if(e instanceof NotSendMailException) {
            renderNotSendMailException(request, response, e);
        } */else {
            renderUnExpected(request, response, e);
        }
    }

    private void renderNotSendMailException(Request request, Response response, Exception e) {
        //response.status(500).contentType(ResponseFormat.JSON.getContentType()).write("{\"code\" : \"error_sending_email\"}");
    }

    private void renderBadRequestException(Response response) {
        response.badRequest().contentType(ResponseFormat.HTML.getContentType()).render("page-error.ftl", buildHashStateCodeHTTP(400));
    }

    private void renderConflictException(Request request, Response response, Exception e) {
        response.conflict().contentType(ResponseFormat.JSON.getContentType()).write("{\"message\" : \"" + e.getMessage() + "\"}"); 
    }

    private void renderNotFound(Request request, Response response, Exception e) {
        response.notFound().contentType(ResponseFormat.HTML.getContentType()).render("404.ftl");
    }

    private void renderUnExpected(Request request, Response response, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    //    response.status(500).contentType(ResponseFormat.HTML.getContentType()).render("page-error.ftl", buildHashStateCodeHTTP(500));
    }
    
    private Map<String,Object> buildHashStateCodeHTTP(int code) {
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("code", code);
        return info;
    }
}