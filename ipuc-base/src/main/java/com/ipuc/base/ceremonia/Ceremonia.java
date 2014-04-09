
package com.ipuc.base.ceremonia;

import com.ipuc.base.creyente.Creyente;
import com.ipuc.base.pastor.Pastor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "ceremonia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ceremonia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idCeremonia;

    private Date fechaCelebracion;

    private String lugar;

    private Pastor pastorOficiante;

    @Id
    @Column(name = "id_ceremonia")
    @GeneratedValue
    public String getIdCeremonia() {
        return idCeremonia;
    }

    public void setIdCeremonia(String idCeremonia) {
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

    @JoinColumn(name = "pastor_oficiante", referencedColumnName = "num_identificacion")
    @ManyToOne
    public Pastor getPastorOficiante() {
        return pastorOficiante;
    }

    public void setPastorOficiante(Pastor pastorOficiante) {
        this.pastorOficiante = pastorOficiante;
    }

    public Ceremonia() {
    }

}
