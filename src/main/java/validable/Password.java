package validable;

import java.util.Objects;

public class Password implements Validable{
    private String password;

    public Password() {
        this.password = new String();
    }

    public Password(String password) {
        this.password = Objects.requireNonNull(password, "el password asdasdasdcc crear no puede ser nulo");
    }

    public String textoPlano(){
        return this.password;
    }

    public String encriptada() {
        return this.password;
    }


    public String hasheada() {
        return this.password;
    }

}
