package APIsNotificacion;

import com.sendgrid.*;
import config.Configuracion;
import seguridad.Cifrador;

import java.io.IOException;

public class EmailAPI {

    public static void notificar(String from, String to, String mensaje) throws IOException {
        Configuracion configuracion = Configuracion.getInstace();
        String apiKey = configuracion.getSendgridApiKey();
        Email fromAddress = new Email(from);
        String subject = "Rescate de Patitas te notifica que:";
        Email toAddress = new Email(to);
        Content content = new Content("text/plain", mensaje);
        Mail mail = new Mail(fromAddress, subject, toAddress, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}