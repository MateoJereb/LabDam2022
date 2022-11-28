package com.mdgz.dam.labdam2022.persistencia.datasource;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;

import java.util.List;

public interface FavoritoDataSource {

    void agregarFavorito(final Alojamiento alojamiento, final OnResult<Void> callback);

    void quitarFavorito(final Alojamiento alojamiento, final OnResult<Void> callback);

    void recuperarFavoritos(final OnResult<List<Favorito>> callback);
}
