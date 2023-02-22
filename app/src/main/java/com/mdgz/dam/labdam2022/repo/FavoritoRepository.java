package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.implementations.FavoritoRoomDataSource;

import java.util.List;

public class FavoritoRepository {

    private final FavoritoDataSource dataSource;

    public FavoritoRepository(Context context) {
        dataSource = new FavoritoRoomDataSource(context);
    }

    public void guardarFavorito(Favorito favorito, OnResult<Void> callback){
        dataSource.guardarFavorito(favorito, callback);
    }

    public void recuperarFavorito(OnResult<List<Favorito>> callback){
        dataSource.recuperarFavoritos(callback);
    }

    public void eliminarFavorito(Favorito favorito, OnResult<Void> callback){
        dataSource.eliminarFavorito(favorito,callback);
    }
}
