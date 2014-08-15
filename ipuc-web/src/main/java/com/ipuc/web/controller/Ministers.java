
package com.ipuc.web.controller;

import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.base.persona.Persona;
import com.ipuc.base.persona.PersonaManager;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.exception.ConflictException;
import com.ipuc.web.form.MinisterRegisterForm;
import com.ipuc.web.helper.ResponseFormat;
import com.ipuc.web.list.CivilStateFormat;
import com.ipuc.web.list.IdentificationTypeFormat;
import com.ipuc.web.list.MinisterStateFormat;
import com.ipuc.web.list.SexFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    private PersonaManager personaManager;
    
    public void registerForm(Request request, Response response) {
        
        List<IdentificationTypeFormat> identificationTypes = IdentificationTypeFormat.getIdentificationTypes();
        List<CivilStateFormat> civilStates = CivilStateFormat.getStatesCivil();
        List<MinisterStateFormat> ministerStates = MinisterStateFormat.getStates();
        
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("identificationTypes", identificationTypes);
        info.put("civilStates", civilStates);
        info.put("ministerStates", ministerStates);
        response.contentType(ResponseFormat.HTML.getContentType()).render("registerPastor.ftl", info);
    }
    
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
        Persona persona = createPerson(form);
        
        pastor.setNumeroIdentificacion(persona.getNumeroIdentificacion());
        pastor.setPassword(form.getPass());
        pastor.setEstado(form.getStatus());
        pastor.setPersona(persona);
        pastor.setFechaNombramiento(form.getN_date());
        pastor.setRoles(Pastor.ROL_ADMIN);
        
        return pastor;
    }
    
    private Persona createPerson (MinisterRegisterForm form) throws ConflictException, Exception {
        
        Persona person = new Persona();
        
        person.setTipoIdentificacion(form.getT_identification());
        person.setNumeroIdentificacion(form.getN_identificacion());
        person.setPrimerNombre(form.getP_name());
        person.setSegundoNombre(form.getS_name());
        person.setPrimerApellido(form.getP_lastname());
        person.setSegundoApellido(form.getS_lastname());
        person.setFechaNacimiento(form.getD_birth());
        person.setLugarNacimiento(form.getP_birth());
        person.setEstadoCivil(form.getM_status());
        person.setTelefono(form.getN_phone());
        person.setEmail(form.getMail()); 
        person.setSexo(SexFormat.M.getCodeSex());
        
        personaManager.create(person);
        
        return person;
        
    }
    
    public void setPastorManager(PastorManager pastorManager) {
        this.pastorManager = pastorManager;
    }

    public void setPersonaManager(PersonaManager personaManager) {
        this.personaManager = personaManager;
    }

}
