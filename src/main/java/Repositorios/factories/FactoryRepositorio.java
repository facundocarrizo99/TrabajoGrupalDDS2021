package Repositorios.factories;


import Repositorios.Repositorio;
import Repositorios.daos.DAO;
import Repositorios.daos.DAOHibernate;
import config.Configuracion;

import java.util.HashMap;

public class FactoryRepositorio {
    private static HashMap<String, Repositorio> repos;

    static {
        repos = new HashMap<>();
    }

    public static <T> Repositorio<T> get(Class<T> type){
        Repositorio repo;
        if(repos.containsKey(type.getName())){
            repo = repos.get(type.getName());
        }
        else{
            if(Configuracion.useDataBase){
                DAO<T> dao = new DAOHibernate<>(type);
                repo = new Repositorio<>(dao);
            }
            else{
                repo = new Repositorio<>(new DAOHibernate<>(type));       //new DAOMemoria<>(Data.getData(type)
            }

            repos.put(type.toString(), repo);
        }
        return repo;
    }
}
