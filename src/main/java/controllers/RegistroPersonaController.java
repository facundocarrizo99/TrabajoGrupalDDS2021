package controllers;

import Repositorios.RepositorioDeUsuarios;
import Repositorios.factories.FactoryRepositorioUsuarios;
import entidades.Direccion;
import entidades.Persona;
import entidades.TipoDoc;
import entidades.usuario.Duenio;
import entidades.usuario.Usuario;
import entidades.usuario.Voluntario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RegistroPersonaController {

    HashMap<String, Object> parametros =  new HashMap<String, Object>();

    public ModelAndView inicio(Request request, Response response){
        return new ModelAndView(parametros,"registro-de-persona.hbs");
    }

    public Response crear(Request request, Response response){

        if(request != null){
            System.out.println(request.queryParams("nombre"));
            System.out.println(request.toString());
        } else System.out.println("request null");

        RepositorioDeUsuarios repo = FactoryRepositorioUsuarios.get();
        Duenio usuario = new Duenio();
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

        /*
        if(request.queryParams("tipo") != null){
            if(request.queryParams("tipo") != "DNI"){
                persona.setTipo(TipoDoc.DNI);
            }else persona.setTipo(TipoDoc.PASAPORTE);
        }
        */

        if(request.queryParams("nroDoc") != null){
            persona.setNroDeDocumento(request.queryParams("nroDoc"));
        }

        if(request.queryParams("calle") != null){
            direccion.setCalle(request.queryParams("calle"));
        }
        if(request.queryParams("altura") != null){
            direccion.setAltura(Integer.parseInt(request.queryParams("altura")));
        }
        if(request.queryParams("codigoPostal") != null){
            direccion.setCodigoPostal(request.queryParams("codigoPostal"));
        }

        if(request.queryParams("email") != null){
            persona.setEmail(request.queryParams("email"));
        }

        if(request.queryParams("telefono") != null){
            persona.setTelefono(request.queryParams("telefono"));
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
