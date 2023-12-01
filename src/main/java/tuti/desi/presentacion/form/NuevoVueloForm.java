package tuti.desi.presentacion.form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class NuevoVueloForm{
    @NotNull(message = "Debe elegir una aerolínea")
    Long aerolineaId;
    @NotNull(message = "Debe elegir una aeronave")
    Long aeronaveId;
    @NotEmpty
    @FutureOrPresent(message="Debe elegir una fecha de hoy en adelante")
    String fechaPartida;
    @NotEmpty@NotNull(message = "Debe elegir la hora de partida")
    String horaPartida;
    @NotNull
    int nroAsientos;
    @NotNull@NotNull(message = "Debe elegir un origen")
    Long origenId;
    @NotNull@NotNull(message = "Debe elegir una destino")
    Long destinoId;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio debe ser mayor que 0.0")
    BigDecimal precio;
}
