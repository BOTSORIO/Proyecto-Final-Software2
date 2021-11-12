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
    private ServicioServicio servicio;

    @Autowired
    private CategoriaProductoServicio categoriaServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()) {

            Administrador admin1 = new Administrador("1193409775", "Sebastian", "BOTSORIO", "sebas123", "sebastianquinteroosorio2104@gmail.com");
            administradorServicio.registrarAdministrador(admin1);

            Administrador admin2 = new Administrador("1193421285", "Braian", "Ghostbit", "braian123", "brahian0987@gmail.com");
            administradorServicio.registrarAdministrador(admin2);

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

            Servicio servicio1 = new Servicio("Guarderia","Guarderia para mascotas",60000,admin1);

            servicio.registrarServicio(servicio1);

            TipoMascota tipo1 = new TipoMascota("Perro");
            TipoMascota tipo2 = new TipoMascota("Gato");
            TipoMascota tipo3 = new TipoMascota("Hamster");
            TipoMascota tipo4 = new TipoMascota("Ratón");
            TipoMascota tipo5 = new TipoMascota("Ave");

            tipoServicio.registrarTipo(tipo1);
            tipoServicio.registrarTipo(tipo2);
            tipoServicio.registrarTipo(tipo3);
            tipoServicio.registrarTipo(tipo4);
            tipoServicio.registrarTipo(tipo5);

            CategoriaProducto categoria1 = new CategoriaProducto("Medicamentos",admin1);
            CategoriaProducto categoria2 = new CategoriaProducto("Alimentos",admin1);
            CategoriaProducto categoria3 = new CategoriaProducto("Accesorios",admin2);

            categoriaServicio.registrarCategoria(categoria1);
            categoriaServicio.registrarCategoria(categoria2);
            categoriaServicio.registrarCategoria(categoria3);

        }
    }
}
