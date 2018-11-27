/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.modelo;

import java.util.Date;
import java.util.Objects;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;

/**
 *
 * @author jefferson
 */
@Entity(noClassnameStored = true,value="usuario")
public class CiudadanoMongo {
    @Id
    private ObjectId id;
    
    @Property("CEDULA")
    @Indexed(options=@IndexOptions(name="CEDULA"),unique = true)
    private String cedula;
    
    @Property("APELLIDO")
    private String apellido;
    
    @Property("NOMBRE")
    private String nombre;
    
    @Property("FECHANACIMIENTO")
    private Date fechaNacimiento;
    
    @Property("CODPROVINCIA")
    private String codprovincia;
    
    @Property("GENERO")
    private String genero;
    
    @Property("ESTADOCIVIL")
    private String estadocivil;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCodprovincia() {
        return codprovincia;
    }

    public void setCodprovincia(String codprovincia) {
        this.codprovincia = codprovincia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CiudadanoMongo other = (CiudadanoMongo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CiudadanoMongo{" + "id=" + id + ", cedula=" + cedula + ", apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", codprovincia=" + codprovincia + ", genero=" + genero + ", estadocivil=" + estadocivil + '}';
    }
    
}
