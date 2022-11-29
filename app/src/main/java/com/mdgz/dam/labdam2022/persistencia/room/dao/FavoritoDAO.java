package com.mdgz.dam.labdam2022.persistencia.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entity.FavoritoEntity;

import java.util.List;

@Dao
public interface FavoritoDAO {
    @Insert
    void guardarFavorito(FavoritoEntity fav);

    @Query("SELECT * FROM favorito")
    List<FavoritoEntity> recuperarFavoritos();
}
