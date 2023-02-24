package com.mdgz.dam.labdam2022.persistencia.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "reserva",
        foreignKeys = {@ForeignKey(entity = AlojamientoEntity.class,parentColumns = "id",childColumns = "id_alojamiento",onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
public class ReservaEntity {
    @PrimaryKey
    @NonNull
    private UUID id;

    @ColumnInfo(name = "fecha_ingreso")
    private Date fechaIngreso;

    @ColumnInfo(name = "fecha_egreso")
    private Date fechaEgreso;

    private Boolean cancelada;

    private Integer cantidad;

    private Double monto;

    @ColumnInfo(name = "id_alojamiento")
    private UUID alojamientoID;

    @ColumnInfo(name = "id_usuario")
    private UUID usuarioID;

    public ReservaEntity() {
    }

    public ReservaEntity(@NonNull UUID id, Date fechaIngreso, Date fechaEgreso, Boolean cancelada, Integer cantidad, Double monto, UUID alojamientoID, UUID usuarioID) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cancelada = cancelada;
        this.cantidad = cantidad;
        this.monto = monto;
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
