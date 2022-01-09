package entidades;

public interface Contacto {
    public void formaDeNotificacion(Contacto contacto);
    public String notificar(String mensaje, Persona destino);
}