package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;
import uniandes.edu.co.bancodelosandes.repositorio.PuntoAtencionRepository;

@Controller
public class PuntosAtencionController {

    @Autowired
    private PuntoAtencionRepository puntosAtencionRepository;

    @GetMapping("/puntosAtencion")
    public String puntosAtencion(Model model) {
        model.addAttribute("puntosAtencion", puntosAtencionRepository.darPuntosAtencion());
        return "puntosAtencion";
    }

    @GetMapping("/puntosAtencion/new")
    public String puntoAtencionForm(Model model) {
        PuntoAtencion nuevoPunto = new PuntoAtencion();
        if (nuevoPunto.getOficinaName() == null) {
            nuevoPunto.setOficinaName(new Oficina());
        }
        model.addAttribute("puntoAtencion", nuevoPunto);
        return "puntoAtencionNuevo";
    }

    @PostMapping("/puntosAtencion/new/save")
    public String puntoAtencionGuardar(@ModelAttribute PuntoAtencion puntoAtencion) {
        //verifica si tiene operaciones realizadas para mostrar un mensaje de error
        if (puntoAtencion.getOperacionesRealizadas() > 0) {
            return "redirect:/puntosAtencion/new";
        }

        puntosAtencionRepository.insertarPuntoAtencion(puntoAtencion.getUbicacionGeografica(),
                puntoAtencion.getOperacionesRealizadas(), puntoAtencion.getTipoPuntoAtencion().toString(),
                puntoAtencion.getOficinaName().getNombre());
        return "redirect:/puntosAtencion";
    }

    @GetMapping("/puntosAtencion/{ubicacionGeografica}/edit")
    public String puntoAtencionEditarForm(@PathVariable("ubicacionGeografica") String ubicacionGeografica,
            Model model) {
        PuntoAtencion puntoAtencion = puntosAtencionRepository.darPuntoAtencion(ubicacionGeografica);
        if (puntoAtencion != null) {
            model.addAttribute("puntoAtencion", puntoAtencion);
            return "puntoAtencionEditar";
        } else {
            return "redirect:/puntosAtencion";
        }
    }

    @PostMapping("/puntosAtencion/{ubicacionGeografica}/edit/save")
    public String puntoAtencionEditarGuardar(@PathVariable("ubicacionGeografica") String ubicacionGeografica,
            @ModelAttribute PuntoAtencion puntoAtencion) {
        puntosAtencionRepository.actualizarPuntoAtencion(puntoAtencion.getOperacionesRealizadas(),
                puntoAtencion.getTipoPuntoAtencion(), puntoAtencion.getOficinaName(), ubicacionGeografica);
        return "redirect:/puntosAtencion";
    }

    @GetMapping("/puntosAtencion/{ubicacionGeografica}/delete")
    public String puntoAtencionEliminar(@PathVariable("ubicacionGeografica") String ubicacionGeografica) {
        puntosAtencionRepository.eliminarPuntoAtencion(ubicacionGeografica);
        return "redirect:/puntosAtencion";
    }
}
