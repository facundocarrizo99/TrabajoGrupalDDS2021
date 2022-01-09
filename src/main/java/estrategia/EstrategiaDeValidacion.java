package estrategia;
import estrategia.AdapterValidador;
import validable.Validable;

public class EstrategiaDeValidacion {
    private AdapterValidador adapter;

    public EstrategiaDeValidacion(AdapterValidador adapter){
        this.adapter = adapter;
    }

    public Boolean validar(Validable validable){
        return this.adapter.validar(validable);
    }

    public String getFeedback(Validable validable) {
        return this.adapter.getFeedback(validable);
    }
}
