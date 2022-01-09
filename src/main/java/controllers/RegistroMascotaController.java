package controllers;

import Repositorios.RepositorioDeUsuarios;
import Repositorios.factories.FactoryRepositorioUsuarios;
import db.EntityManagerHelper;
import entidades.mascota.DescripcionFisica;
import entidades.mascota.MascotaRegistrada;
import entidades.mascota.TipoDeMascota;
import entidades.usuario.Duenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import java.time.LocalDate;
import java.util.HashMap;

public class RegistroMascotaController {

    int randomNum = ThreadLocalRandom.current().nextInt(1, 14 + 1);

    HashMap<String, Object> parametros =  new HashMap<String, Object>();

    public ModelAndView inicio(Request request, Response response){
        return new ModelAndView(parametros,"registro-de-mascota.hbs");
    }

    public Response crear(Request request, Response response){

        String fotourl = "img/img-0" + String.valueOf(randomNum) + ".jpg";

        ArrayList<String> fotosMascota = new ArrayList<String>();

        fotosMascota.add(fotourl);

        MascotaRegistrada mascota = new MascotaRegistrada();


        if(request != null){
            System.out.println(request.queryParams("nombre"));
            System.out.println(request.toString());
        } else System.out.println("request null");

        if(request.cookie("user") != null) {

            System.out.println(request.cookie("user"));
            RepositorioDeUsuarios repo = FactoryRepositorioUsuarios.get();
            Duenio user =(Duenio) repo.buscarUsuario(request.cookie("user"));
            System.out.println(user.toString());
            mascota.setDuenio(user);
        }

        if(request.queryParams("nombre") != null){
            System.out.println(request.queryParams("nombre"));
            mascota.setNombre(request.queryParams("nombre"));
        }

        if(request.queryParams("apodo") != null){
            System.out.println(request.queryParams("apodo"));
            mascota.setApodo(request.queryParams("apodo"));
        }

        if(request.queryParams("peso") != null){
            System.out.println(request.queryParams("peso"));
            Integer peso = Integer.valueOf(request.queryParams("peso"));
            if (peso < 5){
                mascota.setDescripcionFisica(DescripcionFisica.CHICA);
            }
            else if (peso >= 5 && peso <=10){
                mascota.setDescripcionFisica(DescripcionFisica.MEDIANA);
            }
            else {
                mascota.setDescripcionFisica(DescripcionFisica.GRANDE);
            }
        }

        if(request.queryParams("fechaDeNacimiento") != null && !request.queryParams("fechaDeNacimiento").isEmpty()){
            System.out.println(request.queryParams("fechaDeNacimiento").toString());
            LocalDate fechaDeNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
            mascota.setFechaDeNacimiento(fechaDeNacimiento);
        }

        if(request.queryParams("sexo") != null){
            System.out.println(request.queryParams("sexo"));
            if(request.queryParams("sexo").equals("Hembra")){
                mascota.setEsHembra(true);
            }
            else
                mascota.setEsHembra(false);
        }

        if(request.queryParams("tipoMascota") != null){
            System.out.println(request.queryParams("tipoMascota"));
            if(request.queryParams("sexo").equals("Perro")){
                mascota.setTipoDeMascota(TipoDeMascota.PERRO);
            }
            else
                mascota.setTipoDeMascota(TipoDeMascota.GATO);
        }

        mascota.setFotos(fotosMascota);

        EntityManagerHelper.beginTransaction();
        System.out.println("transaction opened");
        EntityManagerHelper.getEntityManager().persist(mascota);
        System.out.println("persisted");
        EntityManagerHelper.commit();

        response.redirect("/index");

        return response;
    }

}
