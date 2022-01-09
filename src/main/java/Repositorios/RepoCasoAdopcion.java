package Repositorios;
import entidades.*;
import entidades.caracteristicas.Caracteristica;
import entidades.mascota.DescripcionFisica;
import entidades.mascota.Mascota;
import entidades.mascota.MascotaRegistrada;
import entidades.mascota.TipoDeMascota;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RepoCasoAdopcion {


    private List<CasoAdopcion> casosAdopcion = new ArrayList<CasoAdopcion>();

    public RepoCasoAdopcion () {
        casosAdopcion = new ArrayList<CasoAdopcion>();
    }

    public List<CasoAdopcion> mostrarTodos() {
        return getCasosAdopcion();
    }

    public static List<CasoAdopcion> mostrarTodosStatic() {
        Persona persona1;
        ArrayList<Contacto> contactos;
        contactos = new ArrayList<Contacto>();
        Contacto mail  = new Email("joacorode97@gmail.com");
        contactos.add(mail);
        Notificable notificableFacu = new Notificable(contactos, new Direccion("montoto", 21, "1203"), "b");
        persona1 = new Persona("Facundo", "Carrizo", LocalDate.of( 1999,10, 28), "12345678", TipoDoc.DNI, contactos);
        persona1.setNotificable(notificableFacu);
        Mascota rick,morty,sarasa;
        Organizacion acme = new Organizacion("ACME");
        Caracteristica caracteristicaTest1 = new Caracteristica("Atletico/asdasdasdcc", true);
        Caracteristica caracteristicaTest2 = new Caracteristica("Peligroso/asdasdasdcc", false);
        Caracteristica caracteristicaTest3 = new Caracteristica("Mimoso/asdasdasdcc", true);
        List<Caracteristica> caracteristicasRick = new ArrayList<Caracteristica>();
        caracteristicasRick.add(caracteristicaTest1);
        caracteristicasRick.add(caracteristicaTest2);
        caracteristicasRick.add(caracteristicaTest3);
        Caracteristica caracteristicaTest4 = new Caracteristica("Guardian/asdasdasdcc", false);
        Caracteristica caracteristicaTest5 = new Caracteristica("Ladrador/asdasdasdcc", true);
        List<Caracteristica> caracteristicasMorty = new ArrayList<Caracteristica>();
        caracteristicasMorty.add(caracteristicaTest4);
        caracteristicasMorty.add(caracteristicaTest5);
        Caracteristica caracteristicaTest6 = new Caracteristica("Tranquilo/asdasdasdcc", false);
        Caracteristica caracteristicaTest7 = new Caracteristica("Juegueton/asdasdasdcc", true);
        List<Caracteristica> caracteristicasSarasa = new ArrayList<Caracteristica>();
        caracteristicasSarasa.add(caracteristicaTest6);
        caracteristicasSarasa.add(caracteristicaTest7);
        rick = new MascotaRegistrada(TipoDeMascota.PERRO, "Rick", LocalDate.of(2010, 5, 4), Boolean.FALSE, acme, DescripcionFisica.CHICA);
        morty = new MascotaRegistrada(TipoDeMascota.GATO, "Morty", LocalDate.of(2015, 6, 12), Boolean.FALSE, acme, DescripcionFisica.GRANDE);
        sarasa = new MascotaRegistrada(TipoDeMascota.PERRO, "Sarasa", LocalDate.of(2015, 6, 12), Boolean.FALSE, acme, DescripcionFisica.GRANDE);
        rick.setCaracteristicas(caracteristicasRick);
        morty.setCaracteristicas(caracteristicasMorty);
        sarasa.setCaracteristicas(caracteristicasSarasa);
        CasoAdopcion caso1 = new CasoAdopcion();
        caso1.completarFormulario(rick,persona1);
        caso1.setStatus(true);

        CasoAdopcion caso2 = new CasoAdopcion();
        caso2.completarFormulario(morty,persona1);
        caso2.setStatus(true);

        CasoAdopcion caso3 = new CasoAdopcion();
        caso3.completarFormulario(sarasa,persona1);
        caso3.setStatus(true);

        List<CasoAdopcion> casos = new ArrayList<CasoAdopcion>();
        casos.add(caso1);
        casos.add(caso2);
        casos.add(caso3);
        return casos;
        //este metodo se va asdasdasdcc usar de esta manera hasta que tengamos un ORM, cuando lo
        //tengamos, se usara el metodo de arriba que traera los casos directo de la BBD
    }

    public List<CasoAdopcion> getCasosAdopcion() {
        return casosAdopcion;
    }

    public CasoAdopcion getByID(String idRecibido){
        AtomicReference<CasoAdopcion> casoAdopcion1 = null;
        this.casosAdopcion.forEach(casoAdopcion -> {
                if(casoAdopcion.getFormulario().getFormularioID().equalsIgnoreCase(idRecibido)){
                    casoAdopcion1.set(casoAdopcion);
                }
            }
        );
        return casoAdopcion1.get();
    }
}
