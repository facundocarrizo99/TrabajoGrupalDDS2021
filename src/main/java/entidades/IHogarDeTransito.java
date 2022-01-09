package entidades;

import entidades.mascota.Mascota;
import entidades.mascota.MascotaNoRegistrada;
import hogaresTransito.Hogar;

import java.io.IOException;

public interface IHogarDeTransito {
    public Hogar buscarHogar(Mascota mascota, double radio) throws IOException;
}
