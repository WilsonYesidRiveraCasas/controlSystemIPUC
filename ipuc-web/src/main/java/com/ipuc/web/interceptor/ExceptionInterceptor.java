package com.ipuc.web.interceptor;

import com.ipuc.web.exception.ExceptionHandler;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.jogger.middleware.router.interceptor.Interceptor;
import org.jogger.middleware.router.interceptor.InterceptorExecution;

/**
 *
 * @author Wilson Rivera
 */
public class ExceptionInterceptor implements Interceptor {

    @Override
    public void intercept(Request request, Response response, InterceptorExecution execution) throws Exception {
        try {
            execution.proceed();
        } catch (Exception e) {
            new ExceptionHandler().handle(e, request, response);
        }
    } 

}