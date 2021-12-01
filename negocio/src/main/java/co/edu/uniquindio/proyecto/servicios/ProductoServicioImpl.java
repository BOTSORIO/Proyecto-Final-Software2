package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl  implements ProductoServicio {

    private final ProductoRepo productoRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    @Override
    public Producto registrarProducto(Producto p) throws Exception {

        if (p.getNombre().length() >100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        return productoRepo.save(p);
    }

    @Override
    public void actualizarProducto(Producto p, String nombre) throws Exception {

        Producto productoEncontrado = obtenerProductoNombre(nombre);

        if (productoEncontrado!=null){
            productoEncontrado.setNombre(p.getNombre());
            productoEncontrado.setDescripcion(p.getDescripcion());
            productoEncontrado.setPrecio(p.getPrecio());
        }else {
            throw new Exception("El producto a actualizar no existe");
        }

    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

        productoRepo.save(p);
    }

    @Override
    public void eliminarProducto(String nombre) throws Exception {

        Producto productoEncontrado = obtenerProductoNombre(nombre);

        if (productoEncontrado!=null){
            productoRepo.delete(productoEncontrado);
        }else{
            throw new Exception("El producto a eliminar no existe");
        }
    }

    @Override
    public Producto obtenerProducto(int id) throws Exception {

        Optional<Producto> productoEncontrado = productoRepo.findById(id);

        if (productoEncontrado.isEmpty()){
            throw new Exception("El producto buscado no existe");
        }

        return productoEncontrado.get();
    }

    @Override
    public Producto obtenerProductoNombre(String nombre) throws Exception {

        Producto productoEncontrado = productoRepo.obtenerProductoNombre(nombre);

        if (productoEncontrado==null){
            throw new Exception("El producto buscado no existe");
        }

        return productoEncontrado;
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepo.findAll();
    }
}
