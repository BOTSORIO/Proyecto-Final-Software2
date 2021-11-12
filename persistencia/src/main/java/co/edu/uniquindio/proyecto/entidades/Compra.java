package co.edu.uniquindio.proyecto.entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

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

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToOne
    @ToString.Exclude
    private Servicio servicio;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;
}
