package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.modelo.TipoHorario; // Added import statement for TipoHorario

public interface OficinaRepository extends JpaRepository<Oficina, String> {
    @Query(value = "SELECT * FROM oficinas", nativeQuery = true)
    Collection<Oficina> darOficinas();

    @Query(value = "SELECT * FROM oficinas WHERE nombre = :nombre", nativeQuery = true)
    Oficina darOficina(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO oficinas (nombre, NUM_PUNTOS_ATENCION, direccion, horario) VALUES (:nombre, :numPuntosAtencion, :direccion, :horario)", nativeQuery = true)
    void insertarOficina(@Param("nombre") String nombre, @Param("numPuntosAtencion") Integer numPuntosAtencion,
            @Param("direccion") String direccion, @Param("horario") String horario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE oficinas SET NUM_PUNTOS_ATENCION = :numPuntosAtencion, direccion = :direccion, horario = :horario WHERE nombre = :nombre", nativeQuery = true)
    void actualizarOficina(@Param("numPuntosAtencion") Integer numPuntosAtencion, @Param("direccion") String direccion,
            @Param("horario") TipoHorario horario, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM oficinas WHERE nombre = :nombre", nativeQuery = true)
    void eliminarOficina(@Param("nombre") String nombre);
}