package entidades.DAOs;

import entidades.Organizacion;

import java.util.List;
import java.util.stream.Collectors;

public class RepoOrganizaciones {
    private List<Organizacion> organizaciones;

    public RepoOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public Organizacion getOrganizacionPorNombre(String nombre) {
        return this.organizaciones.
                stream().
                filter(org -> org.getNombreOrganizacion() == nombre).
                collect(Collectors.toList()).
                get(0);
    }

    public void removeOrganizacionPorNombre(String nombre) {
        this.organizaciones.
                removeIf(org -> org.getNombreOrganizacion() == nombre);
    }

    public void addOrganizacion(Organizacion org) {
        this.organizaciones.add(org);
    }
}
