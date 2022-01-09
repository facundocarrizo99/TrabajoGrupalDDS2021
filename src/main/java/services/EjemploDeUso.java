package services;

import config.Configuracion;
import hogaresTransito.Hogar;
import hogaresTransito.ListadoHogaresDeTransito;
import seguridad.Cifrador;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class EjemploDeUso<posibleHogarDeTransito> {
    public static void main(String[] args) throws IOException {
        Configuracion config = Configuracion.getInstace();
        String token = config.getRefugioDDSToken();
        Integer nroPag = 1;
        ServicioRefugioDDS servicioRefugioDDS = ServicioRefugioDDS.getInstance();
        ListadoHogaresDeTransito listadoHogaresDeTransito;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Listado de hogares");

        do {
            listadoHogaresDeTransito = servicioRefugioDDS.listadoHogaresDeTransito(nroPag, token);
            System.out.println("Numero de pagina actual: " + listadoHogaresDeTransito.offset);
            System.out.println("Paginas totales: " + listadoHogaresDeTransito.getPaginasTotales().toString());
            for (Hogar unHogar : listadoHogaresDeTransito.getHogares()) {
                System.out.println(unHogar.toString());
            }
            System.out.println("Ingrese el numero de pagina que desea consultar, ingrese 0 para salir");
            nroPag = entrada.nextInt();
        }

        while (nroPag != 0);
        entrada.nextLine();
        System.out.println("Ingrese el id de hogar asdasdasdcc consultar");
        String nombreHogar = entrada.nextLine();
        Optional<Hogar> posibleHogarDeTransito = listadoHogaresDeTransito.getHogarByName(nombreHogar);

        if (posibleHogarDeTransito.isPresent()) {

            Hogar hogarSeleccionado = posibleHogarDeTransito.get();
            System.out.println(hogarSeleccionado.toString());

        } else {

            System.out.println("No existe el hogar seleccionado");

        }
    }
}