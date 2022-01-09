package entidades.formularios;

import entidades.db.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "opcion")
public class Opcion extends EntidadPersistente {

    @Column
    private String opcion;

    @Column
    private Boolean activo;                   //true si la opcion esta escogida, false si no esta escogida

    @Column
    private Boolean seleccionada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pregunta_id")
    private Pregunta pregunta;

    public Opcion() {
        this.activo = true;
        this.seleccionada = false;
    }

    public Opcion(String opcion) {
        this.opcion = opcion;
        this.activo = true;
        this.seleccionada = false;
    }

    //////////  GETTERS & SETTERS   //////////////


    public Boolean getSeleccionada() { return seleccionada; }

    public void setSeleccionada(Boolean seleccionada) { this.seleccionada = seleccionada; }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public void seleccionarOpcion() {
        this.seleccionada = true;
    }

    public void habilitarOpcion() { this.activo = true; }

    public void desHabilitarOpcion() { this.activo = false; }

    @Override
    public String toString() {
        return "Opcion{" +
                "opcion='" + opcion + '\'' +
                ", activo=" + activo +
                ", seleccionada=" + seleccionada +
                '}';
    }
}
