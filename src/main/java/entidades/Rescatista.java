package entidades;

import Utils.distanceCalculator;
import entidades.DAOs.RepoHogares;
import entidades.DAOs.RepoOrganizaciones;
import entidades.mascota.Mascota;
import entidades.mascota.MascotaNoRegistrada;
import hogaresTransito.Hogar;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "rescatista")
@DiscriminatorValue("Si")
public class Rescatista extends Persona{
    @OneToOne(cascade = {CascadeType.ALL})

    @Transient
    private IHogarDeTransito adapter;

    @Transient
    private RepoOrganizaciones repoOrganizaciones;

    @Transient
    private RepoHogares repoHogares;

    public Rescatista(String nombre, String apellido, LocalDate fechaDenacimiento,
                      TipoDoc tipo, String nroDeDocumento, Direccion direccion, ArrayList<Contacto> contactos) {
        super(nombre, apellido, fechaDenacimiento, nroDeDocumento, tipo, contactos);
        this.direccion = direccion;
    }

    public Rescatista(String nombre, String apellido, LocalDate fechaDenacimiento,
                      TipoDoc tipo, String nroDeDocumento, Direccion direccion, ArrayList<Contacto> contactos,
                      RepoOrganizaciones repoOrganizaciones, RepoHogares repoHogares) {
        super(nombre, apellido, fechaDenacimiento, nroDeDocumento, tipo, contactos);
        this.direccion = direccion;
        this.repoOrganizaciones = repoOrganizaciones;
        this.repoHogares = repoHogares;
    }

    public Hogar buscarHogar(Mascota mascota, double radio) throws IOException {
        return this.adapter.buscarHogar(mascota, radio);
    }

    public Object bucarLugar (Mascota mascota, double radio) throws IOException{
        Hogar hogar = this.buscarHogar(mascota, radio);
        if (hogar == null){
            return this.buscarOrganizacionCercana(mascota, radio);
        }
        return hogar;
    }

    public Double getDistancia (Organizacion organizacion, Mascota mascota){
        distanceCalculator calculadorDistancia =  distanceCalculator.getDistanceCalculator();
        Double distance = calculadorDistancia.getDistancia(organizacion.getCoordenadas(), mascota.getUbicacion());
        return distance;
    }

    public Boolean hogaresDentroDelRadio (Organizacion organizacion, Mascota mascota, Double radio){
        Double valor = getDistancia(organizacion, mascota);
        return radio > valor;
    }

    public Organizacion tomarLaMenorDistancia (List<Organizacion> listafiltrada, Mascota mascota){
        Organizacion menor = listafiltrada.get(0);
        for(int posicion=1;posicion < listafiltrada.size();posicion++){
            if ((getDistancia(listafiltrada.get(posicion), mascota)) < getDistancia(menor, mascota)) {
                menor = listafiltrada.get(posicion);
            }
        }
        return menor;
    }

    public Organizacion buscarOrganizacionCercana(Mascota mascota, double radio) throws IOException {
        List<Organizacion> listafiltrada = this.repoOrganizaciones.getOrganizaciones().stream().
                filter(o->hogaresDentroDelRadio(o,mascota, radio)).collect(Collectors.toList());
        if (!listafiltrada.isEmpty()) {
            return this.tomarLaMenorDistancia(listafiltrada, mascota);
        }
        return null;
    }

    public void setAdapter(IHogarDeTransito adapter) {
        this.adapter = adapter;
    }

    @Override
    public String toString() {
        return "Rescatista{" +
                "direccion=" + direccion +
                ", adapter=" + adapter +
                ", repoOrganizaciones=" + repoOrganizaciones +
                ", repoHogares=" + repoHogares +
                '}';
    }
}
