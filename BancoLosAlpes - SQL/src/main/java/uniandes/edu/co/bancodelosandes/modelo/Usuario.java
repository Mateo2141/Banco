package uniandes.edu.co.bancodelosandes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_IDENTIFICACION")
    private TipoIdentificacion tipoIdentificacion;

    @Id
    @Column(name = "NUM_IDENTIFICACION")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numIdentificacion;

    @Column(name = "CONTRASENA")
    private String contrasena;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String direccion;
    private String email;
    private String telefono;
    private String ciudad;
    private String departamento;
    @Column(name = "CODIGO_POSTAL")
    private Integer codigoPostal;

    public Usuario(TipoIdentificacion tipoIdentificacion, String contrasena, String nombre, String apellido,
            String nacionalidad, String direccion, String email, String telefono, String ciudad, String departamento,
            Integer codigoPostal) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
    }

    public Usuario() {;}

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

}