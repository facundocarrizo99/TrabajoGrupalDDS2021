package config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import seguridad.Cifrador;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Configuracion {

    private static Configuracion instance;
    private Cifrador cifrador;
    private Integer passwordScoreMinimo;
    private Integer passwordLengthMinimo;
    private String refugioDDSToken;
    private String sendgridApiKey;
    public static boolean useDataBase = true;

    private Configuracion() {
        this.levantarConfiguracion();
        try {
            this.cifrador = new Cifrador();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Configuracion getInstace(){
        if (instance == null){
            instance = new Configuracion();
        }
        return instance;
    }

    public Integer getPasswordScoreMinimo() {
        return this.passwordScoreMinimo;
    }

    public String getRefugioDDSToken() {
        return refugioDDSToken;
    }

    public void setRefugioDDSToken(String refugioDDSToken) {
        this.refugioDDSToken = refugioDDSToken;
    }

    public Integer getPasswordLengthMinimo() {
        return this.passwordLengthMinimo;
    }

    public void setPasswordScoreMinimo(String passwordScoreMinimo) {
        Integer result = Integer.parseInt(passwordScoreMinimo);
        this.passwordScoreMinimo = result;
    }

    public void setPasswordLengthMinimo(String passwordLengthMinimo) {
        Integer result = Integer.parseInt(passwordLengthMinimo);
        this.passwordLengthMinimo = result;
    }

    public String getSendgridApiKey() {
        return this.cifrador.decrypt(this.sendgridApiKey);
    }

    public void setSendgridApiKey(String sendgridApiKey) {
        this.sendgridApiKey = sendgridApiKey;
    }

    public void levantarConfiguracion() {
        try {
            File archivo = new File("src/main/java/config/config.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document archivoDeConfiguracion = documentBuilder.parse(archivo);
            archivoDeConfiguracion.getDocumentElement().normalize();

            Node nodo = archivoDeConfiguracion.getElementsByTagName("General").item(0);

            if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                this.setPasswordScoreMinimo(element.getElementsByTagName("passwordScoreMinimo").item(0).getTextContent());
                this.setPasswordLengthMinimo(element.getElementsByTagName("passwordLengthMinimo").item(0).getTextContent());
                this.setRefugioDDSToken(element.getElementsByTagName("refugioddsapitoken").item(0).getTextContent());
                this.setSendgridApiKey(element.getElementsByTagName("sendgridapikey").item(0).getTextContent());
            } else{
                System.out.println("No es un ELEMENT_NODE");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}