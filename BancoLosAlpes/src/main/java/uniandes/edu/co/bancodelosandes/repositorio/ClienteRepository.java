package uniandes.edu.co.bancodelosandes.repositorio;
import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.bancodelosandes.modelo.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, Integer> {
}
