package uniandes.edu.co.bancodelosandes.modelo;

import lombok.ToString;

@ToString
public class PuntoAtencion {

    private String id;
    private String ubicacionGeografica;
    private Integer operacionesRealizadas;
    private TipoPuntoAtencion tipo;

    public PuntoAtencion(String id, String ubicacionGeografica, Integer operacionesRealizadas, TipoPuntoAtencion tipo) {
        this.id = id;
        this.ubicacionGeografica = ubicacionGeografica;
        this.operacionesRealizadas = operacionesRealizadas;
        this.tipo = tipo;
    }

    public PuntoAtencion() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    public void setUbicacionGeografica(String ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

    public Integer getOperacionesRealizadas() {
        return operacionesRealizadas;
    }

    public void setOperacionesRealizadas(Integer operacionesRealizadas) {
        this.operacionesRealizadas = operacionesRealizadas;
    }

    public TipoPuntoAtencion getTipo() {
        return tipo;
    }

    public void setTipo(TipoPuntoAtencion tipo) {
        this.tipo = tipo;
    }
}