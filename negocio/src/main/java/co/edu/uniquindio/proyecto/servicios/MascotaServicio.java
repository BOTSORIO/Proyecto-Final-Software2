package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface MascotaServicio {

    Mascota registrarMascota(Mascota l) throws Exception;

    void  actualizarMascota(Mascota l, int codigoLugar) throws Exception;

    void actualizarMascota(Mascota l) throws Exception;

    void eliminarMascota(int id) throws Exception;

    Mascota obtenerMascota(int id) throws Exception;

    Mascota obtenerMascota2(int id) throws Exception;

    List<Mascota> listarMascotas();

    List<Mascota> buscarMascotas(String nombre);


    List<Mascota> obtenerMascotasPorTipo(String tipo);

}
