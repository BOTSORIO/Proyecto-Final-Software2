package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto,Integer> {

    //================================= REPOSITORIO DE PRODUCTO =================================//

    @Query("select i from Producto p join p.imagenes i where p.id = :idProducto")
    List<Imagen> obtenerImagenes(int idProducto);

    @Query("select p from Producto p where p.nombre =:nombreProducto")
    Producto obtenerProductoNombre(String nombreProducto);

    @Query("select p from Producto p where p.nombre like concat('%',:cadena,'%')")
    List<Producto> busquedaProductosNombre(String cadena);

    @Query("select r from Producto p join p.resenias r where p.id = :idProducto")
    List<Resenia> obtenerReseniasProducto(int idProducto);

}
