package entidades;

import entidades.formularios.Formulario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Caso {

    private String casoID;
    private Persona rescatista;
    private List<Persona> responsables = new ArrayList<Persona>();
    //private TipoEvento evento;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Boolean publicacionAprobada;
    private LocalDateTime fechaCompletada;
    private Formulario formulario;
    private HogarDeTransito hogarDeTransito;
    private Publicacion publicacion;

    //////////////  Getters y setters   ////////////

    public String getCasoID() {
        return casoID;
    }

    public void setCasoID(String casoID) {
        this.casoID = casoID;
    }

    public Persona getRescatista() {
        return rescatista;
    }

    public void setRescatista(Persona rescatista) {
        this.rescatista = rescatista;
    }

    public List<Persona> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<Persona> responsables) {
        this.responsables = responsables;
    }

//    public TipoEvento getEvento() {
//        return evento;
//    }
//
//    public void setEvento(TipoEvento evento) {
//        this.evento = evento;
//    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Boolean getPublicacionAprobada() {
        return publicacionAprobada;
    }

    public void setPublicacionAprobada(Boolean publicacionAprobada) {
        this.publicacionAprobada = publicacionAprobada;
    }

    public LocalDateTime getFechaCompletada() {
        return fechaCompletada;
    }

    public void setFechaCompletada(LocalDateTime fechaCompletada) {
        this.fechaCompletada = fechaCompletada;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public HogarDeTransito getHogarDeTransito() {
        return hogarDeTransito;
    }

    public void setHogarDeTransito(HogarDeTransito hogarDeTransito) {
        this.hogarDeTransito = hogarDeTransito;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

}