package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.bancodelosandes.modelo.OPeracionPrestamo;
import uniandes.edu.co.bancodelosandes.repositorio.OperacionPrestamoRepository;

@Controller
public class OperacionesPrestamoController {
    @Autowired
    private OperacionPrestamoRepository operacionesPrestamoRepository;

    @GetMapping("/operacionesPrestamo")
    public String operacionesPrestamo(Model model) {
        model.addAttribute("operacionesPrestamo", operacionesPrestamoRepository.darOperacionesPrestamo());
        return "operacionesPrestamo";
    }

    @GetMapping("/operacionesPrestamo/new")
    public String operacionPrestamoForm(Model model) {
        model.addAttribute("operacionPrestamo", new OPeracionPrestamo());
        return "operacionPrestamoNuevo";
    }

    @PostMapping("/operacionPrestamo/new/save")
    public String operacionPrestamoGuardar(@ModelAttribute OPeracionPrestamo operacionPrestamo) {
        operacionesPrestamoRepository.insertarOperacionPrestamo(operacionPrestamo.getId(),
                operacionPrestamo.getTipoOperacion(), operacionPrestamo.getPrestamo(),
                operacionPrestamo.getOperacionBancaria());
        return "redirect:/operacionesPrestamo";
    }

    @GetMapping("/operacionesPrestamo/{id}/edit")
    public String operacionPrestamoEditarForm(@PathVariable("id") Long id, Model model) {
        OPeracionPrestamo operacionPrestamo = operacionesPrestamoRepository.darOperacionPrestamo(id);
        if (operacionPrestamo != null) {
            model.addAttribute("operacionPrestamo", operacionPrestamo);
            return "operacionPrestamoEditar";
        } else {
            return "redirect:/operacionesPrestamo";
        }
    }

    @PostMapping("/operacionesPrestamo/{id}/edit/save")
    public String operacionPrestamoEditarGuardar(@PathVariable("id") Long id,
            @ModelAttribute OPeracionPrestamo operacionPrestamo) {
        operacionesPrestamoRepository.actualizarOperacionPrestamo(operacionPrestamo.getTipoOperacion(),
                operacionPrestamo.getPrestamo(), operacionPrestamo.getOperacionBancaria(), id);
        return "redirect:/operacionesPrestamo";
    }

    @GetMapping("/operacionesPrestamo/{id}/delete")
    public String operacionPrestamoEliminar(@PathVariable("id") Long id) {
        operacionesPrestamoRepository.eliminarOperacionPrestamo(id);
        return "redirect:/operacionesPrestamo";
    }
}
