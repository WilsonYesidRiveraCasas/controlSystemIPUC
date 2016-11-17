package com.ipuc.web.interceptor;

import com.elibom.jogger.http.Request;
import com.elibom.jogger.http.Response;
import com.elibom.jogger.middleware.router.interceptor.Interceptor;
import com.elibom.jogger.middleware.router.interceptor.InterceptorExecution;
import com.ipuc.web.exception.ExceptionHandler;

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