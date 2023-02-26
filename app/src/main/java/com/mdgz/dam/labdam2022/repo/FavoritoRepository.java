package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.implementation.FavoritoRetrofitDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.implementation.FavoritoRoomDataSource;

import java.util.List;
import java.util.UUID;

public class FavoritoRepository {

    private final FavoritoDataSource dataSource;

    private Integer ROOM = 1;
    private Integer RETROFIT = 2;

    public FavoritoRepository(Context context) {
        Integer implementar = RETROFIT;

        if(implementar == ROOM) dataSource = new FavoritoRoomDataSource(context);
        else dataSource = new FavoritoRetrofitDataSource();
    }

    public void guardarFavorito(Favorito favorito, OnResult<Void> callback){
        dataSource.guardarFavorito(favorito, callback);
    }

    public void recuperarFavorito(OnResult<List<Favorito>> callback){
        dataSource.recuperarFavoritos(callback);
    }

    public void eliminarFavorito(UUID alojamientoId, OnResult<Void> callback){
        dataSource.eliminarFavorito(alojamientoId,callback);
    }
}
