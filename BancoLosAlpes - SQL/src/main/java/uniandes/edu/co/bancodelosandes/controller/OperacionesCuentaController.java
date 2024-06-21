package uniandes.edu.co.bancodelosandes.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.modelo.OperacionCuenta;
import uniandes.edu.co.bancodelosandes.modelo.TipoOperacionCuenta;
import uniandes.edu.co.bancodelosandes.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.bancodelosandes.servicios.OperacionesCuentaServicio;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OperacionesCuentaController {
    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;

    @Autowired
    private OperacionesCuentaServicio operacionesCuentaServicio;


    @GetMapping("/operacionesCuenta/transfer")
    public String showTransferForm(Model model) {// Asumiendo que necesitas preparar algún modelo para el formulario
        OperacionCuenta operacioncuenta = new OperacionCuenta();
        operacioncuenta.setNumCuenta(new Cuenta()); // Inicializa numCuenta
        operacioncuenta.setOperacionBancaria(new OperacionBancaria()); // Inicializa operacionBancaria
        model.addAttribute("operacioncuenta", operacioncuenta);
            return "opracionCuentaNuevo"; 
    }


    @GetMapping("/operacionesCuenta/cuentaSerial/{id}")
    public String operacionesCuentaSerial(Model model, @PathVariable("id") String idCuenta,
            RedirectAttributes redirectAttributes) {
        try {
            Collection<OperacionCuenta> operacionesCuentaFecha = operacionesCuentaServicio
                    .darOperacionesCuentaUltimoMesSerial(idCuenta);
            model.addAttribute("operacionesCuenta", operacionesCuentaFecha);
        } catch (Exception e) {
            System.err.println("Error al consultar las operaciones " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo completar la consulta de la cuenta.");
        }
        return "operacionesCuenta";
    }

    @GetMapping("/operacionesCuenta/cuentaReadCommited/{id}")
    public String operacionesCuentaReadC(Model model, @PathVariable("id") String idCuenta,
            RedirectAttributes redirectAttributes) {
        try {
            Collection<OperacionCuenta> operacionesCuentaFecha = operacionesCuentaServicio
                    .darOperacionesCuentaUltimoMesReadC(idCuenta);
            model.addAttribute("operacionesCuenta", operacionesCuentaFecha);
        } catch (Exception e) {
            System.err.println("Error al consultar las operaciones " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo completar la consulta de la cuenta.");
        }
        return "operacionesCuenta";
    }

    @PostMapping("/operacionesCuenta/consignar")
    public String realizarConsignacion(@RequestParam Long idOperacion,
            @RequestParam TipoOperacionCuenta tipoOperacionCuenta, @RequestParam Cuenta cuentaId,
            @RequestParam OperacionBancaria monto, RedirectAttributes redirectAttributes) {
        try {
            operacionesCuentaServicio.realizarConsignacion(idOperacion, tipoOperacionCuenta, cuentaId, monto);
            redirectAttributes.addFlashAttribute("successMessage", "Consignación realizada con éxito");
            return "redirect:/operacionesCuenta";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error en la consignación: " + e.getMessage());
            return "redirect:/operacionesCuenta";
        }
    }

    @PostMapping("/operacionesCuenta/retirar")
    public String realizarRetiro(@RequestParam Long idOperacion, @RequestParam TipoOperacionCuenta tipoOperacionCuenta,
            @RequestParam Cuenta cuentaId, @RequestParam OperacionBancaria monto,
            RedirectAttributes redirectAttributes) {
        try {
            operacionesCuentaServicio.realizarRetiro(idOperacion, tipoOperacionCuenta, cuentaId, monto);
            redirectAttributes.addFlashAttribute("successMessage", "Retiro realizado con éxito");
            return "redirect:/operacionesCuenta";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error en el retiro: " + e.getMessage());
            return "redirect:/operacionesCuenta";
        }
    }

    @PostMapping("/operacionesCuenta/transferir")
    public String realizarTransferencia(@RequestParam Long idOperacion1, @RequestParam Long idOperacion2,
            @RequestParam Cuenta cuentaId1, @RequestParam Cuenta cuentaId2, @RequestParam Float monto,
            RedirectAttributes redirectAttributes) {
        try {
            operacionesCuentaServicio.realizarTransferencia(idOperacion1, idOperacion2, cuentaId1, cuentaId2,
                    "TRANSFERENCIA", monto);
            redirectAttributes.addFlashAttribute("successMessage", "Transferencia realizada con éxito");
            return "redirect:/operacionesCuenta";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error en la transferencia: " + e.getMessage());
            return "redirect:/operacionesCuenta";
        }
    }

    @GetMapping("/operacionesCuenta")
    public String operacionesCuenta(Model model) {
        model.addAttribute("operacionesCuenta", operacionCuentaRepository.darOperacionesCuenta());
        return "operacionesCuenta";
    }

    @GetMapping("/operacionesCuenta/new")
    public String operacionCuentaForm(Model model) {
        model.addAttribute("operacionCuenta", new OperacionCuenta());
        return "operacionCuentaNuevo";
    }

    @PostMapping("operacionCuenta/new/save")
    public String operacionCuentaGuardar(@ModelAttribute OperacionCuenta operacionCuenta) {
        operacionCuentaRepository.insertarOperacionCuenta(operacionCuenta.getId(),
                operacionCuenta.getTipoOperacionCuenta(), operacionCuenta.getNumCuenta(),
                operacionCuenta.getOperacionBancaria());
        return "redirect:/operacionesCuenta";
    }

    @GetMapping("/operacionesCuenta/{id}/edit")
    public String operacionCuentaEditarForm(@PathVariable("id") Long id, Model model) {
        OperacionCuenta operacionCuenta = operacionCuentaRepository.darOperacionCuenta(id);
        if (operacionCuenta != null) {
            model.addAttribute("operacionCuenta", operacionCuenta);
            return "operacionCuentaEditar";
        } else {
            return "redirect:/operacionesCuenta";
        }
    }

    @PostMapping("/operacionesCuenta/{id}/save")
    public String operacionCuentaEditarGuardar(@PathVariable("id") Long id,
            @ModelAttribute OperacionCuenta operacionCuenta) {
        operacionCuentaRepository.actualizarOperacionCuenta(operacionCuenta.getTipoOperacionCuenta(),
                operacionCuenta.getNumCuenta(), operacionCuenta.getOperacionBancaria(), id);
        return "redirect:/operacionesCuenta";
    }

    @GetMapping("/operacionesCuenta/{id}/delete")
    public String operacionCuentaEliminar(@PathVariable("id") Long id) {
        operacionCuentaRepository.eliminarOperacionCuenta(id);
        return "redirect:/operacionesCuenta";
    }

    @GetMapping("/cuentas/extracto")
    public String obtenerExtracto(@RequestParam("numeroCuenta") String numeroCuenta, @RequestParam("mes") int mes,
            Model model) {
        LocalDate inicioMes = LocalDate.of(LocalDate.now().getYear(), mes, 1);
        LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

        Float saldoInicial = 0f;
        Collection<OperacionCuenta> operacionesDelMes = operacionCuentaRepository.findOperacionesPorCuentaYMes(
                numeroCuenta.toString(), java.sql.Date.valueOf(inicioMes), java.sql.Date.valueOf(finMes));

        Float saldoFinal = saldoInicial;
        for (OperacionCuenta operacion : operacionesDelMes) {
            saldoFinal = saldoFinal + operacion.getOperacionBancaria().getMonto();
        }

        model.addAttribute("saldoInicial", saldoInicial);
        model.addAttribute("operaciones", operacionesDelMes);
        model.addAttribute("saldoFinal", saldoFinal);

        return "extractoCuenta";
    }

}