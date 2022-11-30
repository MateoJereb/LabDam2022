package com.mdgz.dam.labdam2022.persistencia.room.implementations;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.AppDataBase;
import com.mdgz.dam.labdam2022.persistencia.room.dao.AlojamientoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.dao.FavoritoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.dao.ReservaDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entity.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.AlojamientoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.DepartamentoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.HabitacionMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {
    private final AlojamientoDAO alojamientoDAO;

    public AlojamientoRoomDataSource(final Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        alojamientoDAO = db.alojamientoDAO();
    }

    @Override
    public void guardarHabitacion(Habitacion habitacion, OnResult<Void> callback) {
        try{
            final AlojamientoEntity aloj = AlojamientoMapper.toEntity(habitacion);
            final HabitacionEntity hab = HabitacionMapper.toEntity(habitacion);

            alojamientoDAO.guardarAlojamiento(aloj);
            alojamientoDAO.guardarHabitacion(hab);

            callback.onSuccess(null);
        }
        catch(final Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void guardarDepartamento(Departamento departamento, OnResult<Void> callback) {
        try{
            final AlojamientoEntity aloj = AlojamientoMapper.toEntity(departamento);
            final DepartamentoEntity dpto = DepartamentoMapper.toEntity(departamento);

            alojamientoDAO.guardarAlojamiento(aloj);
            alojamientoDAO.guardarDepartamento(dpto);

            callback.onSuccess(null);
        }
        catch(final Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void recuperarHabitaciones(OnResult<List<Habitacion>> callback) {
        try{
         List<HabitacionEntity> rtdoEntity = alojamientoDAO.recuperarHabitaciones();
         List<Habitacion> rtdo = new ArrayList<>();

         for(HabitacionEntity h : rtdoEntity){
             AlojamientoEntity aloj = alojamientoDAO.buscarAlojamiento(h.getAlojamientoID());
             rtdo.add(HabitacionMapper.fromEntity(h,aloj));
         }

         callback.onSuccess(rtdo);
        }
        catch(final Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void recuperarDepartamentos(OnResult<List<Departamento>> callback) {
        try{
            List<DepartamentoEntity> rtdoEntity = alojamientoDAO.recuperarDepartamentos();
            List<Departamento> rtdo = new ArrayList<>();

            for(DepartamentoEntity d : rtdoEntity){
                AlojamientoEntity aloj = alojamientoDAO.buscarAlojamiento(d.getAlojamientoID());
                rtdo.add(DepartamentoMapper.fromEntity(d,aloj));
            }

            callback.onSuccess(rtdo);
        }
        catch(final Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void recuperarAlojamientos(OnResult<List<Alojamiento>> callback) {
        try{
            List<DepartamentoEntity> dptoEntity = alojamientoDAO.recuperarDepartamentos();
            List<HabitacionEntity> habEntity = alojamientoDAO.recuperarHabitaciones();
            List<Alojamiento> rtdo = new ArrayList<>();

            for(DepartamentoEntity d : dptoEntity){
                AlojamientoEntity aloj = alojamientoDAO.buscarAlojamiento(d.getAlojamientoID());
                rtdo.add(DepartamentoMapper.fromEntity(d,aloj));
            }

            for(HabitacionEntity h : habEntity){
                AlojamientoEntity aloj = alojamientoDAO.buscarAlojamiento(h.getAlojamientoID());
                rtdo.add(HabitacionMapper.fromEntity(h,aloj));
            }

            Collections.shuffle(rtdo);

            callback.onSuccess(rtdo);
        }
        catch (final Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void buscarPorId(Integer id, OnResult<List<Alojamiento>> callback) {
        //TODO
    }
}
