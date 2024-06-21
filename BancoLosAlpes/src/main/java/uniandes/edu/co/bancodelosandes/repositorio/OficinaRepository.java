package uniandes.edu.co.bancodelosandes.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;

import java.util.List;

@Repository
public interface OficinaRepository extends MongoRepository<Oficina, String> {

    @Query("{_id: ?0}")
    Oficina buscarPorId(String id);

    @Query("{nombre: {$regex: ?0, $options: 'i'}}")
    List<Oficina> buscarPorNombre(String nombre);

    @Query("{_id: ?0, 'puntosAtencion._id': ?1}")
    @Update("{$set: {'puntosAtencion.$': ?2}}")
    void actualizarPuntoAtencion(String idOficina, int idPuntoAtencion, PuntoAtencion puntoAtencion);

    @Query("{_id: ?0}")
    @Update("{$push: {puntosAtencion: ?1}}")
    void agregarPuntoAtencion(String idOficina, PuntoAtencion puntoAtencion);

    @Query("{_id: ?0}")
    @Update("{$pull: {puntosAtencion: {_id: ?1}}}")
    void borrarPuntoAtencion(String idOficina, int idPuntoAtencion);
}
