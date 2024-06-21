package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.EstadoPrestamo;
import uniandes.edu.co.bancodelosandes.modelo.Prestamo;
import uniandes.edu.co.bancodelosandes.modelo.TipoPrestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    @Query(value = "SELECT * FROM prestamos", nativeQuery = true)
    Collection<Prestamo> darPrestamos();

    @Query(value = "SELECT * FROM prestamos WHERE id = :id", nativeQuery = true)
    Prestamo darPrestamo(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO prestamos (id, TIPO_PRESTAMO, ESTADO_PRESTAMO, monto, interes, NUM_CUOTAS, DIA_PAGO, VALOR_CUOTA) VALUES (:id, :tipoPrestamo, :estadoPrestamo, :monto, :interes, :numCuotas, :diaPago, :valorCuota)", nativeQuery = true)
    void insertarPrestamo(@Param("id") Long id, @Param("tipoPrestamo") TipoPrestamo tipoPrestamo,
            @Param("estadoPrestamo") EstadoPrestamo estadoPrestamo, @Param("monto") Float monto,
            @Param("interes") Float interes, @Param("numCuotas") Integer numCuotas, @Param("diaPago") Integer diaPago,
            @Param("valorCuota") Float valorCuota);

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET TIPO_PRESTAMO = :tipoPrestamo, ESTADO_PRESTAMO = :estadoPrestamo, monto = :monto, interes = :interes, NUM_CUOTAS = :numCuotas, DIA_PAGO = :diaPago, VALOR_CUOTA = :valorCuota WHERE id = :id", nativeQuery = true)
    void actualizarPrestamo(@Param("tipoPrestamo") TipoPrestamo tipoPrestamo,
            @Param("estadoPrestamo") EstadoPrestamo estadoPrestamo, @Param("monto") Float monto,
            @Param("interes") Float interes, @Param("numCuotas") Integer numCuotas, @Param("diaPago") Integer diaPago,
            @Param("valorCuota") Float valorCuota, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM prestamos WHERE id = :id", nativeQuery = true)
    void eliminarPrestamo(@Param("id") Long id);
}
