package com.mdgz.dam.labdam2022.persistencia.retrofit.interfaz;

import com.mdgz.dam.labdam2022.persistencia.retrofit.entity.FavoritoRetrofit;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavoritoInterface {
    @Headers("Authorization: Basic amVyZWI6cGFzcw==")
    @GET("favorito")
    Call<List<FavoritoRetrofit>> recuperarFavoritos();

    @Headers("Authorization: Basic amVyZWI6cGFzcw==")
    @POST("favorito")
    Call<FavoritoRetrofit> guardarFavorito(@Body FavoritoRetrofit fav);

    @Headers("Authorization: Basic amVyZWI6cGFzcw==")
    @DELETE("favorito")
    Call<ResponseBody> eliminarFavorito(@Query("alojamientoId") UUID alojamientoID);
}
