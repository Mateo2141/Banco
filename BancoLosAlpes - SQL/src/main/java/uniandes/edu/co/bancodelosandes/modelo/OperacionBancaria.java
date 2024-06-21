package uniandes.edu.co.bancodelosandes.modelo;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OPERACIONESBANCARIAS")
public class OperacionBancaria {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float monto;
    private Date fecha;
    private Time hora;

    @ManyToOne
    @JoinColumn(name = "ubicacionGeografica", referencedColumnName = "ubicacion_Geografica")
    private PuntoAtencion ubicacionGeografica;

    public OperacionBancaria()
    {;}

    public OperacionBancaria(Float monto, Date fecha, Time hora, PuntoAtencion ubicacionGeografica) {
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacionGeografica = ubicacionGeografica;
    }

    public Long getId() {
        return id;
    }

    public Float getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public PuntoAtencion getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setUbicacionGeografica(PuntoAtencion ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

}
