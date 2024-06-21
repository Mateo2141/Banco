package uniandes.edu.co.bancodelosandes.modelo;
import java.io.Serializable;
import jakarta.persistence.Embeddable;


@Embeddable
public class UsuarioPK implements Serializable {
    private TipoIdentificacion tipoIdentificacion;
    private Integer numeroIdentificacion;

    public UsuarioPK()
    {
        super();
    }

    public UsuarioPK(TipoIdentificacion tipoIdentificacion, Integer numeroIdentificacion)
    {
        super();
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public Integer getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void setNumeroIdentificacion(Integer numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
}
