
package com.ipuc.base.membresia;

import com.ipuc.base.persona.Creyente;
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
@Table(name = "membresia")
public class Membresia implements Serializable {

    private int idMembresia;

    private Date fechaIngreso;

    private Date fechaSalida;

    private Creyente creyente;

    private Congregacion congregacion;

    public Membresia() {
    }

    public Membresia(int idMembresia) {
        this.idMembresia = idMembresia;
    }

    public Membresia(int idMembresia, Date fechaIngreso) {
        this.idMembresia = idMembresia;
        this.fechaIngreso = fechaIngreso;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membresia")
    public int getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(int idMembresia) {
        this.idMembresia = idMembresia;
    }

    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @JoinColumn(name = "creyente", referencedColumnName = "numero_identificacion")
    @ManyToOne(optional = false)
    public Creyente getCreyente() {
        return creyente;
    }

    public void setCreyente(Creyente creyente) {
        this.creyente = creyente;
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
