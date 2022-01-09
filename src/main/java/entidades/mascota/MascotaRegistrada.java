package entidades.mascota;

import entidades.Ubicacion;
import entidades.caracteristicas.Caracteristica;
import entidades.Organizacion;
import entidades.usuario.Duenio;

import javax.persistence.*;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("registrada")
public class MascotaRegistrada extends Mascota {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apodo")
    private String apodo;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate fechaDeNacimiento;

    @Column(name = "esHembra")
    private Boolean esHembra;

    @ManyToOne
    @JoinColumn(name = "duenio_id", referencedColumnName = "id")
    private Duenio  duenio;

    @Column
    private String chapita; //codigoqr

    @Override
    public TipoDeMascota getTipoDeMascota() {
        return tipoDeMascota;
    }

    @Override
    public List<Caracteristica> getCaracteristicas() {
        return super.caracteristicas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getChapita() { return chapita; }

    public void setChapita(String chapita) { this.chapita = chapita; }

    @Override
    public Organizacion getOrganizacion() { return super.organizacion;  }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
    public Estado getEstadoMascota() {
        return super.estadoMascota;
    }

    @Override
    public void setEstadoMascota(Estado estadoMascota) {
        super.estadoMascota = estadoMascota;
    }

    @Override
    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    //En persona ya existe setMascota
    //public void setDuenio(Duenio duenio) { this.duenio = duenio; }

    public List<String> getFotos() {
        return super.fotos;
    }

    @Override
    public void setFotos(ArrayList<String> fotos) {
        this.fotos = fotos;
    }

    @Override
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
    public Integer getMascotaID() {
        return this.mascotaID;
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
    public void setUbicacion(Ubicacion ubicacion) {
        super.ubicacion = ubicacion;
    }

    @Override
    public Ubicacion getUbicacion() {
        return super.ubicacion;
    }

    public Boolean getEsHembra() { return esHembra; }

    public void setEsHembra(Boolean esHembra) { this.esHembra = esHembra; }

    public MascotaRegistrada() {}

    public MascotaRegistrada(List<String> fotos, List<Caracteristica> caracteristicas, TipoDeMascota tipoDeMascota,
                             String nombre, LocalDate fechaDeNacimiento, Boolean esHembra,
                             Organizacion organizacion, DescripcionFisica descripcionFisica) {
        super(fotos, descripcionFisica, caracteristicas, tipoDeMascota, organizacion, null);
        this.caracteristicas = caracteristicas;
        this.tipoDeMascota = Objects.requireNonNull(tipoDeMascota, "");
        this.nombre = Objects.requireNonNull(nombre, "");
        this.fechaDeNacimiento = Objects.requireNonNull(fechaDeNacimiento, "");
        this.esHembra = Objects.requireNonNull(esHembra, "");
        this.organizacion = organizacion;
    }



    public MascotaRegistrada(TipoDeMascota tipoDeMascota, String nombre, LocalDate fechaDeNacimiento, Boolean esHembra,
                             Organizacion organizacion, DescripcionFisica descripcionFisica) {
        super(descripcionFisica, organizacion);
        this.caracteristicas = new ArrayList<Caracteristica>();
        this.tipoDeMascota = Objects.requireNonNull(tipoDeMascota, "");
        this.nombre = Objects.requireNonNull(nombre,"");
        this.fechaDeNacimiento = Objects.requireNonNull(fechaDeNacimiento,"");
        this.esHembra = Objects.requireNonNull(esHembra, "");
    }
}
