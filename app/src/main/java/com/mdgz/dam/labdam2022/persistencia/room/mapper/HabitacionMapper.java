package com.mdgz.dam.labdam2022.persistencia.room.mapper;

import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.room.entity.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.HabitacionEntity;

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

    public static Habitacion fromEntity(HabitacionEntity hab){
        //TODO getAlojamiento
        AlojamientoEntity aloj = new AlojamientoEntity();
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
                new Hotel(), //TODO getHotel
                aloj.getImagen()
        );
    }
}
