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
@Table(name = "OPERACIONESCUENTA")
public class OperacionCuenta {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoOperacionCuenta tipoOperacionCuenta;

    @ManyToOne
    @JoinColumn(name = "numCuenta", referencedColumnName = "numCuenta")
    private Cuenta numCuenta;

    @ManyToOne
    @JoinColumn(name = "operacionBancaria", referencedColumnName = "id")
    private OperacionBancaria operacionBancaria;

    public OperacionCuenta()
    {;}

    public OperacionCuenta(TipoOperacionCuenta tipoOperacionCuenta, Cuenta cuenta, OperacionBancaria operacionBancaria) {
        this.tipoOperacionCuenta = tipoOperacionCuenta;
        this.numCuenta = cuenta;
        this.operacionBancaria = operacionBancaria;
    }

    public Long getId() {
        return id;
    }

    public TipoOperacionCuenta getTipoOperacionCuenta() {
        return tipoOperacionCuenta;
    }

    public Cuenta getNumCuenta() {
        return numCuenta;
    }

    public OperacionBancaria getOperacionBancaria() {
        return operacionBancaria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoOperacionCuenta(TipoOperacionCuenta tipoOperacionCuenta) {
        this.tipoOperacionCuenta = tipoOperacionCuenta;
    }

    public void setNumCuenta(Cuenta cuenta) {
        this.numCuenta = cuenta;
    }

    public void setOperacionBancaria(OperacionBancaria operacionBancaria) {
        this.operacionBancaria = operacionBancaria;
    }
}
