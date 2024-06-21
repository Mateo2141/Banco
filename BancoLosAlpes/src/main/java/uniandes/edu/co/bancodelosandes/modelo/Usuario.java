package uniandes.edu.co.bancodelosandes.modelo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;


@Document(collection="usuarios")
@ToString
public class Usuario {

    @Id
    private String numIdentificacion;
    private String contrasena;
    private String nombre;
    private String apellido;

    public Usuario(String numIdentificacion, String contrasena, String nombre, String apellido) {
        this.numIdentificacion = numIdentificacion;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario() {;}

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}

