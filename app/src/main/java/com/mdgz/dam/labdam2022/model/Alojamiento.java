package com.mdgz.dam.labdam2022.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public abstract class Alojamiento implements Serializable{

    protected Integer id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;
    protected Boolean favorito;
    protected Integer imagen;

    public abstract Ubicacion getUbicacion();
    public Double costoDia(){
        return precioBase;
    }

    public Alojamiento(){
        super();
    }

    public Alojamiento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean favorito, Integer imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.favorito = favorito;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public Integer getImagen() { return imagen; }

    public void setImagen(Integer imagen) { this.imagen = imagen; }
}
