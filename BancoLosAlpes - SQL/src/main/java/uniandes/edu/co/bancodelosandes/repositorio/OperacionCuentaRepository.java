package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.modelo.OperacionCuenta;
import uniandes.edu.co.bancodelosandes.modelo.TipoOperacionCuenta;
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;

public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta, Long> {
    @Query(value = "SELECT * FROM operacionesCuenta", nativeQuery = true)
    Collection<OperacionCuenta> darOperacionesCuenta();

    @Query(value = "SELECT * FROM operacionesCuenta WHERE id = :id", nativeQuery = true)
    OperacionCuenta darOperacionCuenta(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacionesCuenta (id, TIPO_OPERACION_CUENTA, NUM_CUENTA, OPERACION_BANCARIA) VALUES (:id, :tipoOperacionCuenta, :numCuenta, :operacionBancaria)", nativeQuery = true)
    void insertarOperacionCuenta(@Param("id") Long id,
            @Param("tipoOperacionCuenta") TipoOperacionCuenta tipoOperacionCuenta, @Param("numCuenta") Cuenta numCuenta,
            @Param("operacionBancaria") OperacionBancaria operacionBancaria);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacionesCuenta (id, TIPO_OPERACION_CUENTA, NUM_CUENTA, OPERACION_BANCARIA) VALUES (:id, :tipoOperacionCuenta, :numCuenta, :operacionBancaria)", nativeQuery = true)
    void insertarOperacionCuenta2(@Param("id") Long id, @Param("tipoOperacionCuenta") String tipoOperacionCuenta, @Param("numCuenta") Long numCuenta, @Param("operacionBancaria") Float operacionBancaria);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE operacionesCuenta SET TIPO_OPERACION_CUENTA = :tipoOperacionCuenta, NUM_CUENTA = :numCuenta, OPERACION_BANCARIA = :operacionBancaria WHERE id = :id", nativeQuery = true)
    void actualizarOperacionCuenta(@Param("tipoOperacionCuenta") TipoOperacionCuenta tipoOperacionCuenta, @Param("numCuenta") Cuenta numCuenta, @Param("operacionBancaria") OperacionBancaria operacionBancaria, @Param("id") Long id);

    @Query(value = "SELECT OC.* FROM OPERACIONESCUENTA OC JOIN OPERACIONESBANCARIAS OB ON OC.OPERACION_BANCARIA = OB.ID WHERE OC.NUM_CUENTA = :numeroCuenta AND OB.FECHA BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    Collection<OperacionCuenta> findOperacionesPorCuentaYMes(@Param("numeroCuenta") String numCuenta,
            @Param("fechaInicio") java.sql.Date fechaInicio, @Param("fechaFin") java.sql.Date fechaFin);

}
