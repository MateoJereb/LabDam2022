package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Hotel;

import java.util.UUID;

@Entity(foreignKeys = { @ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id",childColumns = "id_alojamiento",onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                        @ForeignKey(entity = HotelEntity.class,parentColumns = "id",childColumns = "id_hotel",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class HabitacionEntity {

    @PrimaryKey
    @NonNull
    private UUID id;
    @ColumnInfo(name = "id_alojamiento")
    private UUID alojamientoID;
    @ColumnInfo(name = "camas_individuales")
    private Integer camasIndividuales;
    @ColumnInfo(name = "camas_matrimoniales")
    private Integer camasMatrimoniales;
    @ColumnInfo(name = "tiene_estacionamiento")
    private Boolean tieneEstacionamiento;
    @ColumnInfo(name = "id_hotel")
    private UUID hotelID;

    public HabitacionEntity(@NonNull UUID id, UUID alojamientoID, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, UUID hotelID) {
        this.id = id;
        this.alojamientoID = alojamientoID;
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotelID = hotelID;
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

    public UUID getHotelID() {
        return hotelID;
    }

    public void setHotelID(UUID hotelID) {
        this.hotelID = hotelID;
    }
}
