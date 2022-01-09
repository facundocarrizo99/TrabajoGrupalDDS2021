package entidades.usuario;

import entidades.mascota.Mascota;
import entidades.Persona;
import entidades.mascota.MascotaRegistrada;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("duenio")
public class Duenio extends Usuario {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id")
    private Persona persona;

    //////////Constructors//////////////

    public Duenio() {
    }

    public Duenio(Persona unaPersona) {
        persona = unaPersona;
    }

    public Duenio(String usuario, String contrasena, Persona persona) {
        super(usuario, contrasena);
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void registrarMascota(Mascota mascota) {
        this.persona.addMascota(mascota);
        if (mascota instanceof MascotaRegistrada){
            ((MascotaRegistrada) mascota).setDuenio(this);
        }
    }

    public List<Mascota> traerMascotas() { return this.persona.getMascotas(); }

    public void confirmarMascotaEncontrada(Mascota mascota) {}

    public void ponerMiMascotaEnAdopcion(Mascota mascota){}

    public void quererAdoptarMascota() {}

    public void adoptarMascota (Mascota mascota) {
        this.persona.addMascota(mascota);
    }


}