package com.mdgz.dam.labdam2022.persistencia.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entity.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.HabitacionEntity;

import java.util.List;

@Dao
public interface AlojamientoDAO {
    @Insert
    void insertarAlojamiento(AlojamientoEntity alojamiento);

    @Insert
    void insertarAlojamientos(List<AlojamientoEntity> alojamientos);

    @Insert
    void insertarDepartamento(DepartamentoEntity dpto);

    @Insert
    void insertarDepartamentos(List<DepartamentoEntity> dptos);

    @Insert
    void insertarHabitacion(HabitacionEntity habitacion);

    @Insert
    void insertarHabitaciones(List<HabitacionEntity> habitaciones);

    @Query("SELECT * FROM alojamiento")
    List<AlojamientoEntity> recuperarAlojamientos();

    @Query("SELECT * FROM alojamiento WHERE id=:idAloj")
    List<AlojamientoEntity> buscarAlojamiento(Integer idAloj);

    @Query("SELECT * FROM departamento")
    List<DepartamentoEntity> recuperarDepartamentos();

    @Query("SELECT * FROM departamento WHERE id=:idDpto")
    List<DepartamentoEntity> buscarDepartamento(Integer idDpto);

    @Query("SELECT * FROM habitacion")
    List<HabitacionEntity> recuperarHabitaciones();

    @Query("SELECT * FROM habitacion WHERE id=:idHab")
    List<HabitacionEntity> buscarHabitacion(Integer idHab);
}
