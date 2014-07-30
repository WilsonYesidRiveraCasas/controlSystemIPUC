
package com.ipuc.base.tipoIdentificacion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.Length;

/**
 *
 * @author wilson-rivera
 */
@Entity
@Table(name = "tipo_identificacion")
public class TipoIdentificacion implements Serializable {

    private String codTipoIdentificacion;

    private String nombre;

    private String descripcion;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(String codTipoIdentificacion) {
        this.codTipoIdentificacion = codTipoIdentificacion;
    }

    public TipoIdentificacion(String codTipoIdentificacion, String nombre) {
        this.codTipoIdentificacion = codTipoIdentificacion;
        this.nombre = nombre;
    }

    @Id
    @NotNull
    @Length(max = 6)
    @Column(name = "cod_tipo_identificacion")
    public String getCodTipoIdentificacion() {
        return codTipoIdentificacion;
    }

    public void setCodTipoIdentificacion(String codTipoIdentificacion) {
        this.codTipoIdentificacion = codTipoIdentificacion;
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

    @Length(max = 100)
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
