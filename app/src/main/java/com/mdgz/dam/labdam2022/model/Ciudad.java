package com.mdgz.dam.labdam2022.model;

import java.io.Serializable;
import java.util.UUID;

public class Ciudad implements Serializable {
    UUID id;
    String nombre;
    String abreviatura;

    public Ciudad(){}

    public Ciudad(UUID id, String nombre, String abreviatura) {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
