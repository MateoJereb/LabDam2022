package com.mdgz.dam.labdam2022.model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Reserva {

    private UUID id;
    private UUID alojamientoID;
    private UUID usuarioID;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private Boolean cancelada;
    private Double monto;

}
