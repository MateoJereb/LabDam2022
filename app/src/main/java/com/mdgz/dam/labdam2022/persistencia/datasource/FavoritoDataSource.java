package com.mdgz.dam.labdam2022.persistencia.datasource;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;

import java.util.List;

public interface FavoritoDataSource {
    void guardarFavorito(Favorito favorito, OnResult<Void> callback);

    void recuperarFavoritos(OnResult<List<Favorito>> callback);

    void eliminarFavorito(Favorito favorito, OnResult<Void> callback);
}
