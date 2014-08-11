
package com.ipuc.web.controller;

import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.base.tipoIdentificacion.TipoIdentificacion;
import com.ipuc.base.tipoIdentificacion.TipoIdentificacionManager;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.exception.ConflictException;
import com.ipuc.web.form.MinisterRegisterForm;
import com.ipuc.web.helper.ResponseFormat;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wilson-rivera
 */
public class Ministers {
    
    private static final Logger log = LoggerFactory.getLogger(Ministers.class);
    
    private PastorManager pastorManager;
    
    private TipoIdentificacionManager tipoIdentificacionManager;
    
    @Transactional(rollbackFor=Exception.class)
    public void register(Request request, Response response) throws ConflictException, BadRequestException, Exception {
        log.info("Minister register request /register");
        MinisterRegisterForm registerForm = MinisterRegisterForm.parse(request);
        Pastor pastorAux = pastorManager.find(registerForm.getN_identificacion());
        
       if(pastorAux != null) {
           throw new ConflictException("El pastor ya existe");
       }
       
       Pastor pastor = buildPastor(registerForm);
       pastorManager.create(pastor);
       
       response.status(200).contentType(ResponseFormat.JSON.getContentType()).write("{}");

    }
    
    private Pastor buildPastor(MinisterRegisterForm form) throws ConflictException, Exception {
        Pastor pastor = new Pastor();
        pastor.setTipoIdentificacion(getIdentificationType(form.getT_identification()));
        pastor.setNumeroIdentificacion(form.getN_identificacion());
        pastor.setPrimerNombre(form.getP_name());
        pastor.setSegundoNombre(form.getS_name());
        pastor.setPrimerApellido(form.getP_lastname());
        pastor.setSegundoApellido(form.getS_lastname());
        pastor.setFechaNacimiento(form.getD_birth());
        pastor.setLugarNacimiento(form.getP_birth());
        pastor.setEstadoCivil(form.getM_status());
        pastor.setTelefono(form.getN_phone());
        pastor.setEmail(form.getMail());
        pastor.setPassword(form.getPass());
        pastor.setEstado(form.getStatus());
        
        return pastor;
    }
    
    private TipoIdentificacion getIdentificationType(String t_identificaction) throws ConflictException, Exception {
        
        TipoIdentificacion identificactionType = tipoIdentificacionManager.find(t_identificaction);
        
        if(identificactionType == null) {
            throw new ConflictException("El tipo de identificación no es válido.");
        }
        
        return identificactionType;
    }

    public void setPastorManager(PastorManager pastorManager) {
        this.pastorManager = pastorManager;
    }

    public void setTipoIdentificacionManager(TipoIdentificacionManager tipoIdentificacionManager) {
        this.tipoIdentificacionManager = tipoIdentificacionManager;
    }
   
}
