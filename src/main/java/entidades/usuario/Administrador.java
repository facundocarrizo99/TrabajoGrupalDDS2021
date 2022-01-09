package entidades.usuario;

import entidades.formularios.Opcion;
import entidades.formularios.Pregunta;
import entidades.caracteristicas.Caracteristica;
import entidades.Organizacion;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Organizacion> organizaciones;

    public Administrador(String usuario, String contrasena, List<Organizacion> organizaciones) {
        super(usuario, contrasena);
        this.organizaciones = organizaciones;
    }

    public void agregarCaracteristicasDeMascota(Organizacion organizacion, List<Caracteristica> caracteristicas) {
        if(this.organizaciones.contains(organizacion)){
          organizacion.addCaracteristicas(caracteristicas);
        } else {
         throw new RuntimeException("No pertenece asdasdasdcc una de mis organizaciones");
        }
    }

    public void quitarCaracteristicaDeMascota(Organizacion organizacion, Caracteristica caracteristica) {
        if(this.organizaciones.contains(organizacion)){
            organizacion.removeCaracteristica(caracteristica);
        } else {
            throw new RuntimeException("No pertenece asdasdasdcc una de mis organizaciones");
        }
    }

    public Caracteristica crearNuevaCaracteristica(String key, Boolean value){
        String k = Objects.requireNonNull(key, "La clave no puede ser null");
        Caracteristica caracteristica = new Caracteristica(key, value);
        return caracteristica;
    }

    public void altaDeOrganizacion(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void altaDeOrganizacion(Organizacion organizacion) {
        this.organizaciones.add(organizacion);
    }

    public Organizacion tomarUnaOrganizacionDeLaLista (Organizacion organizacion){
        return this.organizaciones.stream().filter(v -> v.equals(organizacion))
                .findFirst().get();
    }

    public void configurarNormalizacion() {}

    public void altaDeOrganizacion() {}

    public void bajaDeOrganizacion() {}

    public void modificacionDeOrganizacion () {}

    public Voluntario generarUsuarioVoluntario(String myUser, String myPass, Organizacion myOrg) {
          String user = myUser;
          String pass = myPass;
          Voluntario voluntario = new Voluntario(myUser, myPass, myOrg);
          return voluntario;
    }
//////REQUERIMIENTO 2
    public Pregunta crearPregunta (String descripcion){
        Pregunta pregunta = new Pregunta(descripcion);
        return pregunta;
    }

    public void crearOpcionAPregunta (String descripcion, Pregunta pregunta){
        Opcion opc = new Opcion(descripcion);
        //pregunta.addOpcion(opc);
    }

    public void asignarPreguntaObligatoria (Pregunta pregunta){
        this.organizaciones.stream().forEach(o->o.getPreguntasAdopcion().add(pregunta));
    }

    public void eliminarPreguntaObligatoria (Pregunta pregunta){
        this.organizaciones.forEach(o->o.getPreguntasAdopcion().remove(pregunta));
    }

    public void eliminarPreguntaAOrganizacion (Pregunta pregunta, Organizacion organizacion){
        this.tomarUnaOrganizacionDeLaLista(organizacion).getPreguntasAdopcion().remove(pregunta);
    }

    public void eliminarOpcionAPregunta (Pregunta pregunta, String opcion){
        pregunta.getOpciones().remove(opcion);
    }

    public void asignarPreguntaAOrganizacion (Organizacion organizacion, Pregunta pregunta){
        this.organizaciones.stream().filter(o -> o.getNombreOrganizacion() == organizacion.getNombreOrganizacion())
                .findFirst().get().getPreguntasAdopcion().add(pregunta);
    }

    //////////////  Getters y setters   ////////////

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public Organizacion getOrganizacion() {return this.organizaciones.get(0);}

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void addOrganizacion(Organizacion organizacion){this.organizaciones.add(organizacion);}

    //////////////  Constructor   /////////////////


    public Administrador() {
        this.organizaciones = new ArrayList<Organizacion>();
    }

    public Administrador(String usuario, String contrasena){
        super(usuario, contrasena);
        this.organizaciones = new ArrayList<Organizacion>();
    }
}