package tuti.desi.presentacion.form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Validated
public class NuevoVueloForm{
    @NotNull(message = "Debe elegir una aerolínea")
    Long aerolineaId;
    @NotNull(message = "Debe elegir una aeronave")
    Long aeronaveId;
    @NotNull(message="Debe ingresar una fecha a partir de hoy en adelante")
    @FutureOrPresent(message="Debe ingresar una fecha a partir de hoy en adelante")
    LocalDate fechaPartida;
    @NotNull(message = "Debe elegir la hora de partida")
    LocalTime horaPartida;
    @NotNull
    int nroAsientos;
    @NotNull(message = "Debe elegir un origen")
    Long origenId;
    @NotNull(message = "Debe elegir un destino")
    Long destinoId;
    @NotNull(message = "Debe ingresar un precio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio debe ser mayor que 0.0")
    BigDecimal precioNeto;
}
