package entidades.formularios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entidades.PublicacionDetallada;
import entidades.db.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pregunta")
public class Pregunta extends EntidadPersistente {

    @Column
    private String nombrepregunta;

    @OneToMany(mappedBy = "pregunta", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Opcion> opciones;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "tipo_id")
    private TipoDePregunta tipo;


// Ejemplos
    //pregunta:esta vacunado      opciones: si,no,nose
    //pregunta:tamanio            opciones: chico,  mediano, grande
    //pregunta:muerde             opciones: si,no, aun no se
    //pregunta:esta castrado      opciones: si no nose
    //pregunta:edadAprox          opciones: <5, >=5  &  <=10, >10


    public Pregunta() {
        this.opciones = new ArrayList<Opcion>();
    }

    public Pregunta(String pregunta) {
        this.opciones = new ArrayList<Opcion>();
        this.nombrepregunta = Objects.requireNonNull(pregunta, "La pregunta no puede ser nula");
    }

    public void addOpcion(Opcion opcion) {
        opcion.setPregunta(this);
        this.opciones.add(opcion);
    }

    public void addOpcion(String opcion) {
        Opcion opc = new Opcion(opcion);
        opc.setPregunta(this);
        this.opciones.add(opc);
    }


    public void addOpciones(List<Opcion> opciones) {
        this.opciones.addAll(opciones);
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "nombrepregunta='" + nombrepregunta + '\'' +
                ", opciones=" + opciones +
                ", tipo=" + tipo +
                '}';
    }

    //////////  GETTERS & SETTERS   //////////////


    public String getNombrepregunta() {
        return nombrepregunta;
    }

    public void setNombrepregunta(String nombrepregunta) {
        this.nombrepregunta = nombrepregunta;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }


    public TipoDePregunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoDePregunta tipo) {
        this.tipo = tipo;
    }

}