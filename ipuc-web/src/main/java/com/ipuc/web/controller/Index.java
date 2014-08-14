
package com.ipuc.web.controller;

import com.ipuc.web.helper.ResponseFormat;
import com.ipuc.web.list.IdentificationTypeFormat;
import com.ipuc.web.list.CivilStateFormat;
import com.ipuc.web.list.MinisterStateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jogger.http.Request;
import org.jogger.http.Response;

/**
 *
 * @author wilson-rivera
 */
public class Index {
    
    
    public void index(Request request, Response response) {
        
        List<IdentificationTypeFormat> identificationTypes = IdentificationTypeFormat.getIdentificationTypes();
        List<CivilStateFormat> civilStates = CivilStateFormat.getStatesCivil();
        List<MinisterStateFormat> ministerStates = MinisterStateFormat.getStates();
        
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("identificationTypes", identificationTypes);
        info.put("civilStates", civilStates);
        info.put("ministerStates", ministerStates);
        response.contentType(ResponseFormat.HTML.getContentType()).render("registerPastor.ftl", info);                
    }
    
}
