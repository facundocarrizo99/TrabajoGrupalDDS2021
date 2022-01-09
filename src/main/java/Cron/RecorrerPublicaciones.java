package Cron;
import Repositorios.RepoCasoAdopcion;
import Repositorios.RepoPublicacionesBuscaAdopcion;

import entidades.CasoAdopcion;
import entidades.Persona;
import entidades.PublicacionBuscaAdopcion;
import entidades.caracteristicas.Caracteristica;
import org.quartz.*;


import java.util.ArrayList;
import java.util.List;

public class RecorrerPublicaciones implements Job {

    List<CasoAdopcion> casosAdopcionANotificar = new ArrayList<CasoAdopcion>();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Vamo asdasdasdcc ejecutar :)");
        System.out.println("El job empezo en: " + jobExecutionContext.getFireTime());
        List<PublicacionBuscaAdopcion> publicaciones = RepoPublicacionesBuscaAdopcion.mostrarTodosStatic();

        List<CasoAdopcion> casosAdopcion = RepoCasoAdopcion.mostrarTodosStatic();

        publicaciones.forEach(publicacionBuscaAdopcion -> compararCaracteristicas(publicacionBuscaAdopcion, casosAdopcion));
        System.out.println("El próximo job se ejecutará: " + jobExecutionContext.getNextFireTime());
    }

    public void compararCaracteristicas(PublicacionBuscaAdopcion publicacionBuscaAdopcion, List<CasoAdopcion> casosAdopcion){
        this.casosAdopcionANotificar = new ArrayList<CasoAdopcion>();

        casosAdopcion.forEach(casoAdopcion -> analizarYaniadirALista(publicacionBuscaAdopcion,casoAdopcion));

        //notificarCasos(publicacionBuscaAdopcion.getPersona(), this.casosAdopcionANotificar);
    }

    public void analizarYaniadirALista(PublicacionBuscaAdopcion publicacionBuscaAdopcion, CasoAdopcion casoAdopcion) {

        System.out.println("3.1");

        if (casoAdopcion.getStatus() && casoAdopcion != null) { //si la mascota esta disponible
            System.out.println("3.2");

            List<Caracteristica> caracteristicasMascota = casoAdopcion.getFormulario().getMascotaPerdida().getCaracteristicas();
            System.out.println("3.3");

            if (!caracteristicasMascota.isEmpty() && caracteristicasMascota != null && caracteristicasMascota.toString().contentEquals(publicacionBuscaAdopcion.getPreferencias().toString())) {
                //this.casosAdopcionANotificar.add(casoAdopcion);
                notificarCaso(publicacionBuscaAdopcion.getPersona(), casoAdopcion);
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void notificarCasos(Persona persona, List<CasoAdopcion> casosAdopcionANotificar){
        String notificacion = persona.notificar  ( mensajeArmado(casosAdopcionANotificar) );
        System.out.println(notificacion);
    }

    public void notificarCaso(Persona persona,CasoAdopcion casoAdopcionANotificar){
        String notificacion = persona.notificar("Hola! Como estas? Te enviamos las recomendaciones de adopcion que tenemos para vos " +
                                                  "\n\n   publicacion:    \n  "   +     casoAdopcionANotificar.getFormulario().toString() );        //aca iria la publicacion cuando tengamos persistencia
        System.out.println(notificacion);
        System.out.println("9");
    }

    public String mensajeArmado(List<CasoAdopcion> casos){

        casos.forEach(casoAdopcion -> casoAdopcion.toString());
        StringBuilder sb = new StringBuilder();
        for(CasoAdopcion caso: casos){
            sb.append(caso.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}