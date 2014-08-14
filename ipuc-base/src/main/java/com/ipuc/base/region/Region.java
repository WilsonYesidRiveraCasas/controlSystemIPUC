
package com.ipuc.base.region;

import com.ipuc.base.pais.Pais;
import com.ipuc.base.municipio.Municipio;
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
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "region")
public class Region implements Serializable {

    private int idRegion;

    private String nombre;

    private Pais pais;

    private List<Municipio> municipios;

    public Region() {
    }

    public Region(int idRegion) {
        this.idRegion = idRegion;
    }

    public Region(int idRegion, String nombre) {
        this.idRegion = idRegion;
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id_region")
    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
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

    @JoinColumn(name = "pais", referencedColumnName = "codigo_pais")
    @ManyToOne(optional = false)
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    
}
