
package com.ipuc.base.persona;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numIdentificacion;

    private String tipoIdentificacion;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String sexo;

    private Date fechaNacimiento;

    private String lugarNacimiento;

    private String telefono;

    private String estadoCivil;

    private String foto;

    private List<Persona> hijosDeMadre;

    private Persona madre;

    private List<Persona> hijosDePadre;

    private Persona padre;

    @Id
    @Column(name = "num_identificacion")
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    @Column(name = "tipo_identificacion")
    @NotNull
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @Column(name = "primer_nombre")
    @NotNull
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    @Column(name = "segundo_nombre")
    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Column(name = "primer_apellido")
    @NotNull
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Column(name = "segundo_apellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Column(name = "sexo")
    @Length(max = 1)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "fecha_nacimiento")
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "lugar_nacimiento")
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "estado_civil")
    @NotNull
    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Column(name = "foto")
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @JoinColumn(name = "madre", referencedColumnName = "num_identificacion")
    @ManyToOne
    public Persona getMadre() {
        return madre;
    }

    public void setMadre(Persona madre) {
        this.madre = madre;
    }

    @JoinColumn(name = "padre", referencedColumnName = "num_identificacion")
    @ManyToOne
    public Persona getPadre() {
        return padre;
    }

    public void setPadre(Persona padre) {
        this.padre = padre;
    }

    @OneToMany(mappedBy = "madre")
    public List<Persona> getHijosDeMadre() {
        return hijosDeMadre;
    }

    public void setHijosDeMadre(List<Persona> hijosDeMadre) {
        this.hijosDeMadre = hijosDeMadre;
    }

    @OneToMany(mappedBy = "padre")
    public List<Persona> getHijosDePadre() {
        return hijosDePadre;
    }

    public void setHijosDePadre(List<Persona> hijosDePadre) {
        this.hijosDePadre = hijosDePadre;
    }

}
