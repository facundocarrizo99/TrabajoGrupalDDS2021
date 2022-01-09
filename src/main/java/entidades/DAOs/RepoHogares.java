package entidades.DAOs;

import config.Configuracion;
import hogaresTransito.Hogar;
import hogaresTransito.ListadoHogaresDeTransito;
import services.ServicioRefugioDDS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RepoHogares {
    private List<Hogar> hogares = new ArrayList<Hogar>();

    //Constructor que itere sobre las paginas de la api
    public RepoHogares () {
    }

    public List<Hogar> getHogares() {
        try {
            this.cargarHogares();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hogares;
    }

    public Hogar getHogarPorNombre(String nombre) {
        try {
            this.cargarHogares();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.hogares.
                stream().
                filter(home -> home.getNombre() == nombre).
                collect(Collectors.toList()).
                get(0);
    }

    public void removeHogarPorNombre(String nombre) {
        this.hogares.
                removeIf(home -> home.getNombre() == nombre);
    }

    public void addHogar(Hogar hogar) {
        this.hogares.add(hogar);
    }

    public void cargarHogares() throws IOException {
        this.hogares = new ArrayList<Hogar>();
        Configuracion config = Configuracion.getInstace();
        String token = config.getRefugioDDSToken();
        Integer nroPag = 1;
        ServicioRefugioDDS servicioRefugioDDS = ServicioRefugioDDS.getInstance();
        ListadoHogaresDeTransito listadoHogaresDeTransito;

        listadoHogaresDeTransito = servicioRefugioDDS.listadoHogaresDeTransito(nroPag, token);
        for (int i = 1; i <= listadoHogaresDeTransito.getPaginasTotales(); i++) {
            for (Hogar unHogar : listadoHogaresDeTransito.getHogares()) {
                this.hogares.add(unHogar);
            }
        }
    }
}
