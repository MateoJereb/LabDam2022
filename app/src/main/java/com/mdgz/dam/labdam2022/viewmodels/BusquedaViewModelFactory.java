package com.mdgz.dam.labdam2022.viewmodels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.FavoritoRepository;

public class BusquedaViewModelFactory implements ViewModelProvider.Factory{
    private final Context context;

    public BusquedaViewModelFactory(final Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass){
        if(modelClass.isAssignableFrom(BusquedaViewModel.class)){
            final AlojamientoRepository alojRepository = new AlojamientoRepository(context);
            final FavoritoRepository favoritoRepository = new FavoritoRepository(context);

            return (T) new BusquedaViewModel(alojRepository,favoritoRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
