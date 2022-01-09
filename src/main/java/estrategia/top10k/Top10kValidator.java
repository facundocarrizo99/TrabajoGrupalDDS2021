package estrategia.top10k;

import config.Configuracion;
import estrategia.AdapterValidador;
import validable.Validable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;


public class Top10kValidator implements AdapterValidador {
    private String password;
    private Integer passwordLengthMinimo;
    private Configuracion configuracion;
    private Boolean cumpleLength = null;
    private Boolean resultadoValidacion = null;
    private Boolean cumpleValidacion = null;
    private TreeMap<Integer, String> passList;

    public Top10kValidator() {
        this.passList = new TreeMap<Integer, String>();
        this.inicializar();

    }

    public Boolean validar(Validable validable) {
        this.cumpleValidacion = (!this.passList.containsKey(validable.textoPlano().hashCode()));
        this.cumpleLength = (validable.textoPlano().length() >= this.getPasswordLengthMinimo());
        this.resultadoValidacion = cumpleValidacion && cumpleLength;
        return this.resultadoValidacion;
    }

    public String getFeedback(Validable validable) {
        if (resultadoValidacion)
            return "Tu pass esta aceptable ;)";
        else
            return "tu password es un peligro";
    }

    public void inicializar(){
        try {
            File myObj = new File("src/main/resources/seguridad/10k-worst-passwords.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.passList.put(data.hashCode(),data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Integer getPasswordLengthMinimo(){
        this.configuracion = Configuracion.getInstace();
        return configuracion.getPasswordLengthMinimo();
    }
}
