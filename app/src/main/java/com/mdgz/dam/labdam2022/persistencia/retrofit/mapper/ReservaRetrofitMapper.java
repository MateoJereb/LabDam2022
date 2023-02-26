package com.mdgz.dam.labdam2022.persistencia.retrofit.mapper;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entity.ReservaRetrofit;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaRetrofitMapper {
    public ReservaRetrofitMapper() {
    }

    public static ReservaRetrofit toEntity(Reserva res){
        return new ReservaRetrofit(
                res.getFechaIngreso(),
                res.getFechaEgreso(),
                res.getAlojamientoID(),
                res.getUsuarioID()
        );
    }

    public static Reserva fromEntity(ReservaRetrofit res){
        return new Reserva(
                null,
                res.getFechaIngreso(),
                res.getFechaSalida(),
                false,
                1,
                0.0,
                res.getAlojamientoID(),
                res.getUsuarioID()
        );
    }

    public static List<ReservaRetrofit> toEntities(List<Reserva> l){
        return l.stream().map(r -> toEntity(r)).collect(Collectors.toList());
    }

    public static List<Reserva> fromEntities(List<ReservaRetrofit> l){
        return l.stream().map(r -> fromEntity(r)).collect(Collectors.toList());
    }
}
