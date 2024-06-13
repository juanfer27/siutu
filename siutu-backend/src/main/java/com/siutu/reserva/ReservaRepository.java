package com.siutu.reserva;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    // Puedes agregar métodos de consulta personalizados aquí si es necesario
}
