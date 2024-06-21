package uniandes.edu.co.bancodelosandes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.repositorio.OperacionBancariaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OperacionBancariaService {

    @Autowired
    private OperacionBancariaRepository operacionBancariaRepository;

    public OperacionBancaria registrarOperacion(OperacionBancaria operacion) {
        return operacionBancariaRepository.save(operacion);
    }

    public Optional<OperacionBancaria> obtenerOperacionPorId(Integer id) {
        return Optional.ofNullable(operacionBancariaRepository.buscarPorId(id));
    }

    public void borrarOperacion(String id) {
        operacionBancariaRepository.deleteById(id);
    }

    public List<OperacionBancaria> obtenerOperacionesPorCuentaId(Integer cuentaId) {
        return operacionBancariaRepository.buscarPorCuentaId(cuentaId);
    }
}