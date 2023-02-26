package com.mdgz.dam.labdam2022.viewmodels;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.*;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.repo.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class BusquedaViewModel extends ViewModel {
    private List<Ciudad> ciudades;
    private List<Alojamiento> tiposAlojamiento;
    private List<Alojamiento> alojamientos = new ArrayList<>();

    private final AlojamientoRepository alojRepository;
    private final FavoritoRepository favoritoRepository;

    //private LinkedHashMap<UUID,UUID> mapperAlojFav;

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

        //mapperAlojFav = new LinkedHashMap<>();

        new Thread(() -> {
            tiempoBusqueda = System.currentTimeMillis();
            Log.d("Buscar","Alojamiento");
            alojRepository.recuperarAlojamientos(alojamientosCargadosCallback);
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
        //mapperAlojFav.put(aloj.getId(),newId);
        favoritoRepository.guardarFavorito(nuevoFav,voidCallback);
    }

    public void desmarcarFavorito(Alojamiento aloj){
        LinkedHashMap<UUID,Boolean> proxPost = favPost.getValue();
        proxPost.put(aloj.getId(),false);
        favPost.postValue(proxPost);

        favoritoRepository.eliminarFavorito(aloj.getId(),voidCallback);
    }

    private OnResult<List<Alojamiento>> alojamientosCargadosCallback = new OnResult<List<Alojamiento>>() {
        @Override
        public void onSuccess(List<Alojamiento> result) {
            alojamientos = result;
            Log.d("Alojamiento","Encontrado");
            favoritoRepository.recuperarFavorito(favoritosCargadosCallback);
        }

        @Override
        public void onError(Throwable exception) {
            exception.printStackTrace();
            Log.e("ERROR","EXCEPTION AL BUSCAR");
        }
    };

    private OnResult<List<Favorito>> favoritosCargadosCallback = new OnResult<List<Favorito>>() {
        @Override
        public void onSuccess(List<Favorito> result) {
            Log.d("Favorito","Encontrado");
            tiempoBusqueda = System.currentTimeMillis() - tiempoBusqueda;
            for(Favorito f : result){
                for(Alojamiento a : alojamientos) {
                    if (a.getId().equals(f.getAlojamientoID())) {
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

