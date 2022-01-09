package controllers;

import Repositorios.Repositorio;
import Repositorios.RepositorioDeUsuarios;
import Repositorios.factories.FactoryRepositorio;
import Repositorios.factories.FactoryRepositorioUsuarios;
import entidades.*;
import entidades.formularios.Formulario;
import entidades.usuario.Voluntario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FormularioController {
    private Repositorio<Publicacion> repositorio;


    public FormularioController (){
        this.repositorio = FactoryRepositorio.get(Publicacion.class);
    }

    public ModelAndView inicio(Request request, Response response){
        HashMap<String, Object> parametros =  new HashMap<String, Object>();
        return new ModelAndView(parametros,"mascota-perdida-sin-chapita.hbs");
    }

    public Response crear(Request request, Response response) {
        Formulario formulario = new Formulario();
        Ubicacion ubicacionMascota = new Ubicacion();
        Direccion direccionPersona = new Direccion();
        Persona persona = new Persona();
        Publicacion publicacion = new Publicacion();
        //Voluntario voluntario = new Voluntario();



        ////////////////FORMULARIO//////////////////
        if (request.queryParams("ubicacion") != null) {
            ubicacionMascota.setDireccion(request.queryParams("ubicacion"));
        }

        if (request.queryParams("long") != null) {
            ubicacionMascota.setLongitud(Double.parseDouble(request.queryParams("long")));
        }

        if (request.queryParams("lat") != null) {
            ubicacionMascota.setLatitud(Double.parseDouble(request.queryParams("lat")));
        }

        if (request.queryParams("estado") != null) {
            formulario.setEstadoMascotaPerdida(request.queryParams("estado"));
        }


        ///////////////PERSONA/////////////////////
        if (request.queryParams("nombre") != null) {
            persona.setNombre(request.queryParams("nombre"));
        }

        if (request.queryParams("apellido") != null) {
            persona.setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            persona.setFechaDenacimiento(fechaDeNacimiento);
        }

        if(request.queryParams("nroDoc") != null){
            persona.setNroDeDocumento(request.queryParams("nroDoc"));
        }

        if(request.queryParams("email") != null){
            persona.setEmail(request.queryParams("email"));
        }

        if(request.queryParams("telefono") != null){
            persona.setTelefono(request.queryParams("telefono"));
        }
        ////////////////DIRECCION PERSONA////////////////
        if (request.queryParams("calle") != null) {
            direccionPersona.setCalle(request.queryParams("calle"));
        }

        if (request.queryParams("altura") != null) {
            direccionPersona.setAltura(Integer.parseInt(request.queryParams("altura")));
        }

        if (request.queryParams("codPostal") != null) {
            direccionPersona.setCodigoPostal(request.queryParams("codPostal"));
        }

        persona.setDireccion(direccionPersona);

        ////////////////////FORMULARIO///////////////////
        formulario.setUbicacionHallazgoMascotaPerdida(ubicacionMascota);
        formulario.setCreador(persona);
        formulario.setFechaCreacion(LocalDate.now());

        ///////////////////PUBLICACION////////////////////
        publicacion.setAliasRescatista(persona.getNombre());
        publicacion.setFormulario(formulario);
        publicacion.setFechaHoraCreacion(LocalDateTime.now());

        repositorio.agregar(publicacion);
        response.redirect("/index");
        return response; //new ModelAndView(parametros, "");
    }

    public ModelAndView mostrarTodos(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>(); //Creo un diccionario
        List<Publicacion> publicaciones = this.repositorio.buscarTodos(); //Recupere a las publicaciones del repositorio
        parametros.put("publicaciones", publicaciones); // Agrego el listado de usuarios que recupere al repositorio
        return new ModelAndView(parametros,"publicaciones-en-proceso.hbs"); // Se lo mando a la vista
    }

    public ModelAndView mostrarHabilitadas(Request request, Response response){
        //Cuando se hace el finalizar, se lo mando a la org mas cercana // 1) Se lo mandamos a la primera organizacion que encuentra 2) Order By por cercania y me quedo con la mas cercana 3) Le mando la publicacion a todas y la primera que la apruebe se la queda (debo hacer un control de que ya no deberia aparecer en el resto)
        // Filtrar dependiendo de la organizacion (mas cercana al radio en donde se encontro la mascota)
        Map<String, Object> parametros = new HashMap<>(); //Creo un diccionario
        //List<Publicacion> publicaciones = this.repositorio.buscarTodos(); //Recupere a las publicaciones del repositorio
        List<Publicacion> publicaciones = this.repositorio.buscarTodos().stream().filter(publicacion -> publicacion.getHabilitada().equals(true)).collect(Collectors.toList());
        parametros.put("publicaciones", publicaciones); // Agrego el listado de publicaciones que recupere al repositorio
        return new ModelAndView(parametros,"publicaciones-en-proceso.hbs"); // Se lo mando a la vista
    }
}