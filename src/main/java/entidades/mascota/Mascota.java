package entidades.mascota;

import entidades.Organizacion;
import entidades.Ubicacion;
import entidades.caracteristicas.Caracteristica;
import entidades.db.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mascota")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Mascota extends EntidadPersistente {
    @Enumerated(EnumType.STRING)
    protected DescripcionFisica descripcionFisica;

    @ElementCollection
    @Column(name= "fotos")
    protected List<String> fotos;

    @Enumerated(EnumType.STRING)
    protected TipoDeMascota tipoDeMascota;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    protected List<Caracteristica> caracteristicas;

    @Transient
    protected Integer mascotaID;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "organizacion_id")
    protected Organizacion organizacion;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ubicacion_id")
    protected Ubicacion ubicacion;

    @Transient
    protected Estado estadoMascota;

    @Override
    public String toString() {
        return "Mascota{" +
                "descripcionFisica=" + descripcionFisica +
                ", fotos=" + fotos +
                ", tipoDeMascota=" + tipoDeMascota +
                ", caracteristicas=" + caracteristicas +
                ", mascotaID=" + mascotaID +
                ", organizacion=" + organizacion +
                '}';
    }


    public Mascota() {
    }

    /// USAR ESTE
    public Mascota(List<String> fotos, DescripcionFisica descripcionFisica,
                   List<Caracteristica> caracteristicas, TipoDeMascota tipoDeMascota, Organizacion organizacion, Ubicacion ubicacion) {
        this.descripcionFisica = descripcionFisica;
        this.fotos = fotos;
        this.caracteristicas = caracteristicas;
        this.tipoDeMascota = tipoDeMascota;
        this.organizacion = organizacion;
        this.ubicacion = ubicacion;
    }

    public Mascota(DescripcionFisica descripcionFísica, Organizacion organizacion) {
        this.descripcionFisica = descripcionFísica;
        this.organizacion = Objects.requireNonNull(organizacion, "");
        this.fotos = new ArrayList<String>();
        this.mascotaID = this.hashCode();
    }

    public abstract void setDescripcionFisica(DescripcionFisica descripcionFisica);

    public abstract List<String> getFotos();

    public abstract void setFotos(ArrayList<String> fotos);

    public abstract TipoDeMascota getTipoDeMascota();

    public abstract void setTipoDeMascota(TipoDeMascota tipoDeMascota);

    public abstract List<Caracteristica> getCaracteristicas();

    public abstract void setCaracteristicas(List<Caracteristica> caracteristicas);

    public abstract DescripcionFisica getDescripcionFisica();

    public abstract Estado getEstadoMascota();

    public abstract void setEstadoMascota(Estado estadoMascota);

    public abstract List<String> tomarCaracteristicasHabilitadas ();

    public abstract Integer getMascotaID();

    public abstract void setUbicacion(Ubicacion ubicacion);

    public abstract Ubicacion getUbicacion();

    public abstract Organizacion getOrganizacion();

}
