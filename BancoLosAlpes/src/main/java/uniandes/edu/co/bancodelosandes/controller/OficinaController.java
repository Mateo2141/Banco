package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;
import uniandes.edu.co.bancodelosandes.service.OficinaService;

import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/oficinas")
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;

    @GetMapping
    public String listarOficinas(Model model, @RequestParam(required = false) String nombre) {
        List<Oficina> oficinas;
        if (nombre != null && !nombre.isEmpty()) {
            oficinas = oficinaService.obtenerOficinasPorNombre(nombre);
        } else {
            oficinas = oficinaService.obtenerOficinasPorNombre("");
        }
        model.addAttribute("oficinas", oficinas);
        return "oficinas";
    }

    @GetMapping("/{id}")
    public String obtenerOficinaPorId(@PathVariable String id, Model model) {
        Optional<Oficina> oficina = oficinaService.obtenerOficinaPorId(id);
        if (oficina.isPresent()) {
            model.addAttribute("oficina", oficina.get());
            return "detalleOficina";
        } else {
            model.addAttribute("errorMessage", "Oficina no encontrada");
            return "error";
        }
    }

    @GetMapping("/nueva")
    public String nuevaOficinaForm(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinaNuevo";
    }

    @PostMapping("/guardar")
    public String guardarOficina(@ModelAttribute Oficina oficina) {
        Oficina oficinaGuardada = oficinaService.crearOficina(oficina);
        return "redirect:/oficinas/" + oficinaGuardada.getId();
    }

    @GetMapping("/{id}/editar")
    public String editarOficinaForm(@PathVariable String id, Model model) {
        Optional<Oficina> oficina = oficinaService.obtenerOficinaPorId(id);
        if (oficina.isPresent()) {
            model.addAttribute("oficina", oficina.get());
            return "editarOficina";
        } else {
            return "redirect:/oficinas";
        }
    }

    @PostMapping("/{id}/guardar")
    public String actualizarOficina(@PathVariable String id, @ModelAttribute Oficina oficina) {
        oficina.setId(id);
        oficinaService.crearOficina(oficina);
        return "redirect:/oficinas";
    }

    @GetMapping("/{id}/borrar")
    public String borrarOficina(@PathVariable String id) {
        oficinaService.borrarOficina(id);
        return "redirect:/oficinas";
    }

    @GetMapping("/{id}/puntosatencion/nuevo")
    public String nuevoPuntoAtencionForm(@PathVariable String id, Model model) {
        model.addAttribute("puntoAtencion", new PuntoAtencion());
        model.addAttribute("oficinaId", id);
        return "puntoAtencionNuevo";
    }

    @PostMapping("/{id}/puntosatencion/guardar")
    public String agregarPuntoAtencion(@PathVariable String id, @ModelAttribute PuntoAtencion puntoAtencion) {
        oficinaService.agregarPuntoAtencion(id, puntoAtencion);
        return "redirect:/oficinas/" + id;
    }

    @GetMapping("/{id}/puntosatencion/{puntoId}/borrar")
    public String borrarPuntoAtencion(@PathVariable String id, @PathVariable String puntoId) {
        oficinaService.borrarPuntoAtencion(id, puntoId);
        return "redirect:/oficinas/" + id;
    }

    @GetMapping("/{id}/puntosatencion/{puntoId}/editar")
    public String editarPuntoAtencionForm(@PathVariable String id, @PathVariable String puntoId, Model model) {
        Oficina oficina = oficinaService.obtenerOficinaPorId(id).orElse(null);
        if (oficina != null) {
            PuntoAtencion puntoAtencion = oficina.getPuntosAtencion().stream()
                    .filter(pa -> pa.getId().equals(puntoId))
                    .findFirst()
                    .orElse(null);
            if (puntoAtencion != null) {
                model.addAttribute("puntoAtencion", puntoAtencion);
                model.addAttribute("oficinaId", id);
                return "puntoAtencionEditar";
            }
        }
        return "redirect:/oficinas/" + id;
    }

    @PostMapping("/{id}/puntosatencion/{puntoId}/guardar")
    public String actualizarPuntoAtencion(@PathVariable String id, @PathVariable String puntoId, @ModelAttribute PuntoAtencion puntoAtencion) {
        oficinaService.actualizarPuntoAtencion(id, puntoId, puntoAtencion);
        return "redirect:/oficinas/" + id;
    }
}
