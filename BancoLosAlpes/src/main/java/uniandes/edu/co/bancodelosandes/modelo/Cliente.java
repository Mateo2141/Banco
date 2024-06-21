package uniandes.edu.co.bancodelosandes.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.ToString;

import java.util.List;

@Document(collection="clientes")
@ToString
public class Cliente {

    @Id
    private String numIdentificacion;
    private TipoPersona tipoPersona;

    @DBRef
    private Usuario usuario;

    @DBRef
    private List<Cuenta> cuentas;

    @DBRef
    private List<PuntoAtencion> puntosAtencion;

    public Cliente(String numIdentificacion, TipoPersona tipoPersona, Usuario usuario, List<Cuenta> cuentas, List<PuntoAtencion> puntosAtencion) {
        this.numIdentificacion = numIdentificacion;
        this.tipoPersona = tipoPersona;
        this.usuario = usuario;
        this.cuentas = cuentas;
        this.puntosAtencion = puntosAtencion;
    }

    public Cliente() {}

    // Getters y Setters
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<PuntoAtencion> getPuntosAtencion() {
        return puntosAtencion;
    }

    public void setPuntosAtencion(List<PuntoAtencion> puntosAtencion) {
        this.puntosAtencion = puntosAtencion;
    }
}