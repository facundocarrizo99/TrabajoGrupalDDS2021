package entidades;

import entidades.mascota.Mascota;
import entidades.mascota.MascotaNoRegistrada;
import entidades.mascota.MascotaRegistrada;
import entidades.usuario.Duenio;
import entidades.usuario.Usuario;

public interface IHallazgo {

    public Duenio encontrarDuenio(MascotaRegistrada mascota);

    //public void completarFormulario(MascotaNoRegistrada mascota, Rescatista creador);

    public void completarFormulario(Mascota mascotaPerdida, Persona creador, Usuario destinatario);

    public String notificar(Persona persona);
}