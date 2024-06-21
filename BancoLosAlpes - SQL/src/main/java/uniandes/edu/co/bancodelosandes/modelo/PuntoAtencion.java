package uniandes.edu.co.bancodelosandes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="PUNTOSATENCION")
public class PuntoAtencion {
    @Id
    @Column(name = "UBICACION_GEOGRAFICA")
    private String ubicacion_Geografica;

    private Integer operacionesRealizadas;

    @Enumerated(EnumType.STRING)
    private TipoPuntoAtencion tipo_Punto_Atencion;

    @ManyToOne
    @JoinColumn(name = "oficinaName", referencedColumnName = "nombre")
    private Oficina oficinaName;

    public PuntoAtencion()
    {;}

    public PuntoAtencion(Integer operacionesRealizadas, TipoPuntoAtencion tipoPuntoAtencion, Oficina oficina) {
        this.operacionesRealizadas = operacionesRealizadas;
        this.tipo_Punto_Atencion = tipoPuntoAtencion;
        this.oficinaName = oficina;
    }

    public String getUbicacionGeografica() {
        return ubicacion_Geografica;
    }

    public Integer getOperacionesRealizadas() {
        return operacionesRealizadas;
    }

    public TipoPuntoAtencion getTipoPuntoAtencion() {
        return tipo_Punto_Atencion;
    }

    public Oficina getOficinaName() {
        return oficinaName;
    }

    
    public void setUbicacionGeografica(String ubicacionGeografica) {
        this.ubicacion_Geografica = ubicacionGeografica;
    }

    public void setOperacionesRealizadas(Integer operacionesRealizadas) {
        this.operacionesRealizadas = operacionesRealizadas;
    }

    public void setTipoPuntoAtencion(TipoPuntoAtencion tipoPuntoAtencion) {
        this.tipo_Punto_Atencion = tipoPuntoAtencion;
    }

    public void setOficinaName(Oficina oficina) {
        this.oficinaName = oficina;
    }
}
