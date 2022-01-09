package entidades;
import Utils.distanceCalculator;
import entidades.DAOs.RepoHogares;
import entidades.mascota.DescripcionFisica;
import entidades.mascota.Mascota;
import hogaresTransito.Hogar;
import services.ServicioRefugioDDS;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterHogaresA implements IHogarDeTransito{
    public ServicioRefugioDDS servicio;

    @Override
    public Hogar buscarHogar(Mascota mascota, double radio) throws IOException {
        RepoHogares repohogares = new RepoHogares();
        List<Hogar> hogares = repohogares.getHogares();
        List<Hogar> listaFiltrada = hogares.stream().filter(h -> mascotaAdmitida(h, mascota, radio)).collect(Collectors.toList());
        if (!listaFiltrada.isEmpty()) {
            return this.tomarLaMenorDistancia(listaFiltrada, mascota);
        }
        return null;
    }

    public Boolean mascotaAdmitida (Hogar hogar, Mascota mascota, Double radio){
       String tipoDeMascota = mascota.getTipoDeMascota().toString().toLowerCase();
       String valor = tipoDeMascota.concat("s");
       Boolean value = hogar.admisiones.get(valor).booleanValue();
        return value.equals(Boolean.TRUE) &&
                hogar.lugares_disponibles > 0 &&
                mascotaAdmitidaPorCaracteristica (hogar, mascota) &&
                hogaresDentroDelRadio(hogar, mascota, radio) &&
                mascotaAdmitidaPorTamanio(hogar, mascota);
    }

    public Double getDistancia (Hogar hogar, Mascota mascota){
        distanceCalculator calculadorDistancia =  distanceCalculator.getDistanceCalculator();
        Double distance = calculadorDistancia.getDistancia(hogar.ubicacion, mascota.getUbicacion());
        return distance;
    }

    public Boolean mascotaAdmitidaPorTamanio(Hogar hogar, Mascota mascota) {
        return (hogar.patio.equals(Boolean.TRUE) && mascota.getDescripcionFisica().equals(DescripcionFisica.GRANDE))
                || (hogar.patio.equals(Boolean.TRUE) && mascota.getDescripcionFisica().equals(DescripcionFisica.MEDIANA)) ||
                (hogar.patio.equals(Boolean.FALSE) && mascota.getDescripcionFisica().equals(DescripcionFisica.CHICA));
    }

    public Boolean mascotaAdmitidaPorCaracteristica (Hogar hogar, Mascota mascota) {
        if (! hogar.caracteristicas.isEmpty() && mascota.getCaracteristicas().size() != 0) {
            return mascota.tomarCaracteristicasHabilitadas().containsAll(hogar.getCaracteristicas());
        }
        return true;
    }

    public Boolean hogaresDentroDelRadio (Hogar hogar, Mascota mascota, Double radio){
        return radio > getDistancia(hogar, mascota);
    }

    public Hogar tomarLaMenorDistancia (List<Hogar> hogares, Mascota mascota){
        Hogar menor = hogares.get(0);
        for(int posicion=1;posicion < hogares.size();posicion++){
            if ((getDistancia(hogares.get(posicion), mascota)) < getDistancia(menor, mascota)) {
                menor = hogares.get(posicion);
            }
        }
        return menor;
    }
}
