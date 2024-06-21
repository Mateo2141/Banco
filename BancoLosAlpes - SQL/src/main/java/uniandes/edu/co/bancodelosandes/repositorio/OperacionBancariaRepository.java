package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;

public interface OperacionBancariaRepository extends JpaRepository<OperacionBancaria, Long> {
    @Query(value = "SELECT * FROM operacionesBancarias", nativeQuery = true)
    Collection<OperacionBancaria> darOperacionesBancarias();

    @Query(value = "SELECT * FROM operacionesBancarias WHERE id = :id", nativeQuery = true)
    OperacionBancaria darOperacionBancaria(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacionesBancarias (id, monto, fecha, hora, UBICACION_GEOGRAFICA) VALUES (:id, :monto, :fecha, :hora, :ubicacionGeografica)", nativeQuery = true)
    void insertarOperacionBancaria(@Param("id") Long id, @Param("monto") Float monto,
            @Param("fecha") java.sql.Date fecha, @Param("hora") java.sql.Time hora,
            @Param("ubicacionGeografica") PuntoAtencion ubicacionGeografica);

    @Modifying
    @Transactional
    @Query(value = "UPDATE operacionesBancarias SET monto = :monto, fecha = :fecha, hora = :hora, UBICACION_GEOGRAFICA = :ubicacionGeografica WHERE id = :id", nativeQuery = true)
    void actualizarOperacionBancaria(@Param("monto") Float monto, @Param("fecha") java.sql.Date fecha,
            @Param("hora") java.sql.Time hora, @Param("ubicacionGeografica") PuntoAtencion ubicacionGeografica,
            @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operacionesBancarias WHERE id = :id", nativeQuery = true)
    void eliminarOperacionBancaria(@Param("id") Long id);
}
