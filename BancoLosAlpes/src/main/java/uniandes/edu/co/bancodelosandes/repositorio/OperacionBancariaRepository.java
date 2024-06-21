package uniandes.edu.co.bancodelosandes.repositorio;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;

@Repository
public interface OperacionBancariaRepository extends MongoRepository<OperacionBancaria, String> {

    @Query("{_id: ?0}")
    OperacionBancaria buscarPorId(int id);

    @Query("{'cuenta.numCuenta': ?0}")
    List<OperacionBancaria> buscarPorCuentaId(int cuentaId);

    @Query("{ 'cuenta.numCuenta': ?0, 'fecha': { $regex: ?1 } }")
    List<OperacionBancaria> findByCuentaAndMonth(String numCuenta, String mes);
}