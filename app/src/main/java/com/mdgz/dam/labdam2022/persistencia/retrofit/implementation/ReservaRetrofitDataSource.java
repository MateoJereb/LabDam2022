package com.mdgz.dam.labdam2022.persistencia.retrofit.implementation;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entity.ReservaRetrofit;
import com.mdgz.dam.labdam2022.persistencia.retrofit.interfaz.ReservaInterface;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mapper.ReservaRetrofitMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservaRetrofitDataSource implements ReservaDataSource {
    private ReservaInterface reservaInterface;

    public ReservaRetrofitDataSource() {
        AppRetrofit rf = AppRetrofit.getInstance();
        reservaInterface = rf.getReservaInterface();
    }

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Void> callback) {
        ReservaRetrofit reservaEntity = ReservaRetrofitMapper.toEntity(reserva);

        Call<ReservaRetrofit> reqAsyn = reservaInterface.guardarReserva(reservaEntity);
        reqAsyn.enqueue(new Callback<ReservaRetrofit>() {
            @Override
            public void onResponse(Call<ReservaRetrofit> call, Response<ReservaRetrofit> response) {
                if(response.isSuccessful()) callback.onSuccess(null);
                else callback.onError(new Exception("Response not successful"));
            }

            @Override
            public void onFailure(Call<ReservaRetrofit> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {
        Call<List<ReservaRetrofit>> reqAsyn = reservaInterface.recuperarReservas();
        reqAsyn.enqueue(new Callback<List<ReservaRetrofit>>() {
            @Override
            public void onResponse(Call<List<ReservaRetrofit>> call, Response<List<ReservaRetrofit>> response) {
                if(response.isSuccessful()){
                    List<ReservaRetrofit> respuestaEntity = response.body();
                    List<Reserva> respuesta = ReservaRetrofitMapper.fromEntities(respuestaEntity);
                    callback.onSuccess(respuesta);
                }
                else callback.onError(new Exception("Response not successful"));
            }

            @Override
            public void onFailure(Call<List<ReservaRetrofit>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
