package tuti.desi.dto;



import org.springframework.http.ResponseEntity;
import tuti.desi.entidades.Aeropuerto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record VueloDTO(
        Long nroVuelo,
        LocalDate fechaPartida,
        LocalTime horaPartida,
        String avion,
        int nroAsientos,
        Aeropuerto origen,
        Aeropuerto destino,
        BigDecimal precio

){}
