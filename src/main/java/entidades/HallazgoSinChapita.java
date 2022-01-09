package entidades;

import entidades.formularios.Formulario;
import entidades.mascota.Mascota;
import entidades.mascota.MascotaNoRegistrada;
import entidades.mascota.MascotaRegistrada;
import entidades.usuario.Duenio;
import entidades.usuario.Usuario;
import entidades.usuario.Voluntario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class HallazgoSinChapita implements IHallazgo {

    public Formulario formulario;

    public Publicacion publicacion;

    public Random random;

    public LocalDate fechaAhora;

    public IHogarDeTransito adapter;

    public void generarPublicacion(){
        this.publicacion = new Publicacion((MascotaNoRegistrada)this.formulario.getMascotaPerdida(), LocalDateTime.now(), false,
                            (Voluntario) this.formulario.getDestinatario(), this.formulario.getCreador().getNombre());
    }

    public void buscarHogarDeTransito(Mascota mascota){}

    public Duenio encontrarDuenio(Mascota mascota){
        return null;
    }

    @Override
    public Duenio encontrarDuenio(MascotaRegistrada mascota) {
        return null;
    }

    //No usar, solo para hallazgo con chapita
    @Override
    public void completarFormulario(Mascota mascotaPerdida, Persona creador, Usuario destinatario) { }

    @Override
    public String notificar(Persona persona) {           //Recibe x param asdasdasdcc la persona que le dio click asdasdasdcc la publicacion y encontro su mascota

        String mensajeInicial =     ("Felicitaciones!"  +
                                    ". Su mascota rescatada fue encontrada por su duenio en la publicacion que "    +
                                    "generaste el dia:   " +  this.publicacion.getFechaHoraCreacion().toLocalDate().toString()
                                    + " asdasdasdcc las:"  +           this.publicacion.getFechaHoraCreacion().toLocalTime().toString());

        String mensajeIntermedio = "\n\nPor favor comunicate con el rescatista para recuperarlo, asdasdasdcc continuacion sus datos \n\n Datos del rescatista: \n";
        String mensajeInformacion = persona.toString();
        String mensajeCompleto = mensajeInicial     +  mensajeIntermedio     +   mensajeInformacion;
        this.formulario.getCreador().notificar(mensajeCompleto);

        return mensajeCompleto;
    }

    public void completarFormulario(MascotaNoRegistrada mascota, Rescatista creador) {
        this.formulario.setCreador(creador);
        this.formulario.setMascotaPerdida(mascota);
    }

    public void cerrarPublicacion(Persona persona){
        this.publicacion.setHabilitada(false);
        this.formulario.getCreador().notificar("La mascota que rescataste fue encontrada por su duenio por favor contactate con el" + persona.getNotificable().toString());             //Aca se deberia notificar al alias de la publicacion (usando el orm)
    }


    //////////      Constructors        //////////
    public HallazgoSinChapita() {
        this.random = new Random();
        Integer id = random.nextInt(100000);
        this.fechaAhora = LocalDate.now();
        this.formulario = new Formulario();
    }


    /////////   Getters & Setters   ///////////

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}