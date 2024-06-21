package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.bancodelosandes.modelo.Cliente;
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;
import uniandes.edu.co.bancodelosandes.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }


    @DeleteMapping("/{id}")
    public void borrarCliente(@PathVariable Integer id) {
        clienteService.borrarCliente(id);
    }

    @PostMapping("/{clienteId}/cuentas")
    public Cuenta crearCuentaParaCliente(@PathVariable Integer clienteId, @RequestBody Cuenta cuenta) {
        return clienteService.crearCuentaParaCliente(clienteId, cuenta);
    }

    @PostMapping("/{clienteId}/puntosatencion/{puntoAtencionId}")
    public PuntoAtencion asignarPuntoAtencionACliente(@PathVariable Integer clienteId, @PathVariable Integer puntoAtencionId) {
        return clienteService.asignarPuntoAtencionACliente(clienteId, puntoAtencionId);
    }
}