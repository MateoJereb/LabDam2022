package com.mdgz.dam.labdam2022.viewmodels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.repo.ReservaRepository;

public class ReservaViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ReservaViewModelFactory(final Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass){
        if(modelClass.isAssignableFrom(ReservaViewModel.class)){
            final ReservaRepository reservaRepository = new ReservaRepository(context);

            return (T) new ReservaViewModel(reservaRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
