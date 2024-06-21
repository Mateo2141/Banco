package uniandes.edu.co.bancodelosandes.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniandes.edu.co.bancodelosandes.modelo.Usuario;
import uniandes.edu.co.bancodelosandes.modelo.Empleado;
import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.repositorio.ClienteRepository;
import uniandes.edu.co.bancodelosandes.repositorio.EmpleadoRepository;
import uniandes.edu.co.bancodelosandes.repositorio.OficinaRepository;
import uniandes.edu.co.bancodelosandes.repositorio.UsuarioRepository;

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios.
 */
@Controller
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    /**
     * Método para obtener la vista de usuarios y mostrar la lista de usuarios
     * registrados.
     * 
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista a mostrar.
     */
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        Collection<Usuario> usuarios = usuarioRepository.darUsuarios();
        if (usuarios.isEmpty()) {
            model.addAttribute("mensaje", "No hay usuarios registrados");
        } else {
            model.addAttribute("usuarios", usuarios);
        }
        return "usuarios";
    }

    /**
     * Método para obtener la vista de creación de un nuevo empleado.
     * 
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista a mostrar.
     */
    @GetMapping("/usuarios/newempleado")
    public String usuarioEmpleadoForm(Model model) {
        model.addAttribute("oficinas", oficinaRepository.darOficinas());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("empleado", new Empleado());
        return "empleadoNuevo";
    }

    /**
     * Método para guardar un nuevo empleado en el repositorio de usuarios.
     * 
     * @param usuario El objeto Usuario con los datos del empleado a
     *                guardar.
     * @return El nombre de la vista a redireccionar.
     */
    @PostMapping("/usuarios/newempleado/save")
    public String usuarioEmpleadoGuardar(@ModelAttribute Usuario usuario, @ModelAttribute Empleado empleado) {
        String NUM_IDENTIFICACION = usuario.getNumIdentificacion();
        usuarioRepository.insertarUsuario(usuario.getTipoIdentificacion().toString(), NUM_IDENTIFICACION,
                usuario.getContrasena(), usuario.getNombre(), usuario.getApellido(), usuario.getNacionalidad(),
                usuario.getDireccion(), usuario.getEmail(), usuario.getTelefono(), usuario.getCiudad(),
                usuario.getDepartamento(), usuario.getCodigoPostal());

        empleadoRepository.agregarEmpleado(empleado.getTipoEmpleado().toString(), NUM_IDENTIFICACION,
                empleado.getOficina().toString());
        return "redirect:/usuarios"; // Redirecciona al controlador GET que ya maneja la recuperación de usuarios
    }

    /**
     * Método para obtener la vista de creación de un nuevo cliente.
     * 
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista a mostrar.
     */
    @GetMapping("/usuarios/new")
    public String usuarioClienteForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    /**
     * Método para guardar un nuevo cliente en el repositorio de usuarios.
     * 
     * @param usuario            El objeto Usuario con los datos del cliente a
     *                           guardar.
     * @param redirectAttributes Atributos de redirección para mensajes de éxito o
     *                           error.
     * @return El nombre de la vista a redireccionar.
     */
    @PostMapping("/usuarios/new/save")
    public String usuarioClienteGuardar(@ModelAttribute Usuario usuario, String tipopersona) {
        usuarioRepository.insertarUsuario(usuario.getTipoIdentificacion().toString(), usuario.getNumIdentificacion(),
                usuario.getContrasena(), usuario.getNombre(), usuario.getApellido(), usuario.getNacionalidad(),
                usuario.getDireccion(), usuario.getEmail(), usuario.getTelefono(), usuario.getCiudad(),
                usuario.getDepartamento(), usuario.getCodigoPostal());

        clienteRepository.insertarCliente(usuario.getNumIdentificacion(), tipopersona);
        return "redirect:/usuarios"; // Redirecciona al controlador GET que ya maneja la recuperación de usuarios
    }

    /**
     * Método para obtener la vista de edición de un usuario existente.
     * 
     * @param numIdentificacion El número de identificación del usuario a editar.
     * @param model             El modelo de datos para la vista.
     * @return El nombre de la vista a mostrar o redireccionar.
     */
    @GetMapping("/usuarios/{numIdentificacion}/edit")
    public String usuarioEditarForm(@PathVariable("numIdentificacion") String numIdentificacion, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(numIdentificacion);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }

    /**
     * Método para guardar los cambios realizados en la edición de un usuario.
     * 
     * @param numIdentificacion El número de identificación del usuario a editar.
     * @param usuario           El objeto Usuario con los datos actualizados del
     *                          usuario.
     * @return El nombre de la vista a redireccionar.
     */
    @PostMapping("/usuarios/{numIdentificacion}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("numIdentificacion") String numIdentificacion,
            @ModelAttribute Usuario usuario) {
        usuarioRepository.actualizarUsuario(usuario.getContrasena(), usuario.getNombre(), usuario.getApellido(),
                usuario.getNacionalidad(), usuario.getDireccion(), usuario.getEmail(), usuario.getTelefono(),
                usuario.getCiudad(), usuario.getDepartamento(), usuario.getCodigoPostal(), numIdentificacion);
        return "redirect:/usuarios";
    }

    /**
     * Método para eliminar un usuario existente.
     * 
     * @param numIdentificacion El número de identificación del usuario a eliminar.
     * @return El nombre de la vista a redireccionar.
     */
    @GetMapping("/usuarios/{numIdentificacion}/delete")
    public String usuarioEliminar(@PathVariable("numIdentificacion") String numIdentificacion) {
        usuarioRepository.eliminarUsuario(numIdentificacion);
        return "redirect:/usuarios";
    }
}
