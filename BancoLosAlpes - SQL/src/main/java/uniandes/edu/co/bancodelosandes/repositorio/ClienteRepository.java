package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.Cliente;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;
import uniandes.edu.co.bancodelosandes.modelo.TipoPersona;
import uniandes.edu.co.bancodelosandes.modelo.Usuario;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query(value = "SELECT * FROM CLIENTES", nativeQuery = true)
    Collection<Cliente> darClientes();

    @Query(value = "SELECT * FROM CLIENTES WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
    Cliente darCliente(@Param("numIdentificacion") String numIdentificacion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CLIENTES (NUM_IDENTIFICACION, TIPO_PERSONA) VALUES (:numIdentificacion, :tipoPersona)", nativeQuery = true)
    void insertarCliente(@Param("numIdentificacion") String numIdentificacion,
            @Param("tipoPersona") String tipoPersona);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CLIENTES SET TIPO_PERSONA = :tipoPersona, UBICACION_GEOGRAFICA = :ubicacionGeografica, USUARIOS = :usuario WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
    void actualizarCliente(@Param("tipoPersona") TipoPersona tipoPersona,
            @Param("ubicacionGeografica") PuntoAtencion ubicacionGeografica, @Param("usuario") Usuario usuario,
            @Param("numIdentificacion") String numIdentificacion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CLIENTES WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
    void eliminarCliente(@Param("numIdentificacion") String numIdentificacion);

        //CAMBIO IMPORTANTE
        @Query(value = "SELECT c.* FROM clientes c WHERE\r\n" +
                        "(:numIdentificacion IS NULL OR c.NUM_IDENTIFICACION = :numIdentificacion) AND\r\n" +
                        "(:tipoPersona IS NULL OR c.TIPO_PERSONA = :tipoPersona)", nativeQuery = true)
        Collection<Cliente> darClientesPorFiltros(@Param("numIdentificacion") String numIdentificacion,
                        @Param("tipoPersona") String tipoPersona);
}
