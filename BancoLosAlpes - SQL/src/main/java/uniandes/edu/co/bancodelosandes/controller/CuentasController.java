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
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.EstadoCuenta;
import uniandes.edu.co.bancodelosandes.modelo.TipoCuenta;
import uniandes.edu.co.bancodelosandes.repositorio.ClienteRepository;
import uniandes.edu.co.bancodelosandes.repositorio.CuentaRepository;

@Controller
public class CuentasController {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model) {
        model.addAttribute("cuentas", cuentaRepository.darCuentas());
        return "cuentas";
    }

    @GetMapping("/cuentas/filtro")
    public String filtrarCuentas(Model model,
            @RequestParam(required = false) String numIdentificacion,
            @RequestParam(required = false) String tipoCuenta,
            @RequestParam(required = false) String estadoCuenta,
            @RequestParam(required = false) Float saldo,
            @RequestParam(required = false) java.sql.Date fechaCreacion,
            @RequestParam(required = false) java.sql.Date fechaUtimaTransaccion) {
        Collection<Cuenta> cuentasFiltradas = cuentaRepository.darCuentasPorFiltros(numIdentificacion, tipoCuenta,
                estadoCuenta, saldo, fechaCreacion, fechaUtimaTransaccion);
        model.addAttribute("cuentas", cuentasFiltradas);
        return "cuentas";
    }

    @GetMapping("/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("clientes", clienteRepository.darClientes());
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("sexo", new String());
        return "cuentaNuevo";
    }

    @PostMapping("/cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta, @ModelAttribute String sexo) {
        long max = 0;
        for (Cuenta c : cuentaRepository.darCuentas()) {
            if (c.getNumCuenta() > max) {
                max = c.getNumCuenta();
            }
        }
        cuenta.setNumCuenta(max + 1);
        cuentaRepository.insertarCuenta(cuenta.getNumCuenta(), cuenta.getTipoCuenta().toString(),
                cuenta.getEstadoCuenta().toString(),
                cuenta.getSaldo(), cuenta.getFechaUtimaTransaccion(), cuenta.getFechaCreacion(),
                cuenta.getCliente().getNumIdentificacion());
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{numCuenta}/edit")
    public String cuentaEditarForm(@PathVariable("numCuenta") Long numCuenta, Model model) {
        Cuenta cuenta = cuentaRepository.darCuenta(numCuenta);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/cuentas/{numCuenta}/edit/save")
    public String cuentaEditarGuardar(@PathVariable("numCuenta") Long numCuenta,
            @RequestParam("estadoCuenta") String estadoCuenta) {
        Cuenta cuentaedit = cuentaRepository.darCuenta(numCuenta);
        if (cuentaedit != null) {
            cuentaedit.setEstadoCuenta(EstadoCuenta.valueOf(estadoCuenta));
            cuentaRepository.actualizarCuenta(cuentaedit.getTipoCuenta().toString(),
                    cuentaedit.getEstadoCuenta().toString(), cuentaedit.getSaldo(),
                    cuentaedit.getFechaUtimaTransaccion(), cuentaedit.getFechaCreacion(),
                    cuentaedit.getCliente().getNumIdentificacion(), numCuenta);
        }

        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{numCuenta}/delete")
    public String cuentaEliminar(@PathVariable("numCuenta") Long numCuenta) {
        cuentaRepository.eliminarCuenta(numCuenta);
        return "redirect:/cuentas";
    }
}
