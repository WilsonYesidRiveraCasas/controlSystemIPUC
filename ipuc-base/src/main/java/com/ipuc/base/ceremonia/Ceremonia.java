
package com.ipuc.base.ceremonia;

import com.ipuc.base.persona.Pastor;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "ceremonia")
public class Ceremonia implements Serializable {

    private int idCeremonia;

    private Date fechaCelebracion;

    private String lugar;

    private Pastor pastorOficiante;

    public Ceremonia() {
    }

    public Ceremonia(int idCeremonia) {
        this.idCeremonia = idCeremonia;
    }

    public Ceremonia(int idCeremonia, Date fechaCelebracion) {
        this.idCeremonia = idCeremonia;
        this.fechaCelebracion = fechaCelebracion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ceremonia")
    public int getIdCeremonia() {
        return idCeremonia;
    }

    public void setIdCeremonia(int idCeremonia) {
        this.idCeremonia = idCeremonia;
    }

    @NotNull
    @Column(name = "fecha_celebracion")
    @Temporal(TemporalType.DATE)
    public Date getFechaCelebracion() {
        return fechaCelebracion;
    }

    public void setFechaCelebracion(Date fechaCelebracion) {
        this.fechaCelebracion = fechaCelebracion;
    }

    @Column(name = "lugar")
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @JoinColumn(name = "pastor_oficiante", referencedColumnName = "numero_identificacion")
    @ManyToOne(optional = false)
    public Pastor getPastorOficiante() {
        return pastorOficiante;
    }

    public void setPastorOficiante(Pastor pastorOficiante) {
        this.pastorOficiante = pastorOficiante;
    }
    
}
