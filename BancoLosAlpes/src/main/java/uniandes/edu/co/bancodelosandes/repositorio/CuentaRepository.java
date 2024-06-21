package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.bancodelosandes.modelo.Cliente;
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.EstadoCuenta;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;

@Repository
public interface CuentaRepository extends MongoRepository<Cuenta, String> {

    
    @Query("{numCuenta: ?0}")
    Cuenta buscarPorId(String id);

    @Query("{}")
    List<Cuenta> obtenerTodasLasCuentas();

    @Query("{numIdentificacion: {$regex: ?0, $options: 'i'}}")
    List<Cliente> obtenerTodosClientes(String numIdentificacion);

    @Query("{'cliente.numIdentificacion': ?0}")
    List<Cuenta> buscarPorClienteId(int clienteId);

    @Update("{$set: {estado: ?1}}")
    @Query("{numCuenta: ?0, saldo: 0, estado: 'ACTIVA'}")
    void cerrarCuenta(String numCuenta, EstadoCuenta nuevoEstado);

    @Update("{$set: {estado: ?1}}")
    @Query("{numCuenta: ?0, estado: 'ACTIVA'}")
    void desactivarCuenta(String numCuenta, EstadoCuenta nuevoEstado);

    @Update("{$inc: {saldo: ?1}, $push: {operaciones: ?2}}")
    @Query("{numCuenta: ?0}")
    void actualizarSaldoYRegistrarOperacion(String numCuenta, float monto, OperacionBancaria operacion);
 
    @Update("{$inc: {saldo: ?1}}")
    @Query("{numCuenta: ?0}")
    void actualizarSaldo(String numCuenta, float monto);

    @Aggregation(pipeline = {
        "{ $lookup: { from: 'clientes', localField: 'cliente.$id', foreignField: '_id', as: 'cliente_info' } }",
        "{ $unwind: '$cliente_info' }",
        "{ $match: { 'tipo': ?0, 'saldo': { $gte: ?1, $lte: ?2 }, 'fechaCreacion': { $gte: ?3, $lte: ?4 }, 'fechaUltimaTransaccion': { $gte: ?5, $lte: ?6 }, 'cliente_info._id': ?7 } }"
    })
    List<Cuenta> buscarCuentas(String tipo, float saldoMin, float saldoMax, Date fechaCreacionInicio, Date fechaCreacionFin, Date fechaUltimaTransaccionInicio, Date fechaUltimaTransaccionFin, int clienteId);
    
    @Aggregation(pipeline = {
            "{$match: {'tipo': ?0, 'saldo': {$gte: ?1, $lte: ?2}, 'fechaCreacion': {$gte: ?3, $lte: ?4}, 'fechaUltimaTransaccion': {$gte: ?5, $lte: ?6}, 'cliente.numIdentificacion': ?7}}",
            "{$group: {_id: '$tipo', totalSaldo: {$sum: '$saldo'}, cuentas: {$push: '$$ROOT'}}}"
    })
    List<Cuenta> agruparCuentas(String tipo, float saldoMin, float saldoMax, Date fechaCreacionInicio, Date fechaCreacionFin, Date fechaUltimaTransaccionInicio, Date fechaUltimaTransaccionFin, int clienteId);

    

}

