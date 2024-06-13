package com.siutu.viaje;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViajeRepository extends MongoRepository<Viaje, String> {
    boolean existsByConductorIdAndEstadoNot(String conductorId, String estado);
}