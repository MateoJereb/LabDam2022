package com.mdgz.dam.labdam2022.persistencia.datasource;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.OnResult;

import java.util.List;

public interface AlojamientoDataSource {

    void guardarHabitacion(Habitacion habitacion, OnResult<Habitacion> callback);

    void guardarDepartamento(Departamento departamento, OnResult<Departamento> callback);

    void recuperarHabitaciones(OnResult<List<Habitacion>> callback);

    void recuperarDepartamentos(OnResult<List<Departamento>> callback);

    void recuperarAlojamientos(OnResult<List<Alojamiento>> callback);

}
