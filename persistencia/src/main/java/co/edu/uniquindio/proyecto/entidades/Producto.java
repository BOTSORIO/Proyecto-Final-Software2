package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    private String nombre;

    @Column(name = "descripcion",length = 500)
    private String descripcion;

    @Column(name = "precio")
    private double precio;

    //================================= RELACION CON LA ENTIDAD COMPRA =================================//
    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    @JsonIgnore
    private List<CompraProducto> comprasProducto;

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACION CON LA ENTIDAD CATEGORIAPRODUCTO =================================//
    @ManyToOne
    private CategoriaProducto categoria;

    //================================= RELACION CON LA ENTIDAD IMAGEN =================================//
    @OneToMany(mappedBy = "producto",fetch=FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<Imagen> imagenes;

    public Producto(String nombre, String descripcion, double precio, Administrador administrador, CategoriaProducto categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.administrador = administrador;
        this.categoria = categoria;
    }

    public String getImagenPrincipal(){

        if(imagenes!=null && !imagenes.isEmpty()){

            return imagenes.get(0).getUrl();
        }

        return "default.png";
    }
}
