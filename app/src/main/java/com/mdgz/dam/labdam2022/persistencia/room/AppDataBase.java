package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.room.dao.AlojamientoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.dao.FavoritoDAO;
import com.mdgz.dam.labdam2022.persistencia.room.dao.ReservaDAO;
import com.mdgz.dam.labdam2022.persistencia.room.entity.*;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.AlojamientoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.DepartamentoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapper.HabitacionMapper;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Database(entities = {AlojamientoEntity.class, DepartamentoEntity.class, HabitacionEntity.class, ReservaEntity.class, FavoritoEntity.class},
          version = 1,
          exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract AlojamientoDAO alojamientoDAO();
    public abstract ReservaDAO reservaDAO();
    public abstract FavoritoDAO favoritoDAO();

    public static AppDataBase instance;
    public static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static synchronized AppDataBase getInstance(final Context context){
        if(instance == null){
            instance = buildDatabase(context);
        }

        return instance;
    }

    private static AppDataBase buildDatabase(final Context context){
        return Room.databaseBuilder(context,AppDataBase.class,"db_utnbnb").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<Alojamiento> alojamientos = AlojamientoRepository.alojamientosIniciales;
                        List<Departamento> dptos = alojamientos.stream().filter(a -> a.getClass() == Departamento.class).map(d -> (Departamento) d).collect(Collectors.toList());
                        List<Habitacion> habs = alojamientos.stream().filter(a -> a.getClass() == Habitacion.class).map(h -> (Habitacion) h).collect(Collectors.toList());

                        getInstance(context).alojamientoDAO().guardarAlojamientos(AlojamientoMapper.toEntities(alojamientos));
                        getInstance(context).alojamientoDAO().guardarDepartamentos(DepartamentoMapper.toEntities(dptos));
                        getInstance(context).alojamientoDAO().guardarHabitaciones(HabitacionMapper.toEntities(habs));

                        getInstance(context).alojamientoDAO().recuperarAlojamientos();
                    }
                });
            }
        }).build();
    }

}
