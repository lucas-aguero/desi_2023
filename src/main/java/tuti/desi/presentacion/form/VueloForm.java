package tuti.desi.presentacion.form;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

public record VueloForm(
        UUID nroVuelo,
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
