package controllers;

import Repositorios.RepositorioDePublicaciones;
import Repositorios.factories.FactoryRepositorioPublicaciones;
import entidades.Publicacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

import org.eclipse.jetty.util.log.Slf4jLog;

public class PublicacionAdopcionController {

    RepositorioDePublicaciones repoPublicaciones;
    Slf4jLog log;


    HashMap<String, Object> parametros =  new HashMap<String, Object>();



    public ModelAndView mascotasEnAdopcion(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        List<Publicacion> publicaciones = new ArrayList<Publicacion>();
        publicaciones = this.repoPublicaciones.mostrarPublicacionesAdopcion();
        parametros.put("publicaciones", publicaciones);
        parametros.put("nroResultados", "Showing " + String.valueOf(publicaciones.size()) + " Results");
        Iterator it = request.queryParams().iterator();
        int i=0;
        while (it.hasNext()){
            log.info("queryparam " + String.valueOf(i) + ":" + it.next());
            i++;
        }
        return  new ModelAndView(parametros, "publicacion-adopcion.hbs");
    }

    public ModelAndView filtraryMostrar(Request request, Response response) {
        log.info("dentro del metodo filtraryMostrar");
        Map<String, Object> parametros = new HashMap<>();
        Set<Publicacion> publicaciones = new HashSet<Publicacion>();
        log.info(request.queryParams().toString());
        Iterator it = request.queryParams().iterator();
        int i=0;
        while (it.hasNext()){
            String queryparam = (String) it.next();
            log.info("queryparam " + String.valueOf(i) + ":" + queryparam);
            List<Publicacion> publicacionesresult = new ArrayList<Publicacion>();
            publicacionesresult = this.repoPublicaciones.mostrarPublicacionesSegunPregunta(queryparam);
            log.info(publicacionesresult.toString());
            publicaciones.addAll(publicacionesresult);
            i++;
        }
        parametros.put("publicaciones", publicaciones);
        parametros.put("nroResultados", "Showing " + String.valueOf(publicaciones.size()) + " Results");
        return  new ModelAndView(parametros, "publicacion-adopcion.hbs");
    }



    public PublicacionAdopcionController() {
        this.repoPublicaciones = FactoryRepositorioPublicaciones.get();
        this.log = new Slf4jLog("test");
    }
}
