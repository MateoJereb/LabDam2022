package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = AlojamientoEntity.class,parentColumns = "id",childColumns = "id_alojamiento",onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)})
public class FavoritoEntity {
    @PrimaryKey
    @NonNull
    private UUID id;
    @ColumnInfo(name = "id_alojamiento")
    private UUID alojamientoID;
    @ColumnInfo(name = "id_usuario")
    private UUID usuarioID;

    public FavoritoEntity(@NonNull UUID id, UUID alojamientoID, UUID usuarioID) {
        this.id = id;
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
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

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }
}
