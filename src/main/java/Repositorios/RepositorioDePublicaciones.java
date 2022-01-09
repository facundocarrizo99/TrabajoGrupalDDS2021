package Repositorios;

import Repositorios.daos.DAO;
import db.EntityManagerHelper;
import entidades.Organizacion;
import entidades.Publicacion;
import entidades.PublicacionDetallada;
import entidades.formularios.Opcion;
import entidades.formularios.Pregunta;
import entidades.formularios.Respuesta;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositorioDePublicaciones extends Repositorio<Publicacion> {

    public RepositorioDePublicaciones(DAO<Publicacion> dao) {
        super(dao);
    }

    public Boolean existe(String aliasRescatista) {
        return buscarPublicacion(aliasRescatista) != null;
    }

    public Publicacion buscarPublicacion(String aliasRescatista) {
        return this.dao.buscar(condicionAliasRescatista(aliasRescatista));
    }

    public List<Publicacion> mostrarPublicacionesAdopcion() {
        return (List<Publicacion>) EntityManagerHelper.createQuery("from Publicacion where Detallada = 'si'").getResultList();
    }

    public List<Publicacion> mostrarPublicacionesMascotaPerdida() {
        return (List<Publicacion>) this.dao.buscar(condicionNoDetalladas());
    }

    /*
    public List<PublicacionDetallada> mostrarPublicacionesSegunPregunta(String pregunta, String respuesta) {
        List publicaciones = this.mostrarPublicacionesAdopcion();
        List publicacionesFiltradas = new ArrayList<PublicacionDetallada>();
        Iterator<PublicacionDetallada> iterator = publicaciones.iterator();
        while (iterator.hasNext()) {
            PublicacionDetallada publi = iterator.next();
            Iterator<Respuesta> iterator2 = publi.getPreguntasContestadas().iterator();
            while (iterator2.hasNext()) {
                Respuesta respuesta1 = iterator2.next();
                Pregunta pregunta1 = respuesta1.getPregunta();
                if (pregunta1.getNombrepregunta().equalsIgnoreCase(pregunta)) {
                    if(contieneOpcionSeleccionada(pregunta1, respuesta)){
                        publicacionesFiltradas.add(publi);
                    }
                }
            }
        }
        return publicacionesFiltradas;
    }

     */
  /*
    public List<PublicacionDetallada> mostrarPublicacionesSegunPregunta(String pregunta, String respuesta){
        List<PublicacionDetallada> publicacionesFiltradas = new ArrayList<PublicacionDetallada>();
        Opcion opc = (Opcion) EntityManagerHelper.createQuery("from Opcion where opcion = '" + respuesta + "' and seleccionada = 1").getSingleResult();
        if (opc.getPregunta().getNombrepregunta().equalsIgnoreCase(pregunta)) {
            int preguntaId = opc.getPregunta().getId();
            Respuesta rta = (Respuesta) EntityManagerHelper.createQuery("from Respuesta where pregunta_id = " + Integer.toString(preguntaId)).getSingleResult();
            PublicacionDetallada publicacion = rta.getPublicacion();
            publicacionesFiltradas.add(publicacion);
        }
        return publicacionesFiltradas;
    }

    public List<PublicacionDetallada> mostrarPublicacionesSegunPregunta(String respuesta){
        List<PublicacionDetallada> publicacionesFiltradas = new ArrayList<PublicacionDetallada>();
        Opcion opc = (Opcion) EntityManagerHelper.createQuery("from Opcion where opcion = '" + respuesta + "' and seleccionada = 1").getSingleResult();
        int preguntaId = opc.getPregunta().getId();
        Respuesta rta = (Respuesta) EntityManagerHelper.createQuery("from Respuesta where pregunta_id = " + Integer.toString(preguntaId)).getSingleResult();
        PublicacionDetallada publicacion = rta.getPublicacion();
        publicacionesFiltradas.add(publicacion);
        return publicacionesFiltradas;
    }


   */
    public List<Publicacion> mostrarPublicacionesSegunPregunta(String pregunta, String respuesta){
        List<Publicacion> publicacionesFiltradas = new ArrayList<Publicacion>();
        try {
            Opcion opc = (Opcion) EntityManagerHelper.createQuery("from Opcion where opcion = '" + respuesta + "' and seleccionada = 1").getSingleResult();
            if (opc.getPregunta().getNombrepregunta().equalsIgnoreCase(pregunta)) {
                int preguntaId = opc.getPregunta().getId();
                Respuesta rta = (Respuesta) EntityManagerHelper.createQuery("from Respuesta where pregunta_id = " + Integer.toString(preguntaId)).getSingleResult();
                PublicacionDetallada publicacion = rta.getPublicacion();
                publicacionesFiltradas.add(publicacion);
            }
            return publicacionesFiltradas;
        }
        catch ( NoResultException ex){
            ex.printStackTrace();
            return publicacionesFiltradas;
        }
    }


    public List<Publicacion> mostrarPublicacionesSegunPregunta(String respuesta){
        List<Publicacion> publicacionesFiltradas = new ArrayList<Publicacion>();
        try {
            Opcion opc = (Opcion) EntityManagerHelper.createQuery("from Opcion where opcion = '" + respuesta + "' and seleccionada = 1").getSingleResult();
            int preguntaId = opc.getPregunta().getId();
            Respuesta rta = (Respuesta) EntityManagerHelper.createQuery("from Respuesta where pregunta_id = " + Integer.toString(preguntaId)).getSingleResult();
            PublicacionDetallada publicacion = rta.getPublicacion();
            publicacionesFiltradas.add(publicacion);
            return publicacionesFiltradas;
        }
        catch ( NoResultException ex){
            ex.printStackTrace();
            return publicacionesFiltradas;
        }
    }


    private Boolean contieneOpcionSeleccionada(Pregunta pregunta, String opc){
        Iterator<Opcion> iterator = pregunta.getOpciones().iterator();
        while (iterator.hasNext()){
            Opcion opcion = iterator.next();
            if(opcion.getOpcion().equalsIgnoreCase(opc) && opcion.getSeleccionada()){
                return true;
            }
        }
        return false;
    }


    public List<Publicacion> mostrarTodos (){
        return this.dao.buscarTodos();
    }


    public Publicacion buscarPorId(int id){
        return this.dao.buscar(id);
    }

    private BusquedaCondicional condicionAliasRescatista(String aliasRescatista){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();

        CriteriaQuery<Publicacion> publicacionQuery = criteriaBuilder.createQuery(Publicacion.class);

        Root<Publicacion> condicionRaiz = publicacionQuery.from(Publicacion.class);

        Predicate condicionAliasRescatista = criteriaBuilder.equal(condicionRaiz.get("usuario"), aliasRescatista);

        publicacionQuery.where(condicionAliasRescatista);

        return new BusquedaCondicional(null, publicacionQuery);
    }


    private BusquedaCondicional condicionDetalladas(){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();

        CriteriaQuery<Publicacion> publicacionQuery = criteriaBuilder.createQuery(Publicacion.class);

        Root<Publicacion> condicionRaiz = publicacionQuery.from(Publicacion.class);

        Predicate condicionDetallada = criteriaBuilder.equal(condicionRaiz.get("Detallada"), "Si");

        publicacionQuery.where(condicionDetallada);

        return new BusquedaCondicional(null, publicacionQuery);
    }

    private BusquedaCondicional condicionNoDetalladas(){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();

        CriteriaQuery<Publicacion> publicacionQuery = criteriaBuilder.createQuery(Publicacion.class);

        Root<Publicacion> condicionRaiz = publicacionQuery.from(Publicacion.class);

        Predicate condicionNoDetallada = criteriaBuilder.equal(condicionRaiz.get("Detallada"), "No");

        publicacionQuery.where(condicionNoDetallada);

        return new BusquedaCondicional(null, publicacionQuery);
    }

}
