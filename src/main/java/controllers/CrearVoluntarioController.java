package controllers;

import Repositorios.RepositorioDeUsuarios;
import Repositorios.factories.FactoryRepositorioUsuarios;
import entidades.Direccion;
import entidades.Persona;
import entidades.usuario.Voluntario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;

public class CrearVoluntarioController {

    HashMap<String, Object> parametros =  new HashMap<String, Object>();

    public ModelAndView inicio(Request request, Response response){
        return new ModelAndView(parametros,"crear-voluntario.hbs");
    }

    public Response crearVoluntario(Request request, Response response){

        if(request != null){
            System.out.println(request.queryParams("nombre"));
            System.out.println(request.toString());
        } else System.out.println("request null");

        RepositorioDeUsuarios repo = FactoryRepositorioUsuarios.get();
        Voluntario usuario = new Voluntario();
        Persona persona = new Persona();
        Direccion direccion = new Direccion();

        if(request.queryParams("nombre") != null){
            persona.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apellido") != null){
            persona.setApellido(request.queryParams("apellido"));
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            persona.setFechaDenacimiento(fechaDeNacimiento);
        }

        if(request.queryParams("usuario") != null){
            usuario.setUsuario(request.queryParams("usuario"));
        }

        if(request.queryParams("contrasenia") != null){
            usuario.setContrasena(request.queryParams("contrasenia"));
        }

        persona.setDireccion(direccion);
        usuario.setPersona(persona);

        repo.agregar(usuario);
        response.redirect("/index");
        return response;
    }
}
