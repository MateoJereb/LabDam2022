package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = UbicacionEntity.class,parentColumns = "id",childColumns = "id_ubicacion",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class HotelEntity {
    @PrimaryKey
    @NonNull
    private UUID id;
    private String nombre;
    private Integer categoria;
    @ColumnInfo(name = "id_ubicacion")
    private UUID ubicacionID;

    public HotelEntity(@NonNull UUID id, String nombre, Integer categoria, UUID ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacionID = ubicacion;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public UUID getUbicacion() {
        return ubicacionID;
    }

    public void setUbicacion(UUID ubicacion) {
        this.ubicacionID = ubicacion;
    }
}
