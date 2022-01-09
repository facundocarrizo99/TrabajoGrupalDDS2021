package services;

import hogaresTransito.ListadoHogaresDeTransito;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import seguridad.Cifrador;

import java.io.IOException;

public class ServicioRefugioDDS {
    private static ServicioRefugioDDS instance = null;
    private Retrofit retrofit;
    private Cifrador cifrador;

    public static ServicioRefugioDDS getInstance() {
        if(instance ==null){
            try {
                instance = new ServicioRefugioDDS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private ServicioRefugioDDS() throws Exception {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.refugiosdds.com.ar/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.cifrador = new Cifrador();
    }

    public ListadoHogaresDeTransito listadoHogaresDeTransito(Integer nroPagina,String token) throws IOException {
        String tokenDecrypt = this.cifrador.decrypt(token);
        RefugioDDSService refugioDDSService = this.retrofit.create(RefugioDDSService.class);
        Call<ListadoHogaresDeTransito> requestListadoHogares = refugioDDSService.hogares(nroPagina, "Bearer " + tokenDecrypt);
        Response<ListadoHogaresDeTransito> responseListadoHogares = requestListadoHogares.execute();
        ListadoHogaresDeTransito listadoHogares = responseListadoHogares.body();
        return listadoHogares;
    }
}