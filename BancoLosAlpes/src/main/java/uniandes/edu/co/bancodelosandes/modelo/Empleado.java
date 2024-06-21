package uniandes.edu.co.bancodelosandes.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection="empleados")
@ToString
public class Empleado {

    @Id
    private String id;
    private TipoEmpleado tipoEmpleado;

    @DBRef
    private Usuario usuario;

    @DBRef
    private Oficina oficina;

    public Empleado(String id, TipoEmpleado tipoEmpleado, Usuario usuario, Oficina oficina) {
        this.id = id;
        this.tipoEmpleado = tipoEmpleado;
        this.usuario = usuario;
        this.oficina = oficina;
    }

    public Empleado() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
}