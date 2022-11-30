package com.mdgz.dam.labdam2022.persistencia.room.entity;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alojamiento")
public class AlojamientoEntity {
    @PrimaryKey
    @NonNull
    protected Integer id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    @ColumnInfo(name = "precio_base")
    protected Double precioBase;
    protected Boolean favorito;
    protected Integer imagen;

    public AlojamientoEntity() {
    }

    public AlojamientoEntity(@NonNull Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean favorito, Integer imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.favorito = favorito;
        this.imagen = imagen;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
