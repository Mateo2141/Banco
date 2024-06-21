package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;
import uniandes.edu.co.bancodelosandes.modelo.TipoPuntoAtencion;

public interface PuntoAtencionRepository extends JpaRepository<PuntoAtencion, String> {
    @Query(value = "SELECT * FROM puntosAtencion", nativeQuery = true)
    Collection<PuntoAtencion> darPuntosAtencion();

    @Query(value = "SELECT * FROM puntosAtencion WHERE UBICACION_GEOGRAFICA = :ubicacionGeografica", nativeQuery = true)
    PuntoAtencion darPuntoAtencion(@Param("ubicacionGeografica") String ubicacionGeografica);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO puntosAtencion (UBICACION_GEOGRAFICA, OPERACIONES_REALIZADAS, TIPO_PUNTO_ATENCION, OFICINA_NAME) VALUES ( :ubicacionGeografica, :operacionesRealizadas, :tipoPuntoAtencion, :oficinaName)", nativeQuery = true)
    void insertarPuntoAtencion(@Param("ubicacionGeografica") String ubicacionGeografica,
            @Param("operacionesRealizadas") Integer operacionesRealizadas,
            @Param("tipoPuntoAtencion") String tipoPuntoAtencion, @Param("oficinaName") String oficinaName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE puntosAtencion SET OPERACIONES_REALIZADAS = :operacionesRealizadas, TIPO_PUNTO_ATENCION = :tipoPuntoAtencion, OFICINA_NAME = :oficinaName WHERE ubicacionGeografica = :ubicacionGeografica", nativeQuery = true)
    void actualizarPuntoAtencion(@Param("operacionesRealizadas") Integer operacionesRealizadas,
            @Param("tipoPuntoAtencion") TipoPuntoAtencion tipoPuntoAtencion, @Param("oficinaName") Oficina oficinaName,
            @Param("ubicacionGeografica") String ubicacionGeografica);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM puntosAtencion WHERE UBICACION_GEOGRAFICA = :ubicacionGeografica", nativeQuery = true)
    void eliminarPuntoAtencion(@Param("ubicacionGeografica") String ubicacionGeografica);
}
