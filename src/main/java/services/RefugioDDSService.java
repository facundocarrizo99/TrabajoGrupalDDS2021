package services;

import hogaresTransito.ListadoHogaresDeTransito;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RefugioDDSService {
    @GET("hogares")
    Call<ListadoHogaresDeTransito> hogares(@Query("offset") int offset
            ,@Header("Authorization") String authorization);
}