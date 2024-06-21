package uniandes.edu.co.bancodelosandes.servicios;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.modelo.OperacionCuenta;
import uniandes.edu.co.bancodelosandes.modelo.TipoOperacionCuenta;
import uniandes.edu.co.bancodelosandes.repositorio.CuentaRepository;
import uniandes.edu.co.bancodelosandes.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import java.lang.Thread;
import java.sql.Date;


@Service
public class OperacionesCuentaServicio {
    private OperacionCuentaRepository operacionCuentaRepository;
    private CuentaRepository cuentaRepository;


    public OperacionesCuentaServicio(OperacionCuentaRepository operacionCuentaRepository) {
        this.operacionCuentaRepository = operacionCuentaRepository;
    }


    private static final Logger logger = LoggerFactory.getLogger(OperacionesCuentaServicio.class);

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, readOnly = true)
    public Collection<OperacionCuenta> darOperacionesCuentaUltimoMesSerial(String idCuenta) {
        try {
            Thread.sleep(5000);
            LocalDate now = LocalDate.now();
            Date startDate = Date.valueOf(now.minusMonths(1));
            Date endDate = Date.valueOf(now);
            return operacionCuentaRepository.findOperacionesPorCuentaYMes(idCuenta, startDate, endDate);

        } catch (InterruptedException e) {
            logger.error("Thread sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.error("Error retrieving account operations: " + e.getMessage());
        }
        return Collections.emptyList();
    }



    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class, readOnly = true)
    public Collection<OperacionCuenta> darOperacionesCuentaUltimoMesReadC (String idCuenta){
        
           
            LocalDate now = LocalDate.now();
            Date startDate = Date.valueOf(now.minusMonths(1));
            Date endDate = Date.valueOf(now);
            return operacionCuentaRepository.findOperacionesPorCuentaYMes(idCuenta, startDate, endDate);
      
    }




    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void realizarConsignacion(Long idOperacion, TipoOperacionCuenta tipoOperacionCuenta, Cuenta cuentaId, OperacionBancaria operacionBancaria){
        Logger logger = LoggerFactory.getLogger(getClass());
        try {
            Long cuentaId2 = cuentaId.getNumCuenta();
            Float monto = operacionBancaria.getMonto();
            operacionCuentaRepository.insertarOperacionCuenta2(idOperacion, "CONSIGNACION", cuentaId2, monto);
            Cuenta cuenta = cuentaRepository.findById(cuentaId2).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
            cuenta.setSaldo(cuenta.getSaldo() + operacionBancaria.getMonto());
            cuentaRepository.save(cuenta);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Error al realizar la consignación: " + e.getMessage());
            throw e;
        }
    }
    


    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void realizarRetiro(Long idOperacion, TipoOperacionCuenta tipoOperacionCuenta, Cuenta cuentaId, OperacionBancaria operacionBancaria){
        Logger logger = LoggerFactory.getLogger(getClass());
        try {
            Long cuentaId2 = cuentaId.getNumCuenta();
            Float monto = operacionBancaria.getMonto();
            operacionCuentaRepository.insertarOperacionCuenta2(idOperacion, "RETIRO", cuentaId2, monto);
            Cuenta cuenta = cuentaRepository.findById(cuentaId2).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

            if (cuenta.getSaldo() >= operacionBancaria.getMonto()) {
                cuenta.setSaldo(cuenta.getSaldo() - operacionBancaria.getMonto());
                cuentaRepository.save(cuenta);
            } else {
                throw new RuntimeException("Saldo insuficiente para el retiro");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Error al realizar el retiro: " + e.getMessage());
            throw e;
        }
    }


    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void realizarTransferencia(Long idOperacion1, Long idOperacion2, Cuenta cuentaId_1, Cuenta cuentaId_2, String tipoOperacionCuenta, Float operacionBancaria) {
        Logger logger = LoggerFactory.getLogger(getClass());
        try {
            Long cuentaId1 = cuentaId_1.getNumCuenta();
            Long cuentaId2 = cuentaId_2.getNumCuenta();
            operacionCuentaRepository.insertarOperacionCuenta2(idOperacion1, tipoOperacionCuenta, cuentaId1, -operacionBancaria);
            operacionCuentaRepository.insertarOperacionCuenta2(idOperacion2, tipoOperacionCuenta, cuentaId2, operacionBancaria);

            Cuenta cuenta1 = cuentaRepository.findById(cuentaId1).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
            Cuenta cuenta2 = cuentaRepository.findById(cuentaId2).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

            if (cuenta1.getSaldo() >= operacionBancaria) {
                cuenta1.setSaldo(cuenta1.getSaldo() - operacionBancaria);
                cuenta2.setSaldo(cuenta2.getSaldo() + operacionBancaria);
                cuentaRepository.save(cuenta1);
                cuentaRepository.save(cuenta2);
            } else {
                throw new RuntimeException("Saldo insuficiente para realizar la transferencia");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Error al realizar la transferencia: " + e.getMessage());
            throw e;
        }
    }

}
