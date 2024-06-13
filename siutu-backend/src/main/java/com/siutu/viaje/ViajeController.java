package com.siutu.viaje;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siutu.reserva.Reserva;
import com.siutu.reserva.ReservaRepository;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private ReservaRepository reservaRepository; 

    @GetMapping
    public List<Viaje> obtenerTodosLosViajes() {
        return viajeRepository.findAll();
    }

     @PostMapping
    public ResponseEntity<Viaje> crearViaje(@RequestBody Viaje viaje) {
        // Obtener el ID del usuario autenticado usando Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String conductorId = authentication.getName(); // El nombre de usuario suele ser el ID

        // Verificar si el usuario tiene un viaje activo
        boolean tieneViajeActivo = viajeRepository.existsByConductorIdAndEstadoNot(conductorId, "FINALIZADO");

        if (tieneViajeActivo) {
            return ResponseEntity.badRequest().body(null); 
        } else {
            // Establecer el ID del conductor y el estado del viaje
            viaje.setConductorId(conductorId);
            viaje.setEstado("PROGRAMADO"); // O el estado inicial que corresponda

            Viaje nuevoViaje = viajeRepository.save(viaje);
            return ResponseEntity.ok(nuevoViaje);
        }
    }

    @PostMapping("/{viajeId}/reservar")
    public ResponseEntity<String> reservarViaje(@PathVariable String viajeId, @RequestParam String usuarioId) {
        Optional<Viaje> viajeOptional = viajeRepository.findById(viajeId);
        if (viajeOptional.isPresent()) {
            Viaje viaje = viajeOptional.get();
            if (viaje.getAsientosDisponibles() > 0) {
                viaje.setAsientosDisponibles(viaje.getAsientosDisponibles() - 1);
                viajeRepository.save(viaje);

                // Crear una reserva
                Reserva reserva = new Reserva(viajeId, usuarioId, LocalDateTime.now(), true);
                reservaRepository.save(reserva);

                return ResponseEntity.ok("Reserva realizada con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay asientos disponibles en este viaje.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado.");
        }
    }
}
