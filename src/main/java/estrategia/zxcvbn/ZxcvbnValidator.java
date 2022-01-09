package estrategia.zxcvbn;

import com.nulabinc.zxcvbn.Feedback;
import config.Configuracion;
import estrategia.AdapterValidador;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import validable.Validable;


public class ZxcvbnValidator implements AdapterValidador {
    private Configuracion configuracion;
    private Integer passwordScoreMinimo;
    private Integer passwordLengthMinimo;
    public ZxcvbnValidator() {
        this.configuracion = Configuracion.getInstace();
        this.passwordLengthMinimo = configuracion.getPasswordLengthMinimo();
        this.passwordScoreMinimo = configuracion.getPasswordScoreMinimo();
    }

    public Boolean validar(Validable validable) {

        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(validable.textoPlano());
        return ((strength.getScore() > passwordScoreMinimo) && (validable.textoPlano().length() >= passwordLengthMinimo));
    }

    public String getFeedback(Validable validable) {
        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(validable.textoPlano());
        Feedback feedback = strength.getFeedback();
        if ((feedback.getWarning() == null)) {
            return "password ok";
        } else {
            return (feedback.getWarning() + feedback.getSuggestions());
        }
    }

}