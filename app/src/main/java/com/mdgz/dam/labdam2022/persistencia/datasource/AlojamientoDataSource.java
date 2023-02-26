package com.mdgz.dam.labdam2022.persistencia.datasource;

import android.util.Pair;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.OnResult;

import java.util.List;

public interface AlojamientoDataSource {
    void guardarHabitacion(Habitacion habitacion, OnResult<Void> callback);

    void guardarDepartamento(Departamento departamento, OnResult<Void> callback);

    void recuperarHabitaciones(OnResult<List<Habitacion>> callback);

    void recuperarDepartamentos(OnResult<List<Departamento>> callback);

    void recuperarAlojamientos(OnResult<List<Alojamiento>> callback);

    void buscarPorId(Integer id, OnResult<List<Alojamiento>> callback);
}
