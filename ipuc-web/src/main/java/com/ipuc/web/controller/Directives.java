
package com.ipuc.web.controller;

import com.ipuc.base.mail.MailService;
import com.ipuc.base.municipio.Municipio;
import com.ipuc.base.municipio.MunicipioManager;
import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.base.persona.Persona;
import com.ipuc.base.persona.PersonaManager;
import com.ipuc.base.region.Region;
import com.ipuc.base.region.RegionManager;
import com.ipuc.base.util.TemplateProcessor;
import com.ipuc.web.annotation.Secured;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.exception.ConflictException;
import com.ipuc.web.form.MinisterRegisterForm;
import com.ipuc.web.helper.ResponseFormat;
import com.ipuc.web.list.CivilStateFormat;
import com.ipuc.web.list.IdentificationTypeFormat;
import com.ipuc.web.list.MinisterStateFormat;
import com.ipuc.web.list.SexFormat;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jogger.http.Request;
import org.jogger.http.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wilson-rivera
 */
public class Directives {
    
    private static final Logger log = LoggerFactory.getLogger(Directives.class);
    
    private static final String COD_PAIS_DEFAULT = "CO";
    
    private PastorManager pastorManager;
    
    private PersonaManager personaManager;
    
    private RegionManager regionManager;
    
    private MunicipioManager municipioManager;
   
    @Secured(role=Pastor.ROL_DIRECTIVO)
    public void registerForm(Request request, Response response) {
        
        List<IdentificationTypeFormat> identificationTypes = IdentificationTypeFormat.getIdentificationTypes();
        
        Pastor pastor = getPastorFromResponse(response);
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("pastor", pastor);
        info.put("identificationTypes", identificationTypes);
        response.contentType(ResponseFormat.HTML.getContentType()).render("registerPastor2.ftl", info);
    }
    
    @Secured(role=Pastor.ROL_DIRECTIVO)
    @Transactional(rollbackFor=Exception.class)
    public void registerPastor(Request request, Response response) throws ConflictException, BadRequestException, Exception {
        log.info("Minister register request /register");
        MinisterRegisterForm registerForm = MinisterRegisterForm.parse(request);
        Pastor pastorAux = pastorManager.find(registerForm.getN_identificacion());
        
        if(pastorAux != null) {
            throw new ConflictException("El pastor ya existe");
        }

        Pastor pastor = buildPastor(registerForm);
        pastorManager.create(pastor);
        sendMail(pastor.getPersona().getEmail(), pastor.getPersona().getNumeroIdentificacion());
       
       response.status(200).contentType(ResponseFormat.JSON.getContentType()).write("{}");
    }
    
    @Secured(role=Pastor.ROL_DIRECTIVO)
    public void registerCongregacion(Request request, Response response) {
        
    }
    
    @Secured(role=Pastor.ROL_DIRECTIVO)
    public void getMunicipios(Request request, Response response) throws ConflictException, Exception {
        
        JSONObject json = new JSONObject(request.getBody().asString());
        int idRegion = json.getInt("codRegion");
        List<Municipio> municipios = municipioManager.getMunicipioByCodRegion(idRegion);
        if(municipios == null || municipios.isEmpty()) {
            throw new ConflictException("No se encontraron municipios, región inválida");
        }
        response.status(200).contentType(ResponseFormat.JSON.getContentType()).write(getResponseMunicipios(municipios));
    }
    
    private Pastor buildPastor(MinisterRegisterForm form) throws ConflictException, Exception {
        Pastor pastor = new Pastor();
        Persona persona = createPerson(form);
        
        pastor.setNumeroIdentificacion(persona.getNumeroIdentificacion());
        pastor.setPassword(persona.getNumeroIdentificacion());
        pastor.setEstado(MinisterStateFormat.A.getCodeState());
        pastor.setPersona(persona);
        pastor.setFechaNombramiento(new Date());
        pastor.setRoles(Pastor.ROL_PASTOR);
        
        return pastor;
    }
    
    private Persona createPerson (MinisterRegisterForm form) throws ConflictException, Exception {
        
        Persona person = new Persona();
        String undefined = "SIN ASIGNAR";
        
        person.setTipoIdentificacion(form.getT_identification());
        person.setNumeroIdentificacion(form.getN_identificacion());
        person.setPrimerNombre(undefined);
        person.setSegundoNombre(undefined);
        person.setPrimerApellido(undefined);
        person.setSegundoApellido(undefined);
        person.setFechaNacimiento(new Date());
        person.setLugarNacimiento(undefined);
        person.setEstadoCivil(CivilStateFormat.S.getCodeStateCivil());
        person.setTelefono(undefined);
        person.setEmail(form.getCorreo()); 
        person.setSexo(SexFormat.M.getCodeSex());
        
        personaManager.create(person);
        
        return person;
        
    }
    
    private List<Region> getRegiones() throws Exception {
        List<Region> regiones = regionManager.getRegionByPais(COD_PAIS_DEFAULT);
        if(regiones == null || regiones.isEmpty()) {
            throw new ConflictException("No se encontraron regiones, país no contemplado");
        }
        return regiones;
    }
    
    
    
    private void sendMail(String correo, String num_identificacion) throws TemplateException, IOException, Exception {
        
        List<String> to = new ArrayList<String>();
        to.add(correo);
        String subject = "Bienvenido Pastor";
        
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("num_identificacion", num_identificacion);
        info.put("password", num_identificacion);
        String htmlMail = new TemplateProcessor().process("mailRegisterPastor.ftl", info);
        
        MailService.send(to, subject, htmlMail);
        
    }
    
    private String getResponseMunicipios(List<Municipio> municipios) {
        
        JSONArray municipiosJson = new JSONArray();
        
        for(Municipio m : municipios) {
            JSONObject obj = new JSONObject();
            obj.put("codMunicipio", m.getIdMunicipio());
            obj.put("nombre", m.getNombre());
            municipiosJson.put(obj);
        }
        
        return municipiosJson.toString();
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

    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    public void setMunicipioManager(MunicipioManager municipioManager) {
        this.municipioManager = municipioManager;
    }
          
}