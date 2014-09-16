
package com.ipuc.base.pais;

import com.ipuc.base.distrito.Distrito;
import com.ipuc.base.region.Region;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    private String codigoPais;

    private String nombre;

    private List<Region> regiones;
    
    private List<Distrito> distritos;

    public Pais() {
    }

    public Pais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Pais(String codigoPais, String nombre) {
        this.codigoPais = codigoPais;
        this.nombre = nombre;
    }

    @Id
    @NotNull
    @Length(max = 6)
    @Column(name = "codigo_pais")
    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    @NotNull
    @Length(max = 50)
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    public List<Region> getRegiones() {
        return regiones;
    }
    
    public void setRegiones(List<Region> regiones) {
        this.regiones = regiones;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    public List<Distrito> getDistritos() {
        return distritos;
    }

    public void setDistritos(List<Distrito> distritos) {
        this.distritos = distritos;
    }
    
    
    
}
