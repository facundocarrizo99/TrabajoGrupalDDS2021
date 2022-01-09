package entidades;

import entidades.caracteristicas.Caracteristica;

import java.util.ArrayList;
import java.util.List;

public class HogarDeTransito {

    private Persona responsable;
    private String nombre;
    private ContactoTransito telefono;
    private List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();

    //////////////  Getters y setters   ////////////

    public Persona getResponsable() {
        return responsable;
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ContactoTransito getTelefono() {
        return telefono;
    }

    public void setTelefono(ContactoTransito telefono) {
        this.telefono = telefono;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}