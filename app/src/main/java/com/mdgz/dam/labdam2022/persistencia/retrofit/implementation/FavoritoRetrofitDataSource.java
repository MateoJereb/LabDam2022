package com.mdgz.dam.labdam2022.persistencia.retrofit.implementation;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.AppRetrofit;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entity.FavoritoRetrofit;
import com.mdgz.dam.labdam2022.persistencia.retrofit.interfaz.FavoritoInterface;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mapper.FavoritoRetrofitMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.FavoritoMapper;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritoRetrofitDataSource implements FavoritoDataSource {
    private final FavoritoInterface favoritoInterface;

    public FavoritoRetrofitDataSource() {
        AppRetrofit rf = AppRetrofit.getInstance();
        favoritoInterface = rf.getFavoritoInterface();
    }

    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Void> callback) {
        FavoritoRetrofit favEntity = FavoritoRetrofitMapper.toEntity(favorito);

        Call<FavoritoRetrofit> reqAsyn = favoritoInterface.guardarFavorito(favEntity);

        reqAsyn.enqueue(new Callback<FavoritoRetrofit>() {
            @Override
            public void onResponse(Call<FavoritoRetrofit> call, Response<FavoritoRetrofit> response) {
                if(response.isSuccessful()) callback.onSuccess(null);
                else callback.onError(new Exception("Response not successful. Code: "+response.code()));
            }

            @Override
            public void onFailure(Call<FavoritoRetrofit> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void recuperarFavoritos(OnResult<List<Favorito>> callback) {
        Call<List<FavoritoRetrofit>> reqAsyn = favoritoInterface.recuperarFavoritos();
        reqAsyn.enqueue(new Callback<List<FavoritoRetrofit>>() {
            @Override
            public void onResponse(Call<List<FavoritoRetrofit>> call, Response<List<FavoritoRetrofit>> response) {
                if(response.isSuccessful()){
                    List<FavoritoRetrofit> respuestaEntity = response.body();
                    List<Favorito> respuesta = FavoritoRetrofitMapper.fromEntities(respuestaEntity);
                    callback.onSuccess(respuesta);
                }
                else callback.onError(new Exception("Response not successful. Code: "+response.code()));
            }

            @Override
            public void onFailure(Call<List<FavoritoRetrofit>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void eliminarFavorito(UUID alojamientoID, OnResult<Void> callback) {
        Call<ResponseBody> reqAsyn = favoritoInterface.eliminarFavorito(alojamientoID);
        reqAsyn.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())  callback.onSuccess(null);
                else  callback.onError(new Exception("Response not successful. Code: "+response.code()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
