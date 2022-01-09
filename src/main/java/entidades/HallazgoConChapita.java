package entidades;

import entidades.formularios.Formulario;
import entidades.mascota.Mascota;
import entidades.mascota.MascotaRegistrada;
import entidades.usuario.Duenio;
import entidades.usuario.Usuario;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class HallazgoConChapita implements IHallazgo {

    public Formulario formulario;

    public Random random;
    public LocalDate fechaAhora;


    public void setFormulario(Formulario form) {
        formulario = form;
    }

    public HallazgoConChapita() {
        this.random = new Random();
        Integer id = random.nextInt(100000);
        this.fechaAhora = LocalDate.now();
        this.formulario = new Formulario(id.toString() , null, null, null, fechaAhora);
    }

    public HallazgoConChapita(Formulario formulario) {
        this.formulario = Objects.requireNonNull(formulario, "el formulario de un hallazgo con chapita no puede ser null");
    }

    @Override
    public Duenio encontrarDuenio(MascotaRegistrada mascota) {
        return mascota.getDuenio();
    }


    public void completarFormulario(Mascota mascotaPerdida, Persona creador) {
        this.formulario.setCreador(creador);
        this.formulario.setMascotaPerdida(mascotaPerdida);
    }

    @Override
    public String notificar(Persona persona) {
        String mensaje = "Su mascota perdida fue encontrada".concat(formulario.toString());
        return persona.notificar(mensaje);
    }

    public String encontreMascota() throws Exception {
        Mascota mascotaDelFormulario = this.formulario.getMascotaPerdida();
        if (mascotaDelFormulario instanceof MascotaRegistrada) {
            Duenio duenio = encontrarDuenio((MascotaRegistrada) formulario.getMascotaPerdida());
            return notificar(duenio.getPersona());
        }
        else
            throw new Exception("El metodo encontre mascota solo sirve para el caso de mascotas registradas");
    }

    public Formulario getFormulario() {
        return formulario;
    }




    @Override //Solo sirve para hallazgoSinChapita
    public void completarFormulario(Mascota mascotaPerdida, Persona creador, Usuario destinatario){
        return;
    }
}