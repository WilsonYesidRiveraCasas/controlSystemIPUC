
package com.ipuc.web.controller;

import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.base.persona.PersonaManager;
import com.ipuc.web.annotation.Secured;
import com.ipuc.web.helper.ResponseFormat;
import com.ipuc.web.list.CivilStateFormat;
import com.ipuc.web.list.IdentificationTypeFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class Ministers {
    
    private static final Logger log = LoggerFactory.getLogger(Ministers.class);
    
    private PastorManager pastorManager;
    
    private PersonaManager personaManager;
    
    @Secured(role=Pastor.ROL_PASTOR)
    public void registerCreyenteForm(Request request, Response response) {
        
        List<IdentificationTypeFormat> identificationTypes = IdentificationTypeFormat.getIdentificationTypes();
        List<CivilStateFormat> statesTypes = CivilStateFormat.getStatesCivil();
        
        Pastor pastor = getPastorFromResponse(response);
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("pastor", pastor);
        info.put("identificationTypes", identificationTypes);
        info.put("statesTypes", statesTypes);
        response.contentType(ResponseFormat.HTML.getContentType()).render("registerCreyente.ftl", info);
    }
    
    private Pastor getPastorFromResponse(Response response){
        Map<String, Object> atributes = response.getAttributes();
        Pastor pastor = (Pastor) atributes.get("pastor");
        return pastor;
    }

    public void setPastorManager(PastorManager pastorManager) {
        this.pastorManager = pastorManager;
    }

    public void setPersonaManager(PersonaManager personaManager) {
        this.personaManager = personaManager;
    }

}
