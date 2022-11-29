package com.mdgz.dam.labdam2022.persistencia.room.mapper;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.room.entity.ReservaEntity;

public class ReservaMapper {
    public ReservaMapper() {
    }

    public static ReservaEntity toEntity(Reserva res){
        return new ReservaEntity(
                res.getId(),
                res.getFechaIngreso(),
                res.getFechaEgreso(),
                res.getCancelada(),
                res.getMonto(),
                res.getAlojamientoID(),
                res.getUsuarioID()
        );
    }

    public static Reserva fromEntity(ReservaEntity res){
        return new Reserva(
                res.getId(),
                res.getFechaIngreso(),
                res.getFechaEgreso(),
                res.getCancelada(),
                res.getMonto(),
                res.getAlojamientoID(),
                res.getUsuarioID()
        );
    }
}
