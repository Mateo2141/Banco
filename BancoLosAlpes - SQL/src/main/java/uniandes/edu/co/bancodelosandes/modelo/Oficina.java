package uniandes.edu.co.bancodelosandes.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="OFICINAS")
public class Oficina {
    @Id
    @Column(name = "NOMBRE")
    private String nombre;
    private Integer numPuntosAtencion;
    private String direccion;

    @Enumerated(EnumType.STRING)
    private TipoHorario horario;

    public Oficina(){;}

    public Oficina(Integer numPuntosAtencion, String direccion, TipoHorario horario)
    {
        this.numPuntosAtencion = numPuntosAtencion;
        this.direccion = direccion;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumPuntosAtencion() {
        return numPuntosAtencion;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoHorario getHorario() {
        return horario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumPuntosAtencion(Integer numPuntosAtencion) {
        this.numPuntosAtencion = numPuntosAtencion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHorario(TipoHorario horario) {
        this.horario = horario;
    }
}
