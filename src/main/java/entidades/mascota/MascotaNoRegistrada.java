package entidades.mascota;

import entidades.Organizacion;
import entidades.Ubicacion;
import entidades.caracteristicas.Caracteristica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("noregistrada")
public class MascotaNoRegistrada extends Mascota {

    @Column(name = "estado")
    private String estado;



    public MascotaNoRegistrada() {
        this.estado = "";
    }

    public MascotaNoRegistrada(List<String> fotos, String estado, Ubicacion coordenadas, DescripcionFisica descripcionFisica,
                               TipoDeMascota tipoDeMascota, List<Caracteristica> caracteristicas, Organizacion organizacion) {
        super(fotos, descripcionFisica, caracteristicas, tipoDeMascota, organizacion, coordenadas);
        this.estado = estado;
    }
///PARA FORMULARIO
    public MascotaNoRegistrada(List<String> fotos, String estado, Ubicacion coordenadas,
                               TipoDeMascota tipoDeMascota, Organizacion organizacion) {
        super(fotos, null, null, tipoDeMascota, organizacion, coordenadas );
        this.estado = estado;
    }

    @Override
    public List<String> getFotos() {
        return this.fotos;
    }

    @Override
    public void setFotos(ArrayList<String> fotos) {
        this.fotos = fotos;
    }

    @Override
    public void setDescripcionFisica(DescripcionFisica descripcionFisica) {
    this.descripcionFisica = descripcionFisica;
    }

    @Override
    public void setTipoDeMascota(TipoDeMascota tipoDeMascota) {
        this.tipoDeMascota = tipoDeMascota;
    }

    @Override
    public Integer getMascotaID() {
        return this.mascotaID;
    }

    @Override
    public void setUbicacion(Ubicacion ubicacion) {
        super.ubicacion = ubicacion;
    }

    @Override
    public Ubicacion getUbicacion() {
        return super.ubicacion;
    }

    @Override
    public Organizacion getOrganizacion() { return super.organizacion;  }

    public TipoDeMascota getTipoDeMascota() {
        return this.tipoDeMascota;
    }

    
    public List<Caracteristica> getCaracteristicas() {
        return super.caracteristicas;
    }

    @Override
    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public Estado getEstadoMascota() {
        return super.estadoMascota;
    }


    @Override
    public void setEstadoMascota(Estado estadoMascota) {
            super.estadoMascota = estadoMascota;
    }
   
    public DescripcionFisica getDescripcionFisica() {
        return this.descripcionFisica;
    }

    @Override
    public List<String> tomarCaracteristicasHabilitadas() {
        return this.getCaracteristicas().stream().
                filter(m -> m.caracteristicaEstado().equals(Boolean.TRUE))
                .map(Caracteristica::getCaracteristica)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MascotaNoRegistrada{" +
                "descripcionFisica=" + descripcionFisica +
                ", fotos=" + fotos +
                ", tipoDeMascota=" + tipoDeMascota +
                ", caracteristicas=" + caracteristicas +
                ", mascotaID=" + mascotaID +
                ", organizacion=" + organizacion +
                ", ubicacion=" + ubicacion +
                ", estadoMascota=" + estadoMascota +
                ", estado='" + estado + '\'' +
                '}';
    }
}
