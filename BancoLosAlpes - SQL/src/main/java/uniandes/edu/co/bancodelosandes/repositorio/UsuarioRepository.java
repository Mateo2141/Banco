package uniandes.edu.co.bancodelosandes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.bancodelosandes.modelo.TipoIdentificacion;
import uniandes.edu.co.bancodelosandes.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE NUM_IDENTIFICACION = :NUM_IDENTIFICACION", nativeQuery = true)
    Usuario darUsuario(@Param("NUM_IDENTIFICACION") String NUM_IDENTIFICACION);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (TIPO_IDENTIFICACION, NUM_IDENTIFICACION, CONTRASENA, nombre, apellido, nacionalidad, direccion, email, telefono, ciudad, departamento, CODIGO_POSTAL) VALUES ( :tipoIdentificacion, :numIdentificacion, :contrasena, :nombre, :apellido, :nacionalidad, :direccion, :email, :telefono, :ciudad, :departamento, :codigoPostal)", nativeQuery = true)
    void insertarUsuario(@Param("tipoIdentificacion") String tipoIdentificacion,
            @Param("numIdentificacion") String numIdentificacion, @Param("contrasena") String contrasena,
            @Param("nombre") String nombre, @Param("apellido") String apellido,
            @Param("nacionalidad") String nacionalidad, @Param("direccion") String direccion,
            @Param("email") String email, @Param("telefono") String telefono, @Param("ciudad") String ciudad,
            @Param("departamento") String departamento, @Param("codigoPostal") Integer codigoPostal);


    //TODO: tipo de identificacion no aparece en el update
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET CONTRASENA = :contrasena, nombre = :nombre, apellido = :apellido, nacionalidad = :nacionalidad, direccion = :direccion, email = :email, telefono = :telefono, ciudad = :ciudad, departamento = :departamento, CODIGO_POSTAL = :codigoPostal WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
    void actualizarUsuario(@Param("contrasena") String contrasena, @Param("nombre") String nombre,
            @Param("apellido") String apellido, @Param("nacionalidad") String nacionalidad,
            @Param("direccion") String direccion, @Param("email") String email, @Param("telefono") String telefono,
            @Param("ciudad") String ciudad, @Param("departamento") String departamento,
            @Param("codigoPostal") Integer codigoPostal, @Param("numIdentificacion") String numIdentificacion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE NUM_IDENTIFICACION = :numIdentificacion", nativeQuery = true)
    void eliminarUsuario(@Param("numIdentificacion") String numIdentificacion);
}
