package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class PageController {

    HashMap<String, Object> parametros =  new HashMap<String, Object>();

    public ModelAndView registroMascotas(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"registro-de-mascota.hbs");
    }

    public ModelAndView publicacionesProceso(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"publicaciones-en-proceso.hbs");
    }

    public ModelAndView publicaciones(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"publicaciones.hbs");
    }

    public ModelAndView misMascotas(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"mis-mascotas.hbs");
    }

    public ModelAndView mascotaSinChapita(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"mascota-perdida-sin-chapita.hbs");
    }

    public ModelAndView mascotaConChapita(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"mascotas-con-chapita.hbs");
    }

    public ModelAndView hogares(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"hogares.hbs");
    }

    public ModelAndView darAdopcion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"dar-adopcion.hbs");
    }

    public ModelAndView crearVoluntario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"crear-voluntario.hbs");
    }

    public ModelAndView configVoluntario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"configuracionVol.hbs");
    }

    public ModelAndView configDuenio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"configuracionDuenio.hbs");
    }

    public ModelAndView configAdmin(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"configuracionAdmin.hbs");
    }

    public ModelAndView caracteristicas(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"caracteristicas.hbs");
    }

    public ModelAndView adopcion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"adopcion-de-mascota.hbs");
    }
}
