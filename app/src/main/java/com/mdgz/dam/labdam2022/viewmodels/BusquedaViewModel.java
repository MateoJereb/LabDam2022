package com.mdgz.dam.labdam2022.viewmodels;

import android.app.Application;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.persistencia.room.implementations.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.repo.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BusquedaViewModel extends ViewModel {
    private List<Ciudad> ciudades;
    private List<Alojamiento> tiposAlojamiento;
    private List<Alojamiento> alojamientos = new ArrayList<>();

    private final AlojamientoRepository alojRepository;
    private final FavoritoRepository favoritoRepository;

    private LinkedHashMap<UUID,UUID> mapperAlojFav;

    private long tiempoBusqueda = 0;

    private MutableLiveData<Boolean> cargado = new MutableLiveData<Boolean>(false);
    private MutableLiveData<LinkedHashMap<UUID,Boolean>> favPost = new MutableLiveData<>(new LinkedHashMap<>());

    public BusquedaViewModel(final AlojamientoRepository alojRepository, final FavoritoRepository favoritoRepository) {
        ciudades = new ArrayList<>();
        ciudades.add(null);
        ciudades.addAll(CiudadRepository._CIUDADES);

        tiposAlojamiento = new ArrayList<>();
        tiposAlojamiento.add(null);
        tiposAlojamiento.add(new Departamento());
        tiposAlojamiento.add(new Habitacion());

        this.alojRepository = alojRepository;
        this.favoritoRepository = favoritoRepository;

        mapperAlojFav = new LinkedHashMap<>();

        new Thread(() -> {
            tiempoBusqueda = System.currentTimeMillis();
            alojRepository.recuperarAlojamientos(cargadoCallback);
        }).start();
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public List<Alojamiento> getTiposAlojamiento() {
        return tiposAlojamiento;
    }

    public List<Alojamiento> getAlojamientos() {
        return alojamientos;
    }

    public long getTiempo() {
        return tiempoBusqueda;
    }

    public MutableLiveData<Boolean> getCargado() {
        return cargado;
    }

    public MutableLiveData<LinkedHashMap<UUID, Boolean>> getFavPost() {
        return favPost;
    }

    public void marcarFavorito(Alojamiento aloj){
        LinkedHashMap<UUID,Boolean> proxPost = favPost.getValue();
        proxPost.put(aloj.getId(),true);
        favPost.postValue(proxPost);

        UUID newId = UUID.randomUUID();
        Favorito nuevoFav = new Favorito(newId,aloj.getId(),UserRepository.currentUserId());
        mapperAlojFav.put(aloj.getId(),newId);
        favoritoRepository.guardarFavorito(nuevoFav,voidCallback);
    }

    public void desmarcarFavorito(Alojamiento aloj){
        LinkedHashMap<UUID,Boolean> proxPost = favPost.getValue();
        proxPost.put(aloj.getId(),false);
        favPost.postValue(proxPost);

        favoritoRepository.eliminarFavorito(mapperAlojFav.get(aloj.getId()),voidCallback);
        mapperAlojFav.remove(aloj.getId());
    }

    private OnResult<Pair<List<Alojamiento>,List<Favorito>>> cargadoCallback = new OnResult<Pair<List<Alojamiento>, List<Favorito>>>() {
        @Override
        public void onSuccess(Pair<List<Alojamiento>, List<Favorito>> result) {
            tiempoBusqueda = System.currentTimeMillis() - tiempoBusqueda;

            alojamientos = result.first;

            for(Favorito f : result.second){
                mapperAlojFav.put(f.getAlojamientoID(),f.getId());

                for(Alojamiento a : alojamientos){
                    if(a.getId().equals(f.getAlojamientoID())){
                        a.setFavorito(true);
                    }
                }
            }

            cargado.postValue(true);
        }

        @Override
        public void onError(Throwable exception) {
            exception.printStackTrace();
            Log.e("ERROR","EXCEPTION AL BUSCAR");
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

