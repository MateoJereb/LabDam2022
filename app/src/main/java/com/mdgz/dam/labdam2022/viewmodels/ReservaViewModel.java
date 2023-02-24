package com.mdgz.dam.labdam2022.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.OnResult;
import com.mdgz.dam.labdam2022.repo.ReservaRepository;

import java.util.ArrayList;
import java.util.List;

public class ReservaViewModel extends ViewModel {
    private List<Reserva> reservas;

    private MutableLiveData<Boolean> reservaRealizada;

    private ReservaRepository reservaRepository;

    public ReservaViewModel(final ReservaRepository reservaRepository){
        reservas = new ArrayList<>();
        this.reservaRepository = reservaRepository;

        reservaRealizada = new MutableLiveData<>(false);

        new Thread(() -> {
            reservaRepository.recuperarReservas(reservasCallback);
        }).start();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public MutableLiveData<Boolean> getReservaRealizada() {
        return reservaRealizada;
    }

    public void reservar(Reserva res){
        reservaRealizada.postValue(false);
        reservaRepository.guardarReserva(res,guardadaCallback);
    }

    private OnResult<List<Reserva>> reservasCallback = new OnResult<List<Reserva>>() {
        @Override
        public void onSuccess(List<Reserva> result) {
            reservas = result;
            reservaRealizada.postValue(true);
        }

        @Override
        public void onError(Throwable exception) {
            exception.printStackTrace();
            Log.e("ERROR","No se pudieron recuperar las reservas");
        }
    };

    private OnResult<Void> guardadaCallback = new OnResult<Void>() {
        @Override
        public void onSuccess(Void result) {
            Log.d("","Reserva Guardada");
        }

        @Override
        public void onError(Throwable exception) {
            exception.printStackTrace();
            Log.e("ERROR","No se guardo la reserva");
        }
    };
}

