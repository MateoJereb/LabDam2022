package com.mdgz.dam.labdam2022.persistencia.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entity.FavoritoEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface FavoritoDAO {
    @Insert
    void guardarFavorito(FavoritoEntity fav);

    @Query("SELECT * FROM favorito")
    List<FavoritoEntity> recuperarFavoritos();

    @Query("DELETE FROM favorito WHERE id = :favId")
    void eliminarFavorito(UUID favId);
}
