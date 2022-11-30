package com.mdgz.dam.labdam2022.persistencia.room.mapper;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.room.entity.AlojamientoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AlojamientoMapper {
    public AlojamientoMapper() {
    }

    public static AlojamientoEntity toEntity(final Alojamiento aloj){
        return new AlojamientoEntity(
                aloj.getId(),
                aloj.getTitulo(),
                aloj.getDescripcion(),
                aloj.getCapacidad(),
                aloj.getPrecioBase(),
                aloj.getFavorito(),
                aloj.getImagen()
        );
    }

    public static List<AlojamientoEntity> toEntities(final List<Alojamiento> l){
        return l.stream().map(a -> toEntity(a)).collect(Collectors.toList());
    }
}
