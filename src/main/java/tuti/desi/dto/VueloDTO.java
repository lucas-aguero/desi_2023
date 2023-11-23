package tuti.desi.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

public record VueloDTO(
        Long nroVuelo,
        String fechaPartida,
        LocalTime horaPartida,
        String avion,
        int nroAsientos,
        Long origenId,
        Long destinoId,
        String tipoVuelo,
        String estadoVuelo,
        BigDecimal precio

){}
