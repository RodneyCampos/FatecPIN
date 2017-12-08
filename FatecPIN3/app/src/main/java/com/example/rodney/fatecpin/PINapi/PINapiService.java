package com.example.rodney.fatecpin.PINapi;

import com.example.rodney.fatecpin.Modelos.PINresposta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rodney on 22/11/2017.
 */

public interface PINapiService {

    @GET("pins")
    Call<PINresposta> obterListaPIN(@Query("limit") int limit, @Query("offset")int offset);
}
