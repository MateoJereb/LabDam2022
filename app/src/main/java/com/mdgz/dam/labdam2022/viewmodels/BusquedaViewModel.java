package com.mdgz.dam.labdam2022.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.repo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusquedaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Ciudad>> ciudades;
    private List<Alojamiento> tiposAlojamiento;

    public BusquedaViewModel(@NonNull Application application) {
        super(application);
        ciudades = new MutableLiveData<List<Ciudad>>();
        List<Ciudad> listaCiudades = new ArrayList<>();
        listaCiudades.add(null);
        listaCiudades.addAll(CiudadRepository._CIUDADES);
        ciudades.setValue(listaCiudades);

        tiposAlojamiento = new ArrayList<Alojamiento>();
        tiposAlojamiento.add(null);
        tiposAlojamiento.add(new Departamento());
        tiposAlojamiento.add(new Habitacion());
    }

    public MutableLiveData<List<Ciudad>> getCiudades(){
        return ciudades;
    }

    public List<Alojamiento> getAlojamientos(Optional<Alojamiento> tipo, Optional<Integer> capacidad, Optional<Double> minPrecio, Optional<Double> maxPrecio, Optional<Ciudad> ciudad, Optional<Boolean> wifi){
        //TODO filtrar segun criterios de busqueda

        List<Alojamiento> alojamientos = AlojamientoRepository._ALOJAMIENTOS;

        return alojamientos;
    }

    public List<Alojamiento> getTiposAlojamiento() {
        return tiposAlojamiento;
    }

}
