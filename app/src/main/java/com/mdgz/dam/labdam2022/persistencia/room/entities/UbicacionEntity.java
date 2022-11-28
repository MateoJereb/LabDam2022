package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = CiudadEntity.class,parentColumns = "id",childColumns = "id_ciudad",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class UbicacionEntity {
    @PrimaryKey
    @NonNull
    private UUID id;
    private Double lat;
    private Double lng;
    private String calle;
    private String numero;
    @ColumnInfo(name = "id_ciudad")
    private Ciudad ciudadID;

    public UbicacionEntity(@NonNull UUID id, Double lat, Double lng, String calle, String numero, Ciudad ciudadID) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudadID = ciudadID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Ciudad getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Ciudad ciudadID) {
        this.ciudadID = ciudadID;
    }
}
