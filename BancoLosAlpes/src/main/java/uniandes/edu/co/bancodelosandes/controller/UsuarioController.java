package uniandes.edu.co.bancodelosandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.bancodelosandes.modelo.Usuario;
import uniandes.edu.co.bancodelosandes.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crearUsuario")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}")
    public String obtenerUsuarioPorId(@PathVariable String id, Model model) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarioDetalle";  
        } else {
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/{id}/delete")
    public String borrarUsuario(@PathVariable String id) {
        usuarioService.borrarUsuario(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/buscarPorNombre")
    public String buscarUsuariosPorNombre(@RequestParam String nombre, Model model) {
        List<Usuario> usuarios = usuarioService.buscarUsuariosPorNombre(nombre);
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping
    public String obtenerTodosLosUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";  
    }

    @GetMapping("/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";  
    }

    @GetMapping("/{id}/edit")
    public String usuarioEditarForm(@PathVariable("id") String id, Model model) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarioEditar";  
        } else {
            return "redirect:/usuarios";
        }
    }

    
    @PostMapping("/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("id") String id, @ModelAttribute Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioExistente.isPresent()) {
            usuario.setNumIdentificacion(id);
            usuarioService.crearUsuario(usuario); 
        }
        return "redirect:/usuarios";
    }

}
