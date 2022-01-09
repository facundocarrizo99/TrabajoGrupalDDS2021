import Repositorios.RepositorioDePublicaciones;
import Repositorios.factories.FactoryRepositorioPublicaciones;
import db.EntityManagerHelper;
import entidades.Direccion;
import entidades.Organizacion;
import entidades.Persona;
import entidades.Ubicacion;
import entidades.Publicacion;
import entidades.PublicacionDetallada;
import entidades.caracteristicas.Caracteristica;
import entidades.formularios.*;
import entidades.mascota.*;
import entidades.usuario.Administrador;
import entidades.usuario.Duenio;
import entidades.usuario.Usuario;
import entidades.usuario.Voluntario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityManagerTest {

    @Test
    public void apersistir1UsuarioTest(){



        String foto1 = "img/img-01.jpg";
        String foto2 = "img/img-02.jpg";
        String foto3 = "img/img-03.jpg";
        String foto4 = "img/img-04.jpg";
        String foto5 = "img/img-05.jpg";
        String foto6 = "img/img-06.jpg";
        String foto7 = "img/img-07.jpg";
        String foto8 = "img/img-08.jpg";
        String foto9 = "img/shib3.jpg";

        ArrayList<String> fotosRick = new ArrayList<String>();
        fotosRick.add(foto4);

        ArrayList<String> fotosFirulais = new ArrayList<String>();
        fotosFirulais.add(foto2);

        ArrayList<String> fotosToby = new ArrayList<String>();
        fotosToby.add(foto8);

        ArrayList<String> fotosRagnar = new ArrayList<String>();
        fotosRagnar.add(foto9);

        /////////////////-----------            Configurando las preguntas      -----------------------/////////////////////

        TipoDePregunta tipoDePreguntaBooleana = new TipoDePregunta("booleana");
        TipoDePregunta tipoDePreguntaSingleChoice = new TipoDePregunta("single choice");
        TipoDePregunta tipoDePreguntaMultipleChoice = new TipoDePregunta("multiple choice");
        TipoDePregunta tipoDePreguntaTextoLibre = new TipoDePregunta("texto libre");


        Opcion menorQueCincoKg =    new Opcion("Menos de 5kg");
        Opcion entreCincoYDiezKg =  new Opcion("Entre 5kg y 10kg");
        Opcion masDeDiezKg =        new Opcion("Mas de 10 kg");
        masDeDiezKg.setSeleccionada(true);
        entreCincoYDiezKg.setSeleccionada(true);
        menorQueCincoKg.setSeleccionada(true);

        Pregunta preguntaPeso = new Pregunta("Peso de la mascota");
        preguntaPeso.addOpcion(masDeDiezKg);
        preguntaPeso.setTipo(tipoDePreguntaSingleChoice);

        Pregunta preguntaPeso1 = new Pregunta("Peso de la mascota");
        preguntaPeso1.addOpcion(entreCincoYDiezKg);
        preguntaPeso1.setTipo(tipoDePreguntaSingleChoice);

        Pregunta preguntaPeso2 = new Pregunta("Peso de la mascota");
        preguntaPeso2.addOpcion(menorQueCincoKg);
        preguntaPeso2.setTipo(tipoDePreguntaSingleChoice);


        Opcion razaLabrador         =     new Opcion("Labrador");
        Opcion razaCaniche          =     new Opcion("Caniche");
        Opcion razaOvejero          =     new Opcion("Ovejero");
        Opcion razaSalchicha        =     new Opcion("Salchicha");
        Opcion razaBulldog          =     new Opcion("Bulldog");
        Opcion razaShiba            =     new Opcion("Shiba inu");
        Opcion razaNoRaza           =     new Opcion("Sin Raza");

        razaNoRaza.setSeleccionada(true);
        razaLabrador.setSeleccionada(true);
        razaShiba.setSeleccionada(true);

        Pregunta preguntaRaza = new Pregunta("Raza de la mascota");
        preguntaRaza.addOpcion(razaNoRaza);
        preguntaRaza.setTipo(tipoDePreguntaMultipleChoice);

        Pregunta preguntaRaza1 = new Pregunta("Raza de la mascota");
        preguntaRaza1.setTipo(tipoDePreguntaMultipleChoice);
        preguntaRaza1.addOpcion(razaLabrador);

        Pregunta preguntaRaza2 = new Pregunta("Raza de la mascota");
        preguntaRaza2.setTipo(tipoDePreguntaMultipleChoice);
        preguntaRaza2.addOpcion(razaShiba);

        Pregunta preguntaTextoLibre = new Pregunta("Describa brevemente la personalidad de su mascota");
        preguntaTextoLibre.setTipo(tipoDePreguntaTextoLibre);




        /////////////////-----------            Configurando las respuestas a las preguntas      -----------------------/////////////////////

        Respuesta         respuestaAPreguntaPeso = new Respuesta();

        respuestaAPreguntaPeso.setPregunta(preguntaPeso);


        Respuesta         respuestaAPreguntaRaza = new Respuesta();

        respuestaAPreguntaRaza.setPregunta(preguntaRaza);

        Respuesta         respuestaAPreguntaTextoLibrePubliRick = new Respuesta();

        respuestaAPreguntaTextoLibrePubliRick.setRespuestaLibre("mascota muy juguetona, atletica y companiera. Sufre de las articulaciones pero no le reduce su movilidad");
        respuestaAPreguntaTextoLibrePubliRick.setPregunta(preguntaTextoLibre);

        Respuesta         respuestaAPreguntaPeso1 = new Respuesta();
        respuestaAPreguntaPeso1.setPregunta(preguntaPeso1);

        Respuesta         respuestaAPreguntaRaza1 = new Respuesta();
        respuestaAPreguntaRaza1.setPregunta(preguntaRaza1);

        Respuesta         respuestaAPreguntaPeso2 = new Respuesta();
        respuestaAPreguntaPeso2.setPregunta(preguntaPeso2);

        Respuesta         respuestaAPreguntaRaza2 = new Respuesta();
        respuestaAPreguntaRaza2.setPregunta(preguntaRaza2);

        /////////////////-----------            Configurando cosas varias      -----------------------/////////////////////
        MascotaRegistrada rick;
        MascotaRegistrada toby;
        MascotaRegistrada ragnar;
        MascotaNoRegistrada morty;
        List<String> fotos = new ArrayList<>();
        List<String> fotos2 = new ArrayList<>();

        Direccion direccion2 = new Direccion("Mozart", 2300, "23109");
        Direccion direccion3 = new Direccion("Area 51", 51, "0213123");
        Direccion direccion4 = new Direccion("Don Bosco" , 537, "B1879");

        Ubicacion ubicacionOrganizacion = new Ubicacion(direccion2, -34.65943, -58.46750);
        Ubicacion ubicacionMorty = new Ubicacion(direccion3, -34.722094, -58.267638);
        Ubicacion ubicacionRicardo = new Ubicacion(direccion4, -34.715922, -58.286337);

        Organizacion organizacion = new Organizacion();
        organizacion.setNombreOrganizacion("acme");
        organizacion.setCoordenadas(ubicacionOrganizacion);
        //organizacion.setCaracteristicas();
        //organizacion.setPreguntas();



        Direccion direccion = new Direccion("Piermont Dr. Albuquerque", 3828, "87112");


        Persona persona = new Persona();
        persona.setNombre("Walter");
        persona.setApellido("White");
        persona.setEmail("ww@gmail.com");
        persona.setTelefono("203120393");
        persona.setFechaDenacimiento(LocalDate.of(1970, 9, 8));
        persona.setDireccion(direccion);

        Persona persona2 = new Persona();
        persona2.setNombre("Joaco");
        persona2.setApellido("Rode");
        persona2.setEmail("testestorelli@gmail.com");
        persona2.setTelefono("203120213");
        persona2.setFechaDenacimiento(LocalDate.of(1997, 9, 8));
        persona2.setDireccion(direccion2);

        Persona persona3 = new Persona();
        persona3.setNombre("Walter");
        persona3.setApellido("Staltari");
        persona3.setEmail("walter@gmail.com");
        persona3.setTelefono("1133212931");
        persona3.setFechaDenacimiento(LocalDate.of(1997, 9, 30));
        persona3.setDireccion(direccion3);

        Persona persona4 = new Persona();
        persona4.setNombre("Ricardo");
        persona4.setApellido("Riganti");
        persona4.setEmail("ricardovich@gmail.com");
        persona4.setTelefono("1133262921");
        persona4.setFechaDenacimiento(LocalDate.of(1991, 2, 26));
        persona4.setDireccion(direccion4);


        Duenio usuario = new Duenio("heisenberg", "Slasmcx3!", persona);

        Voluntario usuario2 = new Voluntario();
        usuario2.setUsuario("joaco");
        usuario2.setContrasena("nan97caa.");
        usuario2.setPersona(persona2);
        usuario2.setOrganizacion(organizacion);
        usuario2.setIntentosInicioSesionFallidos(0);


        Ubicacion ubicacionRegistroFirulais = new Ubicacion();
        ubicacionRegistroFirulais.setLatitud(-34.636603);
        ubicacionRegistroFirulais.setLongitud(-58.474740);



        Organizacion org = new Organizacion();
        org.setNombreOrganizacion("vamonos pest");
        org.setCoordenadas(ubicacionRegistroFirulais);

        List organizacionesDelAdmin1 = new ArrayList<Organizacion>();
        organizacionesDelAdmin1.add(org);

        Administrador administrador1 = new Administrador("facundo", "Hola123");
        administrador1.setBloqueado(false);
        administrador1.setStatus(true);
        administrador1.setOrganizaciones(organizacionesDelAdmin1);

        MascotaRegistrada firulais = new MascotaRegistrada(TipoDeMascota.GATO, "Firulais", LocalDate.of(2015, 6, 12), Boolean.FALSE, org, DescripcionFisica.GRANDE);
        firulais.setUbicacion(ubicacionRegistroFirulais);
        firulais.setChapita("http://encontraTuMascota/forms/123893.jsf");
        firulais.setApodo("pichi");
        firulais.setEsHembra(false);
        firulais.setDuenio(usuario);
        firulais.setFotos(fotosFirulais);

        Caracteristica caracteristica1 = new Caracteristica("Pesa 70 kg", true);
        Caracteristica caracteristica2 = new Caracteristica("castrado/a", false);
        Caracteristica caracteristica3 = new Caracteristica("inquieto/a", true);
        Caracteristica caracteristica4 = new Caracteristica("inquieto/a", false);
        Caracteristica caracteristica5 = new Caracteristica("agresivo/a", true);
        Caracteristica caracteristica6 = new Caracteristica("agresivo/a", false);
        Caracteristica caracteristica7 = new Caracteristica("guardian/a", true);
        Caracteristica caracteristica8 = new Caracteristica("guardian/a", false);
        Caracteristica caracteristica9 = new Caracteristica("cachorro/a", true);
        Caracteristica caracteristica10 = new Caracteristica("cachorro/a", false);
        Caracteristica caracteristica11 = new Caracteristica("No es pedigree", true);
        Caracteristica caracteristica12 = new Caracteristica("inteligente", false);
        Caracteristica caracteristica13 = new Caracteristica("Labrador", true);
        Caracteristica caracteristica14 = new Caracteristica("Pesa 10 kg", true);
        Caracteristica caracteristica15 = new Caracteristica("Pesa 4 kg", true);
        Caracteristica caracteristica16 = new Caracteristica("Rufian", true);
        Caracteristica caracteristica17 = new Caracteristica("Destructor", true);
        Caracteristica caracteristica18 = new Caracteristica("Exotico", true);


        List<Caracteristica> caracteristicasRick = new ArrayList<>();
        caracteristicasRick.add(caracteristica1);
        caracteristicasRick.add(caracteristica11);
        caracteristicasRick.add(caracteristica8);
        caracteristicasRick.add(caracteristica6);

        List<Caracteristica> caracteristicasMorty = new ArrayList<>();
        caracteristicasMorty.add(caracteristica5);
        caracteristicasMorty.add(caracteristica7);
        caracteristicasMorty.add(caracteristica11);

        rick = new MascotaRegistrada(TipoDeMascota.PERRO, "Rick", LocalDate.of(2010,7,12), false, org, DescripcionFisica.CHICA);

        rick.setCaracteristicas(caracteristicasRick);

        rick.setFotos(fotosRick);

        rick.setApodo("Rick");

        morty = new MascotaNoRegistrada(fotos, "sucio", ubicacionMorty, TipoDeMascota.PERRO, org);

        morty.setCaracteristicas(caracteristicasMorty);

        List<Caracteristica> caracteristicasToby = new ArrayList<>();
        caracteristicasToby.add(caracteristica3);
        caracteristicasToby.add(caracteristica9);
        caracteristicasToby.add(caracteristica14);
        caracteristicasToby.add(caracteristica12);
        caracteristicasToby.add(caracteristica18);

        toby = new MascotaRegistrada(TipoDeMascota.PERRO, "Toby", LocalDate.of(2015,8,15), false, org, DescripcionFisica.GRANDE);

        toby.setCaracteristicas(caracteristicasToby);
        toby.setFotos(fotosToby);
        toby.setApodo("Toby");

        List<Caracteristica> caracteristicasRagnar = new ArrayList<>();
        caracteristicasRagnar.add(caracteristica15);
        caracteristicasRagnar.add(caracteristica16);
        caracteristicasRagnar.add(caracteristica17);

        ragnar = new MascotaRegistrada(TipoDeMascota.PERRO, "Ragnar", LocalDate.of(2013,8,18),false,org, DescripcionFisica.CHICA);
        ragnar.setCaracteristicas(caracteristicasRagnar);
        ragnar.setFotos(fotosRagnar);
        ragnar.setApodo("Ragnar");
        /////////////////-----------            Configurando formularios y publicaciones     -----------------------/////////////////////

        Formulario formularioHallazgoMorty = new Formulario();
        formularioHallazgoMorty.setFotosMascotaPerdida(fotos);
        formularioHallazgoMorty.setEstadoMascotaPerdida("sucio, polvoriento y apestoso");
        formularioHallazgoMorty.setUbicacionHallazgoMascotaPerdida(ubicacionMorty);
        formularioHallazgoMorty.setCreador(persona3);
        formularioHallazgoMorty.setFechaCreacion(LocalDate.of(2021, 10,28));

        Publicacion publicacionMortySubidaPorWalter = new Publicacion();
        publicacionMortySubidaPorWalter.setFormulario(formularioHallazgoMorty);
        publicacionMortySubidaPorWalter.setAliasRescatista("El Walter");
        publicacionMortySubidaPorWalter.setFechaHoraCreacion(LocalDateTime.of(2021, 10,28,10,30));
        publicacionMortySubidaPorWalter.setAprobada(true);
        publicacionMortySubidaPorWalter.setHabilitada(true);

        Formulario formularioHallazgoRagnar = new Formulario();
        formularioHallazgoRagnar.setFotosMascotaPerdida(fotos2);
        formularioHallazgoRagnar.setEstadoMascotaPerdida("jugueton, escurridizo, tiene pulgas");
        formularioHallazgoRagnar.setUbicacionHallazgoMascotaPerdida(ubicacionRegistroFirulais);
        formularioHallazgoRagnar.setCreador(persona4);
        formularioHallazgoRagnar.setFechaCreacion(LocalDate.of(2021, 10,28));

        Publicacion publicacionRagnarSubidaPorRicardo = new Publicacion();
        publicacionRagnarSubidaPorRicardo.setFormulario(formularioHallazgoRagnar);
        publicacionRagnarSubidaPorRicardo.setAliasRescatista("El Ricardo");
        publicacionRagnarSubidaPorRicardo.setFechaHoraCreacion(LocalDateTime.of(2021, 10,28,13,10));
        publicacionRagnarSubidaPorRicardo.setAprobada(false);
        publicacionRagnarSubidaPorRicardo.setHabilitada(true);


        //Creo la publicacion y le agrego las preguntas
        PublicacionDetallada publicacionDarEnAdopcionARick = new PublicacionDetallada(rick, LocalDateTime.of(2021,10,23,8,30), true, usuario2, "heisenberg");

        publicacionDarEnAdopcionARick.addRespuesta(respuestaAPreguntaPeso);
        respuestaAPreguntaPeso.setPublicacion(publicacionDarEnAdopcionARick);

        publicacionDarEnAdopcionARick.addRespuesta(respuestaAPreguntaRaza);
        respuestaAPreguntaRaza.setPublicacion(publicacionDarEnAdopcionARick);

        publicacionDarEnAdopcionARick.addRespuesta(respuestaAPreguntaTextoLibrePubliRick);
        respuestaAPreguntaTextoLibrePubliRick.setPublicacion(publicacionDarEnAdopcionARick);

        PublicacionDetallada publicacionDarEnAdopcionAToby = new PublicacionDetallada(toby, LocalDateTime.of(2021,11,28, 11, 10), true, usuario2, "heisenberg");

        publicacionDarEnAdopcionAToby.addRespuesta(respuestaAPreguntaPeso1);
        respuestaAPreguntaPeso1.setPublicacion(publicacionDarEnAdopcionAToby);

        publicacionDarEnAdopcionAToby.addRespuesta(respuestaAPreguntaRaza1);
        respuestaAPreguntaRaza1.setPublicacion(publicacionDarEnAdopcionAToby);

        PublicacionDetallada publicacionDarEnAdopcionARagnar = new PublicacionDetallada(ragnar, LocalDateTime.of(2019,11,28, 11, 10), true, usuario2, "joaco");

        publicacionDarEnAdopcionARagnar.addRespuesta(respuestaAPreguntaPeso2);
        respuestaAPreguntaPeso2.setPublicacion(publicacionDarEnAdopcionARagnar);

        publicacionDarEnAdopcionARagnar.addRespuesta(respuestaAPreguntaRaza2);
        respuestaAPreguntaRaza2.setPublicacion(publicacionDarEnAdopcionARagnar);

        /////////////////-----------           Script alta en la bbdd    -----------------------/////////////////////


        EntityManagerHelper.beginTransaction();


        Query query = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Opcion");
        int rows = query.executeUpdate();
        System.out.println("DELETED ROWS: " + rows);
        Query query2 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Respuesta");
        int rows2 = query2.executeUpdate();
        System.out.println("DELETED ROWS: " + rows2);
        Query query3 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Pregunta");
        int rows3 = query3.executeUpdate();
        System.out.println("DELETED ROWS: " + rows3);
        Query query4 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM TipoDePregunta");
        int rows4 = query4.executeUpdate();
        System.out.println("DELETED ROWS: " + rows4);
        Query query5 = EntityManagerHelper.getEntityManager().createNativeQuery("DELETE FROM Mascota_caracteristica");
        int rows5 = query5.executeUpdate();
        System.out.println("DELETED ROWS: " + rows5);
        Query query6 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Publicacion");
        int rows6 = query6.executeUpdate();
        System.out.println("DELETED ROWS: " + rows6);
        Query query16 = EntityManagerHelper.getEntityManager().createNativeQuery("DELETE FROM mascota_fotos");
        int rows16 = query16.executeUpdate();
        System.out.println("DELETED ROWS: " + rows16);
        Query query7 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Mascota");
        int rows7 = query7.executeUpdate();
        System.out.println("DELETED ROWS: " + rows7);
        Query query15 = EntityManagerHelper.getEntityManager().createNativeQuery("DELETE FROM usuario_organizacion");
        int rows15 = query15.executeUpdate();
        System.out.println("DELETED ROWS: " + rows15);
        Query query8 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Usuario");
        int rows8 = query8.executeUpdate();
        System.out.println("DELETED ROWS: " + rows8);
        Query query9 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Organizacion");
        int rows9 = query9.executeUpdate();
        System.out.println("DELETED ROWS: " + rows9);
        Query query10 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Formulario");
        int rows10 = query10.executeUpdate();
        System.out.println("DELETED ROWS: " + rows10);
        Query query11 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Ubicacion");
        int rows11 = query11.executeUpdate();
        System.out.println("DELETED ROWS: " + rows11);
        Query query12 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Persona");
        int rows12 = query12.executeUpdate();
        System.out.println("DELETED ROWS: " + rows12);
        Query query13 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Direccion");
        int rows13 = query13.executeUpdate();
        System.out.println("DELETED ROWS: " + rows13);
        Query query14 = EntityManagerHelper.getEntityManager().createQuery("DELETE FROM Caracteristica");
        int rows14 = query14.executeUpdate();
        System.out.println("DELETED ROWS: " + rows14);




        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.getEntityManager().persist(firulais);
        EntityManagerHelper.getEntityManager().persist(usuario2);
        EntityManagerHelper.getEntityManager().persist(administrador1);
        EntityManagerHelper.getEntityManager().persist(respuestaAPreguntaPeso);
        EntityManagerHelper.getEntityManager().persist(respuestaAPreguntaRaza);
        EntityManagerHelper.getEntityManager().persist(respuestaAPreguntaPeso1);
        EntityManagerHelper.getEntityManager().persist(respuestaAPreguntaRaza1);
        EntityManagerHelper.getEntityManager().persist(respuestaAPreguntaPeso2);
        EntityManagerHelper.getEntityManager().persist(respuestaAPreguntaRaza2);
        EntityManagerHelper.getEntityManager().persist(respuestaAPreguntaTextoLibrePubliRick);
        EntityManagerHelper.getEntityManager().persist(publicacionMortySubidaPorWalter);
        EntityManagerHelper.getEntityManager().persist(publicacionRagnarSubidaPorRicardo);
        EntityManagerHelper.getEntityManager().persist(publicacionDarEnAdopcionARick);
        EntityManagerHelper.getEntityManager().persist(publicacionDarEnAdopcionAToby);
        EntityManagerHelper.getEntityManager().persist(publicacionDarEnAdopcionARagnar);

        EntityManagerHelper.commit();
        }

    /////////////////-----------           tests    -----------------------/////////////////////

    @Test
    public void brecuperandoAWalter(){
        Usuario heisenberg = (Usuario) EntityManagerHelper.createQuery("from Usuario where usuario = 'heisenberg'").getSingleResult();
        System.out.println(heisenberg.toString());
        Assert.assertEquals("heisenberg", heisenberg.getUsuario());
    }


    @Test
    public void crecuperandoAFirulais(){
        MascotaRegistrada firulaisTest = (MascotaRegistrada) EntityManagerHelper.createQuery("from Mascota where apodo = 'pichi'").getSingleResult();
        MascotaRegistrada firulaisTest2 = (MascotaRegistrada) EntityManagerHelper.createQuery("from Mascota where apodo = 'Rick'").getSingleResult();
        System.out.println(firulaisTest.toString());
        Assert.assertEquals("pichi", firulaisTest.getApodo());
    }


    @Test
    public void drecuperandoOrganizacionTest(){
        Organizacion orgTest = (Organizacion) EntityManagerHelper.createQuery("from Organizacion where nombreOrganizacion = 'vamonos pest'").getSingleResult();
        Assert.assertEquals("vamonos pest", orgTest.getNombreOrganizacion());
    }

    @Test
    public void erecuperandoPublicacionMortyTest(){
        Publicacion publicacion = (Publicacion) EntityManagerHelper.createQuery("from Publicacion where aliasRescatista = 'El Walter'").getSingleResult();
        Assert.assertEquals("sucio, polvoriento y apestoso", publicacion.getFormulario().getEstadoMascotaPerdida());
    }

    @Test
    public void frecuperandoPublicacionRicardoTest(){
        Publicacion publicacion = (Publicacion) EntityManagerHelper.createQuery("from Publicacion where aliasRescatista = 'El Ricardo'").getSingleResult();
        Assert.assertEquals("jugueton, escurridizo, tiene pulgas", publicacion.getFormulario().getEstadoMascotaPerdida());
    }

    @Test
    public void grecuperandoPublicacionDarEnAdopcionARick(){
        PublicacionDetallada publicaciondetallada = (PublicacionDetallada) EntityManagerHelper.createQuery("from Publicacion where aliasRescatista = 'heisenberg'").getSingleResult();
        System.out.println(publicaciondetallada.toString());
        String publicacionId = Integer.toString(publicaciondetallada.getId());
        List<Respuesta> preguntasContestedas =  EntityManagerHelper.createQuery("from Respuesta where publicacion_id = '" + publicacionId + "'").getResultList();
        Assert.assertEquals("Peso de la mascota", publicaciondetallada.getPreguntasContestadas().get(0).getPregunta().getNombrepregunta());
    }


    @Test
    public void hfiltrarPublicacionPorPreguntaContestadaTest(){
        RepositorioDePublicaciones repoPublicaciones = FactoryRepositorioPublicaciones.get();
        List<Publicacion> publicacionesFiltradas = repoPublicaciones.mostrarPublicacionesSegunPregunta("Peso de la mascota", "Mas de 10 kg");
        Assert.assertEquals(1, publicacionesFiltradas.size());
        System.out.println(publicacionesFiltradas.get(0).toString());
    }


}
