package com.mdgz.dam.labdam2022.persistencia.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.UUID;

@Entity(tableName = "departamento",
        foreignKeys = {@ForeignKey(entity = AlojamientoEntity.class,parentColumns = "id",childColumns = "id_alojamiento",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class DepartamentoEntity {
    @PrimaryKey
    @NonNull
    private UUID id;

    @ColumnInfo(name = "tiene_wifi")
    private Boolean tieneWifi;

    @ColumnInfo(name = "costo_limpieza")
    private Double costoLimpieza;

    @ColumnInfo(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones;

    @ColumnInfo(name = "id_ubicacion")
    private Integer ubicacionID;

    @ColumnInfo(name = "id_alojamiento")
    private UUID alojamientoID;

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(@NonNull UUID id, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones, Integer ubicacionID, UUID alojamientoID) {
        this.id = id;
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacionID = ubicacionID;
        this.alojamientoID = alojamientoID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
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

    public Integer getUbicacionID() {
        return ubicacionID;
    }

    public void setUbicacionID(Integer ubicacionID) {
        this.ubicacionID = ubicacionID;
    }

    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }
}
