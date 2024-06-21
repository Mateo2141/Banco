package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.EstadoCuenta;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.repositorio.CuentaRepository;
import uniandes.edu.co.bancodelosandes.service.CuentaService;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new org.springframework.beans.propertyeditors.CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/new")
    public String mostrarFormularioNuevaCuenta(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("clientes", cuentaService.obtenerClientes(""));
        return "cuentaNuevo";
    }

    @PostMapping("/new")
    public String crearCuenta(@Validated @ModelAttribute Cuenta cuenta, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", cuentaService.obtenerClientes(""));
            return "cuentaNuevo";
        }
        cuentaService.crearCuenta(cuenta);
        redirectAttributes.addFlashAttribute("message", "Cuenta creada con éxito");
        return "redirect:/cuentas";
    }

    @GetMapping
    public String listarCuentas(Model model) {
        List<Cuenta> cuentas = cuentaRepository.obtenerTodasLasCuentas();
        model.addAttribute("cuentas", cuentas);
        return "cuentas";
    }

    @GetMapping("/{id}")
    public String obtenerCuentaPorId(@PathVariable String id, Model model) {
        Optional<Cuenta> cuentaOpt = cuentaService.obtenerCuentaPorId(id);
        if (cuentaOpt.isPresent()) {
            model.addAttribute("cuenta", cuentaOpt.get());
            return "cuentaDetalle"; 
        } else {
            return "redirect:/cuentas";
        }
    }

    @GetMapping("/{id}/delete")
    public String borrarCuenta(@PathVariable String id) {
        cuentaService.borrarCuenta(id);
        return "redirect:/cuentas";
    }

    @PostMapping("/{id}/operaciones")
    public String registrarOperacion(@PathVariable String id, @ModelAttribute OperacionBancaria operacion, Model model) {
        operacion.setCuenta(new Cuenta());
        operacion.getCuenta().setNumCuenta(id);
        OperacionBancaria operacionRegistrada = cuentaService.registrarOperacion(operacion);
        model.addAttribute("operacion", operacionRegistrada);
        return "operacionDetalle"; 
    }

    @GetMapping("/{numCuenta}/edit")
    public String mostrarFormularioEditarCuenta(@PathVariable String numCuenta, Model model) {
        Optional<Cuenta> cuentaOpt = cuentaService.obtenerCuentaPorId(numCuenta);
        if (cuentaOpt.isPresent()) {
            Cuenta cuenta = cuentaOpt.get();
            if (cuenta.getEstado() == null) {
                cuenta.setEstado(EstadoCuenta.ACTIVA); 
            }
            model.addAttribute("cuenta", cuenta);
            model.addAttribute("titulo", "Editar Cuenta");
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/{numCuenta}/edit/save")
    public String guardarEdicionCuenta(@PathVariable String numCuenta, @RequestParam EstadoCuenta nuevoEstado, RedirectAttributes redirectAttributes) {
        try {
            cuentaService.cambiarEstadoCuenta(numCuenta, nuevoEstado);
            redirectAttributes.addFlashAttribute("message", "Estado de la cuenta actualizado con éxito");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/cuentas/" + numCuenta + "/edit";
        }
        return "redirect:/cuentas";
    }

    @GetMapping("/{numCuenta}/consignar")
    public String mostrarFormularioConsignar(@PathVariable String numCuenta, Model model) {
        Optional<Cuenta> cuentaOpt = cuentaService.obtenerCuentaPorId(numCuenta);
        if (cuentaOpt.isPresent()) {
            model.addAttribute("cuenta", cuentaOpt.get());
            return "consignar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/{numCuenta}/consignar")
    public String consignarDinero(@PathVariable String numCuenta, @RequestParam Float monto, RedirectAttributes redirectAttributes) {
        
        try {
            redirectAttributes.addFlashAttribute("message", "iniciar proceso de consigna");
            cuentaService.consignarDinero(numCuenta, monto);
            redirectAttributes.addFlashAttribute("message", "Consignación realizada con éxito");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @GetMapping("/{numCuenta}/retirar")
    public String mostrarFormularioRetirar(@PathVariable String numCuenta, Model model) {
        Optional<Cuenta> cuentaOpt = cuentaService.obtenerCuentaPorId(numCuenta);
        if (cuentaOpt.isPresent()) {
            model.addAttribute("cuenta", cuentaOpt.get());
            return "retirar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/{numCuenta}/retirar")
    public String retirarDinero(@PathVariable String numCuenta, @RequestParam Float monto, RedirectAttributes redirectAttributes) {
        try {
            cuentaService.retirarDinero(numCuenta, monto);
            redirectAttributes.addFlashAttribute("message", "Retiro realizado con éxito");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @GetMapping("/{numCuenta}/transferir")
    public String mostrarFormularioTransferir(@PathVariable String numCuenta, Model model) {
        Optional<Cuenta> cuentaOpt = cuentaService.obtenerCuentaPorId(numCuenta);
        if (cuentaOpt.isPresent()) {
            model.addAttribute("cuenta", cuentaOpt.get());
            return "transferir";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/{numCuenta}/transferir")
    public String transferirDinero(@PathVariable String numCuenta, @RequestParam String cuentaDestino, @RequestParam Float monto, RedirectAttributes redirectAttributes) {
        try {
            cuentaService.transferirDinero(numCuenta, cuentaDestino, monto);
            redirectAttributes.addFlashAttribute("message", "Transferencia realizada con éxito");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/cuentas";
    }

    @GetMapping("/buscar")
    public String buscarCuentas(
                @RequestParam(required = false, defaultValue = "AHORRO") String tipo,
                @RequestParam(required = false, defaultValue = "0") float saldoMin,
                @RequestParam(required = false, defaultValue = "999999999") float saldoMax,
                @RequestParam(required = false, defaultValue = "2020-05-30") Date fechaCreacionInicio,
                @RequestParam(required = false, defaultValue = "2024-05-30") Date fechaCreacionFin,
                @RequestParam(required = false, defaultValue = "2020-05-30") Date fechaUltimaTransaccionInicio,
                @RequestParam(required = false, defaultValue = "2024-05-30") Date fechaUltimaTransaccionFin,
                @RequestParam(required = false, defaultValue = "123456789") int clienteId,
                Model model) {
        List<Cuenta> cuentas = cuentaService.buscarCuentas(tipo, saldoMin, saldoMax, fechaCreacionInicio, fechaCreacionFin, fechaUltimaTransaccionInicio, fechaUltimaTransaccionFin, clienteId);
        model.addAttribute("cuentas", cuentas);
        return "cuentas"; 
    }

    @GetMapping("/agrupar")
    public String agruparCuentas(
            @RequestParam String tipo,
            @RequestParam float saldoMin,
            @RequestParam float saldoMax,
            @RequestParam Date fechaCreacionInicio,
            @RequestParam Date fechaCreacionFin,
            @RequestParam Date fechaUltimaTransaccionInicio,
            @RequestParam Date fechaUltimaTransaccionFin,
            @RequestParam int clienteId,
            Model model) {
        List<Cuenta> cuentasAgrupadas = cuentaService.agruparCuentas(tipo, saldoMin, saldoMax, fechaCreacionInicio, fechaCreacionFin, fechaUltimaTransaccionInicio, fechaUltimaTransaccionFin, clienteId);
        model.addAttribute("cuentasAgrupadas", cuentasAgrupadas);
        return "cuentasAgrupadasList"; 
    }


    @GetMapping("/extracto")
    public String mostrarFormularioExtracto(Model model) {
        return "formularioExtracto";
    }

    @PostMapping("/extracto")
    public ResponseEntity<InputStreamResource> generarExtracto(@RequestParam String numCuenta, @RequestParam String mes, RedirectAttributes redirectAttributes) {
        Optional<Cuenta> cuentaOpt = cuentaService.obtenerCuentaPorId(numCuenta);
        if (!cuentaOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Cuenta no encontrada");
            return ResponseEntity.badRequest().build();
        }

        Cuenta cuenta = cuentaOpt.get();
        List<OperacionBancaria> operaciones = cuentaService.obtenerOperacionesPorMes(numCuenta, mes);

        StringBuilder extracto = new StringBuilder();
        extracto.append("Extracto Bancario\n");
        extracto.append("Número de Cuenta: ").append(cuenta.getNumCuenta()).append("\n");
        extracto.append("Mes: ").append(mes).append("\n\n");
        extracto.append("Operaciones:\n");

        for (OperacionBancaria operacion : operaciones) {
            extracto.append("Fecha: ").append(new SimpleDateFormat("yyyy-MM-dd").format(operacion.getFecha()))
                    .append(", Tipo: ").append(operacion.getTipo())
                    .append(", Monto: ").append(operacion.getMonto()).append("\n");
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(extracto.toString().getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=extracto_" + numCuenta + "_" + mes + ".txt");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.TEXT_PLAIN)
                .body(new InputStreamResource(byteArrayInputStream));
    }
}
