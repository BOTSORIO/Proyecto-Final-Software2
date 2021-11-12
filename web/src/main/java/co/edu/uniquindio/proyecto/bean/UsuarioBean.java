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
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private MascotaServicio mascotaServicio;

    @Autowired
    @Getter @Setter
    private CiudadServicio ciudadServicio;

    @Autowired
    private MailService mailService;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private Usuario usuarioAux;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Mascota>mascotasUsuario;


    @PostConstruct
    public void inicializar() {
        this.usuario  = new Usuario();
        this.usuarioAux = obtenerUsuario();
        this.ciudades = ciudadServicio.listarCiudades();
        this.mascotasUsuario = obtenerMascotasUsuario();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! te registramos correctamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void registrarTarjetaUsuario(){

        try {

            usuarioServicio.registrarTarjetaUsuario(personaLogin.getId(),usuario.getNumeroTarjeta(),usuario.getCodigoTarjeta(),usuario.getFechatarjeta());
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! tu tarjeta se ha registrado con exito");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }catch (Exception e){

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public String eliminarUsuario(){

        try {
            if (personaLogin!=null) {

                usuarioServicio.eliminarUsuario(usuario.getEmail(),usuario.getPassword());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }

        return null;
    }


    public String actualizarUsuario(){

        try{

            if(personaLogin!=null){

                usuarioServicio.actualizarUsuario(personaLogin.getEmail(),personaLogin.getPassword(), usuario);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
        return null;
    }


    public List<Mascota> obtenerMascotasUsuario(){

        List<Mascota> mascotasU = null;

        if (personaLogin!=null){
            try{
                mascotasU= usuarioServicio.obtenerMascotasPorUsuario(personaLogin.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mascotasU;

    }

    public Usuario obtenerUsuario(){

        Usuario usuarioEncontrado = new Usuario();

        if (personaLogin!=null){

            try {
                usuarioEncontrado = usuarioServicio.obtenerUsuario(personaLogin.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return usuarioEncontrado;
    }


    public void sendMailRespuesta(String respuesta,String email){

        String subject = "En hora buena, alguien respondio tu comentario";
        String message = respuesta;

        mailService.sendMail("unilocal0804@gmail.com", email,subject,message);

    }

}
