package com.mdgz.dam.labdam2022.persistencia.room.mapper;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.room.entity.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.DepartamentoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DepartamentoMapper {
    public DepartamentoMapper() {
    }

    public static DepartamentoEntity toEntity(Departamento dpto){
        return new DepartamentoEntity(
                dpto.getId(),
                dpto.getTieneWifi(),
                dpto.getCostoLimpieza(),
                dpto.getCantidadHabitaciones(),
                dpto.getUbicacion().getId(),
                dpto.getId()
        );
    }

    public static Departamento fromEntity(DepartamentoEntity dpto, AlojamientoEntity aloj){
        return new Departamento(
                aloj.getId(),
                aloj.getTitulo(),
                aloj.getDescripcion(),
                aloj.getCapacidad(),
                aloj.getPrecioBase(),
                aloj.getFavorito(),
                dpto.getTieneWifi(),
                dpto.getCostoLimpieza(),
                dpto.getCantidadHabitaciones(),
                new Ubicacion(), //TODO getUbicacion
                aloj.getImagen()
        );
    }

    public static List<DepartamentoEntity> toEntities(List<Departamento> l){
        return l.stream().map(d -> toEntity(d)).collect(Collectors.toList());
    }
}
