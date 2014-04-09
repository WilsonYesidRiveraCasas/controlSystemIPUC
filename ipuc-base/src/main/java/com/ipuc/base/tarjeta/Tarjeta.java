
package com.ipuc.base.tarjeta;

import com.ipuc.base.historialTarjeta.HistorialTarjeta;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "tarjeta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idTarjeta;

    private String nombreTarjeta;

    private String descripcion;

    private List<HistorialTarjeta> historialTarjetaList;

    @Id
    @Column(name = "id_tarjeta")
    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    @Column(name = "nombre_tarjeta")
    @NotNull
    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tarjeta() {
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjeta")
    public List<HistorialTarjeta> getHistorialTarjetaList() {
        return historialTarjetaList;
    }

    public void setHistorialTarjetaList(List<HistorialTarjeta> historialTarjetaList) {
        this.historialTarjetaList = historialTarjetaList;
    }

}
