/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.creyente;

import com.ipuc.base.ceremonia.Ceremonia;
import com.ipuc.base.membresia.Membresia;
import com.ipuc.base.persona.Persona;
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
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "creyente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Creyente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numIdentificacion;

    private Date recepEspSanto;

    private String email;

    private String estado;

    private Ceremonia bautizo;

    private Persona persona;

    private List<Membresia> membresiaHistorial;

    @Id
    @Column(name = "num_identificacion")
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    @Column(name = "recep_esp_santo")
    @Temporal(TemporalType.DATE)
    public Date getRecepEspSanto() {
        return recepEspSanto;
    }

    public void setRecepEspSanto(Date recepEspSanto) {
        this.recepEspSanto = recepEspSanto;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @JoinColumn(name = "bautizo", referencedColumnName = "id_ceremonia")
    @ManyToOne(optional = false)
    public Ceremonia getBautizo() {
        return bautizo;
    }

    public void setBautizo(Ceremonia bautizo) {
        this.bautizo = bautizo;
    }

    @JoinColumn(name = "num_identificacion", referencedColumnName = "num_identificacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Creyente() {
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creyente")
    public List<Membresia> getMembresiaHistorial() {
        return membresiaHistorial;
    }

    public void setMembresiaHistorial(List<Membresia> membresiaHistorial) {
        this.membresiaHistorial = membresiaHistorial;
    }

}
