package uniandes.edu.co.bancodelosandes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uniandes.edu.co.bancodelosandes.modelo.Cliente;
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.EstadoCuenta;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.modelo.TipoOperacionCuenta;
import uniandes.edu.co.bancodelosandes.repositorio.CuentaRepository;
import uniandes.edu.co.bancodelosandes.repositorio.OperacionBancariaRepository;
import uniandes.edu.co.bancodelosandes.repositorio.ClienteRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private OperacionBancariaRepository operacionBancariaRepository;


    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Optional<Cuenta> obtenerCuentaPorId(String id) {
        return cuentaRepository.findById(id);
    }

    public void borrarCuenta(String id) {
        cuentaRepository.deleteById(id);
    }

    public OperacionBancaria registrarOperacion(OperacionBancaria operacion) {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(operacion.getCuenta().getNumCuenta());
        if (cuentaOpt.isPresent()) {
            Cuenta cuenta = cuentaOpt.get();
            operacion.setCuenta(cuenta);
            operacionBancariaRepository.save(operacion);
            cuenta.getOperaciones().add(operacion);
            cuentaRepository.save(cuenta);
            return operacion;
        } else {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }
    }

    public Optional<OperacionBancaria> obtenerOperacionPorId(String id) {
        return operacionBancariaRepository.findById(id);
    }

    public void borrarOperacion(String id) {
        operacionBancariaRepository.deleteById(id);
    }

    public Cuenta cambiarEstadoCuenta(String numCuenta, EstadoCuenta nuevoEstado) {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(numCuenta);
        if (!cuentaOpt.isPresent()) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }

        Cuenta cuenta = cuentaOpt.get();

        if (cuenta.getEstado() != EstadoCuenta.ACTIVA) {
            throw new IllegalStateException("Solo se pueden cambiar cuentas activas.");
        }

        if (nuevoEstado == EstadoCuenta.CERRADA && cuenta.getSaldo() != 0) {
            throw new IllegalStateException("Para cerrar la cuenta, el saldo debe estar en cero.");
        }

        cuenta.setEstado(nuevoEstado);
        cuentaRepository.save(cuenta);
        return cuenta;
    }

    public OperacionBancaria consignarDinero(String numCuenta, Float monto) {
        return registrarOperacion(numCuenta, null, monto, TipoOperacionCuenta.CONSIGNACION);
    }

    public OperacionBancaria retirarDinero(String numCuenta, Float monto) {
        return registrarOperacion(numCuenta, null, monto, TipoOperacionCuenta.RETIRO);
    }

    public OperacionBancaria transferirDinero(String cuentaOrigen, String cuentaDestino, Float monto) {
        return registrarOperacion(cuentaOrigen, cuentaDestino, monto, TipoOperacionCuenta.TRANSFERENCIA);
    }

    private OperacionBancaria registrarOperacion(String numCuentaOrigen, String numCuentaDestino, Float monto, TipoOperacionCuenta tipoOperacion) {
        Optional<Cuenta> cuentaOrigenOpt = cuentaRepository.findById(numCuentaOrigen);
        if (!cuentaOrigenOpt.isPresent()) {
            throw new IllegalArgumentException("Cuenta de origen no encontrada");
        }

        Cuenta cuentaOrigen = cuentaOrigenOpt.get();
        OperacionBancaria operacion = new OperacionBancaria();
        operacion.setFecha(new Date());
        operacion.setMonto(monto);
        operacion.setTipo(tipoOperacion);
        operacion.setCuenta(cuentaOrigen);

        if (tipoOperacion == TipoOperacionCuenta.TRANSFERENCIA) {
            Optional<Cuenta> cuentaDestinoOpt = cuentaRepository.findById(numCuentaDestino);
            if (!cuentaDestinoOpt.isPresent()) {
                throw new IllegalArgumentException("Cuenta de destino no encontrada");
            }
            Cuenta cuentaDestino = cuentaDestinoOpt.get();
            operacion.setCuentaDestino(cuentaDestino);

            if (cuentaOrigen.getSaldo() < monto) {
                throw new IllegalArgumentException("Fondos insuficientes en la cuenta de origen");
            }

            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            cuentaRepository.save(cuentaOrigen);
            cuentaRepository.save(cuentaDestino);
        } else if (tipoOperacion == TipoOperacionCuenta.RETIRO) {
            if (cuentaOrigen.getSaldo() < monto) {
                throw new IllegalArgumentException("Fondos insuficientes en la cuenta");
            }
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            cuentaRepository.save(cuentaOrigen);
        } else if (tipoOperacion == TipoOperacionCuenta.CONSIGNACION) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() + monto);
            cuentaRepository.save(cuentaOrigen);
        }

        return operacionBancariaRepository.save(operacion);
    }

    public List<Cuenta> buscarCuentas(String tipo, float saldoMin, float saldoMax, Date fechaCreacionInicio, Date fechaCreacionFin, Date fechaUltimaTransaccionInicio, Date fechaUltimaTransaccionFin, int clienteId) {
        return cuentaRepository.buscarCuentas(tipo, saldoMin, saldoMax, fechaCreacionInicio, fechaCreacionFin, fechaUltimaTransaccionInicio, fechaUltimaTransaccionFin, clienteId);
    }

    public List<Cuenta> agruparCuentas(String tipo, float saldoMin, float saldoMax, Date fechaCreacionInicio, Date fechaCreacionFin, Date fechaUltimaTransaccionInicio, Date fechaUltimaTransaccionFin, int clienteId) {
        return cuentaRepository.agruparCuentas(tipo, saldoMin, saldoMax, fechaCreacionInicio, fechaCreacionFin, fechaUltimaTransaccionInicio, fechaUltimaTransaccionFin, clienteId);
    }
    
    public List<Cliente> obtenerClientes(String numIdentificacion) {
        return cuentaRepository.obtenerTodosClientes(numIdentificacion);
    }

    public List<OperacionBancaria> obtenerOperacionesPorMes(String numCuenta, String mes) {
        return operacionBancariaRepository.findByCuentaAndMonth(numCuenta, mes);
    }
}
