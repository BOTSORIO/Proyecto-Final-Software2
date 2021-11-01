package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import java.util.List;

public interface ProductoServicio {

    Producto registrarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto p,String nombre) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(String nombre) throws Exception;

    Producto obtenerProducto(int id) throws Exception;

    Producto obtenerProductoNombre(String nombre) throws Exception;

    List<Producto> listarProductos();
}
