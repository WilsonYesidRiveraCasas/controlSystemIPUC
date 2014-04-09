
package com.ipuc.base.congregacion;

import com.ipuc.base.membresia.Membresia;
import com.ipuc.base.trayectoria.Trayectoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "congregacion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Congregacion implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "congregacion")
    private List<Membresia> membresiaList;


    private static final long serialVersionUID = 1L;

    private String codCongregacion;

    private String nombre;

    private Date fechaApertura;

    private String direccion;

    private String telefono;

    private String distrito;

    private String municipio;

    private String departamento;

    private List<Trayectoria> trayectoriaList;

    @Id
    @Column(name = "cod_congregacion")
    public String getCodCongregacion() {
        return codCongregacion;
    }

    public void setCodCongregacion(String codCongregacion) {
        this.codCongregacion = codCongregacion;
    }

    @Column(name = "nombre")
    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "fecha_apertura")
    @NotNull
    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Column(name = "direccion")
    @NotNull
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "distrito")
    @NotNull
    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @Column(name = "municipio")
    @NotNull
    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Column(name = "departamento")
    @NotNull
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Congregacion() {
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "congregacion")
    public List<Trayectoria> getTrayectoriaList() {
        return trayectoriaList;
    }

    public void setTrayectoriaList(List<Trayectoria> trayectoriaList) {
        this.trayectoriaList = trayectoriaList;
    }

    @XmlTransient
    public List<Membresia> getMembresiaList() {
        return membresiaList;
    }

    public void setMembresiaList(List<Membresia> membresiaList) {
        this.membresiaList = membresiaList;
    }

}
