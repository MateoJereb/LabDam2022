package com.mdgz.dam.labdam2022.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.implementations.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.repo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BusquedaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Ciudad>> ciudades;
    private List<Alojamiento> tiposAlojamiento;

    private final AlojamientoRepository alojRepository;
    private List<Alojamiento> alojamientos = new ArrayList<>();

    private final FavoritoRepository favoritoRepository;
    private List<Favorito> favoritos = new ArrayList<>();

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

        alojRepository = new AlojamientoRepository(application);
        new Thread(() -> {
            alojRepository.recuperarAlojamientos(alojCallback);
        }).start();

        favoritoRepository = new FavoritoRepository(application);
        new Thread(() -> {
            favoritoRepository.recuperarFavorito(favCallback);
        }).start();


        for(Favorito f : favoritos){
            for(Alojamiento a : alojamientos){
                if(a.getId().toString() == f.getAlojamientoID().toString()){
                    a.setFavorito(true);
                    break;
                }
            }
        }
    }

    public MutableLiveData<List<Ciudad>> getCiudades(){
        return ciudades;
    }

    public List<Alojamiento> getAlojamientos(Optional<Alojamiento> tipo, Optional<Integer> capacidad, Optional<Double> minPrecio, Optional<Double> maxPrecio, Optional<Ciudad> ciudad, Optional<Boolean> wifi){
        return alojamientos;
    }

    public List<Alojamiento> getTiposAlojamiento() {
        return tiposAlojamiento;
    }

    public void marcarFavorito(Alojamiento aloj){
        Favorito nuevoFav = new Favorito(UUID.randomUUID(),UUID.fromString(aloj.getId().toString()),UserRepository.currentUserId());
        favoritos.add(nuevoFav);
        favoritoRepository.guardarFavorito(nuevoFav,voidCallback);
    }

    public void desmarcarFavorito(Alojamiento aloj){
        for(Favorito f : favoritos){
            if(aloj.getId().toString() == f.getAlojamientoID().toString()) {
                favoritos.remove(f);
                favoritoRepository.eliminarFavorito(f,voidCallback);
                return;
            }
        }


    }

    private OnResult<List<Alojamiento>> alojCallback = new OnResult<List<Alojamiento>>() {
        @Override
        public void onSuccess(List<Alojamiento> result) {
            alojamientos = result;
        }

        @Override
        public void onError(Throwable exception) {
            Toast toast = Toast.makeText(getApplication(),"Error al buscar alojamientos",Toast.LENGTH_SHORT);
            toast.show();
            exception.printStackTrace();
        }
    };

    private OnResult<List<Favorito>> favCallback = new OnResult<List<Favorito>>() {
        @Override
        public void onSuccess(List<Favorito> result) {
            favoritos = result;
        }

        @Override
        public void onError(Throwable exception) {
            Toast toast = Toast.makeText(getApplication(),"Error al buscar favoritos",Toast.LENGTH_SHORT);
            toast.show();
            exception.printStackTrace();
        }
    };

    private OnResult<Void> voidCallback = new OnResult<Void>() {
        @Override
        public void onSuccess(Void result) {

        }

        @Override
        public void onError(Throwable exception) {
            Toast toast = Toast.makeText(getApplication(),"Error al realizar el cambio",Toast.LENGTH_SHORT);
            toast.show();
            exception.printStackTrace();
        }
    };
}
