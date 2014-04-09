/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.trayectoria;

import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.pastor.Pastor;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "trayectoria")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Trayectoria implements Serializable {

    private static final long serialVersionUID = 1L;

    protected TrayectoriaPK trayectoriaPK;

    private Date fechaPosesion;

    private Date fechaTraslado;

    private String observacion;

    private Congregacion congregacion;

    private Pastor pastor;

    @EmbeddedId
    public TrayectoriaPK getTrayectoriaPK() {
        return trayectoriaPK;
    }

    public void setTrayectoriaPK(TrayectoriaPK trayectoriaPK) {
        this.trayectoriaPK = trayectoriaPK;
    }

    @NotNull
    @Column(name = "fecha_posesion")
    @Temporal(TemporalType.DATE)
    public Date getFechaPosesion() {
        return fechaPosesion;
    }

    public void setFechaPosesion(Date fechaPosesion) {
        this.fechaPosesion = fechaPosesion;
    }

    @Column(name = "fecha_traslado")
    @Temporal(TemporalType.DATE)
    public Date getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(Date fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    @Column(name = "observacion")
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @JoinColumn(name = "cod_congregacion", referencedColumnName = "cod_congregacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    public Congregacion getCongregacion() {
        return congregacion;
    }

    public void setCongregacion(Congregacion congregacion) {
        this.congregacion = congregacion;
    }

    @JoinColumn(name = "num_identificacion", referencedColumnName = "num_identificacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    public Pastor getPastor() {
        return pastor;
    }

    public void setPastor(Pastor pastor) {
        this.pastor = pastor;
    }

}
