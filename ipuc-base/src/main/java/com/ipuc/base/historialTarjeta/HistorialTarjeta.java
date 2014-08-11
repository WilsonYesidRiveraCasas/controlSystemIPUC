
package com.ipuc.base.historialTarjeta;

import com.ipuc.base.persona.Pastor;
import com.ipuc.base.tarjeta.Tarjeta;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "historial_tarjeta")
public class HistorialTarjeta implements Serializable {

    private int idHistorialTarjeta;

    private Date fechaRecibida;

    private Tarjeta tarjeta;

    private Pastor pastor;

    public HistorialTarjeta() {
    }

    public HistorialTarjeta(int idHistorialTarjeta) {
        this.idHistorialTarjeta = idHistorialTarjeta;
    }

    public HistorialTarjeta(int idHistorialTarjeta, Date fechaRecibida) {
        this.idHistorialTarjeta = idHistorialTarjeta;
        this.fechaRecibida = fechaRecibida;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id_historial_tarjeta")
    public int getIdHistorialTarjeta() {
        return idHistorialTarjeta;
    }

    public void setIdHistorialTarjeta(int idHistorialTarjeta) {
        this.idHistorialTarjeta = idHistorialTarjeta;
    }

    @NotNull
    @Column(name = "fecha_recibida")
    @Temporal(TemporalType.DATE)
    public Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    @JoinColumn(name = "id_tarjeta", referencedColumnName = "cod_tarjeta")
    @ManyToOne(optional = false)
    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    @JoinColumn(name = "id_identificacion_pastor")
    @ManyToOne(optional = false)
    public Pastor getPastor() {
        return pastor;
    }

    public void setPastor(Pastor pastor) {
        this.pastor = pastor;
    }

}
