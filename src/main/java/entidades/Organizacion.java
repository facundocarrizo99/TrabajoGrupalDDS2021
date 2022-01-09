package entidades;

import entidades.caracteristicas.Caracteristica;
import entidades.db.EntidadPersistente;
import entidades.formularios.Pregunta;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "organizacion")
public class Organizacion extends EntidadPersistente {

    @Column
    private String nombreOrganizacion;

    @Transient
    private INormalizadorDeImagenes adapter;

    @Transient
    private List<Caracteristica> caracteristicas;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion coordenadas;

    @Transient
    private Set<Pregunta> preguntasAdopcion;


    public Organizacion() {
        this.caracteristicas = new ArrayList<Caracteristica>();
        this.preguntasAdopcion = new HashSet<Pregunta>();
    }

    public Organizacion(String nombreOrganizacion, INormalizadorDeImagenes adapter,
                        Ubicacion coordenadas) {
        this.nombreOrganizacion = nombreOrganizacion;
        this.adapter = adapter;
        this.coordenadas = coordenadas;
        this.caracteristicas = new ArrayList<Caracteristica>();
        this.preguntasAdopcion = new HashSet<Pregunta>();
    }

    public Organizacion(String nombreOrganizacion){
        this.nombreOrganizacion = Objects.requireNonNull(nombreOrganizacion, "El nombrre de la organizacion no puede ser nulo");
        this.preguntasAdopcion = new HashSet<Pregunta>();
    }

    public Organizacion(String nombreOrganizacion, Set<Pregunta> preguntas){
        this.nombreOrganizacion = nombreOrganizacion;
        this.preguntasAdopcion = preguntas;
    }


    public int cantidadDeCaracteristicas (){
        return this.caracteristicas.size();
    }

    public void addCaracteristica(Caracteristica caracteristica){
        this.caracteristicas.add(caracteristica);
    }

    public void addCaracteristicas(List<Caracteristica> caracteristicas){
        this.caracteristicas.addAll(caracteristicas);
    }

    public void removeCaracteristica(Caracteristica caracteristica){
        this.caracteristicas.remove(caracteristica);
    }

    public void addPreguntasAdopcion(Set<Pregunta> preguntasAdopcion){
        this.preguntasAdopcion.addAll(preguntasAdopcion);
    }

    public void addPreguntaAdopcion(Pregunta preguntaAdopcion){
        this.preguntasAdopcion.add(preguntaAdopcion);
    }

    public void removePreguntaAdopcion(Pregunta preguntaAdopcion){
        this.preguntasAdopcion.remove(preguntaAdopcion);
    }

    //////////////  Getters y setters   ////////////

    public void setCoordenadas(Ubicacion coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Set<Pregunta> getPreguntas() {
        return this.preguntasAdopcion;
    }

    public void setPreguntas(Set<Pregunta> preguntas) {
        this.preguntasAdopcion = preguntas;
    }

    public INormalizadorDeImagenes getAdapter() {
        return adapter;
    }

    public void setAdapter(INormalizadorDeImagenes adapter) {
        this.adapter = adapter;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public Ubicacion getCoordenadas() {return this.coordenadas;}

    public Set<Pregunta> getPreguntasAdopcion() {
        return preguntasAdopcion;
    }

    @Override
    public String toString() {
        return "Organizacion{" +
                "nombreOrganizacion='" + nombreOrganizacion + '\'' +
                ", adapter=" + adapter +
                ", caracteristicas=" + caracteristicas +
                ", coordenadas=" + coordenadas +
                '}';
    }
}
