package Repositorios.factories;

import Repositorios.RepositorioDePreguntas;
import Repositorios.RepositorioDePublicaciones;
import Repositorios.daos.DAO;
import Repositorios.daos.DAOHibernate;
import config.Configuracion;
import entidades.Publicacion;
import entidades.formularios.Pregunta;

public class FactoryRepositorioPreguntas {
    private static RepositorioDePreguntas repo;

    static {
        repo = null;
    }

    public static RepositorioDePreguntas get(){
        if(repo == null){
            if(Configuracion.useDataBase){
                DAO<Pregunta> dao = new DAOHibernate<>(Pregunta.class);
                repo = new RepositorioDePreguntas(dao);
            }
            /*
            else{
                repo = new RepositorioDeUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
            }
             */
        }
        return repo;
    }
}
