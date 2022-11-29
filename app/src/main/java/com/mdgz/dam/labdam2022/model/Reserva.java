package com.mdgz.dam.labdam2022.model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Reserva {

    private Integer id;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private Boolean cancelada;
    private Double monto;

    private UUID alojamientoID;
    private UUID usuarioID;

    public Reserva() {
    }

    public Reserva(Integer id, Date fechaIngreso, Date fechaEgreso, Boolean cancelada, Double monto, UUID alojamientoID, UUID usuarioID) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = cancelada;
        this.monto = monto;
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


