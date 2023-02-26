package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.implementation.ReservaRetrofitDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.implementation.ReservaRoomDataSource;

import java.util.List;

public class ReservaRepository {

    private final ReservaDataSource dataSource;

    private Integer ROOM = 1;
    private Integer RETROFIT = 2;

    public ReservaRepository(Context context) {
        Integer implementar = RETROFIT;

        if(implementar == ROOM) dataSource = new ReservaRoomDataSource(context);
        else dataSource = new ReservaRetrofitDataSource();
    }

    public void guardarReserva(Reserva reserva, OnResult<Void> callback){
        dataSource.guardarReserva(reserva, callback);
    }

    public void recuperarReservas(OnResult<List<Reserva>> callback){
        dataSource.recuperarReservas(callback);
    }
}
