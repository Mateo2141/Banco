package uniandes.edu.co.bancodelosandes.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Document(collection="oficinas")
@ToString
public class Oficina {

    @Id
    private String id;
    private String nombre;
    private String direccion;
    private Integer numPuntosAtencion;
    private String horario;
    private List<PuntoAtencion> puntosAtencion = new ArrayList<>();


    public Oficina(String id, String nombre, String direccion, Integer numPuntosAtencion, String horario, List<PuntoAtencion> puntosAtencion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numPuntosAtencion = numPuntosAtencion;
        this.horario = horario;
        this.puntosAtencion = puntosAtencion != null ? puntosAtencion : new ArrayList<>(); 
    }

    public Oficina() {this.puntosAtencion = new ArrayList<>();}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumPuntosAtencion() {
        return numPuntosAtencion;
    }

    public void setNumPuntosAtencion(Integer numPuntosAtencion) {
        this.numPuntosAtencion = numPuntosAtencion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<PuntoAtencion> getPuntosAtencion() {
        return puntosAtencion;
    }

    public void setPuntosAtencion(List<PuntoAtencion> puntosAtencion) {
        this.puntosAtencion = puntosAtencion;
    }
}