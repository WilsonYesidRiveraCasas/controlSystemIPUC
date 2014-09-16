
package com.ipuc.base.distrito;

import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.pais.Pais;
import com.ipuc.base.persona.Pastor;
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
@Table(name = "distrito")
public class Distrito implements Serializable {
    
    private int idDistrito;
    
    private String nombreDistrito;
    
    private Pais pais;
    
    private List<Congregacion> congregaciones;
    
    private List<Pastor> pastores;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_distrito")
    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    @NotNull
    @Length(max = 100)
    @Column(name = "nombre_distrito")
    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    @JoinColumn(name = "pais", referencedColumnName = "codigo_pais")
    @ManyToOne(optional = false)
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distrito")
    public List<Congregacion> getCongregaciones() {
        return congregaciones;
    }

    public void setCongregaciones(List<Congregacion> congregaciones) {
        this.congregaciones = congregaciones;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distrito")
    public List<Pastor> getPastores() {
        return pastores;
    }
    
    public void setPastores(List<Pastor> pastores) {
        this.pastores = pastores;
    }
    
}
