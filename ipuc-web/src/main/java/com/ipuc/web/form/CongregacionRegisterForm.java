
package com.ipuc.web.form;

import com.ipuc.web.exception.BadRequestException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jogger.http.Request;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author WRIVER1
 */
public class CongregacionRegisterForm {
    
    private static Logger log = LoggerFactory.getLogger(CongregacionRegisterForm.class);
    
    private String nombre;
    
    private String direccion;
    
    private String telefono;
    
    private Date fecha_apertura;
    
    private int idMunicipio;
    
    private String num_identi_pastor;
    
    public static CongregacionRegisterForm parse(Request request) throws BadRequestException {
        return getRegister(request);
    }
    
    private static CongregacionRegisterForm getRegister(Request request) throws BadRequestException {
        try {
            JSONObject json = new JSONObject(request.getBody().asString());
            CongregacionRegisterForm register = new CongregacionRegisterForm();
            register.setNombre(validaNombre(json.getString("nombre")));
            register.setDireccion(validaDireccion(json.getString("direccion")));
            register.setTelefono(validaDireccion(json.getString("telefono")));
            register.setFecha_apertura(validaFechaApertura(json.getString("fecha_apertura")));
            register.setIdMunicipio(validaMunicipio(json.getString("municipio")));
            register.setNum_identi_pastor(json.getString("pastor"));
            return register;
        } catch(JSONException e) {
            log.error("Error parsing CongregacionRegisterForm. Message: " + e.getMessage());
            throw new BadRequestException("Error manejando la petición. Inténtelo de nuevo y si persiste contáctese con el administrador");
        }
    }
    
    private static String validaNombre(String nombre) throws BadRequestException {
        if(nombre == null || nombre.isEmpty()) {
            throw new BadRequestException("El nombre de la congregación es requerido");
        }
        
        if(nombre.length() > 50) {
            nombre = nombre.substring(0, 49);
        }
        
        return nombre;
    }
    
    private static String validaDireccion(String direccion) throws BadRequestException {
        if(direccion == null || direccion.isEmpty()) {
            throw new BadRequestException("La dirección de la congregación es requerida");
        }
        
        if(direccion.length() > 200) {
            direccion = direccion.substring(0, 199);
        }
        
        return direccion;
    }
    
    private static Date validaFechaApertura(String fecha) throws BadRequestException {
        if(fecha == null || fecha.isEmpty()) {
            throw new BadRequestException("La fecha de la congregación es requerida");
        }
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/dd/yyyy");
        Date nuevaFecha = null;
        try {
            nuevaFecha = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            throw new BadRequestException("Formato de fecha inválida");
        }
        
        return nuevaFecha;
    }
    
    private static int validaMunicipio(String muncipio) throws BadRequestException {
        if(muncipio == null || muncipio.isEmpty()) {
            throw new BadRequestException("La dirección de la congregación es requerida");
        }
        
        if(muncipio.length() > 11) {
            muncipio = muncipio.substring(0, 10);
        }
        
        int idMunicipio = -1;
        try {
            idMunicipio = Integer.parseInt(muncipio);
        } catch (NumberFormatException e) {
            throw new BadRequestException("El municipio es inválido");
        }
        
        return idMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNum_identi_pastor() {
        return num_identi_pastor;
    }

    public void setNum_identi_pastor(String num_identi_pastor) {
        this.num_identi_pastor = num_identi_pastor;
    }
    
    
    
}
