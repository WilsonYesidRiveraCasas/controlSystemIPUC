
package com.ipuc.base.historialTarjeta;

import com.ipuc.base.pastor.Pastor;
import com.ipuc.base.tarjeta.Tarjeta;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "historial_tarjeta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HistorialTarjeta implements Serializable {

    private static final long serialVersionUID = 1L;

    protected HistorialTarjetaPK historialTarjetaPK;

    private Date fechaRecibida;

    private Pastor pastor;

    private Tarjeta tarjeta;

    @EmbeddedId
    public HistorialTarjetaPK getHistorialTarjetaPK() {
        return historialTarjetaPK;
    }

    public void setHistorialTarjetaPK(HistorialTarjetaPK historialTarjetaPK) {
        this.historialTarjetaPK = historialTarjetaPK;
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

    @JoinColumn(name = "num_identificacion", referencedColumnName = "num_identificacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    public Pastor getPastor() {
        return pastor;
    }

    public void setPastor(Pastor pastor) {
        this.pastor = pastor;
    }

    @JoinColumn(name = "id_tarjeta", referencedColumnName = "id_tarjeta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

}
