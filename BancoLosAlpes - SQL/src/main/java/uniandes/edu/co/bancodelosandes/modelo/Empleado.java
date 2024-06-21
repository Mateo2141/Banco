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
@Table(name="EMPLEADOS")
public class Empleado {
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numIdentificacion;

    @ManyToOne
    @JoinColumn(name = "oficina", referencedColumnName = "nombre")
    private Oficina oficina;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    public Empleado(){;}

    public Empleado(TipoEmpleado tipoEmpleado, Oficina oficina)
    {
        this.tipoEmpleado = tipoEmpleado;
        this.oficina = oficina;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public Oficina getOficina() {
        return oficina;
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

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

}
