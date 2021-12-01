package co.edu.uniquindio.proyecto.entidades;

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
public class CompraProducto implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "horaCompra", length = 100)
    private Date horaCompra;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD PRODUCTO =================================//
    @ManyToOne
    @ToString.Exclude
    private Producto producto;
}
