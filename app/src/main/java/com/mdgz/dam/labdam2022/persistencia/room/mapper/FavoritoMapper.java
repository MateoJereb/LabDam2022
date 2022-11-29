package com.mdgz.dam.labdam2022.persistencia.room.mapper;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.room.entity.FavoritoEntity;

public class FavoritoMapper {
    public FavoritoMapper(){}

    public static FavoritoEntity toEntity(Favorito fav){
        return new FavoritoEntity(
                fav.getId(),
                fav.getAlojamientoID(),
                fav.getUsuarioID()
        );
    }

    public static Favorito fromEntity(FavoritoEntity fav){
        return new Favorito(
                fav.getId(),
                fav.getAlojamientoID(),
                fav.getUsuarioID());
    }
}
