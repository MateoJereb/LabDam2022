package com.mdgz.dam.labdam2022.persistencia.room.implementation;

import android.content.Context;
import android.util.Pair;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.AppDataBase;
import com.mdgz.dam.labdam2022.persistencia.room.dao.AlojamientoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.dao.FavoritoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entity.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entity.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.AlojamientoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.DepartamentoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.FavoritoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.HabitacionMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {
    private final AlojamientoDAO alojamientoDAO;
    private final FavoritoDAO favoritoDAO;

    public AlojamientoRoomDataSource(final Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        alojamientoDAO = db.alojamientoDAO();
        favoritoDAO = db.favoritoDAO();
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
    public void recuperarAlojamientos(OnResult<Pair<List<Alojamiento>,List<Favorito>>> callback) {
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

            List<FavoritoEntity> favEntity = favoritoDAO.recuperarFavoritos();
            List<Favorito> rtdo2 = favEntity.stream().map(f -> FavoritoMapper.fromEntity(f)).collect(Collectors.toList());

            Collections.shuffle(rtdo);

            Pair<List<Alojamiento>,List<Favorito>> salida = new Pair<>(rtdo,rtdo2);
            callback.onSuccess(salida);
        }
        catch (final Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void buscarPorId(Integer id, OnResult<List<Alojamiento>> callback) {
        try{
            //TODO
        }
        catch (final Exception e){
            callback.onError(e);
        }
    }
}
