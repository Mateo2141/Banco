package uniandes.edu.co.bancodelosandes.modelo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

import java.util.Date;

@Document(collection="operacionesBancarias")
@ToString
public class OperacionBancaria {

    @Id
    private String id;
    private Date fecha;
    private TipoOperacionCuenta tipo;
    private Float monto;

    @DBRef
    private Cuenta cuenta;

    @DBRef
    private Cuenta cuentaDestino;  


    @DBRef
    private PuntoAtencion puntoAtencion;

    public OperacionBancaria(String id, Date fecha, TipoOperacionCuenta tipo, Float monto, Cuenta cuenta, Cuenta cuentaDestino,PuntoAtencion puntoAtencion) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.monto = monto;
        this.cuenta = cuenta;
        this.cuentaDestino = cuentaDestino;
        this.puntoAtencion = puntoAtencion;
    }

    public OperacionBancaria() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoOperacionCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoOperacionCuenta tipo) {
        this.tipo = tipo;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public PuntoAtencion getPuntoAtencion() {
        return puntoAtencion;
    }

    public void setPuntoAtencion(PuntoAtencion puntoAtencion) {
        this.puntoAtencion = puntoAtencion;
    }
}