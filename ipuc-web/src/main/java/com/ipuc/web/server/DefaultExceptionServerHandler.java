package com.ipuc.web.server;

import com.ipuc.web.helper.ResponseFormat;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.jogger.ExceptionHandler;
import org.jogger.exception.WebApplicationException;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class DefaultExceptionServerHandler implements ExceptionHandler {
    
    private final Logger log = LoggerFactory.getLogger(DefaultExceptionServerHandler.class);

    public DefaultExceptionServerHandler() {
    }

    @Override
    public void handle(Exception e, Request request, Response response) {
        if (WebApplicationException.class.isInstance(e)) {
            handleWebApplicationException((WebApplicationException) e, request, response);
        } else {
            handleException(e, response);
        }
    }

    private void handleWebApplicationException(WebApplicationException wae, Request request, Response response) {
        response.status(wae.getStatus());

        if (wae.getStatus() == Response.INTERNAL_ERROR) {
            response.status(500).contentType(ResponseFormat.HTML.getContentType()).render("500.ftl");
        } else {
            response.notFound().contentType(ResponseFormat.HTML.getContentType()).render("404.ftl");
        }
    }

    private void handleException(Exception e, Response response) {
        response.status(Response.INTERNAL_ERROR);
        log.error(getStackTrace(e));
    }

    private String getStackTrace(Throwable exception) {
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

}
