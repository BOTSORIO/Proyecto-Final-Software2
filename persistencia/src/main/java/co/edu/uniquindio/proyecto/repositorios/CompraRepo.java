package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra,Integer> {

    @Query("select c from Compra c where c.id =:idServicio and c.usuario.id =:cedulaUsuario")
    Compra obtenerServicioUsuario(int idServicio,String cedulaUsuario);

    @Query("select c from Compra c where c.usuario.id =:cedulaU")
    List<Compra> obtenerhistorialServiciosUsuario(String cedulaU);

    @Query("select c from Compra c where c.usuario.id =:cedulaU and c.estado = true")
    List<Compra> obtenerServiciosActivosUsuario(String cedulaU);

}
