package com.mdgz.dam.labdam2022.persistencia.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity
public class ReservaEntity {
    @PrimaryKey
    @NonNull
    private UUID id;
    @ColumnInfo(name = "id_alojamiento")
    private UUID alojamientoID;
    @ColumnInfo(name = "id_usuario")
    private UUID usuarioID;
    @ColumnInfo(name = "fecha_ingreso")
    private Date fechaIngreso;
    @ColumnInfo(name = "fecha_egreso")
    private Date fechaEgreso;
    private Boolean cancelada;
    private Double monto;

    public ReservaEntity(@NonNull UUID id, UUID alojamientoID, UUID usuarioID, Date fechaIngreso, Date fechaEgreso, Boolean cancelada, Double monto) {
        this.id = id;
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = cancelada;
        this.monto = monto;
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
}
