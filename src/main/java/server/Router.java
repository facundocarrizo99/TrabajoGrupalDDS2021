package server;


import com.github.jknack.handlebars.Helper;
import controllers.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

import middleware.Auth;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.HandlebarsTemplateEngineBuilder;
import spark.utils.BooleanHelper;

import com.github.jknack.handlebars.Options;

public class Router {

    private static HandlebarsTemplateEngine engine;


    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .withHelper("inc", new Helper<Integer>() {
                    public Integer apply(Integer value, Options options){ return value+1;}
                })
                .build();
    }


    public static void init() {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
        Router.initEngine();
        Spark.staticFileLocation("/public");
        System.out.println("before doing configure");

        Router.configure();
    }


    private static void configure() {
        // UsuarioRestControllerEjemplo usuarioRestControllerEjemplo = new UsuarioRestControllerEjemplo();
        //UsuarioController usuarioController = new UsuarioController();

        RegistroMascotaController registroMascotaController = new RegistroMascotaController();

        MisMascotasController misMascotasController = new MisMascotasController();

        FormularioController formularioController = new FormularioController();

        LoginController loginController = new LoginController();

        IndexController indexController = new IndexController();

        PageController pageController = new PageController();

        CrearVoluntarioController crearVoluntarioController = new CrearVoluntarioController();

        RegistroPersonaController registroPersonaController = new RegistroPersonaController();

        PublicacionAdopcionController publicacionAdopcionController = new PublicacionAdopcionController();

        Auth authMiddleware = new Auth();

        //Spark.before("/", authMiddleware::verificarSesion);

        //index controller
        Spark.get("/", indexController::directoIndex, Router.engine);

        Spark.get("/index", indexController::inicio, Router.engine);


        //login controller
        Spark.get("/login", loginController::inicio, Router.engine);

        Spark.post("/login", loginController::login, Router.engine);

        Spark.get("/logout", loginController::logout);


        //registro de persona
        Spark.get("/registro-de-persona", registroPersonaController::inicio, Router.engine);

        Spark.post("/registro-de-persona", registroPersonaController::crear);


        //registro de voluntario
        Spark.get("/crear-voluntario", crearVoluntarioController::inicio, Router.engine);

        Spark.post("/crear-voluntario", crearVoluntarioController::crearVoluntario);


        //publicaciones adopcion controller
        Spark.get("/publicaciones-adopcion-filtradas", publicacionAdopcionController::filtraryMostrar, Router.engine);

        Spark.get("/publicaciones-adopcion", publicacionAdopcionController::mascotasEnAdopcion, Router.engine);


        //registro mascota
        Spark.get("/registro-de-mascota", registroMascotaController::inicio, Router.engine);

        Spark.post("/registro-de-mascota", registroMascotaController::crear);

        Spark.get("/publicaciones-en-proceso", pageController::publicacionesProceso, Router.engine);

        Spark.get("/publicaciones", pageController::publicaciones, Router.engine);

        Spark.get("/mis-mascotas", misMascotasController::inicio , Router.engine);

        Spark.get("/mascota-perdida-sin-chapita", pageController::mascotaSinChapita, Router.engine);

        Spark.get("/mascota-con-chapita", pageController::mascotaConChapita, Router.engine);

        Spark.get("/hogares", pageController::hogares, Router.engine);

        Spark.get("/dar-adopcion", pageController::darAdopcion, Router.engine);

        Spark.get("/configuracionVol", pageController::configVoluntario, Router.engine);

        Spark.get("/configuracionDuenio", pageController::configDuenio, Router.engine);

        Spark.get("/configuracionAdmin", pageController::configAdmin, Router.engine);

        Spark.get("/caracteristicas", pageController::caracteristicas, Router.engine);

        Spark.get("/adopcion-de-mascota", pageController::adopcion, Router.engine);

        Spark.get("/publicaciones-en-proceso", formularioController::mostrarHabilitadas, Router.engine);

        Spark.get("/mascota-perdida-sin-chapita", formularioController::inicio, Router.engine);

        Spark.post("/mascota-perdida-sin-chapita", formularioController::crear);

        //Spark.get("/usuarios", usuarioController::mostrarTodos, Router.engine);

        //Spark.get("/usuario/:id", usuarioController::mostrar, Router.engine);

        //Spark.get("/usuario", usuarioController::crear, Router.engine);

        //Spark.post("/usuario/:id", usuarioController::modificar);

        //Spark.post("/usuario", usuarioController::guardar);

        //Spark.delete("/usuario/:id", usuarioController::eliminar);

        //Spark.get("/api/usuario/:id", usuarioRestControllerEjemplo::mostrar);

    }

    public static void CheckIfAuthenticated(Request request, Response response) {
        if (request.cookie("id") == null || !request.cookie("id").equals(request.session().id())) {
            System.out.printf("USUARIO NO AUTENTICADO, REDIRECT A LOGIN. cookie-id: %s, session-id: %s", request.cookie("id"), request.session().id());
            response.redirect("/");
        }
    }
}