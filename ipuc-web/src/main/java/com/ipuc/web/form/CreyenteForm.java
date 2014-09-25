
package com.ipuc.web.form;

import com.ipuc.base.util.RegexValidator;
import com.ipuc.web.exception.BadRequestException;
import com.ipuc.web.list.CivilStateFormat;
import com.ipuc.web.list.IdentificationTypeFormat;
import com.ipuc.web.list.SexFormat;
import com.ipuc.web.util.Preconditions;
import java.util.Date;
import org.jogger.http.Request;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wilson-rivera
 */
public class CreyenteForm {
    
    private static Logger log = LoggerFactory.getLogger(CreyenteForm.class);
    
    private String tipoIdentificacion;
    
    private String numIdentificacion;
    
    private String p_nombre;
    
    private String s_nombre;
    
    private String p_apellido;
    
    private String s_apellido;
    
    private String sexo;
    
    private String estadoCivil;
    
    private Date fechaNacimiento;
    
    private String lugarNacimiento;
    
    private String telefono;
    
    private String correo;
    
    private String nombrePadre;
    
    private String nombreMadre;
    
    private Date fechaRecepcionES;
    
    private Date fechaBautizo;
    
    private String lugarCeremonia;
    
    private String pastorOficiante;
    
    public static CreyenteForm parse(Request request) throws BadRequestException {
        return getRegister(request);
    }
    
    private static CreyenteForm getRegister(Request request) throws BadRequestException {
        try {
            JSONObject json = new JSONObject(request.getBody().asString());
            CreyenteForm register = new CreyenteForm();
            register.setTipoIdentificacion(getTipoIdentificacion(json.getString("tipo_identi")));
            register.setNumIdentificacion(Preconditions.getNotEmpty(json.getString("num_identi"), "El número de indetificación es requerido"));
            register.setP_nombre(Preconditions.getNotEmpty(json.getString("p_name"), "El primer nombre es requerido"));
            register.setS_nombre(json.getString("s_name"));
            register.setP_apellido(Preconditions.getNotEmpty(json.getString("p_apellido"), "El primer apellido es requerido"));
            register.setS_apellido(json.getString("s_apellido"));
            register.setSexo(getSexo(json.getString("sexo")));
            register.setEstadoCivil(getEstadoCivil(json.getString("s_civil")));
            register.setFechaNacimiento(Preconditions.notNullDate(json.getString("date_naci"), "Fecha de nacimiento requerida"));
            register.setLugarNacimiento(json.getString("l_naci"));
            register.setTelefono(json.getString("tele"));
            register.setCorreo(validateCorreo(json.getString("correo")));
            register.setNombrePadre(json.getString("name_papa"));
            register.setNombreMadre(json.getString("name_madre"));
            register.setFechaRecepcionES(Preconditions.getDate(json.getString("date_e_s")));
            register.setFechaBautizo(Preconditions.notNullDate(json.getString("date_bauti"), "Fecha de bautismo requerida"));
            register.setLugarCeremonia(json.getString("l_cere"));
            register.setPastorOficiante(json.getString("pastor_ofi"));
            
            return register;
        } catch(IllegalArgumentException e) {
            log.error("Error parsing request register creyente. " + e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch(JSONException e) {
            log.error("Error parsing CongregacionRegisterForm. Message: " + e.getMessage());
            throw new BadRequestException("Error manejando la petición. Inténtelo de nuevo y si persiste contáctese con el administrador");
        }
    }
    
    private static  String getTipoIdentificacion(String tipo) throws BadRequestException {
        
        Preconditions.notEmpty(tipo, "El tipo de identificación es requerido");
        
        boolean isValid = IdentificationTypeFormat.isValidCode(tipo);
        if(!isValid) {
            throw new BadRequestException("Tipo de identificación es inválido");
        }
        
        return tipo;
    }
    
    private static String getSexo(String sexo) throws BadRequestException {
        Preconditions.notEmpty(sexo, "El sexo es requerido");
        boolean isValid = SexFormat.isValidSex(sexo);
        if(!isValid) {
            throw new BadRequestException("El sexo es inválido");
        }
        return sexo;
    }
    
    private static String getEstadoCivil(String estadoCivil) throws BadRequestException {
        Preconditions.notEmpty(estadoCivil, "El estado civil es requerido");
        boolean isValid = CivilStateFormat.isValidStateCivil(estadoCivil);
        if(!isValid) {
            throw new BadRequestException("El estado civil es inválido");
        }
        return estadoCivil;
    }
    
    private static String validateCorreo(String correo) throws BadRequestException { 
        if(correo == null || correo.isEmpty()) {
            throw new BadRequestException("El correo es requerido");
        }
        boolean is_valid = RegexValidator.isValidateEmail(correo);
        if(!is_valid) {
            throw new BadRequestException("El correo es inválido");
        }
        return correo;
    }
        
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getP_nombre() {
        return p_nombre;
    }

    public void setP_nombre(String p_nombre) {
        this.p_nombre = p_nombre;
    }

    public String getS_nombre() {
        return s_nombre;
    }

    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }

    public String getP_apellido() {
        return p_apellido;
    }

    public void setP_apellido(String p_apellido) {
        this.p_apellido = p_apellido;
    }

    public String getS_apellido() {
        return s_apellido;
    }

    public void setS_apellido(String s_apellido) {
        this.s_apellido = s_apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public Date getFechaRecepcionES() {
        return fechaRecepcionES;
    }

    public void setFechaRecepcionES(Date fechaRecepcionES) {
        this.fechaRecepcionES = fechaRecepcionES;
    }

    public Date getFechaBautizo() {
        return fechaBautizo;
    }

    public void setFechaBautizo(Date fechaBautizo) {
        this.fechaBautizo = fechaBautizo;
    }

    public String getLugarCeremonia() {
        return lugarCeremonia;
    }

    public void setLugarCeremonia(String lugarCeremonia) {
        this.lugarCeremonia = lugarCeremonia;
    }

    public String getPastorOficiante() {
        return pastorOficiante;
    }

    public void setPastorOficiante(String pastorOficiante) {
        this.pastorOficiante = pastorOficiante;
    }
}
