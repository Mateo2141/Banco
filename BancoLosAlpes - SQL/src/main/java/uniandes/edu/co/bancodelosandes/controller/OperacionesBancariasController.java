package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.bancodelosandes.modelo.OperacionBancaria;
import uniandes.edu.co.bancodelosandes.repositorio.OperacionBancariaRepository;

@Controller
public class OperacionesBancariasController {
    @Autowired
    private OperacionBancariaRepository operacionBancariaRepository;

    @GetMapping("/operacionesBancarias")
    public String operacionesBancarias(Model model) {
        model.addAttribute("operacionesBancarias", operacionBancariaRepository.darOperacionesBancarias());
        return "operacionesBancarias";
    }

    @GetMapping("/operacionesBancarias/new")
    public String operacionBancariaForm(Model model) {
        model.addAttribute("operacionBancaria", new OperacionBancaria());
        return "operacionBancariaNuevo";
    }

    @PostMapping("operacionBancaria/new/save")
    public String operacionBancariaGuardar(@ModelAttribute OperacionBancaria operacionBancaria) {
        operacionBancariaRepository.insertarOperacionBancaria(operacionBancaria.getId(), operacionBancaria.getMonto(),
                operacionBancaria.getFecha(), operacionBancaria.getHora(), operacionBancaria.getUbicacionGeografica());
        return "redirect:/operacionesBancarias";
    }

    @GetMapping("/operacionesBancarias/{id}/edit")
    public String operacionBancariaEditarForm(@PathVariable("id") Long id, Model model) {
        OperacionBancaria operacionBancaria = operacionBancariaRepository.darOperacionBancaria(id);
        if (operacionBancaria != null) {
            model.addAttribute("operacionBancaria", operacionBancaria);
            return "operacionBancariaEditar";
        } else {
            return "redirect:/operacionesBancarias";
        }
    }

    @PostMapping("/operacionesBancarias/{id}/save")
    public String operacionBancariaEditarGuardar(@PathVariable("id") Long id,
            @ModelAttribute OperacionBancaria operacionBancaria) {
        operacionBancariaRepository.actualizarOperacionBancaria(operacionBancaria.getMonto(),
                operacionBancaria.getFecha(), operacionBancaria.getHora(), operacionBancaria.getUbicacionGeografica(),
                id);
        return "redirect:/operacionesBancarias";
    }

    @GetMapping("/operacionesBancarias/{id}/delete")
    public String operacionBancariaEliminar(@PathVariable("id") Long id) {
        operacionBancariaRepository.eliminarOperacionBancaria(id);
        return "redirect:/operacionesBancarias";
    }
}
