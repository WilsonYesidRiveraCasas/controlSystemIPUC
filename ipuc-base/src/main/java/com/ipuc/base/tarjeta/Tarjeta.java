
package com.ipuc.base.tarjeta;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "tarjeta")
public class Tarjeta implements Serializable {

    private String codTarjeta;

    private String nombre;

    private int orden;

    public Tarjeta() {
    }

    public Tarjeta(String codTarjeta) {
        this.codTarjeta = codTarjeta;
    }

    public Tarjeta(String codTarjeta, String nombre, int orden) {
        this.codTarjeta = codTarjeta;
        this.nombre = nombre;
        this.orden = orden;
    }

    @Id
    @NotNull
    @Column(name = "cod_tarjeta")
    public String getCodTarjeta() {
        return codTarjeta;
    }

    public void setCodTarjeta(String codTarjeta) {
        this.codTarjeta = codTarjeta;
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

    @NotNull
    @Column(name = "orden")
    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
    
}
