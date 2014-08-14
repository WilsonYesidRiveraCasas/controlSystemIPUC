
package com.ipuc.base.auth;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "auth")
public class Auth implements Serializable {

    
    private String numeroIdentificacion;
    
    private String idSesion;

    public Auth() {
    }

    public Auth(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Auth(String numeroIdentificacion, String idSesion) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.idSesion = idSesion;
    }

    @Id
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numero_identificacion")
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "id_sesion")
    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }
    
}
