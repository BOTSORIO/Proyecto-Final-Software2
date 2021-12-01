package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Value("#{param['producto']}")
    private  String idproducto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Producto productoAux;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<String>urlImagenes;

    @PostConstruct
    public void inicializar(){

        this.productos = productoServicio.listarProductos();
        this.usuario = new Usuario();
        this.productoAux = new Producto();

        if (idproducto!=null && !"".equals(idproducto)){
            try {
                int id = Integer.parseInt(idproducto);

                this.producto = productoServicio.obtenerProducto(id);
                this.urlImagenes = new ArrayList<>();

                List<Imagen>imagenes = producto.getImagenes();

                if(imagenes.size()>0){

                    for(Imagen i:imagenes){

                        urlImagenes.add(i.getUrl());
                    }
                }else{

                    urlImagenes.add("default.png");
                }

               List<Producto>productosAux=new ArrayList<>();
                productosAux.add(producto);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String irADetalle(int idProducto){
        return  "/detalleProducto?faces-redirect=true&amp;producto="+idProducto;
    }


    public void adquirirProducto() {

        Producto productoEncontrado;

        if(personaLogin!= null){

            try {

                usuario = usuarioServicio.obtenerUsuario(personaLogin.getId());
                productoEncontrado = productoServicio.obtenerProductoNombre(producto.getNombre());

                usuarioServicio.adquirirProducto(productoEncontrado,usuario.getNombre(),usuario.getId(),usuario.getNumeroTarjeta());


                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! has adquirido el producto");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }


}
