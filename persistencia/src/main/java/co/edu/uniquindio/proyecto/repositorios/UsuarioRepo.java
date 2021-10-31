package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {


    //================================= REPOSITORIO DE USUARIO =================================//

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios();

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Pageable pageable);

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Sort sort);

    Usuario findByEmailAndPassword(String email,String password);

    Optional<Usuario> findByNickname(String nickname);

    Usuario findByEmail(String email);

    List<Usuario> findByNombre(String nombre);


}
