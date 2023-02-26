package com.mdgz.dam.labdam2022.persistencia.retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class ReservaRetrofit {
    @SerializedName("alojamientoId")
    private UUID alojamientoID;

    @SerializedName("usuarioId")
    private UUID usuarioID;

    @SerializedName("fechaIngreso")
    private Date fechaIngreso;

    @SerializedName("fechaSalida")
    private Date fechaSalida;

    public ReservaRetrofit(Date fechaIngreso, Date fechaSalida, UUID alojamientoID, UUID usuarioID) {
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
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
