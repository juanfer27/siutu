package com.siutu.viaje;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("viajes")
public class Viaje {

    @Id
    private String id;
    private String conductorId; 
    private String tipoVehiculo; 
    private int asientosDisponibles;
    private String lugarRecogida;
    private LocalDateTime horaSalida;
    private String lugarDestino; // Nuevo atributo
    private String estado; // Valores posibles: "PROGRAMADO", "EN_CURSO", "FINALIZADO"

    // Constructor vacío (requerido por Spring Data)
    public Viaje() {}

    // Constructor con atributos
    public Viaje(String conductorId, String tipoVehiculo, int asientosDisponibles, String lugarRecogida, LocalDateTime horaSalida, String lugarDestino, String estado) {
        this.conductorId = conductorId;
        this.tipoVehiculo = tipoVehiculo;
        this.asientosDisponibles = asientosDisponibles;
        this.lugarRecogida = lugarRecogida;
        this.horaSalida = horaSalida;
        this.lugarDestino = lugarDestino;
        this.estado = estado;
    }

    // Getters 
    public String getId() {
        return id;
    }

    public String getConductorId() {
        return conductorId;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public String getLugarRecogida() {
        return lugarRecogida;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }
    
    public String getestado(){
        return estado;
    }

    // Setters 
    public void setId(String id) {
        this.id = id;
    }

    public void setConductorId(String conductorId) {
        this.conductorId = conductorId;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public void setLugarRecogida(String lugarRecogida) {
        this.lugarRecogida = lugarRecogida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}



