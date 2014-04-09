
package com.ipuc.base.historialTarjeta;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Embeddable
public class HistorialTarjetaPK implements Serializable {
    
    private String numIdentificacion;
    
    private String idTarjeta;

    @NotNull
    @Column(name = "num_identificacion")
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    @NotNull
    @Column(name = "id_tarjeta")
    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

}
