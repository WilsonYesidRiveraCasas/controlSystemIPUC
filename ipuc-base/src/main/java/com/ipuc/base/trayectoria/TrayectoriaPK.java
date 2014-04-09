
package com.ipuc.base.trayectoria;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.NotNull;

/**
 *
 * @author wilson-rivera
 */
@Embeddable
public class TrayectoriaPK implements Serializable {
    
    private String numIdentificacion;
    
    private String codCongregacion;

    @NotNull
    @Column(name = "num_identificacion")
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    @NotNull
    @Column(name = "cod_congregacion")
    public String getCodCongregacion() {
        return codCongregacion;
    }

    public void setCodCongregacion(String codCongregacion) {
        this.codCongregacion = codCongregacion;
    }

}
