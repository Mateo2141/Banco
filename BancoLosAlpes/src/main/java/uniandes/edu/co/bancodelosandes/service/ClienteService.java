package uniandes.edu.co.bancodelosandes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniandes.edu.co.bancodelosandes.modelo.Cliente;
import uniandes.edu.co.bancodelosandes.modelo.Usuario;
import uniandes.edu.co.bancodelosandes.repositorio.ClienteRepository;
import uniandes.edu.co.bancodelosandes.repositorio.CuentaRepository;
import uniandes.edu.co.bancodelosandes.repositorio.PuntoAtencionRepository;
import uniandes.edu.co.bancodelosandes.repositorio.UsuarioRepository;
import uniandes.edu.co.bancodelosandes.modelo.Cuenta;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private PuntoAtencionRepository puntoAtencionRepository;

    public Cliente crearCliente(Cliente cliente) {
        Optional<Usuario> usuario = usuarioRepository.findById(cliente.getUsuario().getNumIdentificacion());
        if (usuario.isPresent()) {
            cliente.setUsuario(usuario.get());
            return clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }



    public void borrarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cuenta crearCuentaParaCliente(Integer clienteId, Cuenta cuenta) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            cuenta.setCliente(cliente.get());
            Cuenta nuevaCuenta = cuentaRepository.save(cuenta);
            cliente.get().getCuentas().add(nuevaCuenta);
            clienteRepository.save(cliente.get());
            return nuevaCuenta;
        } else {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
    }

    public PuntoAtencion asignarPuntoAtencionACliente(Integer clienteId, Integer puntoAtencionId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        Optional<PuntoAtencion> puntoAtencion = puntoAtencionRepository.findById(puntoAtencionId.toString());

        if (cliente.isPresent() && puntoAtencion.isPresent()) {
            cliente.get().getPuntosAtencion().add(puntoAtencion.get());
            clienteRepository.save(cliente.get());
            return puntoAtencion.get();
        } else {
            throw new IllegalArgumentException("Cliente o Punto de Atención no encontrado");
        }
    }
}