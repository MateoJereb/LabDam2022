package com.mdgz.dam.labdam2022.persistencia.retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class FavoritoRetrofit {
    @SerializedName("id")
    private UUID id;

    @SerializedName("alojamientoID")
    private UUID alojamientoID;

    @SerializedName("usuarioID")
    private UUID usuarioID;

    public FavoritoRetrofit(UUID id, UUID alojamientoID, UUID usuarioID) {
        this.id = id;
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
