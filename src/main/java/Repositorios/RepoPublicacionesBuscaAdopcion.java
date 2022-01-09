package Repositorios;

import entidades.*;
import entidades.caracteristicas.Caracteristica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static entidades.TipoDoc.DNI;

public class RepoPublicacionesBuscaAdopcion {


    private List<PublicacionBuscaAdopcion> publicacionesCasoAdopcion = new ArrayList<PublicacionBuscaAdopcion>();

    public RepoPublicacionesBuscaAdopcion () {
        publicacionesCasoAdopcion = new ArrayList<PublicacionBuscaAdopcion>();
    }

    public List<PublicacionBuscaAdopcion> mostrarTodos() {
        return getPublicacionBuscaAdopcion();
    }

    public static List<PublicacionBuscaAdopcion> mostrarTodosStatic() {

        Email mail = new Email("juan.manuel.anello@gmail.com");
        ArrayList<Contacto> contactosFacu = new ArrayList<Contacto>();
        contactosFacu.add(mail);
        Notificable notificableFacu = new Notificable(contactosFacu, new Direccion("sarasa", 123, "1301"), "b");
        Persona facu = new Persona("Facundo", "Carrizo", LocalDate.of(1999, 12, 3),  "12345678", DNI, contactosFacu);
        facu.setEmail("juan.manuel.anello@gmail.com");
        facu.setNotificable(notificableFacu);
        Caracteristica caracteristicaTest1 = new Caracteristica("Atletico/asdasdasdcc", true);
        Caracteristica caracteristicaTest2 = new Caracteristica("Peligroso/asdasdasdcc", false);
        Caracteristica caracteristicaTest3 = new Caracteristica("Mimoso/asdasdasdcc", true);
        List<Caracteristica> caracteristicasBuscadas = new ArrayList<Caracteristica>();
        caracteristicasBuscadas.add(caracteristicaTest1);
        caracteristicasBuscadas.add(caracteristicaTest2);
        caracteristicasBuscadas.add(caracteristicaTest3);
        Comodidad comodidad = new Comodidad("Tiene patio");
        List<Comodidad> comodidades = new ArrayList<Comodidad>();
        comodidades.add(comodidad);
        PublicacionBuscaAdopcion publicacion = new PublicacionBuscaAdopcion(caracteristicasBuscadas,comodidades,facu);
        List<PublicacionBuscaAdopcion> publicaciones = new ArrayList<PublicacionBuscaAdopcion>();
        publicaciones.add(publicacion);
        return publicaciones;
        //este metodo se va asdasdasdcc usar de esta manera hasta que tengamos un ORM, cuando lo
        //tengamos, se usara el metodo de arriba que traera los casos directo de la BBD
    }

    public List<PublicacionBuscaAdopcion> getPublicacionBuscaAdopcion() {
        return this.publicacionesCasoAdopcion;
    }
}
