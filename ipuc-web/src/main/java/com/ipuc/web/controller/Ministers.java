
package com.ipuc.web.controller;

import com.elibom.jogger.asset.Asset;
import com.elibom.jogger.http.Request;
import com.elibom.jogger.http.Response;
import com.ipuc.base.ceremonia.Ceremonia;
import com.ipuc.base.ceremonia.CeremoniaManager;
import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.congregacion.CongregacionManager;
import com.ipuc.base.exception.ReportException;
import com.ipuc.base.membresia.Membresia;
import com.ipuc.base.membresia.MembresiaManager;
import com.ipuc.base.persona.Creyente;
import com.ipuc.base.persona.CreyenteManager;
import com.ipuc.base.persona.Pastor;
import com.ipuc.base.persona.PastorManager;
import com.ipuc.base.persona.Persona;
import com.ipuc.base.persona.PersonaManager;
import com.ipuc.base.util.ReportsProcessor;
import com.ipuc.web.annotation.Secured;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.exception.ConflictException;
import com.ipuc.web.form.CreyenteForm;
import com.ipuc.web.helper.ResponseFormat;
import com.ipuc.web.list.CivilStateFormat;
import com.ipuc.web.list.IdentificationTypeFormat;
import com.ipuc.web.util.Random;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
public class Ministers {
    
    private static final Logger log = LoggerFactory.getLogger(Ministers.class);
    
    private PastorManager pastorManager;
    
    private PersonaManager personaManager;
    
    private CreyenteManager creyenteManager;
    
    private CongregacionManager congregacionManager;
    
    private MembresiaManager membresiaManager;
    
    private CeremoniaManager ceremoniaManager;
    
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
    
    @Secured(role=Pastor.ROL_PASTOR)
    @Transactional(rollbackFor=Exception.class)
    public void registerCreyente(Request request, Response response) throws BadRequestException, ConflictException, Exception {
        log.info("Minister register request /registerCreyente");
        CreyenteForm form = CreyenteForm.parse(request);
        Creyente aux = creyenteManager.find(form.getNumIdentificacion());
        
        if(aux != null) {
            throw new ConflictException("El creyente ya existe");
        }
        
        Creyente creyente = buildCreyente(response, form);
        creyenteManager.create(creyente);
        
        createMembresia(creyente, creyente.getCongregacion());
        response.status(200).contentType(ResponseFormat.JSON.getContentType()).write("{}");
    }
    
    @Secured(role=Pastor.ROL_PASTOR)
    @Transactional(rollbackFor=Exception.class)
    public void downloadCertificado(Request request, Response response) throws ConflictException, Exception {
        String numIdentificacionCreyente = request.getPathVariable("numIdentificacion");
        Creyente creyente = creyenteManager.find(numIdentificacionCreyente);
        
        if(creyente == null) {
            throw new ConflictException("No es posible generar el certificado de membresía porque el creyente no existe");
        }
        
        byte[] bytes = renderReportMembresia(creyente);
        responsePdf(response, bytes);
                
    }
    
    private byte[] renderReportMembresia(Creyente creyente) {
        
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("nombreCreyente", creyente.getPersona().nombreCompleto());
        info.put("nombreCongregacion", creyente.getCongregacion().getNombre());
        info.put("nombrePastor", creyente.getCongregacion().getPastor().getPersona().nombreCompleto());
        info.put("tipoIdentificacion", IdentificationTypeFormat.getNombreTipoIdenti(creyente.getPersona().getTipoIdentificacion()));
        info.put("numIdentificacion", creyente.getNumeroIdentificacion());
        byte[] bytes;
        try {
            bytes = ReportsProcessor.process("certificadoMembresia", info);
        } catch (ReportException ex) {
            log.error("No fue posible renderizar el reporte de certificado de membresias. Message " + ex.getMessage());
            bytes = null;
        }
        return bytes;
    }
    
    private void responsePdf(Response response, byte[] bytes) {
        InputStream in = new ByteArrayInputStream(bytes);
        String nombre = Random.generateString(30) + ".pdf";
        String contentType = ResponseFormat.PDF.getContentType();
        Asset asset = new Asset(in, nombre, contentType, bytes.length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + asset.getName() + "\"");
        response.write(asset);
    }
    
