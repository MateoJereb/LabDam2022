package com.mdgz.dam.labdam2022.persistencia.room.implementation;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.AppDataBase;
import com.mdgz.dam.labdam2022.persistencia.room.dao.ReservaDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entity.ReservaEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.ReservaMapper;

import java.util.ArrayList;
import java.util.List;

public class ReservaRoomDataSource implements ReservaDataSource {
    private final ReservaDAO reservaDAO;

    public ReservaRoomDataSource(final Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        reservaDAO = db.reservaDAO();
    }

    @Override
    public void guardarReserva(Reserva reserva, OnResult<Void> callback) {
        try{
            final ReservaEntity res = ReservaMapper.toEntity(reserva);
            reservaDAO.guardarReserva(res);
            callback.onSuccess(null);
        }
        catch (Exception e){
            callback.onError(e);
        }
    }

    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {
        try{
            List<ReservaEntity> rtdoEntity = reservaDAO.recuperarReservas();
            List<Reserva> rtdo = new ArrayList<>();

            for(ReservaEntity r : rtdoEntity){
                Reserva res = ReservaMapper.fromEntity(r);
                rtdo.add(res);
            }

            callback.onSuccess(rtdo);
        }
        catch (Exception e){
            callback.onError(e);
        }
    }
}
