
package com.ipuc.base.membresia;

import com.ipuc.base.congregacion.Congregacion;
import com.ipuc.base.creyente.Creyente;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "membresia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Membresia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idMembresia;

    private String numFolio;

    private Date fechaIngreso;

    private Date fechaTranslado;

    private Congregacion congregacion;

    private Creyente creyente;

    private List<Membresia> membresiaList;

    private Membresia padreMembresia;

    @Id
    @Column(name = "id_membresia")
    @GeneratedValue
    public String getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(String idMembresia) {
        this.idMembresia = idMembresia;
    }

    @NotNull
    @Column(name = "num_folio")
    public String getNumFolio() {
        return numFolio;
    }

    public void setNumFolio(String numFolio) {
        this.numFolio = numFolio;
    }

    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Column(name = "fecha_translado")
    @Temporal(TemporalType.DATE)
    public Date getFechaTranslado() {
        return fechaTranslado;
    }

    public void setFechaTranslado(Date fechaTranslado) {
        this.fechaTranslado = fechaTranslado;
    }

    @JoinColumn(name = "congregacion", referencedColumnName = "cod_congregacion")
    @ManyToOne(optional = false)
    public Congregacion getCongregacion() {
        return congregacion;
    }

    public void setCongregacion(Congregacion congregacion) {
        this.congregacion = congregacion;
    }

    @JoinColumn(name = "creyente", referencedColumnName = "num_identificacion")
    @ManyToOne(optional = false)
    public Creyente getCreyente() {
        return creyente;
    }

    public void setCreyente(Creyente creyente) {
        this.creyente = creyente;
    }

    @OneToMany(mappedBy = "padreMembresia")
    public List<Membresia> getMembresiaList() {
        return membresiaList;
    }

    public void setMembresiaList(List<Membresia> membresiaList) {
        this.membresiaList = membresiaList;
    }

    @JoinColumn(name = "padre_membresia", referencedColumnName = "id_membresia")
    @ManyToOne
    public Membresia getPadreMembresia() {
        return padreMembresia;
    }

    public void setPadreMembresia(Membresia padreMembresia) {
        this.padreMembresia = padreMembresia;
    }

}
