package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;

@Repository
public interface PuntoAtencionRepository extends MongoRepository<PuntoAtencion, String> {

    @Query("{_id: ?0}")
    PuntoAtencion buscarPorId(int id);

    @Query("{tipo: ?0}")
    List<PuntoAtencion> buscarPorTipo(String tipo);

    @Query("{'ubicacionGeografica': ?0}")
    List<PuntoAtencion> buscarPorUbicacion(String ubicacion);

    @Query("{_id: ?0}")
    @Update("{$push: {operacionesRealizadas: ?1}}")
    void agregarOperacion(int id, OperacionBancaria operacion);
}