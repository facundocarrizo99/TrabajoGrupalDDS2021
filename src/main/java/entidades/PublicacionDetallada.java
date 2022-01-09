package entidades;

import entidades.formularios.Pregunta;
import entidades.formularios.Respuesta;
import entidades.mascota.Mascota;
import entidades.usuario.Voluntario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@DiscriminatorValue("Si")
public class PublicacionDetallada extends Publicacion {

    @OneToMany(mappedBy = "publicacion", cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Respuesta> preguntasContestadas;

    /////////////////////   Constructors    //////////////////////////

    public PublicacionDetallada() {
        super();
    }

    public PublicacionDetallada(List<Respuesta> preguntasContestadas) {
        this.preguntasContestadas = preguntasContestadas;
    }

    public PublicacionDetallada(Mascota mascotaPerdida, LocalDateTime fechaCreacion, Boolean aprobada, Voluntario aprobador, String aliasRescatista) {
        super(mascotaPerdida, fechaCreacion, aprobada, aprobador, aliasRescatista);           //aliasRescatista es el user de quien sube la publicacion
        this.preguntasContestadas = new ArrayList<Respuesta>();
    }

    /////////////////////   Getters & Setters   //////////////////////

    public List<Respuesta> getPreguntasContestadas() {
        return preguntasContestadas;
    }

    public void setPreguntasContestadas(List<Respuesta> preguntasContestadas) {
        this.preguntasContestadas = preguntasContestadas;
    }


    @Override
    public String toString() {
        return "PublicacionDetallada{" +
                "preguntasContestadas=" + preguntasContestadas +
                ", aliasRescatista='" + aliasRescatista + '\'' +
                ", formulario=" + formulario +
                ", fechaHoraCreacion=" + fechaHoraCreacion +
                ", aprobador=" + aprobador +
                ", mascota=" + mascota +
                ", aprobada=" + aprobada +
                ", habilitada=" + habilitada +
                '}';
    }

    public void addRespuesta(Respuesta respuesta){
        Objects.requireNonNull(respuesta, "La pregunta de la publicacion detallada no puede ser null");
        this.preguntasContestadas.add(respuesta);
    }

    public void addRespuestas(Set<Respuesta> respuestas){
        Objects.requireNonNull(respuestas, "La pregunta de la publicacion detallada no puede ser null");
        this.preguntasContestadas.addAll(respuestas);
    }

    @Override
    public Mascota getMascota() { return mascota; }

    @Override
    public void setMascota(Mascota mascota) { this.mascota = mascota; }


    public void getPreguntasDeOrganizacion(Organizacion organizacion){
       // organizacion.getPreguntasAdopcion().stream().forEach(pregunta -> this.preguntasContestadas.add(pregunta));
    }


/*
    public void contestarPregunta(String pregunta, String opcion){
        for(Respuesta preg: this.preguntasContestadas){
            if(respuesta.equals(preg)){
            }
        }
    }

    public void contestarPregunta(Pregunta pregunta, List<String> opcion){
        for(Pregunta preg: this.preguntasContestadas){
            if(pregunta.equals(preg)){
            }
        }
    }

    public void contestarPregunta(String pregunta, String opcion){
        for(Pregunta preg: this.preguntasContestadas){
            if(preg.getPregunta().equals(pregunta)){
            }
        }
    }

    public void contestarPregunta(String pregunta, List<String> opcion){
        for(Pregunta preg: this.preguntasContestadas){
            if(preg.getPregunta().equals(pregunta)){
            }
        }
    }

 */

}
