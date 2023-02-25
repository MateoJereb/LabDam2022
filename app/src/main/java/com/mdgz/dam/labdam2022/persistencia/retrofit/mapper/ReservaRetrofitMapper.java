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
                res.getId(),
                res.getFechaIngreso(),
                res.getFechaEgreso(),
                res.getCancelada(),
                res.getCantidad(),
                res.getMonto(),
                res.getAlojamientoID(),
                res.getUsuarioID()
        );
    }

    public static Reserva fromEntity(ReservaRetrofit res){
        return new Reserva(
                res.getId(),
                res.getFechaIngreso(),
                res.getFechaEgreso(),
                res.getCancelada(),
                res.getCantidad(),
                res.getMonto(),
                res.getAlojamientoID(),
                res.getUsuarioID()
        );
    }

    public List<ReservaRetrofit> toEntities(List<Reserva> l){
        return l.stream().map(r -> toEntity(r)).collect(Collectors.toList());
    }

    public List<Reserva> fromEntities(List<ReservaRetrofit> l){
        return l.stream().map(r -> fromEntity(r)).collect(Collectors.toList());
    }
}
