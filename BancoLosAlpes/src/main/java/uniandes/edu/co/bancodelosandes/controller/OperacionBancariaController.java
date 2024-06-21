package uniandes.edu.co.bancodelosandes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.service.OperacionBancariaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operaciones")
public class OperacionBancariaController {

    @Autowired
    private OperacionBancariaService operacionBancariaService;

    @PostMapping
    public OperacionBancaria registrarOperacion(@RequestBody OperacionBancaria operacion) {
        return operacionBancariaService.registrarOperacion(operacion);
    }

    @GetMapping("/{id}")
    public Optional<OperacionBancaria> obtenerOperacionPorId(@PathVariable Integer id) {
        return operacionBancariaService.obtenerOperacionPorId(id);
    }

    @DeleteMapping("/{id}")
    public void borrarOperacion(@PathVariable String id) {
        operacionBancariaService.borrarOperacion(id);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<OperacionBancaria> obtenerOperacionesPorCuentaId(@PathVariable Integer cuentaId) {
        return operacionBancariaService.obtenerOperacionesPorCuentaId(cuentaId);
    }
}