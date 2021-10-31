package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class AdministradorBean implements Serializable {

    @Autowired
    private TrabajadorServicio trabajadorServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private Administrador administrador;

    @Getter @Setter
    private Trabajador trabajador;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;


    @PostConstruct
    public void inicializar() {
        this.trabajador = new Trabajador();
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

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador se ha creado con exito");
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
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador ha sido eliminado con exito");
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
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador se actualizo con exito");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No hemos podido encontrar el moderador");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }

}
