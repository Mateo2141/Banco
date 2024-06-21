package uniandes.edu.co.bancodelosandes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniandes.edu.co.bancodelosandes.modelo.Oficina;
import uniandes.edu.co.bancodelosandes.modelo.PuntoAtencion;
import uniandes.edu.co.bancodelosandes.repositorio.OficinaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    public Oficina crearOficina(Oficina oficina) {
        if (oficina.getPuntosAtencion() == null) {
            oficina.setPuntosAtencion(new ArrayList<>()); // Inicializar si es null
        }
        return oficinaRepository.save(oficina);
    }

    public Optional<Oficina> obtenerOficinaPorId(String id) {
        return oficinaRepository.findById(id);
    }

    public void borrarOficina(String id) {
        oficinaRepository.deleteById(id);
    }

    public PuntoAtencion agregarPuntoAtencion(String oficinaId, PuntoAtencion puntoAtencion) {
        Oficina oficina = obtenerOficinaPorId(oficinaId).orElseThrow(() -> new IllegalArgumentException("Oficina no encontrada"));
        if (oficina.getPuntosAtencion() == null) {
            oficina.setPuntosAtencion(new ArrayList<>()); // Inicializar si es null
        }
        oficina.getPuntosAtencion().add(puntoAtencion);
        oficina.setNumPuntosAtencion(oficina.getPuntosAtencion().size()); // actualizar el número de puntos de atención
        oficinaRepository.save(oficina);
        return puntoAtencion;
    }

    public void borrarPuntoAtencion(String oficinaId, String puntoAtencionId) {
        Oficina oficina = obtenerOficinaPorId(oficinaId).orElseThrow(() -> new IllegalArgumentException("Oficina no encontrada"));
        if (oficina.getPuntosAtencion() != null) {
            oficina.getPuntosAtencion().removeIf(pa -> pa.getId().equals(puntoAtencionId));
            oficina.setNumPuntosAtencion(oficina.getPuntosAtencion().size()); // actualizar el número de puntos de atención
            oficinaRepository.save(oficina);
        }
    }

    public List<Oficina> obtenerOficinasPorNombre(String nombre) {
        return oficinaRepository.buscarPorNombre(nombre);
    }

    public void actualizarPuntoAtencion(String oficinaId, String puntoAtencionId, PuntoAtencion puntoAtencion) {
        Oficina oficina = obtenerOficinaPorId(oficinaId).orElseThrow(() -> new IllegalArgumentException("Oficina no encontrada"));
        if (oficina.getPuntosAtencion() != null) {
            oficina.getPuntosAtencion().removeIf(pa -> pa.getId().equals(puntoAtencionId));
            oficina.getPuntosAtencion().add(puntoAtencion);
            oficinaRepository.save(oficina);
        }
    }
}
