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
    private MutableLiveData<List<Alojamiento>> alojamientos = new MutableLiveData<>(new ArrayList<>());

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
        favoritoRepository = new FavoritoRepository(application);

        new Thread(() -> {
            alojRepository.recuperarAlojamientos(alojCallback);
            favoritoRepository.recuperarFavorito(favCallback);

            for(Favorito f : favoritos){
                for(Alojamiento a : alojamientos.getValue()){
                    if(a.getId().equals(f.getAlojamientoID())){
                        a.setFavorito(true);
                        break;
                    }
                }
            }
        }).start();
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

    public void marcarFavorito(Alojamiento aloj){
        Favorito nuevoFav = new Favorito(UUID.randomUUID(),UUID.fromString(aloj.getId().toString()),UserRepository.currentUserId());
        favoritos.add(nuevoFav);
        favoritoRepository.guardarFavorito(nuevoFav,voidCallback);

        List<Alojamiento> nuevoAloj = alojamientos.getValue();
        for(Alojamiento a : nuevoAloj){
            if(a.getId() == aloj.getId()){
                a.setFavorito(true);
                alojamientos.postValue(nuevoAloj);
                break;
            }
        }
    }

    public void desmarcarFavorito(Alojamiento aloj){
        for(Favorito f : favoritos){
            if(aloj.getId() == f.getAlojamientoID()) {
                favoritos.remove(f);
                favoritoRepository.eliminarFavorito(f,voidCallback);
                break;
            }
        }

        List<Alojamiento> nuevoAloj = alojamientos.getValue();
        for(Alojamiento a : nuevoAloj){
            if(a.getId() == aloj.getId()){
                a.setFavorito(false);
                alojamientos.postValue(nuevoAloj);
                break;
            }
        }


    }

    private OnResult<List<Alojamiento>> alojCallback = new OnResult<List<Alojamiento>>() {
        @Override
        public void onSuccess(List<Alojamiento> result) {
            alojamientos.postValue(result);
        }

        @Override
        public void onError(Throwable exception) {
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
            exception.printStackTrace();
        }
    };

    private OnResult<Void> voidCallback = new OnResult<Void>() {
        @Override
        public void onSuccess(Void result) {

        }

        @Override
        public void onError(Throwable exception) {
            exception.printStackTrace();
        }
    };
}
