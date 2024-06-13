package com.siutu.reserva;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reservas")
public class Reserva {

    @Id
    private String id;
    private String viajeId; 
    private String usuarioId; 
    private LocalDateTime fechaHoraReserva; 
    private boolean estado; 

    // Constructor vacío
    public Reserva() {}

    // Constructor con atributos
    public Reserva(String viajeId, String usuarioId, LocalDateTime fechaHoraReserva, boolean estado) {
        this.viajeId = viajeId;
        this.usuarioId = usuarioId;
        this.fechaHoraReserva = fechaHoraReserva;
        this.estado = estado;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getViajeId() {
        return viajeId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getFechaHoraReserva() {
        return fechaHoraReserva;
    }

    public boolean isEstado() {
        return estado;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setViajeId(String viajeId) {
        this.viajeId = viajeId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setFechaHoraReserva(LocalDateTime fechaHoraReserva) {
        this.fechaHoraReserva = fechaHoraReserva;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}


