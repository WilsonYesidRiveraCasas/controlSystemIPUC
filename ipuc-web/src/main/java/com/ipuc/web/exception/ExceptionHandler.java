package com.ipuc.web.exception;

import com.ipuc.base.exception.NotSendMailException;
import com.ipuc.web.controller.Login;
import com.ipuc.web.helper.ResponseFormat;
import org.jogger.exception.NotFoundException;
import org.jogger.exception.UnAuthorizedException;
import org.jogger.http.Cookie;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.json.JSONObject;
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
            renderNotFound(response, e);            
        } else if(e instanceof ConflictException) {
            renderConflictException(response, e);
        } else if(e instanceof BadRequestException) {
            renderBadRequestException(response, e);
        } else if(e instanceof NotSendMailException) {
            renderNotSendMailException(response, e);
        } else if(e instanceof UnAuthorizedException) {
            renderUnauthorized(response);
        } else {
            renderUnExpected(response, e);
        }
    }

    private void renderNotSendMailException(Response response, Exception e) {
        response.status(202).contentType(ResponseFormat.JSON.getContentType()).write("Error enviando el correo");
    }

    private void renderBadRequestException(Response response, Exception e) {
        response.badRequest().contentType(ResponseFormat.JSON.getContentType()).write(e.getMessage());
    }

    private void renderConflictException(Response response, Exception e) {
        response.conflict().contentType(ResponseFormat.JSON.getContentType()).write(e.getMessage()); 
    }

    private void renderNotFound(Response response, Exception e) {
        response.notFound().contentType(ResponseFormat.HTML.getContentType()).render("404.ftl");
    }
    
    private void renderUnauthorized(Response response) {
        response.removeCookie(new Cookie(Login.COOKIE_N_IDENTIFICATION, null));
        response.removeCookie(new Cookie(Login.COOKIE_SESSION_ID, null));
        response.redirect("/");
    }

    private void renderUnExpected(Response response, Exception e) {
        log.error("Error no contemplado : " + e.getMessage());
        e.printStackTrace();
        response.status(500).contentType(ResponseFormat.HTML.getContentType()).render("500.ftl");
    }

}