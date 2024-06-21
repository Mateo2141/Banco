package uniandes.edu.co.bancodelosandes.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.bancodelosandes.modelo.Cliente;
import uniandes.edu.co.bancodelosandes.repositorio.ClienteRepository;

@Controller
public class ClientesController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public String clientes(Model model) {
        model.addAttribute("clientes", clienteRepository.darClientes());
        return "clientes";
    }

    //CAMBIO IMPORTANTE
    @GetMapping("/clientes/filtro")
    public String filtrarCuentas(Model model,
                                @RequestParam(required = false) String numIdentificacion,
                                @RequestParam(required = false) String tipoCuenta){
        Collection<Cliente> clientesFiltrados = clienteRepository.darClientesPorFiltros(numIdentificacion, tipoCuenta);
        model.addAttribute("clientes", clientesFiltrados);
        return "clientes";
    }

    @GetMapping("/clientes/new")
    public String clienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clienteNuevo";
    }
/* 
    @PostMapping("/cliente/new/save")
    public String clienteGuardar(@ModelAttribute Cliente cliente) {
        clienteRepository.insertarCliente(cliente.getNumIdentificacion(), cliente.getTipoPersona(),
                cliente.getUbicacionGeografica(), cliente.getUsuario());
        return "redirect:/clientes";
    }*/

    @GetMapping("/clientes/{numIdentificacion}/edit")
    public String clienteEditarForm(@PathVariable("numIdentificacion") String numIdentificacion, Model model) {
        Cliente cliente = clienteRepository.darCliente(numIdentificacion);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "clienteEditar";
        } else {
            return "redirect:/clientes";
        }
    }

    @PostMapping("/clientes/{numIdentificacion}/save")
    public String clienteEditarGuardar(@PathVariable("numIdentificacion") String numIdentificacion,
            @ModelAttribute Cliente cliente) {
        clienteRepository.actualizarCliente(cliente.getTipoPersona(), cliente.getUbicacionGeografica(),
                cliente.getUsuario(), numIdentificacion);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{numIdentificacion}/delete")
    public String clienteEliminar(@PathVariable("numIdentificacion") String numIdentificacion) {
        clienteRepository.eliminarCliente(numIdentificacion);
        return "redirect:/clientes";
    }
}