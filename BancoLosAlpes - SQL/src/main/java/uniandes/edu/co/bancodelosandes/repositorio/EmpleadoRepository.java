package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.Empleado;
import uniandes.edu.co.bancodelosandes.modelo.Oficina;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
        @Query(value = "SELECT * FROM empleados", nativeQuery = true)
        Collection<Empleado> darEmpleados();

        @Query(value = "SELECT * FROM empleados WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
        Empleado darEmpleado(@Param("numIdentificacion") String numIdentificacion);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO empleados (TIPO_EMPLEADO, NUM_IDENTIFICACION, OFICINA) VALUES (:tipoEmpleado, :numIdentificacion, :oficina)", nativeQuery = true)
        void agregarEmpleado(@Param("tipoEmpleado") String tipoEmpleado,
                        @Param("numIdentificacion") String numIdentificacion,
                        @Param("oficina") String oficina);

        @Modifying
        @Transactional
        @Query(value = "UPDATE empleados SET TIPO_EMPLEADO = :tipoEmpleado, oficina = :oficina WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
        void actualizarEmpleado(@Param("tipoEmpleado") String tipoEmpleado, @Param("oficina") Oficina oficina,
                        @Param("numIdentificacion") String numIdentificacion);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM empleados WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
        void eliminarEmpleado(@Param("numIdentificacion") String numIdentificacion);
}
