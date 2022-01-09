package entidades.caracteristicas;

import entidades.db.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica")

public class Caracteristica extends EntidadPersistente {

    @Column(name = "caracteristica")
    private String caracteristica;
    //Agregar nombre de la caracteristica junto con el valor

    @Column(name = "habilitada")
    private Boolean habilitada;

    public Caracteristica() {}

    public Caracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Caracteristica(String caracteristica, Boolean habilitada) {
        this.caracteristica = caracteristica;
        this.habilitada = habilitada;
    }

    public String getCaracteristica () {return this.caracteristica;}

    public Boolean caracteristicaEstado() {
        return this.habilitada;
    }

    public void habilitar(){
        this.habilitada = true;
    }

    public void deshabilitar(){
        this.habilitada = false;
    }

    @Override
    public String toString() {
        return " " + caracteristica + " ";
    }
}