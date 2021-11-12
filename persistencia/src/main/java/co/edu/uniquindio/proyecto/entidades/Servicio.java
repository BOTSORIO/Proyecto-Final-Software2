package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Servicio implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "horaInicio", length = 100)
    private String horaInicio;

    @Column(name = "horaFin", length = 100)
    private String horaFin;

    @Column(name = "precio")
    private double precio;

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACION CON LA ENTIDAD MASCOTA =================================//
    @OneToMany(mappedBy = "servicio")
    @ToString.Exclude
    @JsonIgnore
    private List<Mascota> mascotas;

    //================================= RELACION CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "servicio")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compras;

    public Servicio(String nombre, String descripcion, String horaInicio, String horaFin, Administrador administrador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.administrador = administrador;
        mascotas = new ArrayList<>();
        compras = new ArrayList<>();
    }

    public Servicio(String nombre, String descripcion, double precio,Administrador administrador) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.administrador = administrador;
        mascotas = new ArrayList<>();
        compras = new ArrayList<>();
    }



}
