package Repositorios;
import javax.persistence.criteria.CriteriaBuilder;
import Repositorios.daos.DAO;
import entidades.Publicacion;
import entidades.usuario.Usuario;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDeUsuarios extends Repositorio<Usuario> {

    public RepositorioDeUsuarios(DAO<Usuario> dao) {
        super(dao);
    }

    public Boolean existe (String nombreDeUsuario, String contrasenia){
        return buscarUsuario(nombreDeUsuario, contrasenia) != null;
    }

    public Usuario buscarUsuario (String nombreDeUsuario, String contrasenia){
        return this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
    }

    public Usuario buscarUsuario (String nombreDeUsuario){
        return this.dao.buscar(condicionUsuario(nombreDeUsuario));
    }

    public List<Usuario> mostrarTodos (){
        return this.dao.buscarTodos();
    }

    private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("usuario"), nombreDeUsuario);
        Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contrasena"), contrasenia);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

        usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }

    private BusquedaCondicional condicionUsuario(String nombreDeUsuario){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("usuario"), nombreDeUsuario);

        usuarioQuery.where(condicionNombreDeUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }
}
