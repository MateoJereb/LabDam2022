package com.mdgz.dam.labdam2022.repo;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;
import java.util.UUID;

public class AlojamientoRepository {

    private static final Ubicacion ubicacion1 = new Ubicacion(UUID.fromString("1"),-42.6,-38.3,"San Martin","1989",CiudadRepository._CIUDADES.get(0));
    private static final Ubicacion ubicacion2 = new Ubicacion(UUID.fromString("2"),-42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1));

    public static final List<Alojamiento> _ALOJAMIENTOS = List.of(
            new Departamento(UUID.fromString("1"), "Depto 1", "Luminoso y amplio", 6, 300.0,false,true, 1500.0, 3,ubicacion1,null),
            new Habitacion(UUID.fromString("2"), "Habitacion 2", "Espectacular suite",4, 1200.0, false, 2,1,false,new Hotel(UUID.fromString("1"),"Hotel 1",3,ubicacion2),null)
    );

    public List<Alojamiento> listaCiudades(){
        return  _ALOJAMIENTOS;
    }
}
