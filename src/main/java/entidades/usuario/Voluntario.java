package entidades.usuario;

import entidades.Organizacion;
import entidades.Persona;
import entidades.Publicacion;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("voluntario")
public class Voluntario extends Usuario {

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="persona_id")
    private Persona persona;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public void aprobarPublicacion(Publicacion publicacion) {
        publicacion.setAprobada(true);
    }

    public void gestionarPreguntasDeAdopcion() {

    }

    public void rechazarPublicacion(Publicacion publicacion) {
        publicacion.setHabilitada(false);
    }

    //////////////  Getters y setters   ////////////

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    //////////////  Constructors   ////////////


    public Voluntario() {
    }

    public Voluntario(String usuario, String contrasena, Organizacion organizacion) {
        super(usuario, contrasena);
        this.organizacion = Objects.requireNonNull(organizacion, "La organizacion no puede ser null");
    }
}