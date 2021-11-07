package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarkerDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.MascotaServicio;
import co.edu.uniquindio.proyecto.servicios.MailService;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class DetalleMascotaBean implements Serializable {

    @Value("#{param['mascota']}")
    private  String idMascota;

    @Autowired
    private MascotaServicio mascotaServicio;

    @Autowired
    private MailService mailService;

    @Getter @Setter
    private Mascota mascota;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private String icono;

    @Getter @Setter
    private List<String>urlImagenes;

    @PostConstruct
    public void inicializar(){

        if (idMascota!=null && !"".equals(idMascota)){
            try {
                int id = Integer.parseInt(idMascota);

                this.mascota = mascotaServicio.obtenerMascota(id);
                this.urlImagenes = new ArrayList<>();

                List<Imagen>imagenes = mascota.getImagenes();

                if(imagenes.size()>0){

                    for(Imagen i:imagenes){

                        urlImagenes.add(i.getUrl());
                    }
                }else{

                    urlImagenes.add("default.png");
                }

                List<Mascota>mascotas=new ArrayList<>();
                mascotas.add(mascota);

            } catch (Exception e) {
                e.printStackTrace();
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
