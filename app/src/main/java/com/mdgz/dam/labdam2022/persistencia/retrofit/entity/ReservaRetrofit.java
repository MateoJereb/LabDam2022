package com.mdgz.dam.labdam2022.persistencia.retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class ReservaRetrofit {

    @SerializedName("id")
    private UUID id;

    @SerializedName("fechaIngreso")
    private Date fechaIngreso;

    @SerializedName("fechaEgreso")
    private Date fechaEgreso;

    @SerializedName("cancelada")
    private Boolean cancelada;

    @SerializedName("cantidad")
    private Integer cantidad;

    @SerializedName("monto")
    private Double monto;

    @SerializedName("alojamientoID")
    private UUID alojamientoID;

    @SerializedName("usuarioID")
    private UUID usuarioID;

    public ReservaRetrofit(UUID id, Date fechaIngreso, Date fechaEgreso, Boolean cancelada, Integer cantidad, Double monto, UUID alojamientoID, UUID usuarioID) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = cancelada;
        this.cantidad = cantidad;
        this.monto = monto;
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
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
