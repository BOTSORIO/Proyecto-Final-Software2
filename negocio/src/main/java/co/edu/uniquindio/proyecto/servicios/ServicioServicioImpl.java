package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ServicioServicioImpl implements ServicioServicio{

    private final ServicioRepo servicioRepo;

    public ServicioServicioImpl(ServicioRepo servicioRepo) {
        this.servicioRepo = servicioRepo;
    }


    @Override
    public Servicio registrarServicio(Servicio s) throws Exception {

        if (s.getNombre().length() >100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        if (s.getDescripcion().length()>200){
            throw new Exception("No se puede exceder los 200 caracteres");
        }

        if(s.getHoraInicio().length() > 100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        if(s.getHoraFin().length() > 100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        return servicioRepo.save(s);
    }

    @Override
    public void actualizarServicio(Servicio s, int id) throws Exception {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado!=null){

            servicioEncontrado.setNombre(s.getNombre());
            servicioEncontrado.setDescripcion(s.getDescripcion());
            servicioEncontrado.setHoraInicio(s.getHoraInicio());
            servicioEncontrado.setHoraFin(s.getHoraFin());
        }else {
            throw new Exception("El servicio que desea modificar no existe");
        }
    }

    @Override
    public void eliminarServicio(int id) throws Exception {

        Servicio servicioEncontrado = obtenerServicio(id);

        if (servicioEncontrado != null){
            servicioRepo.delete(servicioEncontrado);
        }else {
            throw new Exception("El servicio que desea borrar no existe");
        }
    }

    @Override
    public Servicio obtenerServicio(int id) throws Exception {

        Optional<Servicio> servicioEncontrado = servicioRepo.findById(id);

        if (servicioEncontrado.isEmpty()){

            throw new Exception("No existe un servicio con el id ingresado");
        }
        return servicioEncontrado.get();
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepo.findAll();
    }
}
