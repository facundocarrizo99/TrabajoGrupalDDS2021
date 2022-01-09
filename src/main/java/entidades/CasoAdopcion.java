package entidades;

import entidades.db.EntidadPersistente;
import entidades.formularios.Formulario;
import entidades.mascota.Mascota;
import entidades.usuario.Voluntario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "casoAdopcion")
public class CasoAdopcion extends EntidadPersistente {

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "formulario_id")
    private Formulario formulario;

    @Transient
    private Random random;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaAhora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publicacionDetallada")
    private PublicacionDetallada publicacionDetallada;

    @Column
    private Boolean status;

    //////////////////////  Constructors    /////////////////////////////////

    public CasoAdopcion() {
        this.random = new Random();
        Integer id = random.nextInt(100000);
        this.fechaAhora = LocalDate.now();
        this.formulario = new Formulario();
    }

    ////////////////////  GETTERS & SETTERS   /////////////////////////////
    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public PublicacionDetallada getPublicacionDetallada() {
        return publicacionDetallada;
    }

    public void setPublicacionDetallada(PublicacionDetallada publicacionDetallada) {
        this.publicacionDetallada = publicacionDetallada;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }




    public void completarFormulario(Mascota mascota, Persona creador) {
        this.formulario.setCreador(creador);
        this.formulario.setMascotaPerdida(mascota);
    }


    // El metodo se llama pasandole por parametro las preguntas contestadas
    public void generarPublicacionDetallada(Map<String, String> preguntasContestadas){
        this.publicacionDetallada = new PublicacionDetallada((Mascota)this.formulario.getMascotaPerdida(),         //genera una publi detallada
                                                                       LocalDateTime.now(), false,         //con lo que tiene el formulario
                                                            (Voluntario) this.formulario.getDestinatario(),
                                                                 this.formulario.getCreador().getNombre());

        //this.publicacionDetallada.addPreguntas(this.formulario.getMascotaPerdida().getOrganizacion().getPreguntasAdopcion());     //asdasdasdcc la publi le agrega las preguntas de la orga de la mascota
        for(String key: preguntasContestadas.keySet()){                                                                           //contesta las preguntas que se le pasan por parametro
            //this.publicacionDetallada.contestarPregunta(key, preguntasContestadas.get(key));
        }

        //this.formulario.getMascotaPerdida().getOrganizacion().notificarAprobacionPublicacionPendiente();

    }

    public String mostrarInteres(Persona persona){
        String mensajeNotificar = "Buenas noticias, la persona: " + persona.getNombre() +" " + persona.getApellido() + " " + "esta interesado en adoptar asdasdasdcc tu mascota, puedes comunicarte con el interesado mediante: " + persona.getTelefono();
        return this.formulario.getCreador().notificar(mensajeNotificar);
    }

    public String toString() {
        return "CasoAdopcion{" +
                "formulario=" + formulario.toString() +
                ", random=" + random.toString() +
                ", fechaAhora=" + fechaAhora.toString() +
                ", publicacionDetallada=" + publicacionDetallada.toString() +
                ", status=" + status +
                '}';
    }
}
