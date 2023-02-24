package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Favorito {
    private UUID id;

    private UUID alojamientoID;
    private UUID usuarioID;

    public Favorito() {
    }

    public Favorito(UUID id, UUID alojamientoID, UUID usuarioID) {
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


