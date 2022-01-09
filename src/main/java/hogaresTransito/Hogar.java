package hogaresTransito;
import entidades.Ubicacion;
import entidades.db.EntidadPersistente;
import entidades.mascota.TipoDeMascota;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "hogar")

public class Hogar extends EntidadPersistente {

    @Transient
    public String id;

    @Column
    public String nombre;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ubicacion_id")
    public Ubicacion ubicacion;

    @Column
    public String telefono;

    @Transient
    public Map<String,Boolean> admisiones;

    @Column
    public Integer capacidad;

    @Column
    public Integer lugares_disponibles;

    @Column
    public Boolean patio;

    @ElementCollection
    @Column(name="caracteristicas")
    public List<String> caracteristicas;

    @Override
    public String toString() {
        return "Hogar{" +
                "  \nid='" + id + '\'' +
                ", \nnombre='" + nombre + '\'' +
                ", \nubicacion=" + ubicacion +
                ", \ntelefono='" + telefono + '\'' +
                ", \nadmisiones=" + admisiones +
                ", \ncapacidad=" + capacidad +
                ", \nlugaresDisponibles=" + lugares_disponibles +
                ", \npatio=" + patio +
                ", \ncaracteristicas=" + caracteristicas +
                '}';
    }

    public Hogar(String id, String nombre, Ubicacion ubicacion, String telefono, Map<String,Boolean> admisiones,
                 Integer capacidad, Integer lugares_disponibles, Boolean patio, List<String> caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.admisiones = admisiones;
        this.capacidad = capacidad;
        this.lugares_disponibles = lugares_disponibles;
        this.patio = patio;
        this.caracteristicas = caracteristicas;
    }

    public Hogar() {
        this.admisiones = new HashMap<>();
        this.caracteristicas = new ArrayList<>();
    }

    public List<String> getCaracteristicas() {
        return this.caracteristicas;
    }

    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public Map<String,Boolean> getAdmisiones() {
        return this.admisiones;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void addCaracteristica(String... caracteristica){
        Collections.addAll(this.caracteristicas, caracteristica);
    }

}
