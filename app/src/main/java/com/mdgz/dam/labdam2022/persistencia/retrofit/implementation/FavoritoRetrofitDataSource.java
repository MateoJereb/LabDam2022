package com.mdgz.dam.labdam2022.persistencia.retrofit.implementation;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.FavoritoDataSource;

import java.util.List;
import java.util.UUID;

public class FavoritoRetrofitDataSource implements FavoritoDataSource {
    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Void> callback) {

    }

    @Override
    public void recuperarFavoritos(OnResult<List<Favorito>> callback) {

    }

    @Override
    public void eliminarFavorito(UUID favoritoId, OnResult<Void> callback) {

    }
}
