package estrategia;

import validable.Validable;

public interface AdapterValidador {
    public Boolean validar(Validable validable);
    public String getFeedback(Validable validable);
}
