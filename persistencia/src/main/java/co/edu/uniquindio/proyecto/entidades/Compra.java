package co.edu.uniquindio.proyecto.entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "horaInicio", length = 100)
    private Date horaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "horaFin", length = 100)
    private Date horaFin;

    @Column(name = "estado")
    private Boolean estado;

    //================================= RELACION CON LA ENTIDAD SERVICIO=================================//
    @ManyToOne
    @ToString.Exclude
    private Servicio servicio;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
