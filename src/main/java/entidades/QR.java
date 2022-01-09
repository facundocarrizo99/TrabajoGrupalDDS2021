package entidades;

import entidades.formularios.Formulario;

public class QR {

    private String mascotaID;
    private Persona duenio;
    private String url;
    private Formulario formulario;

    //////////////  Getters y setters   ////////////

    public String getMascotaID() {
        return mascotaID;
    }

    public void setMascotaID(String mascotaID) {
        this.mascotaID = mascotaID;
    }

    public Persona getDuenio() {
        return duenio;
    }

    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }
}