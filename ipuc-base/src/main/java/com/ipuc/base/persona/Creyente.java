
package com.ipuc.base.persona;

import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.ceremonia.Ceremonia;
import com.ipuc.base.membresia.Membresia;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "creyente")
public class Creyente implements Serializable {

    private String numeroIdentificacion;
    
    private Date recepEspirituSanto;

    private List<Membresia> membresias;

    private Ceremonia ceremoniaBautizo;

    private Congregacion congregacion;
    
    private Persona persona;

    @Id
    @NotNull
    @Column(name = "numero_identificacion")
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }
    
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    
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

    @JoinColumn(name = "numero_identificacion", referencedColumnName = "numero_identificacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    

}
