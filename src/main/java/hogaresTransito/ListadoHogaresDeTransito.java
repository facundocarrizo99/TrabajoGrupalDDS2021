package hogaresTransito;

import java.util.*;

public class ListadoHogaresDeTransito {
    public Integer total;
    public String offset;
    public List<Hogar> hogares;

    public ListadoHogaresDeTransito(Integer total, String offset, List<Hogar> hogares) {
        this.total = total;
        this.offset = offset;
        this.hogares = hogares;
    }

    public Optional<Hogar> getHogarByName(String name){
        return this.hogares.stream()
                .filter(h -> h.nombre.equals(name))
                .findFirst();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getOffset() {
        return offset;
    }

    public Integer getPaginasTotales() {
        return (int)(Math.ceil(this.total / 10.0));
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public List<Hogar> getHogares() {
        return hogares;
    }

    public void setHogares(List<Hogar> hogares) {
        this.hogares = hogares;
    }

    public void addHogar(Hogar... hogar){
        Collections.addAll(this.hogares, hogar);
    }
}
