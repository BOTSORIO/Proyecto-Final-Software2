package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ImagenRepo;
import co.edu.uniquindio.proyecto.repositorios.MascotaRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenServicioImpl implements ImagenServicio{

    private final ImagenRepo imagenRepo;
    private final MascotaRepo lugarRepo;

    public ImagenServicioImpl(ImagenRepo imagenRepo, MascotaRepo lugarRepo) {
        this.imagenRepo = imagenRepo;
        this.lugarRepo = lugarRepo;
    }

    @Override
    public Imagen registrarImagen(Imagen i) throws Exception {

        if (i.getUrl().length() > 100){
            throw  new Exception("La URL no es valida");
        }

        return imagenRepo.save(i);
    }

    @Override
    public Imagen actualizarImagen(Imagen i) throws Exception {

        if (i.getUrl().length() > 100){
            throw  new Exception("La URL no es valida");
        }

        return imagenRepo.save(i);
    }

    @Override
    public void eliminarImagen(int id) throws Exception {

        Imagen imagenEncontrada = obtenerImagen(id);

        if(imagenEncontrada != null){
            imagenRepo.delete(imagenEncontrada);
        }else {
            throw new Exception("No se encontro la imagen");
        }
    }

    @Override
    public List<Imagen> obtenerImagenesMascota(int idLugar) throws Exception {

        List<Imagen> imagenes = lugarRepo.obtenerImagenes(idLugar) ;

        if(imagenes ==null){
            throw new Exception("No se encontraron las imagenes");
        }

        return imagenes;
    }

    @Override
    public Imagen obtenerImagen(int id) throws Exception {

        Optional<Imagen> imagen = imagenRepo.findById(id);

        if(imagen.isEmpty()){
            throw  new Exception("No se encontro la imagen");
        }
        return imagen.get();
    }

    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepo.findAll();
    }
}
