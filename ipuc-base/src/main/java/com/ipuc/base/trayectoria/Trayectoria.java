
package com.ipuc.base.trayectoria;

import com.ipuc.base.persona.Pastor;
import com.ipuc.base.congregacion.Congregacion;
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
@Table(name = "trayectoria")
public class Trayectoria implements Serializable {

    private int idTrayectoria;

    private Date fechaPosesion;

    private String fechaTraslado;

    private Pastor pastor;

    private Congregacion congregacion;

    public Trayectoria() {
    }

    public Trayectoria(int idTrayectoria) {
        this.idTrayectoria = idTrayectoria;
    }

    public Trayectoria(int idTrayectoria, Date fechaPosesion) {
        this.idTrayectoria = idTrayectoria;
        this.fechaPosesion = fechaPosesion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id_trayectoria")
    public int getIdTrayectoria() {
        return idTrayectoria;
    }

    public void setIdTrayectoria(int idTrayectoria) {
        this.idTrayectoria = idTrayectoria;
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
    public String getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(String fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    @JoinColumn(name = "pastor", referencedColumnName = "numero_identificacion")
    @ManyToOne(optional = false)
    public Pastor getPastor() {
        return pastor;
    }

    public void setPastor(Pastor pastor) {
        this.pastor = pastor;
    }

    @JoinColumn(name = "congregacion", referencedColumnName = "cod_congregacion")
    @ManyToOne(optional = false)
    public Congregacion getCongregacion() {
        return congregacion;
    }

    public void setCongregacion(Congregacion congregacion) {
        this.congregacion = congregacion;
    }

}
