package entidades.formularios;

import entidades.db.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tipodepregunta")
public class TipoDePregunta extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDePregunta() {}

    public TipoDePregunta(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoDePregunta{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
