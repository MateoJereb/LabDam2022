package com.mdgz.dam.labdam2022.persistencia.datasource;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.OnResult;

import java.util.Date;
import java.util.List;

public interface ReservaDataSource {

    void guardarReservas(final Reserva reserva, final OnResult<Void> callback);

    void recuperarReservas(final OnResult<List<Reserva>> callback);
}
