package entidades.formularios;

import entidades.PublicacionDetallada;
import entidades.db.EntidadPersistente;
import entidades.formularios.Pregunta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="respuesta")
public class Respuesta extends EntidadPersistente {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

    @Column
    private String respuestaLibre;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PublicacionDetallada publicacion;


    public Respuesta() {
    }

    public Respuesta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }


    public String getRespuestaLibre() {
        return respuestaLibre;
    }

    public void setRespuestaLibre(String respuestaLibre) {
        this.respuestaLibre = respuestaLibre;
    }

    public PublicacionDetallada getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionDetallada publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "pregunta=" + pregunta +
                ", respuestaLibre='" + respuestaLibre + '\'' +
                '}';
    }
}
