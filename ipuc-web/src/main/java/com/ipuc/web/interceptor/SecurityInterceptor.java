
package com.ipuc.web.interceptor;

import com.ipuc.base.auth.Auth;
import com.ipuc.base.auth.AuthManager;
import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.web.annotation.Secured;
import com.ipuc.web.controller.Login;
import org.jogger.exception.UnAuthorizedException;
import org.jogger.http.Cookie;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.jogger.middleware.router.interceptor.Interceptor;
import org.jogger.middleware.router.interceptor.InterceptorExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class SecurityInterceptor implements Interceptor {
    
    private final Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);
    
    private AuthManager authManager;
    
    private PastorManager pastorManager;

    @Override
    public void intercept(Request request, Response response, InterceptorExecution execution) throws UnAuthorizedException, Exception {
        
        boolean requireAuth = requiresAuthentication(execution);
        if(requireAuth) {
            manageDataPastorToReponse(request, response);
            manageSecuredRequest(response, execution);
        } else {
            execution.proceed();
        }
    }
    
    
    
    private boolean requiresAuthentication(InterceptorExecution execution) {
        boolean requiresAuth = execution.getController().getAnnotation(Secured.class) != null;
        if (!requiresAuth) {
            requiresAuth = execution.getAction().getAnnotation(Secured.class) != null;
        }
        return requiresAuth;
    }
    
    private void manageDataPastorToReponse(Request request, Response response) throws UnAuthorizedException, Exception {
        
        Auth auth = getAuth(request, response);
        
        if(auth != null) {
            Pastor pastor = pastorManager.find(auth.getNumeroIdentificacion());
        
            if(pastor == null) {
                response.removeCookie(new Cookie(Login.COOKIE_N_IDENTIFICATION, null));
                response.removeCookie(new Cookie(Login.COOKIE_SESSION_ID, null));
                response.redirect("/");
            }

            response.setAttribute("pastor", pastor);
        } else {
            response.removeCookie(new Cookie(Login.COOKIE_N_IDENTIFICATION, null));
            response.removeCookie(new Cookie(Login.COOKIE_SESSION_ID, null));
            response.redirect("/");
        }       
    }
    
    private Auth getAuth(Request request, Response response) throws UnAuthorizedException, Exception {
        Cookie n_identification = request.getCookie(Login.COOKIE_N_IDENTIFICATION);
        Cookie sessionId = request.getCookie(Login.COOKIE_SESSION_ID);
        
        Auth auth = null;
        
        if(n_identification != null && sessionId != null) {
            auth = authManager.findBySession(n_identification.getValue(), sessionId.getValue());
        
            if(auth == null) {
                throw new UnAuthorizedException("Not found session auth");
            }
        }
        
        if(n_identification == null && sessionId != null) {
            response.removeCookie(new Cookie(Login.COOKIE_SESSION_ID, null));
        } else if(n_identification != null && sessionId == null) {
            response.removeCookie(new Cookie(Login.COOKIE_N_IDENTIFICATION, null));
        }      

        return auth;
    }
    
    private void manageSecuredRequest(Response response, InterceptorExecution execution) throws UnAuthorizedException, Exception{
        Pastor pastor = (Pastor) response.getAttributes().get("pastor");

        if (pastor == null) {
            log.info("Render not found for not authorized resource");
            throw new UnAuthorizedException("Pastor not exists in response's attributes");
        }

        //check if require role validation
        String roleValidation = getRoleValidation(execution);
        if (!roleValidation.isEmpty()) {
            if (pastor.getRoles().contains(roleValidation)) {
                execution.proceed();
            } else {
                response.redirect("/home");
            }
        } else {
            execution.proceed();
        }
    }
    
    private String getRoleValidation(InterceptorExecution execution) {
        Secured annotation = execution.getAction().getAnnotation(Secured.class);
        if (annotation != null) {
            return annotation.role();
        }
        return null;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public void setPastorManager(PastorManager pastorManager) {
        this.pastorManager = pastorManager;
    }
   
}
