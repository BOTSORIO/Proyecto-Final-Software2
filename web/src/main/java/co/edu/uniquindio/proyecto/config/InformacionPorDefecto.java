package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private TipoServicio tipoServicio;

    @Autowired
    private MascotaServicio mascotaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()) {

            Administrador admin1 = new Administrador("1193409775", "Sebastian", "BOTSORIO", "sebas123", "sebastianquinteroosorio2104@gmail.com");
            administradorServicio.registrarAdministrador(admin1);


            Ciudad ciudad1 = new Ciudad("Calarcá");
            Ciudad ciudad3 = new Ciudad("Medellin");
            Ciudad ciudad4 = new Ciudad("Pereira");
            Ciudad ciudad5 = new Ciudad("Armenia");
            Ciudad ciudad6 = new Ciudad("Bogota");
            Ciudad ciudad7 = new Ciudad("Cucuta");
            Ciudad ciudad8 = new Ciudad("Villavicencio");
            Ciudad ciudad9 = new Ciudad("Cali");
            Ciudad ciudad10 = new Ciudad("Tulua");
            Ciudad ciudad11 = new Ciudad("Ibague");

            ciudadServicio.registrarCiudad(ciudad1);
            ciudadServicio.registrarCiudad(ciudad3);
            ciudadServicio.registrarCiudad(ciudad4);
            ciudadServicio.registrarCiudad(ciudad5);
            ciudadServicio.registrarCiudad(ciudad6);
            ciudadServicio.registrarCiudad(ciudad7);
            ciudadServicio.registrarCiudad(ciudad8);
            ciudadServicio.registrarCiudad(ciudad9);
            ciudadServicio.registrarCiudad(ciudad10);
            ciudadServicio.registrarCiudad(ciudad11);

            Usuario u = new Usuario("12345", "Braian", "Ghostbit", "braian123", "braian@mail.com");
            u.setCiudad(ciudad4);
            usuarioServicio.registrarUsuario(u);

        }

    }
}
