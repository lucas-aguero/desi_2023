package tuti.desi.dto;



import tuti.desi.entidades.Aeropuerto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record NuevoVueloDTO(
        Long nroVuelo,
        LocalDate fechaPartida,
        LocalTime horaPartida,
        String avion,
        int nroAsientos,
        Aeropuerto origen,
        Aeropuerto destino,
        BigDecimal precio

) {}
