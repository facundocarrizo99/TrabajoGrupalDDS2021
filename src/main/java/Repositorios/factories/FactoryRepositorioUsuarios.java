package Repositorios.factories;


import Repositorios.RepositorioDeUsuarios;
import Repositorios.daos.DAO;
import Repositorios.daos.DAOHibernate;
import config.Configuracion;
import entidades.usuario.Usuario;

public class FactoryRepositorioUsuarios {
    private static RepositorioDeUsuarios repo;

    static {
        repo = null;
    }

    public static RepositorioDeUsuarios get(){
        if(repo == null){
            if(Configuracion.useDataBase){
                DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
                repo = new RepositorioDeUsuarios(dao);
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
