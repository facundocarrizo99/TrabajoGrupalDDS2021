package entidades;

import entidades.caracteristicas.Caracteristica;
import entidades.Email;
import entidades.db.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publicacion_busca_adopcion")
public class PublicacionBuscaAdopcion extends EntidadPersistente {

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Caracteristica> preferencias;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Comodidad> comodidades;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "persona_id")
    private Persona persona;

    //////////////    Constructors    ////////////


    public PublicacionBuscaAdopcion() {}

    public PublicacionBuscaAdopcion(List<Caracteristica> preferencias, List<Comodidad> comodidades, Persona persona) {
        this.preferencias = preferencias;
        this.comodidades = comodidades;
        this.persona = persona;
    }

    //////////////  Getters y setters   ////////////

    public void generarPublicacion(){
        Email mail = new Email("patitas@org.com");
        mail.notificar("¡Gracias por buscar una mascota con nosotros! mail de baja: www.link.com.ar/baja", persona);
        //publicacion push asdasdasdcc un repositorio de publicaciones
    }

    public List<Comodidad> getComodidades() {
        return comodidades;
    }

    public void setComodidades(List<Comodidad> comodidades) {
        this.comodidades = comodidades;
    }

    public List<Caracteristica> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Caracteristica> preferencias) {
        this.preferencias = preferencias;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "PublicacionBuscaAdopcion{" +
                "preferencias=" + preferencias +
                ", comodidades=" + comodidades +
                ", persona=" + persona +
                '}';
    }
}
