package uniandes.edu.co.bancodelosandes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniandes.edu.co.bancodelosandes.modelo.Empleado;
import uniandes.edu.co.bancodelosandes.repositorio.EmpleadoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> obtenerEmpleadoPorId(Integer id) {
        return Optional.ofNullable(empleadoRepository.buscarPorId(id));
    }

    public void borrarEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }

    public List<Empleado> buscarEmpleadosPorNombre(String nombre) {
        return empleadoRepository.buscarPorNombre(nombre);
    }

    public List<Empleado> buscarEmpleadosPorOficinaId(Integer oficinaId) {
        return empleadoRepository.buscarPorOficinaId(oficinaId);
    }
}
