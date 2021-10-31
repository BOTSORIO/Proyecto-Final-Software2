package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Mascota;
import co.edu.uniquindio.proyecto.servicios.MascotaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Getter
@Setter
public class BusquedaBean implements Serializable {

    @Value("#{param['busqueda']}")
    private String busquedaParam;
    private String busqueda;

    private List<Mascota> mascotas;

    @Autowired
    private MascotaServicio mascotaServicio;

    @PostConstruct
    public void inicializar(){
        if (busquedaParam!=null && !busquedaParam.isEmpty()){

          mascotas =   mascotaServicio.buscarMascotas(busquedaParam);
        }
    }

    public String buscar(){
        if(!busqueda.isEmpty()){
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
        }
        return "";
    }

}
