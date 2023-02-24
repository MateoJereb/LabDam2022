package com.mdgz.dam.labdam2022.persistencia.datasource;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.OnResult;

import java.util.List;

public interface ReservaDataSource {
    void guardarReserva(Reserva reserva, OnResult<Void> callback);

    void recuperarReservas(OnResult<List<Reserva>> callback);
}
