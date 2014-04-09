/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.pastor;

import com.ipuc.base.ceremonia.Ceremonia;
import com.ipuc.base.historialTarjeta.HistorialTarjeta;
import com.ipuc.base.persona.Persona;
import com.ipuc.base.trayectoria.Trayectoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "pastor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pastor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numIdentificacion;

    private String codPastor;

    private Date fechaNombramiento;

    private String rol;

    private String password;

    private Persona persona;

    private List<HistorialTarjeta> historialTarjetaList;

    private List<Ceremonia> ceremoniaList;

    private List<Trayectoria> trayectoria;

    @Id
    @Column(name = "num_identificacion")
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    @Column(name = "cod_pastor")
    @NotNull
    public String getCodPastor() {
        return codPastor;
    }

    public void setCodPastor(String codPastor) {
        this.codPastor = codPastor;
    }

    @Column(name = "fecha_nombramiento")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getFechaNombramiento() {
        return fechaNombramiento;
    }

    public void setFechaNombramiento(Date fechaNombramiento) {
        this.fechaNombramiento = fechaNombramiento;
    }

    @Column(name = "rol")
    @NotNull
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Column(name = "password")
    @Type(type = "encryptedPassword")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JoinColumn(name = "num_identificacion", referencedColumnName = "num_identificacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Pastor() {
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pastor")
    public List<HistorialTarjeta> getHistorialTarjetaList() {
        return historialTarjetaList;
    }

    public void setHistorialTarjetaList(List<HistorialTarjeta> historialTarjetaList) {
        this.historialTarjetaList = historialTarjetaList;
    }

    @OneToMany(mappedBy = "pastorOficiante")
    public List<Ceremonia> getCeremoniaList() {
        return ceremoniaList;
    }

    public void setCeremoniaList(List<Ceremonia> ceremoniaList) {
        this.ceremoniaList = ceremoniaList;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pastor")
    public List<Trayectoria> getTrayectoriaList() {
        return trayectoria;
    }

    public void setTrayectoriaList(List<Trayectoria> trayectoriaList) {
        this.trayectoria = trayectoriaList;
    }

}