    @Secured(role=Pastor.ROL_PASTOR)
    public void getPastores(Request request, Response response) throws ConflictException, Exception {
        List<Pastor> pastores = pastorManager.findAll();
                
        if(pastores == null) {
            throw new ConflictException("No existen pastores");
        }
        
        response.status(200).contentType(ResponseFormat.JSON.getContentType()).write(getResponsePastores(pastores));
        
    }
    
    private Creyente buildCreyente(Response response, CreyenteForm form) throws Exception {
        Creyente creyente = new Creyente();
        Persona persona = createPerson(form);
        Pastor pastor = getPastorFromResponse(response);
        Ceremonia bautismo = createCeremoniaBautizo(form);        
        
        //Data creyente
        creyente.setNumeroIdentificacion(form.getNumIdentificacion());
        creyente.setRecepEspirituSanto(form.getFechaRecepcionES());
        Congregacion congregacion = congregacionManager.getCongregacionByPastor(pastor.getNumeroIdentificacion());
        if(congregacion == null) {
            throw new ConflictException("No es posible crear creyentes porque el pastor " + pastor.nombreApellido() + " no tiene congregación");
        }
        creyente.setCongregacion(congregacion);
        creyente.setPersona(persona);
        creyente.setCeremoniaBautizo(bautismo);
        
        return creyente;
    }
    
    private Ceremonia createCeremoniaBautizo(CreyenteForm form) throws Exception {
        Ceremonia bautismo = new Ceremonia();
        bautismo.setFechaCelebracion(form.getFechaBautizo());
        bautismo.setLugar(form.getLugarCeremonia());
        Pastor pastorOficiante;
        if(form.getPastorOficiante() != null) {
           pastorOficiante = pastorManager.find(form.getPastorOficiante());
        } else {
            pastorOficiante = null;
        }       
        bautismo.setPastorOficiante(pastorOficiante);
        ceremoniaManager.create(bautismo);
        
        return bautismo;
    }
    
    private void createMembresia(Creyente creyente, Congregacion congregacion) throws Exception {
        Membresia membresia = new Membresia();
        membresia.setCongregacion(congregacion);
        membresia.setCreyente(creyente);
        membresia.setFechaIngreso(new Date());
        membresiaManager.create(membresia);
    }
    
    private Persona createPerson (CreyenteForm form) throws ConflictException, Exception {
        
        Persona persona = new Persona();
        
        persona.setNumeroIdentificacion(form.getNumIdentificacion());
        persona.setTipoIdentificacion(form.getTipoIdentificacion());
        persona.setPrimerNombre(form.getP_nombre());
        persona.setSegundoNombre(form.getS_nombre());
        persona.setPrimerApellido(form.getP_apellido());
        persona.setSegundoApellido(form.getS_apellido());
        persona.setSexo(form.getSexo());
        persona.setEstadoCivil(form.getEstadoCivil());
        persona.setFechaNacimiento(form.getFechaNacimiento());
        persona.setLugarNacimiento(form.getLugarNacimiento());
        persona.setTelefono(form.getTelefono());
        persona.setEmail(form.getCorreo());
        persona.setPadre(form.getNombrePadre());
        persona.setMadre(form.getNombreMadre());
        
        personaManager.create(persona);
        
        return persona;
        
    }
    
    private Pastor getPastorFromResponse(Response response){
        Map<String, Object> atributes = response.getAttributes();
        Pastor pastor = (Pastor) atributes.get("pastor");
        return pastor;
    }
    
    private String getResponsePastores(List<Pastor> pastores) {
        JSONArray pastoresJson = new JSONArray();
        
        for(Pastor pastor : pastores) {
            JSONObject obj = new JSONObject();
            obj.put("num_identi", pastor.getNumeroIdentificacion());
            obj.put("nombre", pastor.nombreLegal());
            pastoresJson.put(obj);
        }
        
        return pastoresJson.toString();
    }

    public void setPastorManager(PastorManager pastorManager) {
        this.pastorManager = pastorManager;
    }

    public void setPersonaManager(PersonaManager personaManager) {
        this.personaManager = personaManager;
    }

    public void setCreyenteManager(CreyenteManager creyenteManager) {
        this.creyenteManager = creyenteManager;
    }

    public void setCongregacionManager(CongregacionManager congregacionManager) {
        this.congregacionManager = congregacionManager;
    }

    public void setMembresiaManager(MembresiaManager membresiaManager) {
        this.membresiaManager = membresiaManager;
    }

    public void setCeremoniaManager(CeremoniaManager ceremoniaManager) {
        this.ceremoniaManager = ceremoniaManager;
    }
       
}
