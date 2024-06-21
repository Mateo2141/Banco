package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.bancodelosandes.modelo.Empleado;
import uniandes.edu.co.bancodelosandes.service.EmpleadoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.crearEmpleado(empleado);
    }

    @GetMapping("/{id}")
    public Optional<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        return empleadoService.obtenerEmpleadoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void borrarEmpleado(@PathVariable Integer id) {
        empleadoService.borrarEmpleado(id);
    }

    @GetMapping("/buscarPorNombre")
    public List<Empleado> buscarEmpleadosPorNombre(@RequestParam String nombre) {
        return empleadoService.buscarEmpleadosPorNombre(nombre);
    }

    @GetMapping("/buscarPorOficinaId")
    public List<Empleado> buscarEmpleadosPorOficinaId(@RequestParam Integer oficinaId) {
        return empleadoService.buscarEmpleadosPorOficinaId(oficinaId);
    }
}
