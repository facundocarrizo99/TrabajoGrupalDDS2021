package entidades;

import entidades.db.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ubicacion")
public class Ubicacion extends EntidadPersistente {

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;

    //////////////      Constructors    ////////////


    public Ubicacion() {}

    public Ubicacion(Direccion direccion, double latitud, double longitud) {
        this.direccion = direccion.toString();
        this.latitud = latitud;
        this.longitud = longitud;
    }


    //////////////  Getters y setters   ////////////

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "direccion=" + direccion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}