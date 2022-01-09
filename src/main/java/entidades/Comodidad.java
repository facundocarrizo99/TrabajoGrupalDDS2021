package entidades;

import entidades.db.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comodidad")
public class Comodidad extends EntidadPersistente {

    @Column(name = "comodidad_nombre")
    private String comodidad;

    public Comodidad() {}

    public Comodidad(String comodidad){
        this.comodidad = comodidad;
    }

    public String getComodidad() {
        return comodidad;
    }

    public void setComodidad(String comodidad) {
        this.comodidad = comodidad;
    }

    @Override
    public String toString() {
        return "Comodidad{" +
                "comodidad='" + comodidad + '\'' +
                '}';
    }
}
