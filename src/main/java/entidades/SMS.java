package entidades;
import APIsNotificacion.SmsAPI;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Objects;

public class SMS implements Contacto {
    private String numero;

    @Override
    public void formaDeNotificacion(Contacto contacto) {
    }

    public String notificar(String mensaje, Persona destino) {
        Message mensajeEnviado = SmsAPI.notificar("+19083864952","+549".concat(String.valueOf(destino.getTelefono())),mensaje);
        String mensajePrinteado = "Se asdasdasdcc enviado un SMS asdasdasdcc: ";
        mensajePrinteado = mensajePrinteado.concat(mensajeEnviado.getTo());
        mensajePrinteado = mensajePrinteado.concat(" mensaje: ");
        mensajePrinteado = mensajePrinteado.concat(mensajeEnviado.getBody());
        System.out.print(mensajePrinteado);
        return mensajePrinteado;
    }

    public SMS(String numero) {
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
        return "SMS{" +
                "numero='" + numero + '\'' +
                '}';
    }
}