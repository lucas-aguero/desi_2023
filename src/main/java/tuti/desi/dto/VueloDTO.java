package tuti.desi.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

public record VueloDTO(
        UUID nroVuelo,
        String aerolinea,
        String aeronave,
        String fechaPartida,
        LocalTime horaPartida,
        int nroAsientos,
        Long origenId,
        Long destinoId,
        BigDecimal precio

){}
