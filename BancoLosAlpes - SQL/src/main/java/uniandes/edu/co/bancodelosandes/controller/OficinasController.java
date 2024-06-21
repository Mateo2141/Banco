package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.repositorio.OficinaRepository;

@Controller
public class OficinasController {
    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping("/oficinas")
    public String oficinas(Model model) {
        model.addAttribute("oficinas", oficinaRepository.darOficinas());
        return "oficinas";
    }

    @GetMapping("/oficinas/new")
    public String oficinaForm(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinaNuevo";
    }

    @PostMapping("oficinas/new/save")
    public String oficinaGuardar(@ModelAttribute Oficina oficina) {
        oficinaRepository.insertarOficina(oficina.getNombre(), oficina.getNumPuntosAtencion(), oficina.getDireccion(), oficina.getHorario().toString());
        return "redirect:/oficinas";
    }

    @GetMapping("/oficinas/{nombre}/edit")
    public String oficinaEditarForm(@PathVariable("nombre") String nombre, Model model) {
        Oficina oficina = oficinaRepository.darOficina(nombre);
        if (oficina != null) {
            model.addAttribute("oficina", oficina);
            return "oficinaEditar";
        } else {
            return "redirect:/oficinas";
        }
    }

    @PostMapping("/oficinas/{nombre}/edit/save")
    public String oficinaEditarGuardar(@PathVariable("nombre") String nombre, @ModelAttribute Oficina oficina) {
        oficinaRepository.actualizarOficina(oficina.getNumPuntosAtencion(), oficina.getDireccion(),
                oficina.getHorario(), nombre);
        return "redirect:/oficinas";
    }

    @GetMapping("/oficinas/{nombre}/delete")
    public String oficinaEliminar(@PathVariable("nombre") String nombre) {
        oficinaRepository.eliminarOficina(nombre);
        return "redirect:/oficinas";
    }
}
