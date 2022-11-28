package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.UUID;

@Entity(foreignKeys = { @ForeignKey(entity = AlojamientoEntity.class,parentColumns = "id",childColumns = "id_alojamiento",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE),
                        @ForeignKey(entity = UbicacionEntity.class,parentColumns = "id",childColumns = "id_ubicacion",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class DepartamentoEntity {
    @PrimaryKey
    @NonNull
    private UUID id;
    @ColumnInfo(name = "id_alojamiento")
    private UUID alojamientoID;
    @ColumnInfo(name = "tiene_wifi")
    private Boolean tieneWifi;
    @ColumnInfo(name = "costo_limpieza")
    private Double costoLimpieza;
    @ColumnInfo(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones;
    @ColumnInfo(name = "id_ubicacion")
    private UUID ubicacionID;

    public DepartamentoEntity(@NonNull UUID id, UUID alojamientoID, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones, UUID ubicacionID) {
        this.id = id;
        this.alojamientoID = alojamientoID;
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacionID = ubicacionID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }

    public Boolean getTieneWifi() {
        return tieneWifi;
    }

    public void setTieneWifi(Boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public Double getCostoLimpieza() {
        return costoLimpieza;
    }

    public void setCostoLimpieza(Double costoLimpieza) {
        this.costoLimpieza = costoLimpieza;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public UUID getUbicacionID() {
        return ubicacionID;
    }

    public void setUbicacionID(UUID ubicacionID) {
        this.ubicacionID = ubicacionID;
    }
}
