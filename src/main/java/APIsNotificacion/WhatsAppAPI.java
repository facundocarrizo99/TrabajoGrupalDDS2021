package APIsNotificacion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class WhatsAppAPI {
    public static final String ACCOUNT_SID = "AC7ed67bfa96825513df7c77aff6867054";
    public static final String AUTH_TOKEN = "75589a05d7345cc9293d9418c2213b39";

    public static Message notificar(String from,String  to,String  mensaje) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(("whatsapp:").concat(to)),
                new com.twilio.type.PhoneNumber(("whatsapp:").concat(from)),
                mensaje)
                .create();
       return message;
    }
}