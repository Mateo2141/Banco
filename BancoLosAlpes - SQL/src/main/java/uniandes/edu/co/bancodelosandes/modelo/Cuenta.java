package uniandes.edu.co.bancodelosandes.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="CUENTAS")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numCuenta;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;
    
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta;
    private Float saldo;
    private Date fechaUtimaTransaccion;
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "numIdentificacion", referencedColumnName = "numIdentificacion")
    private Cliente numIdentificacion;

    public Cuenta()
    {;}

    public Cuenta(TipoCuenta tipoCuenta, EstadoCuenta estadoCuenta, Float saldo, Date fechaUtimaTransaccion, Date fechaCreacion, Cliente cliente) {
        this.tipoCuenta = tipoCuenta;
        this.estadoCuenta = estadoCuenta;
        this.saldo = saldo;
        this.fechaUtimaTransaccion = fechaUtimaTransaccion;
        this.fechaCreacion = fechaCreacion;
        this.numIdentificacion = cliente;
    }

    public Long getNumCuenta() {
        return numCuenta;
    }

    public Cliente getNumIdentificacion() {
        return numIdentificacion;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    public Float getSaldo() {
        return saldo;
    }

    public Date getFechaUtimaTransaccion() {
        return fechaUtimaTransaccion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Cliente getCliente() {
        return numIdentificacion;
    }

    public void setNumCuenta(Long numCuenta) {
        this.numCuenta = numCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public void setFechaUtimaTransaccion(Date fechaUtimaTransaccion) {
        this.fechaUtimaTransaccion = fechaUtimaTransaccion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setCliente(Cliente cliente) {
        this.numIdentificacion = cliente;
    }

    public void setNumIdentificacion(Cliente numIdentificacion) {
        this.numIdentificacion = numIdentificacion;}

}