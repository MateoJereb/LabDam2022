package com.mdgz.dam.labdam2022.persistencia.retrofit.implementation;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.ReservaDataSource;

import java.util.List;

public class ReservaRetrofitDataSource implements ReservaDataSource {
    @Override
    public void guardarReserva(Reserva reserva, OnResult<Void> callback) {

    }

    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {

    }
}
