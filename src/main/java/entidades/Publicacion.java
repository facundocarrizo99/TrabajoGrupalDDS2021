package entidades;

import entidades.db.EntidadPersistente;
import entidades.formularios.Formulario;
import entidades.mascota.Mascota;
import entidades.usuario.Voluntario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Detallada")
@DiscriminatorValue("No")
public class Publicacion extends EntidadPersistente {

    @Column
    protected String aliasRescatista;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name= "formulario_id")
    protected Formulario formulario;

    @Column( name = "fechaHoraCreacion", columnDefinition = "DATETIME")
    protected LocalDateTime fechaHoraCreacion;

    @Transient
    protected Voluntario aprobador;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "mascota_id")            //Dejar en null si es una publicacion de mascota perdida
    protected Mascota mascota;

    @Column
    protected Boolean aprobada;

    @Column
    protected Boolean habilitada;


    //////////////    Constructors    ////////////

    public Publicacion() {
        this.habilitada = true;
        this.aprobada = true;
    }

    //cambie mascotaNoRegistrada por Mascota
    public Publicacion(Mascota mascotaPerdida, LocalDateTime fechaHoraCreacion, Boolean aprobada, Voluntario aprobador, String aliasRescatista) {
        this.mascota = mascotaPerdida;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.aprobada = aprobada;
        this.aprobador = aprobador;
        this.aliasRescatista = aliasRescatista;
        this.habilitada = true;
        this.aprobada = true;
    }

    //////////////  Getters y setters   ////////////

    public Mascota getMascota() { return mascota; }

    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public LocalDateTime getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public Boolean getAprobada() {
        return aprobada;
    }

    public void setAprobada(Boolean aprobada) {
        this.aprobada = aprobada;
    }

    public Voluntario getAprobador() {
        return aprobador;
    }

    public void setAprobador(Voluntario aprobador) {
        this.aprobador = aprobador;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(Boolean asd) {
        this.habilitada = asd;
    }

    public Formulario getFormulario() {return formulario; }

    public void setFormulario(Formulario formulario) { this.formulario = formulario; }

    public String getAliasRescatista() { return aliasRescatista; }

    public void setAliasRescatista(String aliasRescatista) { this.aliasRescatista = aliasRescatista; }

    public void aceptarPublicacion(){ this.aprobada = true; }

    public void rechazarPublicacion(){ this.habilitada = false; }


    @Override
    public String toString() {
        return "Publicacion{" +
                ", mascotaPerdida=" + mascota.toString() +
                ", aliasRescatista='" + aliasRescatista + '\'' +
                ", fechaCreacion=" + fechaHoraCreacion +
                ", formulario=" + formulario.toString() +
                ", aprobada=" + aprobada +
                ", aprobador=" + aprobador +
                '}';
    }
}
