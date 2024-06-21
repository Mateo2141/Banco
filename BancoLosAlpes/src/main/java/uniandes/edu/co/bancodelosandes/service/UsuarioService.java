package uniandes.edu.co.bancodelosandes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniandes.edu.co.bancodelosandes.modelo.Usuario;
import uniandes.edu.co.bancodelosandes.repositorio.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(String id) {
        return Optional.ofNullable(usuarioRepository.buscarPorId(id));
    }

    public void borrarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        return usuarioRepository.buscarPorNombre(nombre);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}
