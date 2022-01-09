package controllers;

import Repositorios.RepositorioDeUsuarios;
import Repositorios.factories.FactoryRepositorioUsuarios;
import db.EntityManagerHelper;
import entidades.mascota.Mascota;
import entidades.usuario.Duenio;
import org.eclipse.jetty.util.log.Slf4jLog;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class MisMascotasController {

    Slf4jLog log;
    HashMap<String, Object> parametros =  new HashMap<String, Object>();

    public ModelAndView inicio(Request request, Response response) {
        System.out.println(request.cookie("user"));
        RepositorioDeUsuarios repo = FactoryRepositorioUsuarios.get();
        Duenio user = (Duenio) repo.buscarUsuario(request.cookie("user"));
        System.out.println(user.toString());
        String idPersona = String.valueOf(user.getId());
        System.out.println(idPersona);

        Map<String, Object> parametros = new HashMap<>();

        String query = "from Mascota where duenio_id = '" + idPersona + "'";

        System.out.println(query);

        List<Mascota>mascotas = EntityManagerHelper.createQuery(query).getResultList();

        parametros.put("mascotas", mascotas);

        return  new ModelAndView(parametros, "mis-mascotas.hbs");
    }
}