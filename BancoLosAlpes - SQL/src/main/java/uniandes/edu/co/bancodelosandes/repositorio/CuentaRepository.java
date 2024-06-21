package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
        @Query(value = "SELECT * FROM cuentas", nativeQuery = true)
        Collection<Cuenta> darCuentas();

        @Query(value = "SELECT * FROM cuentas WHERE NUM_CUENTA = :numCuenta", nativeQuery = true)
        Cuenta darCuenta(@Param("numCuenta") Long numCuenta);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO cuentas (NUM_CUENTA, TIPO_CUENTA, ESTADO_CUENTA, saldo, FECHA_UTIMA_TRANSACCION, FECHA_CREACION, NUM_IDENTIFICACION) VALUES (:numCuenta, :tipoCuenta, :estadoCuenta, :saldo, :fechaUtimaTransaccion, :fechaCreacion, :numIdentificacion)", nativeQuery = true)
        void insertarCuenta(@Param("numCuenta") Long numCuenta, @Param("tipoCuenta") String tipoCuenta,
                        @Param("estadoCuenta") String estadoCuenta, @Param("saldo") Float saldo,
                        @Param("fechaUtimaTransaccion") java.sql.Date fechaUtimaTransaccion,
                        @Param("fechaCreacion") java.sql.Date fechaCreacion,
                        @Param("numIdentificacion") String numIdentificacion);

        @Modifying
        @Transactional
        @Query(value = "UPDATE cuentas SET TIPO_CUENTA = :tipoCuenta, ESTADO_CUENTA = :estadoCuenta, saldo = :saldo, FECHA_UTIMA_TRANSACCION = :fechaUtimaTransaccion, FECHA_CREACION = :fechaCreacion, NUM_IDENTIFICACION = :numIdentificacion WHERE NUM_CUENTA = :numCuenta", nativeQuery = true)
        void actualizarCuenta(@Param("tipoCuenta") String tipoCuenta,
                        @Param("estadoCuenta") String estadoCuenta,
                        @Param("saldo") Float saldo,
                        @Param("fechaUtimaTransaccion") java.sql.Date fechaUtimaTransaccion,
                        @Param("fechaCreacion") java.sql.Date fechaCreacion,
                        @Param("numIdentificacion") String numIdentificacion,
                        @Param("numCuenta") Long numCuenta);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM cuentas WHERE NUM_CUENTA = :numCuenta", nativeQuery = true)
        void eliminarCuenta(@Param("numCuenta") Long numCuenta);

        @Query(value = "SELECT c.* FROM cuentas c WHERE\r\n" +
                        "(:numIdentificacion IS NULL OR c.NUM_IDENTIFICACION = :numIdentificacion) AND\r\n" +
                        "(:tipoCuenta IS NULL OR c.TIPO_CUENTA = :tipoCuenta) AND\r\n" +
                        "(:estadoCuenta IS NULL OR c.ESTADO_CUENTA = :estadoCuenta) AND\r\n" +
                        "(:saldo IS NULL OR c.saldo >= :saldo) AND\r\n" +
                        "(:fechaCreacion IS NULL OR c.FECHA_CREACION >= :fechaCreacion) AND\r\n" +
                        "(:fechaUtimaTransaccion IS NULL OR c.FECHA_UTIMA_TRANSACCION <= :fechaUtimaTransaccion)", nativeQuery = true)
        Collection<Cuenta> darCuentasPorFiltros(@Param("numIdentificacion") String numIdentificacion,
                        @Param("tipoCuenta") String tipoCuenta,
                        @Param("estadoCuenta") String estadoCuenta,
                        @Param("saldo") Float saldo,
                        @Param("fechaCreacion") java.sql.Date fechaCreacion,
                        @Param("fechaUtimaTransaccion") java.sql.Date fechaUtimaTransaccion);

}
