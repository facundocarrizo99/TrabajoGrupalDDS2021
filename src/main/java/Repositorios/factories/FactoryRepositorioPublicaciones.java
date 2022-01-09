package Repositorios.factories;

import Repositorios.RepositorioDePublicaciones;
import Repositorios.RepositorioDeUsuarios;
import Repositorios.daos.DAO;
import Repositorios.daos.DAOHibernate;
import config.Configuracion;
import entidades.Publicacion;

public class FactoryRepositorioPublicaciones {
    private static RepositorioDePublicaciones repo;

    static {
        repo = null;
    }

    public static RepositorioDePublicaciones get(){
        if(repo == null){
            if(Configuracion.useDataBase){
                DAO<Publicacion> dao = new DAOHibernate<>(Publicacion.class);
                repo = new RepositorioDePublicaciones(dao);
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
