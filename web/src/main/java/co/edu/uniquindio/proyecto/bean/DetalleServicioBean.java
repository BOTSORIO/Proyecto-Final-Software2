package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Servicio;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.MailService;
import co.edu.uniquindio.proyecto.servicios.ServicioServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
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


@Component
@ViewScoped
public class DetalleServicioBean implements Serializable {

    @Value("#{param['servicio']}")
    private  String idServicio;

    @Autowired
    private ServicioServicio servicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private MailService mailService;

    @Getter @Setter
    private Servicio guarderia;

    @Getter @Setter
    private Usuario usuario;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;


    @PostConstruct
    public void inicializar(){

        try {
            this.guarderia = servicio.obtenerServicioNombre("Guarderia");
            this.usuario = new Usuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void adquirirServicioGuarderia() {

        Servicio servicioEncontrado;

        if(personaLogin!= null){

            try {

                usuario = usuarioServicio.obtenerUsuario(personaLogin.getId());

                servicioEncontrado = servicio.obtenerServicioNombre("Guarderia");
                servicio.adquirirServicioGuarderia(servicioEncontrado,usuario.getNombre(),usuario.getId(),usuario.getNumeroTarjeta());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! has adquirido el servicio");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }

    public void sendMailComentario(){

        String subject = "¡Tu comentario se registro con exito!";
        String message = "Cordial saludo, este correo es para informarte que acabas de publicar un comentario, ¡gracias por tu aporte!";

        mailService.sendMail("unilocal0804@gmail.com", personaLogin.getEmail(),subject,message);

    }

    public void sendMailComentarioCreador(String comentario,String email){

        String subject = "¡Alguien comento tu publicacion!";
        String message = comentario ;

        mailService.sendMail("unilocal0804@gmail.com", email,subject,message);

    }

}
