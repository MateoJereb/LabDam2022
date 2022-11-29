package com.mdgz.dam.labdam2022.persistencia.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "habitacion",
        foreignKeys = {@ForeignKey(entity = AlojamientoEntity.class,parentColumns = "id",childColumns = "id_alojamiento",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class HabitacionEntity {
    @PrimaryKey
    @NonNull
    private Integer id;

    @ColumnInfo(name = "camas_individuales")
    private Integer camasIndividuales;

    @ColumnInfo(name = "camas_matrimoniales")
    private Integer camasMatrimoniales;

    @ColumnInfo(name = "tiene_estacionamiento")
    private Boolean tieneEstacionamiento;

    @Ignore
    private Integer hotelID;

    @ColumnInfo(name = "id_alojamiento")
    private Integer alojamientoID;

    public HabitacionEntity() {
    }

    public HabitacionEntity(@NonNull Integer id, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, Integer hotelID, Integer alojamientoID) {
        this.id = id;
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotelID = hotelID;
        this.alojamientoID = alojamientoID;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public Integer getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(Integer camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public Integer getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(Integer camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(Integer alojamientoID) {
        this.alojamientoID = alojamientoID;
    }
}
