package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Mascota implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre",length =100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Column(name = "raza",length =50)
    @NotBlank
    @Size(max = 200)
    private String raza;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    @Column(name = "cantidad_vacunas")
    @Positive
    private int cantidadVacunas;

    @Column(name = "peso")
    private double peso;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Trabajador trabajador;

    //================================= RELACION CON LA ENTIDAD TIPO =================================//
    @ManyToOne
    @JsonIgnore
    private TipoMascota tipo;

    //================================= RELACION CON LA ENTIDAD IMAGEN =================================//
    @OneToMany(mappedBy = "mascota",fetch=FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<Imagen> imagenes;

    //================================= RELACION CON LA ENTIDAD SERVICIO =================================//
    @ManyToOne
    private Servicio servicio;

    //================================= CONSTRUCTOR  =================================//
    @Builder
    public Mascota(String nombre, String raza, Date fechaRegistro) {

        this.nombre = nombre;
        this.raza = raza;
        this.fechaRegistro = fechaRegistro;
        this.imagenes = new ArrayList<>();
    }

    @Builder
    public Mascota(String nombre, String raza, Date fechaRegistro, Usuario usuario, List<Imagen>imagenes, TipoMascota tipo) {

        this.nombre = nombre;
        this.raza = raza;
        this.fechaRegistro = fechaRegistro;
        this.imagenes = imagenes;
        this.usuario  = usuario;
        this.tipo = tipo;

    }

    public String getImagenPrincipal(){

        if(imagenes!=null && !imagenes.isEmpty()){

            return imagenes.get(0).getUrl();
        }

        return "default.png";
    }

}
