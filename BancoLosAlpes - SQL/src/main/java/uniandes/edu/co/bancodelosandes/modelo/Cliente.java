package uniandes.edu.co.bancodelosandes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name="CLIENTES")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numIdentificacion;

    @Enumerated(EnumType.STRING)
    private TipoPersona tipoPersona;

    @ManyToOne
    @JoinColumn(name = "ubicacionGeografica", referencedColumnName = "ubicacion_Geografica")
    private PuntoAtencion ubicacionGeografica;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    public Cliente()
    {;}

    public Cliente(TipoPersona tipoPersona, PuntoAtencion ubigeografica)
    {
        this.tipoPersona = tipoPersona;
        this.ubicacionGeografica = ubigeografica;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public PuntoAtencion getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTipoPersona(TipoPersona persona) {
        this.tipoPersona = persona;
    }

    public void setUbicacionGeografica(PuntoAtencion ubigeografica) {
        this.ubicacionGeografica = ubigeografica;
    }
    
    
    

}
