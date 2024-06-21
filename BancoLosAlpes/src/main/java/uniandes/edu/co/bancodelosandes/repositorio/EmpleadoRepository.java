package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.bancodelosandes.modelo.Empleado;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, Integer> {

    @Query("{numIdentificacion: ?0}")
    Empleado buscarPorId(int id);

    @Query("{nombre: ?0}")
    List<Empleado> buscarPorNombre(String nombre);

    @Query("{'oficina.id': ?0}")
    List<Empleado> buscarPorOficinaId(int oficinaId);
}
