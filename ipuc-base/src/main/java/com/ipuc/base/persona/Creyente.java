
package com.ipuc.base.persona;

import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.ceremonia.Ceremonia;
import com.ipuc.base.membresia.Membresia;
import com.ipuc.base.persona.Persona;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wilson-rivera
 */
@Entity
@PrimaryKeyJoinColumn(name="numeroIdentificacion")
public class Creyente extends Persona {

    private Date recepEspirituSanto;

    private List<Membresia> membresias;

    private Ceremonia ceremoniaBautizo;

    private Congregacion congregacion;

    @Column(name = "recep_espiritu_santo")
    @Temporal(TemporalType.DATE)
    public Date getRecepEspirituSanto() {
        return recepEspirituSanto;
    }

    public void setRecepEspirituSanto(Date recepEspirituSanto) {
        this.recepEspirituSanto = recepEspirituSanto;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creyente")
    public List<Membresia> getMembresias() {
        return membresias;
    }

    public void setMembresias(List<Membresia> membresias) {
        this.membresias = membresias;
    }

    @JoinColumn(name = "ceremonia_bautizo", referencedColumnName = "id_ceremonia")
    @ManyToOne(optional = false)
    public Ceremonia getCeremoniaBautizo() {
        return ceremoniaBautizo;
    }

    public void setCeremoniaBautizo(Ceremonia ceremoniaBautizo) {
        this.ceremoniaBautizo = ceremoniaBautizo;
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
