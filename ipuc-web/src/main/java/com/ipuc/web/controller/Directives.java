
package com.ipuc.web.controller;

import com.elibom.jogger.http.Request;
import com.elibom.jogger.http.Response;
import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.congregacion.CongregacionManager;
import com.ipuc.base.exception.NotSendMailException;
import com.ipuc.base.mail.MailService;
import com.ipuc.base.municipio.Municipio;
import com.ipuc.base.municipio.MunicipioManager;
import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.base.persona.Persona;
import com.ipuc.base.persona.PersonaManager;
import com.ipuc.base.region.Region;
import com.ipuc.base.region.RegionManager;
import com.ipuc.base.trayectoria.Trayectoria;
import com.ipuc.base.trayectoria.TrayectoriaManager;
import com.ipuc.base.util.TemplateProcessor;
import com.ipuc.web.annotation.Secured;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.exception.ConflictException;
import com.ipuc.web.form.CongregacionRegisterForm;
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
    
    private CongregacionManager congregacionManager;
    
    private TrayectoriaManager trayectoriaManager;
   
    @Secured(role=Pastor.ROL_DIRECTIVO)
    public void registerForm(Request request, Response response) {
        
        List<IdentificationTypeFormat> identificationTypes = IdentificationTypeFormat.getIdentificationTypes();
        
        Pastor pastor = getPastorFromResponse(response);
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("pastor", pastor);
        info.put("identificationTypes", identificationTypes);
        response.contentType(ResponseFormat.HTML.getContentType()).render("registerPastor.ftl", info);
    }
    
    @Secured(role=Pastor.ROL_DIRECTIVO)
    @Transactional(rollbackFor=Exception.class)
    public void registerPastor(Request request, Response response) throws ConflictException, BadRequestException, NotSendMailException, Exception {
        log.info("Minister register request /register");
        MinisterRegisterForm registerForm = MinisterRegisterForm.parse(request);
        Pastor pastorAux = pastorManager.find(registerForm.getN_identificacion());
        
        if(pastorAux != null) {
            throw new ConflictException("El pastor ya existe");
        }

        Pastor directivoLogueado = getPastorFromResponse(response);
        Pastor pastor = buildPastor(registerForm, directivoLogueado);
        pastorManager.create(pastor);
        sendMail(pastor.getPersona().getEmail(), pastor.getPersona().getNumeroIdentificacion());
       
       response.status(200).contentType(ResponseFormat.JSON.getContentType()).write("{}");
    }
    
    @Secured(role=Pastor.ROL_DIRECTIVO)
    public void registerCongregacionForm(Request request, Response response) throws Exception {
        List<Region> regiones = getRegiones();        
        Pastor pastor = getPastorFromResponse(response);
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("pastor", pastor);
        info.put("regiones", regiones);
        response.contentType(ResponseFormat.HTML.getContentType()).render("registerCongregacion.ftl", info);
    }
    
    @Secured(role=Pastor.ROL_DIRECTIVO)
    @Transactional(rollbackFor=Exception.class)
    public void registerCongregacion(Request request, Response response) throws BadRequestException, Exception {
        log.info("Congregacion register request /register");
        CongregacionRegisterForm register = CongregacionRegisterForm.parse(request);
        Pastor directivoLogueado = getPastorFromResponse(response);
        Congregacion congregacion = buildCongregacion(register, directivoLogueado);
        congregacionManager.create(congregacion);
        
        if(congregacion.getPastor() != null) {
            Trayectoria trayectoria = new Trayectoria();
            trayectoria.setCongregacion(congregacion);
            trayectoria.setFechaPosesion(new Date());
            trayectoria.setPastor(congregacion.getPastor());
            trayectoriaManager.create(trayectoria);            
        }
        
        response.status(200).contentType(ResponseFormat.JSON.getContentType()).write("{}");
    }
    
    @Secured(role=Pastor.ROL_DIRECTIVO)
    public void getMunicipios(Request request, Response response) throws ConflictException, Exception {
        
        int idRegion = Integer.parseInt(request.getPathVariable("region"));
        List<Municipio> municipios = municipioManager.getMunicipioByCodRegion(idRegion);
        if(municipios == null || municipios.isEmpty()) {
            throw new ConflictException("No se encontraron municipios, región inválida");
        }
        response.status(200).contentType(ResponseFormat.JSON.getContentType()).write(getResponseMunicipios(municipios));
    }
    
    public void getPastoresSinCongregacion(Request request, Response response) throws ConflictException, Exception {
        
        List<Pastor> pastores = pastorManager.getPastoresDelDistritoSinCongregacion();
        if(pastores == null || pastores.isEmpty()) {
            throw new ConflictException("No se encontraron pastores sin congregación");
        }
        response.status(200).contentType(ResponseFormat.JSON.getContentType()).write(getResponsePastores(pastores));
    }
    
    private Congregacion buildCongregacion(CongregacionRegisterForm form, Pastor directivoLogueado) throws Exception {
        Congregacion congregacion = new Congregacion();
        congregacion.setNombre(form.getNombre());
        congregacion.setDireccion(form.getDireccion());
        congregacion.setTelefono(form.getTelefono());
        congregacion.setDistrito(directivoLogueado.getDistrito());
        congregacion.setFechaApertura(form.getFecha_apertura());
        Pastor pastor = null;
        if(form.getNum_identi_pastor() != null && !form.getNum_identi_pastor().isEmpty()) {
            pastor = pastorManager.find(form.getNum_identi_pastor());
            if(pastor == null) {
                throw new ConflictException("El pastor con cédula " + form.getNum_identi_pastor() + " no existe");
            }
        }
        congregacion.setPastor(pastor);
        
        Municipio municipio = municipioManager.find(form.getIdMunicipio());
        if(municipio == null) {
            throw new ConflictException("El municipio no existe");
        }
        
        congregacion.setMunicipio(municipio);
        
        return congregacion;
    }
    
    private Pastor buildPastor(MinisterRegisterForm form, Pastor directivoLogueado) throws ConflictException, Exception {
        Pastor pastor = new Pastor();
        Persona persona = createPerson(form);
        
        pastor.setNumeroIdentificacion(persona.getNumeroIdentificacion());
        pastor.setPassword(persona.getNumeroIdentificacion());
        pastor.setEstado(MinisterStateFormat.A.getCodeState());
        pastor.setPersona(persona);
        pastor.setFechaNombramiento(new Date());
        pastor.setRoles(Pastor.ROL_PASTOR);
        pastor.setDistrito(directivoLogueado.getDistrito());
        
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
    
    
    
    private void sendMail(String correo, String num_identificacion) throws TemplateException, IOException, NotSendMailException {
        
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
    
    private String getResponsePastores(List<Pastor> pastores) {
        JSONArray pastoresJson = new JSONArray();
        
        for(Pastor pastor : pastores) {
            JSONObject obj = new JSONObject();
            obj.put("num_identi", pastor.getNumeroIdentificacion());
            obj.put("nombre", pastor.nombreApellido());
            pastoresJson.put(obj);
        }
        
        return pastoresJson.toString();
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

    public void setCongregacionManager(CongregacionManager congregacionManager) {
        this.congregacionManager = congregacionManager;
    }

    public void setTrayectoriaManager(TrayectoriaManager trayectoriaManager) {
        this.trayectoriaManager = trayectoriaManager;
    }
    
}
