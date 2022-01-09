package validador;

import estrategia.EstrategiaDeValidacion;
import validable.Password;
import validable.Validable;

public class ValidadorDePassword {
    private Validable passwordAValidar;
    private EstrategiaDeValidacion estrategiaDeValidacion;

    public ValidadorDePassword(EstrategiaDeValidacion estrategia){
        this.estrategiaDeValidacion = estrategia;
    }

    public void setPasswordAValidar(Validable passwordAValidar) {
        this.passwordAValidar = passwordAValidar;
    }

    public void setEstrategiaDeValidacion(EstrategiaDeValidacion estrategiaDeValidacion) {
        this.estrategiaDeValidacion = estrategiaDeValidacion;
    }

    public Boolean validar(){
        return this.estrategiaDeValidacion.validar(this.passwordAValidar);
    }

    public String getFeedback(Password password){
        return this.estrategiaDeValidacion.getFeedback(this.passwordAValidar);
    }
}