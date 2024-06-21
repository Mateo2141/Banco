package uniandes.edu.co.bancodelosandes.modelo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Document(collection="cuentas")
@ToString
public class Cuenta {

    @Id
    private String numCuenta;
    private TipoCuenta tipo;
    private EstadoCuenta estado;
    private Float saldo;
    private Date fechaUltimaTransaccion;
    private Date fechaCreacion;

    @DBRef
    private Cliente cliente;

    @DBRef
    private List<OperacionBancaria> operaciones;

    public Cuenta(String numCuenta, TipoCuenta tipo, EstadoCuenta estado, Float saldo, Date fechaUltimaTransaccion, Date fechaCreacion, Cliente cliente, List<OperacionBancaria> operaciones) {
        this.numCuenta = numCuenta;
        this.tipo = tipo;
        this.estado = estado;
        this.saldo = saldo;
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.operaciones = operaciones;
    }

    public Cuenta() {}

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Date getFechaUltimaTransaccion() {
        return fechaUltimaTransaccion;
    }

    public void setFechaUltimaTransaccion(Date fechaUltimaTransaccion) {
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OperacionBancaria> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<OperacionBancaria> operaciones) {
        this.operaciones = operaciones;
    }
}