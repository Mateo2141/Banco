package uniandes.edu.co.bancodelosandes.modelo;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class EmpleadoPK implements Serializable{
    @ManyToOne
    @JoinColumn(name = "numid", referencedColumnName = "numIdentificacion")
    private Usuario numid;
    @ManyToOne
    @JoinColumn(name = "tipoid", referencedColumnName = "TipoIdentificacion")
    private TipoIdentificacion tipoid;

    public EmpleadoPK()
    {
        super();
    }

    public EmpleadoPK(Usuario numid, TipoIdentificacion tipoid)
    {
        super();
        this.numid = numid;
        this.tipoid = tipoid;
    }

    public Usuario getNumid() {
        return numid;
    }

    public TipoIdentificacion getTipoid() {
        return tipoid;
    }

    public void setNumid(Usuario numid) {
        this.numid = numid;
    }

    public void setTipoid(TipoIdentificacion tipoid) {
        this.tipoid = tipoid;
    }
}
