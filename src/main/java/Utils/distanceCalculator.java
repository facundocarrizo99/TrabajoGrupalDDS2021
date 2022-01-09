package Utils;

import entidades.Ubicacion;

public class distanceCalculator {
    private String url;
    private String baseDatos;
    private static distanceCalculator oneDistanceCalculator = null;

    public  static distanceCalculator getDistanceCalculator() {
        if (oneDistanceCalculator==null) {
            oneDistanceCalculator=new distanceCalculator();
        }
        return oneDistanceCalculator;
    }

    private distanceCalculator(){}

    public Double getDistancia (Ubicacion ubi1, Ubicacion ubi2){
        Double Long1 = ubi1.getLatitud();
        Double Lat1 = ubi1.getLongitud();
        Double Long2 = ubi2.getLatitud();
        Double Lat2 = ubi2.getLongitud();
        final int R = 6371; // Radio de la tierra
        double latDistance = Math.toRadians(Long1 - Long2);
        double lonDistance = Math.toRadians(Lat1 - Lat2);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(Long2)) * Math.cos(Math.toRadians(Long1))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }
}
