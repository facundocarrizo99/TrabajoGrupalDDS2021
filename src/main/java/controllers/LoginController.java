package controllers;

import Repositorios.RepositorioDeUsuarios;
import Repositorios.factories.FactoryRepositorioUsuarios;
import Utils.Exceptions.UserAlreadyLoggedException;
import Utils.Exceptions.UserBlockedException;
import Utils.Exceptions.UserNotFoundException;
import entidades.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    HashMap<String, Object> parametros =  new HashMap<String, Object>();

    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs"); ///CAMBIO A INDEX PARA NO ARRANCAR CON UN LOGIN
    }

    public ModelAndView login(Request request, Response response){

        try{

            RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contrasenia     = request.queryParams("contrasenia");
            System.out.println("username" +nombreDeUsuario);
            System.out.println("contrasenia" + contrasenia);

            if(repoUsuarios.existe(request.queryParams("nombreDeUsuario"), request.queryParams("contrasenia"))){
                String rol = "user";
                System.out.printf("-3");

                Usuario usuario = repoUsuarios.buscarUsuario(nombreDeUsuario, contrasenia);

                System.out.printf("Dentro del login controller usuario encontrado: " + usuario.toString());

                if(usuario.getClass().getName().contains("Administrador")){
                    System.out.printf(usuario.getClass().getName());
                    rol = "admin";
                }

                else if(usuario.getClass().getName().contains("Duenio")){
                    System.out.printf(usuario.getClass().getName());
                    rol = "duenio";
                }

                else if(usuario.getClass().getName().contains("Voluntario")){
                    System.out.printf(usuario.getClass().getName());
                    rol = "voluntario";
                }

                if(usuario.getIntentosInicioSesionFallidos() != null && usuario.getIntentosInicioSesionFallidos() >= 5){
                    throw new UserBlockedException("usuario bloqueado por poseer mas de 4 intentos fallidos de inicio de sesion");
                }

                if(usuario.getLastLogin() != null && usuario.getLastLogout() !=null && usuario.getLastLogin().isAfter(usuario.getLastLogout())){
                    throw new UserAlreadyLoggedException("usuario ya logueado, cierre sesion e intente nuevamente");
                }

                if(usuario.getLastLogin() != null && usuario.getLastLogout() == null){
                    throw new UserAlreadyLoggedException("usuario ya logueado, cierre sesion e intente nuevamente");
                }

                    System.out.printf("1");

                if(usuario.getLastLogin() != null && usuario.getLastLogout() == null){
                    throw new UserAlreadyLoggedException("usuario ya logueado, cierre sesion e intente nuevamente");
                }

                request.session(true);
                request.session().attribute("usuario", usuario.getUsuario());
                response.cookie("rol", rol);
                response.cookie("id", request.session().id(), 1000000000);
                response.cookie("user", usuario.getUsuario());
                usuario.iniciarSesion();
                repoUsuarios.modificar(usuario);
                System.out.printf("Usuario: %s con id %s y rol %s", usuario.getUsuario(), request.session().id(), usuario.getClass().getName());
                response.redirect("/index");
            }
            else{
                throw new UserNotFoundException();
            }
        }

        catch (UserBlockedException e){
            parametros.put("bloqueado",true);
            return new ModelAndView(parametros,"login.hbs");
        }

        catch (UserAlreadyLoggedException e){
            parametros.put("user-logueado",true);
            return new ModelAndView(parametros,"login.hbs");
        }

        catch (UserNotFoundException e ){
            parametros.put("user-invalido",true);
            return new ModelAndView(parametros,"login.hbs");
        }

        catch(NoResultException ex){
            parametros.put("user-invalido",true);
            return new ModelAndView(parametros,"login.hbs");
        }

            return new ModelAndView(parametros,"login.hbs");
    }

    public Response logout(Request request, Response response){
        RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();
        String usuarioRequest = request.session().attribute("usuario");
        System.out.println("usuario request: " + usuarioRequest);
        Usuario usuario = repoUsuarios.buscarUsuario(usuarioRequest);
        if(usuario != null){
            usuario.cerrarSesion();
            repoUsuarios.modificar(usuario);
        }
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

}