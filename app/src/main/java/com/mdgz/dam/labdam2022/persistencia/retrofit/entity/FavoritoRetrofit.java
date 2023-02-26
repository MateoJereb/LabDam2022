package com.mdgz.dam.labdam2022.persistencia.retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class FavoritoRetrofit {
    @SerializedName("alojamientoId")
    private UUID alojamientoID;

    @SerializedName("usuarioId")
    private UUID usuarioID;

    public FavoritoRetrofit(UUID alojamientoID, UUID usuarioID) {
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
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
