package com.mdgz.dam.labdam2022.persistencia.retrofit.mapper;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entity.FavoritoRetrofit;

import java.util.List;
import java.util.stream.Collectors;

public class FavoritoRetrofitMapper {
    public FavoritoRetrofitMapper(){

    }

    public static FavoritoRetrofit toEntity(Favorito fav){
        return new FavoritoRetrofit(
                fav.getId(),
                fav.getAlojamientoID(),
                fav.getUsuarioID()
        );
    }

    public static Favorito fromEntity(FavoritoRetrofit fav){
        return new Favorito(
                fav.getId(),
                fav.getAlojamientoID(),
                fav.getUsuarioID()
        );
    }

    public static List<FavoritoRetrofit> toEntities(List<Favorito> lista){
        return lista.stream().map(f -> toEntity(f)).collect(Collectors.toList());
    }

    public static List<Favorito> fromEntities(List<FavoritoRetrofit> lista){
        return lista.stream().map(f -> fromEntity(f)).collect(Collectors.toList());
    }
}
