package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.bancodelosandes.modelo.Prestamo;
import uniandes.edu.co.bancodelosandes.repositorio.PrestamoRepository;

@Controller
public class PrestamosController {
    @Autowired
    private PrestamoRepository prestamosRepository;

    @GetMapping("/prestamos")
    public String prestamos(Model model) {
        model.addAttribute("prestamos", prestamosRepository.darPrestamos());
        return "prestamos";
    }

    @GetMapping("/prestamos/new")
    public String prestamoForm(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        return "prestamoNuevo";
    }

    @PostMapping("/prestamos/new/save")
    public String prestamoGuardar(@ModelAttribute Prestamo prestamo) {
        prestamosRepository.insertarPrestamo(prestamo.getId(), prestamo.getTipoPrestamo(), prestamo.getEstadoPrestamo(),
                prestamo.getMonto(), prestamo.getInteres(), prestamo.getNumCuotas(), prestamo.getDiaPago(),
                prestamo.getValorCuota());
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/{id}/edit")
    public String prestamoEditarForm(@PathVariable("id") Long id, Model model) {
        Prestamo prestamo = prestamosRepository.darPrestamo(id);
        if (prestamo != null) {
            model.addAttribute("prestamo", prestamo);
            return "prestamoEditar";
        } else {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/prestamos/{id}/edit/save")
    public String prestamoEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Prestamo prestamo) {
        prestamosRepository.actualizarPrestamo(prestamo.getTipoPrestamo(), prestamo.getEstadoPrestamo(),
                prestamo.getMonto(), prestamo.getInteres(), prestamo.getNumCuotas(), prestamo.getDiaPago(),
                prestamo.getValorCuota(), id);
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/{id}/delete")
    public String prestamoEliminar(@PathVariable("id") Long id) {
        prestamosRepository.eliminarPrestamo(id);
        return "redirect:/prestamos";
    }
}
