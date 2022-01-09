package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Notificable{

    private List<Contacto> mediosDeComunicacion = new ArrayList<Contacto>();
    private Direccion direccion;
    private String nombre;

    public Notificable(List<Contacto> contactos, Direccion direccionPaula, String nombre) {
        mediosDeComunicacion = contactos;
        direccion = direccionPaula;
        this.nombre = nombre;
    }

    public String notificar(String mensaje, Persona destinatario){
        AtomicReference<String> recibido = new AtomicReference<>("Notificacion final:");
        mediosDeComunicacion.forEach(contacto -> recibido.set(recibido.get().concat("\n").concat(contacto.notificar(mensaje, destinatario))));
        return recibido.get();
    }

    public void getEmial(){ //ARMAR PARA QUE MANDE UN CONTACTO A EMAIL
        //return
    }

    //////////////  Getters y setters   ////////////

    public List<Contacto> getMediosDeComunicacion() {
        return mediosDeComunicacion;
    }

    public void setMediosDeComunicacion(List<Contacto> mediosDeComunicacion) {
        this.mediosDeComunicacion = mediosDeComunicacion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Notificable{" +
                "mediosDeComunicacion=" + mediosDeComunicacion +
                ", direccion=" + direccion +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
