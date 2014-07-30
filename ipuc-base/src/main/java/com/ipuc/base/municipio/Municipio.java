
package com.ipuc.base.municipio;

import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.region.Region;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.Length;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "municipio")
public class Municipio implements Serializable {

    private int idMunicipio;

    private String nombre;

    private Region region;

    private List<Congregacion> congregaciones;

    public Municipio() {
    }

    public Municipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Municipio(int idMunicipio, String nombre) {
        this.idMunicipio = idMunicipio;
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id_municipio")
    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
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

    @JoinColumn(name = "region", referencedColumnName = "id_region")
    @ManyToOne(optional = false)
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio")
    public List<Congregacion> getCongregaciones() {
        return congregaciones;
    }

    public void setCongregaciones(List<Congregacion> congregaciones) {
        this.congregaciones = congregaciones;
    }
    
}
