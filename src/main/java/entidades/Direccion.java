package entidades;

import entidades.db.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion extends EntidadPersistente {

    @Column
    private String calle;

    @Column
    private int altura;

    @Column
    private String codigoPostal;

    public Direccion() {}

    public Direccion(String calle, int altura, String codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.codigoPostal = codigoPostal;
    }

    public Direccion(String calle) {
        this.calle = calle;
    }

    //////////////  Getters y setters   ////////////

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }


}