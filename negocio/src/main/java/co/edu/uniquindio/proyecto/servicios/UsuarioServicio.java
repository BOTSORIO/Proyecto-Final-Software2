package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    void actualizarUsuario(String email, String password, Usuario u) throws Exception;

    void actualizar(String id,Usuario u) throws Exception;

    void eliminarUsuario(String email, String password) throws Exception;

    void registrarTarjetaUsuario(String idUsuario,String numero,String codigo,String fecha)throws Exception;

    void eliminarUsuarioId(String id) throws Exception;

    Usuario obtenerUsuario(String id) throws Exception;

    Usuario obtenerUsuarioEmailPassword(String email, String password) throws Exception;

    Mascota obtenerMascotaUsuario(String nombreMascota,String idUsuario) throws Exception;

    List<Mascota> obtenerMascotasPorUsuario(String idUsuario) throws Exception;

    List<Usuario> listarUsuarios();

}
