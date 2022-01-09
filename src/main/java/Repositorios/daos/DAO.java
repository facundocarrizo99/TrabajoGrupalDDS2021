package Repositorios.daos;

import java.util.List;
import Repositorios.BusquedaCondicional;

import javax.persistence.NoResultException;

public interface DAO<T> {
    List<T> buscarTodos();
    T buscar(int id)  throws NoResultException;
    T buscar(BusquedaCondicional condicional) throws NoResultException;
    void agregar(Object unObjeto);
    void modificar(Object unObjeto);
    void eliminar(Object unObjeto);
}
