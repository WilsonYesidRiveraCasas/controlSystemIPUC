
package com.ipuc.web.controller;

import com.ipuc.base.congregacion.CongregacionManager;
import com.ipuc.base.persona.CreyenteManager;
import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.web.annotation.Secured;
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
    
    private CongregacionManager congregacionManager;
    
    private PastorManager pastorManager;
    
    private CreyenteManager creyenteManager;
    
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
    
    @Secured
    public void home(Request request, Response response) throws Exception {
        
        Pastor pastor = getPastorFromResponse(response);
        String roles = pastor.getRoles();
        Map<String, Object> info = new HashMap<String, Object>();
        
        if(roles.contains(Pastor.ROL_CONSISTORIO)) {
            
        } else {
            if(roles.contains(Pastor.ROL_DIRECTIVO)) {
                getInfoResponseDirectivo(info);
            }            
        }        
        info.put("pastor", pastor);
        
        response.contentType(ResponseFormat.HTML.getContentType()).render("home.ftl", info);
        
    }
   
    private void getInfoResponseDirectivo(Map<String, Object> info) throws Exception {
        long countCongregaciones = congregacionManager.countCongregacion();
        long countPastores = pastorManager.countPastores();
        long countCreyentes = creyenteManager.countCreyentes();
        
        info.put("numCongregaciones", countCongregaciones);
        info.put("numPastores", countPastores);
        info.put("numCreyentes", countCreyentes);
        
    }
    
    private Pastor getPastorFromResponse(Response response){
        Map<String, Object> atributes = response.getAttributes();
        Pastor pastor = (Pastor) atributes.get("pastor");
        return pastor;
    }

    
    private int getYear() {
        String formato="yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(new Date()));
    }
    
    public void setCongregacionManager(CongregacionManager congregacionManager) {
        this.congregacionManager = congregacionManager;
    }

    public void setPastorManager(PastorManager pastorManager) {
        this.pastorManager = pastorManager;
    }

    public void setCreyenteManager(CreyenteManager creyenteManager) {
        this.creyenteManager = creyenteManager;
    }

}
