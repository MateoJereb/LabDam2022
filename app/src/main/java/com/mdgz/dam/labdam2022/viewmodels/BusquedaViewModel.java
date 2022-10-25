package com.mdgz.dam.labdam2022.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.repo.*;

import java.util.ArrayList;
import java.util.List;

public class BusquedaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Ciudad>> ciudades;
    private MutableLiveData<List<Alojamiento>> alojamientos;
    private List<Alojamiento> tiposAlojamiento;

    public BusquedaViewModel(@NonNull Application application) {
        super(application);
        ciudades = new MutableLiveData<List<Ciudad>>();
        List<Ciudad> listaCiudades = new ArrayList<>();
        listaCiudades.add(null);
        listaCiudades.addAll(CiudadRepository._CIUDADES);
        ciudades.setValue(listaCiudades);

        alojamientos = new MutableLiveData<List<Alojamiento>>();
        List<Alojamiento> listaAlojamientos = new ArrayList<>();
        listaAlojamientos.add(null);
        listaAlojamientos.addAll(AlojamientoRepository._ALOJAMIENTOS);
        alojamientos.setValue(listaAlojamientos);

        tiposAlojamiento = new ArrayList<Alojamiento>();
        tiposAlojamiento.add(null);
        tiposAlojamiento.add(new Departamento());
        tiposAlojamiento.add(new Habitacion());
    }

    public MutableLiveData<List<Ciudad>> getCiudades(){
        return ciudades;
    }

    public MutableLiveData<List<Alojamiento>> getAlojamientos(){
        return alojamientos;
    }

    public List<Alojamiento> getTiposAlojamiento() {
        return tiposAlojamiento;
    }

    public void buscar(Alojamiento criterios){

    }
}
