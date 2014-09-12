
package com.ipuc.base.persona;

import com.ipuc.base.historialTarjeta.HistorialTarjeta;
import com.ipuc.base.trayectoria.Trayectoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;

/**
 *
 * @author wilson-rivera
 */
@TypeDef(
    name = "encryptedPassword",
    typeClass = EncryptedStringType.class,
    parameters = {
        @Parameter(name = "encryptorRegisteredName",
        value = "jasyptEncryptor")
    })
@Entity
@Table(name = "pastor")
public class Pastor implements Serializable {
    
    
    private String numeroIdentificacion;
    
    private Date fechaNombramiento;
    
    private String roles;
    
    private String password;
    
    private String estado;
    
    private List<Trayectoria> trayectoriaCongregacion;

    private Persona persona;
    
    private List<HistorialTarjeta> tarjetas;
    
    public static final String ROL_PASTOR = "PASTOR";
    
    public static final String ROL_DIRECTIVO = "DIRECTIVO";
    
    public static final String ROL_CONSISTORIO = "CONSISTORIO";

    @Id
    @NotNull
    @Column(name = "numero_identificacion")
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    @NotNull
    @Column(name = "fecha_nombramiento")
    @Temporal(TemporalType.DATE)
    public Date getFechaNombramiento() {
        return fechaNombramiento;
    }

    public void setFechaNombramiento(Date fechaNombramiento) {
        this.fechaNombramiento = fechaNombramiento;
    }

    @NotNull
    @Column(name = "roles")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String rol) {
        if(!rol.equals(Pastor.ROL_PASTOR)) {
            if(rol.equals(Pastor.ROL_CONSISTORIO)) {
                this.roles = Pastor.ROL_PASTOR + "," + Pastor.ROL_DIRECTIVO + "," + Pastor.ROL_CONSISTORIO;
            } else {
                this.roles = Pastor.ROL_PASTOR + "," + Pastor.ROL_DIRECTIVO;
            }
        } else {
            this.roles = rol;
        }
    }

    @NotNull
    @Column(name = "password")
    @Type(type = "encryptedPassword")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pastor")
    public List<Trayectoria> getTrayectoriaCongregacion() {
        return trayectoriaCongregacion;
    }

    public void setTrayectoriaCongregacion(List<Trayectoria> trayectoriaCongregacion) {
        this.trayectoriaCongregacion = trayectoriaCongregacion;
    }

    @JoinColumn(name = "numero_identificacion", referencedColumnName = "numero_identificacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pastor")
    public List<HistorialTarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<HistorialTarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    public String nombreApellido() {
        return this.getPersona().getPrimerNombre() + " " + this.getPersona().getPrimerApellido();
    }
    
    public boolean esDirectivo() {
        return roles.contains(ROL_DIRECTIVO);
    }
    
    public boolean esConsistorio() {
        return roles.contains(ROL_CONSISTORIO);
    }
    
    public String rolMasImportante() {
        String [] separatedRoles = roles.split(",");
        
        switch(separatedRoles.length) {
            case 1 : return ROL_PASTOR;
            case 2 : return ROL_DIRECTIVO;
            case 3 : return ROL_CONSISTORIO;
            default : return "UNDEFINED";
        }
    }
}
