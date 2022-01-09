package APIsNotificacion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsAPI {
    public static final String ACCOUNT_SID = "ACa5c50bdb09cfa89ca0289821b0428802";
    public static final String AUTH_TOKEN = "fe7b89d87eff4c58ebace0258c9c32bd";

    public static Message notificar(String from,String  to,String  mensaje) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(to), // to
                        new PhoneNumber(from), // from
                        mensaje)
                .create();

        return message;
    }
}

