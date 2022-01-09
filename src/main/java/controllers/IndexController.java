package controllers;

import server.Router;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class IndexController {

    Map<String, Object> parametros = new HashMap<>();

    public ModelAndView inicio(Request request, Response response) {
        checkIfAuthenticated(request, response);
        if (request.cookie("rol") != null) {

            String rol = request.cookie("rol");
            System.out.println("dentro del index controller rol: " + rol);

            if (rol.equals("duenio")) {
                return new ModelAndView(parametros, "indexDuenio.hbs");
            } else if (rol.equals("admin")) {
                return new ModelAndView(parametros, "indexAdmin.hbs");
            } else if (rol.equals("voluntario")) {
                return new ModelAndView(parametros, "indexVoluntario.hbs");
            } else return new ModelAndView(parametros, "index.hbs");
        } else return new ModelAndView(parametros, "index.hbs");
    }

    public void checkIfAuthenticated(Request request, Response response) {
        if (request.cookie("id") == null || !request.cookie("id").equals(request.session().id())) {
            System.out.printf("USUARIO NO AUTENTICADO, REDIRECT A LOGIN. cookie-id: %s, session-id: %s", request.cookie("id"), request.session().id());
            response.redirect("/");
        }
    }

    public ModelAndView directoIndex(Request request, Response response){
        return new ModelAndView(parametros,"index.hbs");
    }
}



