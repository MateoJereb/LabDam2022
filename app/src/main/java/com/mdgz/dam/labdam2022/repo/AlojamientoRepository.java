package com.mdgz.dam.labdam2022.repo;

import android.content.Context;
import android.util.Pair;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.datasource.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.implementation.AlojamientoRoomDataSource;

import java.util.List;
import java.util.UUID;

public class AlojamientoRepository {

     private final AlojamientoDataSource dataSource;

    private static final Ubicacion ubicacion1 = new Ubicacion(-42.6,-38.3,"San Martin","1989",CiudadRepository._CIUDADES.get(0));
    private static final Ubicacion ubicacion2 = new Ubicacion(-42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1));

    private static final Hotel hotel1 = new Hotel(1,"Hotel 1",3,ubicacion2);

    public static final List<Ubicacion> _UBICACIONES = List.of(ubicacion1,ubicacion2);

    public static final List<Hotel> _HOTELES = List.of(hotel1);

    public static final List<Alojamiento> alojamientosIniciales = List.of(new Departamento(UUID.fromString("193fb10e-34fa-4223-8bff-53001da9cbaf"), "Depto 1", "Luminoso y amplio", 6, 300.0,false,true, 1500.0, 3,ubicacion1,R.drawable.dpto1),
            new Habitacion(UUID.fromString("a6584d68-b48f-4829-989d-c069bb44e4d8"), "Habitacion 2", "Espectacular suite",4, 1200.0, false, 2,1,false,hotel1,R.drawable.hab1)
    );

    public AlojamientoRepository(Context context){
        dataSource = new AlojamientoRoomDataSource(context);
    }

    public void guardarHabitacion(Habitacion habitacion, OnResult<Void> callback){
        dataSource.guardarHabitacion(habitacion, callback);
    }

    public void guardarDepartamnto(Departamento departamento, OnResult<Void> callback){
        dataSource.guardarDepartamento(departamento, callback);
    }

    public void recuperarHabitaciones(OnResult<List<Habitacion>> callback){
        dataSource.recuperarHabitaciones(callback);
    }

    public void recuperarDepartamentos(OnResult<List<Departamento>> callback){
        dataSource.recuperarDepartamentos(callback);
    }

    public void recuperarAlojamientos(OnResult<List<Alojamiento>> callback){
        dataSource.recuperarAlojamientos(callback);
    }
}
