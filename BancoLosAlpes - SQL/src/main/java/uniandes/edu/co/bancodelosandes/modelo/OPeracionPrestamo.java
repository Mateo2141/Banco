package uniandes.edu.co.bancodelosandes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OPERACIONESPRESTAMO")
public class OPeracionPrestamo {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoOperacionPrestamo tipoOperacion;

    @ManyToOne
    @JoinColumn(name = "prestamo", referencedColumnName = "id")
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "operacionBancaria", referencedColumnName = "id")
    private OperacionBancaria operacionBancaria;

    public OPeracionPrestamo()
    {;}

    public OPeracionPrestamo(TipoOperacionPrestamo tipoOperacion, Prestamo prestamo, OperacionBancaria operacionBancaria) {
        this.tipoOperacion = tipoOperacion;
        this.prestamo = prestamo;
        this.operacionBancaria = operacionBancaria;
    }

    public Long getId() {
        return id;
    }

    public TipoOperacionPrestamo getTipoOperacion() {
        return tipoOperacion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public OperacionBancaria getOperacionBancaria() {
        return operacionBancaria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoOperacion(TipoOperacionPrestamo tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public void setOperacionBancaria(OperacionBancaria operacionBancaria) {
        this.operacionBancaria = operacionBancaria;
    }

}
