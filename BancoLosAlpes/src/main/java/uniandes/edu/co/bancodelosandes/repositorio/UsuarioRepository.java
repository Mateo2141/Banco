package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.bancodelosandes.modelo.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Query("{numIdentificacion: ?0}")
    Usuario buscarPorId(String id);

    @Query("{nombre: ?0}")
    List<Usuario> buscarPorNombre(String nombre);
}