package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class AdministradorBean implements Serializable {

    @Autowired
    private TrabajadorServicio trabajadorServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    @Autowired
    private CategoriaProductoServicio categoriaServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Getter @Setter
    private Administrador administrador;

    @Getter @Setter
    private Trabajador trabajador;

    @Getter @Setter
    private Servicio servicio;

    @Getter @Setter
    private CategoriaProducto categoria;

    @Getter @Setter
    private Producto producto;

    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<Imagen> imagenes;

    @Getter
    @Setter
    private List<CategoriaProducto> categorias;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;


    @PostConstruct
    public void inicializar() {
        this.trabajador = new Trabajador();
        this.servicio= new Servicio();
        this.categoria= new CategoriaProducto();
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        this.categorias = categoriaServicio.listarCategorias();
        this.administrador = obtenerAdministrador();

    }

    public Administrador obtenerAdministrador(){

        Administrador administradorEncontrado = new Administrador();

        if(personaLogin!=null){

            try{

                administradorEncontrado = administradorServicio.obtenerAdministrador(personaLogin.getId());
                personaLogin.toString();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return administradorEncontrado;
    }


    public void registrarTrabajador() {
        try {
            if (personaLogin != null) {
                trabajador.setAdministrador((Administrador) personaLogin);
                trabajadorServicio.registrarTrabajador(trabajador);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el trabajador se ha creado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public void eliminarTrabajador(){

        try {
            if (personaLogin!=null ) {
                trabajador.setAdministrador(null);
                trabajadorServicio.eliminarTrabajador(trabajador.getEmail(), trabajador.getPassword());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el trabajador ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void actualizarTrabajador(){

        try{
            if(personaLogin!=null){

                Trabajador trabajadorAux= trabajadorServicio.obtenerTrabajador(trabajador.getId());

                if (trabajadorAux!=null){

                    trabajador.setAdministrador((Administrador) personaLogin);
                    trabajadorServicio.actualizarTrabajador(trabajador,trabajadorAux.getEmail(),trabajadorAux.getPassword());
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el trabajador se actualizo con exito");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No hemos podido encontrar el trabajador");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }


    public void registrarServicio() {
        try {
            if (personaLogin != null) {
                servicio.setAdministrador((Administrador) personaLogin);
                servicioServicio.registrarServicio(servicio);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el servicio se ha creado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public void eliminarServicio(){

        try {
            if (personaLogin!=null ) {
                servicio.setAdministrador(null);
                servicioServicio.eliminarServicio(servicio.getId());

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el servicio ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void actualizarServicio(){

        try{
            if(personaLogin!=null){

                Servicio servicioAux = servicioServicio.obtenerServicio(servicio.getId());

                if (servicioAux!=null){

                    servicio.setAdministrador((Administrador) personaLogin);
                    servicioServicio.actualizarServicio(servicio,servicioAux.getId());
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el servicio se actualizo con exito");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No hemos podido encontrar el servicio");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }



    public String registrarProducto() {

        try {
            if (personaLogin != null) {
                if (!imagenes.isEmpty()){

                    producto.setAdministrador((Administrador) personaLogin);

                    Producto productoCreado = productoServicio.registrarProducto(this.producto);

                    for (Imagen i : imagenes) {
                        i.setProducto(productoCreado);
                        imagenServicio.registrarImagen(i);
                    }

                   productoCreado.setImagenes(imagenes);

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el producto se creo correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return null;
    }


    public void eliminarProducto(){

        try {
            if (personaLogin!=null ) {

                producto.setAdministrador(null);
                producto.setCategoria(null);
                productoServicio.eliminarProducto(producto.getId());

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el producto ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void actualizarProducto() {

        try {
            if (personaLogin != null) {

                Producto productoAux = productoServicio.obtenerProducto(producto.getId());

                if (productoAux != null) {

                    productoAux.setAdministrador((Administrador) personaLogin);
                    productoServicio.actualizarProducto(producto,productoAux.getId());

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el producto se actualizo con exito");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                } else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No hemos podido encontrar el producto");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }

    public void subirImagenes(FileUploadEvent event) {

        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);

        if (nombreImagen != null) {

            Imagen foto = new Imagen(nombreImagen);

            imagenes.add(foto);
        }
    }


    public String subirImagen(UploadedFile file) {

        try {
            InputStream input = file.getInputStream();
            String fileName = FilenameUtils.getName(file.getFileName());
            String baseName = FilenameUtils.getBaseName(fileName) + "_";
            String extension = "." + FilenameUtils.getExtension(fileName);
            File fileDest = File.createTempFile(baseName, extension, new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);

            return fileDest.getName();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }



}
