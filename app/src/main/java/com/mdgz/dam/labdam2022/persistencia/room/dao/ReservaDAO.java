package com.mdgz.dam.labdam2022.persistencia.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entity.ReservaEntity;

import java.util.List;

@Dao
public interface ReservaDAO {
    @Insert
    void guardarReserva(ReservaEntity reserva);

    @Query("SELECT * FROM reserva")
    List<ReservaEntity> recuperarReservas();
}
