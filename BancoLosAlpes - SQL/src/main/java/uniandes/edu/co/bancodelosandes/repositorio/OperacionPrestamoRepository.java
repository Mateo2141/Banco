package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.bancodelosandes.modelo.OPeracionPrestamo;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.modelo.Prestamo;
import uniandes.edu.co.bancodelosandes.modelo.TipoOperacionPrestamo;

public interface OperacionPrestamoRepository extends JpaRepository<OPeracionPrestamo, Long> {
    @Query(value = "SELECT * FROM operacionesPrestamo", nativeQuery = true)
    Collection<OPeracionPrestamo> darOperacionesPrestamo();

    @Query(value = "SELECT * FROM operacionesPrestamo WHERE id = :id", nativeQuery = true)
    OPeracionPrestamo darOperacionPrestamo(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacionesPrestamo (id, TIPO_OPERACION, prestamo, OPERACION_BANCARIA) VALUES (:id, :tipoOperacion, :prestamo, :operacionBancaria)", nativeQuery = true)
    void insertarOperacionPrestamo(@Param("id") Long id, @Param("tipoOperacion") TipoOperacionPrestamo tipoOperacion,
            @Param("prestamo") Prestamo prestamo, @Param("operacionBancaria") OperacionBancaria operacionBancaria);

    @Modifying
    @Transactional
    @Query(value = "UPDATE operacionesPrestamo SET TIPO_OPERACION = :tipoOperacion, prestamo = :prestamo, OPERACION_BANCARIA = :operacionBancaria WHERE id = :id", nativeQuery = true)
    void actualizarOperacionPrestamo(@Param("tipoOperacion") TipoOperacionPrestamo tipoOperacion,
            @Param("prestamo") Prestamo prestamo, @Param("operacionBancaria") OperacionBancaria operacionBancaria,
            @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operacionesPrestamo WHERE id = :id", nativeQuery = true)
    void eliminarOperacionPrestamo(@Param("id") Long id);
}