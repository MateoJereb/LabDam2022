package com.mdgz.dam.labdam2022.persistencia.retrofit.interfaz;

import com.mdgz.dam.labdam2022.persistencia.retrofit.entity.ReservaRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ReservaInterface {
    @Headers("Authorization: Basic amVyZWI6cGFzcw==")
    @POST("reserva")
    Call<Response> guardarReserva(@Body ReservaRetrofit res);

    @Headers("Authorization: Basic amVyZWI6cGFzcw==")
    @GET("reserva")
    Call<List<ReservaRetrofit>> recuperarReservas();
}
