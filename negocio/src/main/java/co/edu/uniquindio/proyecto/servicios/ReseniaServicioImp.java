package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Resenia;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.ReseniaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseniaServicioImp implements ReseniaServicio {

    private final ReseniaRepo reseniaRepo;
    private final ProductoRepo productoRepo;

    public ReseniaServicioImp(ReseniaRepo reseniaRepo,ProductoRepo productoRepo) {
        this.reseniaRepo = reseniaRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    public Resenia registrarResenia(Resenia resenia) throws Exception {

        try{
            return reseniaRepo.save(resenia);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Resenia> obtenerReseniasProducto(int idProducto) throws Exception {

        List<Resenia>resenias = productoRepo.obtenerReseniasProducto(idProducto);

        if(resenias ==null){

            throw new Exception("No se encontraron las reseñas");
        }

        return resenias;
    }

    @Override
    public List<Resenia> listarResenias() {
        return reseniaRepo.findAll();
    }

}
