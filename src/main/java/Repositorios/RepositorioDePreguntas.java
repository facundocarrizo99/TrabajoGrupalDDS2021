package Repositorios;

import Repositorios.daos.DAO;
import db.EntityManagerHelper;
import entidades.Organizacion;
import entidades.formularios.Pregunta;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDePreguntas extends Repositorio<Pregunta> {

    public RepositorioDePreguntas(DAO<Pregunta> dao) {
        super(dao);
    }

    public Boolean existe (String nombreDePregunta){
        return buscarPregunta(nombreDePregunta) != null;
    }

    public Pregunta buscarPregunta (String nombreDePregunta){
        return this.dao.buscar(condicionPregunta(nombreDePregunta));
    }


    public List<Pregunta> mostrarTodos (){
        return this.dao.buscarTodos();
    }


    private BusquedaCondicional condicionPregunta(String nombreDePregunta){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Pregunta> preguntaQuery = criteriaBuilder.createQuery(Pregunta.class);

        Root<Pregunta> condicionRaiz = preguntaQuery.from(Pregunta.class);

        Predicate condicionNombreDePregunta = criteriaBuilder.equal(condicionRaiz.get("Pregunta"), nombreDePregunta);

        preguntaQuery.where(condicionNombreDePregunta);

        return new BusquedaCondicional(null, preguntaQuery);
    }
}
