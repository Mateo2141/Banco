package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.bancodelosandes.modelo.Empleado;
import uniandes.edu.co.bancodelosandes.repositorio.EmpleadoRepository;

@Controller
public class EmpleadosController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public String empleados(Model model) {
        model.addAttribute("empleados", empleadoRepository.darEmpleados());
        return "empleados";
    }

    @GetMapping("/empleados/new")
    public String empleadoForm(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleadoNuevo";
    }
/* 
    @PostMapping("empleado/new/save")
    public String empleadoGuardar(@ModelAttribute Empleado empleado) {
        empleadoRepository.agregarEmpleado(empleado.getTipoEmpleado(), empleado.getNumIdentificacion(),
                empleado.getOficina());
        return "redirect:/empleados";
    }*/

    @GetMapping("/empleados/{numIdentificacion}/edit")
    public String empleadoEditarForm(@PathVariable("numIdentificacion") String numIdentificacion, Model model) {
        Empleado empleado = empleadoRepository.darEmpleado(numIdentificacion);
        if (empleado != null) {
            model.addAttribute("empleado", empleado);
            return "empleadoEditar";
        } else {
            return "redirect:/empleados";
        }
    }

    @PostMapping("/empleados/{numIdentificacion}/save")
    public String empleadoEditarGuardar(@PathVariable("numIdentificacion") String numIdentificacion,
            @ModelAttribute Empleado empleado) {
        empleadoRepository.actualizarEmpleado(empleado.getTipoEmpleado().toString(), empleado.getOficina(), numIdentificacion);
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/{numIdentificacion}/delete")
    public String empleadoEliminar(@PathVariable("numIdentificacion") String numIdentificacion) {
        empleadoRepository.eliminarEmpleado(numIdentificacion);
        return "redirect:/empleados";
    }
}
