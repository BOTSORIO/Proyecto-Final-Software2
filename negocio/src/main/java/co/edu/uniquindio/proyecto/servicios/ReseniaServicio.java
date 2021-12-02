package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Resenia;

import java.util.List;

public interface ReseniaServicio {

    Resenia registrarResenia(Resenia resenia) throws Exception;

    List<Resenia> obtenerReseniasProducto(int idProducto) throws Exception;

    List<Resenia> listarResenias();
}
