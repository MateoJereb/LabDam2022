package com.mdgz.dam.labdam2022.persistencia.room.implementations;

import android.content.Context;
import android.util.Log;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.AppDataBase;
import com.mdgz.dam.labdam2022.persistencia.room.dao.FavoritoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entity.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.FavoritoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FavoritoRoomDataSource implements FavoritoDataSource {
    private final FavoritoDAO favoritoDAO;

    public FavoritoRoomDataSource(final Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        favoritoDAO = db.favoritoDAO();
    }

    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Void> callback) {
        try{
            final FavoritoEntity fav = FavoritoMapper.toEntity(favorito);
            favoritoDAO.guardarFavorito(fav);
            callback.onSuccess(null);

        }
        catch (Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void recuperarFavoritos(OnResult<List<Favorito>> callback) {
        try{
            List<FavoritoEntity> rtdoEntity = favoritoDAO.recuperarFavoritos();
            List<Favorito> rtdo = new ArrayList<>();

            for(FavoritoEntity f : rtdoEntity){
                Favorito fav = FavoritoMapper.fromEntity(f);
                rtdo.add(fav);
            }

            callback.onSuccess(rtdo);
        }
        catch (Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void eliminarFavorito(UUID favoritoId, OnResult<Void> callback) {
        try{
            favoritoDAO.eliminarFavorito(favoritoId);
            Log.d("Eliminar",favoritoId.toString());
            callback.onSuccess(null);
        }
        catch (Exception e){
            callback.onError(e);
        }
    }
}
