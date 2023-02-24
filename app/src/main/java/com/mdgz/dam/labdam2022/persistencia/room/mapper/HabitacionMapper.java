package com.mdgz.dam.labdam2022.persistencia.room.mapper;

import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.room.entity.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.HabitacionEntity;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class HabitacionMapper {
    public HabitacionMapper() {
    }

    public static HabitacionEntity toEntity(Habitacion hab){
        return new HabitacionEntity(
                hab.getId(),
                hab.getCamasIndividuales(),
                hab.getCamasMatrimoniales(),
                hab.getTieneEstacionamiento(),
                hab.getHotel().getId(),
                hab.getId()
        );
    }

    public static Habitacion fromEntity(HabitacionEntity hab, AlojamientoEntity aloj){
        return new Habitacion(
                aloj.getId(),
                aloj.getTitulo(),
                aloj.getDescripcion(),
                aloj.getCapacidad(),
                aloj.getPrecioBase(),
                aloj.getFavorito(),
                hab.getCamasIndividuales(),
                hab.getCamasMatrimoniales(),
                hab.getTieneEstacionamiento(),
                AlojamientoRepository._HOTELES.stream().filter(h -> h.getId() == hab.getHotelID()).findFirst().get(),
                aloj.getImagen()
        );
    }

    public static List<HabitacionEntity> toEntities(List<Habitacion> l){
        return l.stream().map(h -> toEntity(h)).collect(Collectors.toList());
    }
}
