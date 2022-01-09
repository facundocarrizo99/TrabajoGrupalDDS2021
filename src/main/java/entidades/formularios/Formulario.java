package entidades.formularios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.Caso;
import entidades.Persona;
import entidades.Ubicacion;
import entidades.db.EntidadPersistente;
import entidades.mascota.*;
import entidades.usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "formulario")
public class Formulario extends EntidadPersistente {

    @Transient
    private Random random;

    @Transient
    private String formularioID;

    @ElementCollection
    private List<String> fotosMascotaPerdida = new ArrayList<String>();

    @Column
    private String estadoMascotaPerdida;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacionHallazgoMascotaPerdida;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "persona_id")
    private Persona creador;

    @ManyToOne(cascade = {CascadeType.ALL})                             //Si el form es hallazgo con chapita destinatario es duenio
    @JoinColumn(name = "destinatario_id")                               //Si el form es hallazgo sin chapita esto es null
    private Usuario destinatario;

    @Column(name = "fechaCreacion", columnDefinition = "DATE")
    private LocalDate fechaCreacion;

    //////////////    Constructors    ////////////

    public Formulario() {
        this.random = new Random();
        Integer id = random.nextInt(100000);
        this.formularioID = id.toString();
    }

    //Usar para crear Formulario para mascota perdida con chapita
    public Formulario(String formularioID, Mascota mascota, Persona creador, Usuario destinatario, LocalDate fechaCreacion) {
        this.random = new Random();
        Integer id = random.nextInt(100000);
        this.formularioID = id.toString();
        this.creador = creador;
        this.destinatario = destinatario;
        this.fechaCreacion = fechaCreacion;
    }

    //Usar para Formulario para mascota perdida sin chapita y dar mascota en adopcion
    public Formulario(Mascota mascota, Persona creador, LocalDate fechaCreacion, String estadoMascotaPerdida, Ubicacion ubicacionHallazgoMascotaPerdida) {
        this.random = new Random();
        Integer id = random.nextInt(100000);
        this.formularioID = id.toString();
        this.creador = creador;
        this.fechaCreacion = fechaCreacion;
        this.estadoMascotaPerdida = estadoMascotaPerdida;
        this.ubicacionHallazgoMascotaPerdida = ubicacionHallazgoMascotaPerdida;
    }

        // Aca deberia haber algun llamado asdasdasdcc algun metodo de alguna clase para que asigne como destinatario al voluntario de la org mas
        // cercana al lugar donde se encontro asdasdasdcc la mascota perdida

        //Creo que es mejor asignarlo como dueño asdasdasdcc la organización o asdasdasdcc algún voluntario de la misma.


    //////////////      Getters y setters    ////////////

    public String getFormularioID() {
        return this.formularioID;
    }

    public void setFormularioID(String formularioID) {
        this.formularioID = formularioID;
    }

    public Persona getCreador() {
        return creador;
    }

    public void setCreador(Persona creador) {
        this.creador = creador;
    }

    public Usuario getDestinatario() {
        return this.destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<String> getFotosMascotaPerdida() {
        return fotosMascotaPerdida;
    }

    public void setFotosMascotaPerdida(List<String> fotosMascotaPerdida) {
        this.fotosMascotaPerdida = fotosMascotaPerdida;
    }

    public void addFotoMascotaPerdida(String rutaDefotoMascotaPerdida) {
        this.fotosMascotaPerdida.add(rutaDefotoMascotaPerdida);
    }

    public String getEstadoMascotaPerdida() {
        return estadoMascotaPerdida;
    }

    public void setEstadoMascotaPerdida(String estadoMascotaPerdida) {
        this.estadoMascotaPerdida = estadoMascotaPerdida;
    }

    public Ubicacion getUbicacionHallazgoMascotaPerdida() {
        return ubicacionHallazgoMascotaPerdida;
    }

    public void setUbicacionHallazgoMascotaPerdida(Ubicacion ubicacionHallazgoMascotaPerdida) {
        this.ubicacionHallazgoMascotaPerdida = ubicacionHallazgoMascotaPerdida;
    }

    public Caso getCaso() { return null;}                                         //deprecated

    public void setCaso(Caso caso) {}                                //deprecated

    public Mascota getMascotaPerdida() {return null;}                                  //deprecated

    public void setMascotaPerdida(Mascota mascotaPerdida) {}               //deprecated

    @Override
    public String toString() {
        return "Formulario{" +
                ", formularioID='" + formularioID + '\'' +
                ", fotosMascotaPerdida=" + fotosMascotaPerdida +
                ", estadoMascotaPerdida='" + estadoMascotaPerdida + '\'' +
                ", ubicacionHallazgoMascotaPerdida=" + ubicacionHallazgoMascotaPerdida.toString() +
                ", creador=" + creador.toString() +
                ", destinatario=" + destinatario.toString() +
                ", fechaCreacion=" + fechaCreacion.toString() +
                '}';
    }
}