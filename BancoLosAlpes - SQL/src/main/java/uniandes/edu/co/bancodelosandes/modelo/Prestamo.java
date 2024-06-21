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
@Table(name = "PRESTAMOS")
public class Prestamo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private TipoPrestamo tipoPrestamo;
    
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estadoPrestamo;
    private Float monto;
    private Float interes;
    private Integer numCuotas;
    private Integer diaPago;
    private Float valorCuota;

    public Prestamo() 
    {;}

    public Prestamo(TipoPrestamo tipoPrestamo, EstadoPrestamo estadoPrestamo, Float monto, Float interes, Integer numCuotas, Integer diaPago, Float valorCuota) {
        this.tipoPrestamo = tipoPrestamo;
        this.estadoPrestamo = estadoPrestamo;
        this.monto = monto;
        this.interes = interes;
        this.numCuotas = numCuotas;
        this.diaPago = diaPago;
        this.valorCuota = valorCuota;
    }

    public Long getId() {
        return id;
    }

    public TipoPrestamo getTipoPrestamo() {
        return tipoPrestamo;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public Float getMonto() {
        return monto;
    }

    public Float getInteres() {
        return interes;
    }

    public Integer getNumCuotas() {
        return numCuotas;
    }

    public Integer getDiaPago() {
        return diaPago;
    }

    public Float getValorCuota() {
        return valorCuota;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoPrestamo(TipoPrestamo tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public void setNumCuotas(Integer numCuotas) {
        this.numCuotas = numCuotas;
    }

    public void setDiaPago(Integer diaPago) {
        this.diaPago = diaPago;
    }

    public void setValorCuota(Float valorCuota) {
        this.valorCuota = valorCuota;
    }

    
}
