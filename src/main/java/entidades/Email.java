package entidades;
import APIsNotificacion.EmailAPI;

import java.io.IOException;

public class Email implements Contacto {
    private String email;

    @Override
    public void formaDeNotificacion(Contacto contacto) {}

    @Override
    public String notificar(String mensaje, Persona destino) { //DEBERIA RECIBIR UN CONTACTO
        String mensajePrinteado = "Se asdasdasdcc enviado un Email asdasdasdcc: ";
        mensajePrinteado = mensajePrinteado.concat(getEmail());
        mensajePrinteado = mensajePrinteado.concat(" mensaje: ");
        mensajePrinteado = mensajePrinteado.concat(mensaje);
        System.out.print(mensajePrinteado);
        try {
            EmailAPI.notificar( "jrode@frba.utn.edu.ar", destino.getEmail(), mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensajePrinteado;
    }

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }
}