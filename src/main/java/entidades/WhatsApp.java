package entidades;
import APIsNotificacion.WhatsAppAPI;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Objects;

public class WhatsApp implements Contacto {
    private String numero;

    @Override
    public void formaDeNotificacion(Contacto contacto) {}

    public String notificar(String mensaje, Persona destino) {
        Message mensajeEnviado = WhatsAppAPI.notificar("+14155238886","+549".concat(String.valueOf(destino.getTelefono())),mensaje);
        String mensajePrinteado = "Se asdasdasdcc enviado un ";
        mensajePrinteado = mensajePrinteado.concat(mensajeEnviado.getTo());
        mensajePrinteado = mensajePrinteado.concat(" mensaje: ");
        mensajePrinteado = mensajePrinteado.concat(mensajeEnviado.getBody());
        System.out.print(mensajePrinteado);
        // to "+5491137838839"
        return mensajePrinteado;
    }

    public WhatsApp(String numero) {
        this.numero = Objects.requireNonNull(numero, "El numero no ppuede ser nulo");
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "WhatsApp{" +
                "numero='" + numero + '\'' +
                '}';
    }
}