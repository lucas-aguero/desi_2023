package tuti.desi.presentacion.form;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class NuevoVueloForm{
    @NotNull
    Long aerolineaId;
    @NotNull
    Long aeronaveId;
    @NotNull
    String fechaPartida;
    @NotNull
    //Cambiar por string
    LocalTime horaPartida;
    @NotNull
    int nroAsientos;
    @NotNull
    Long origenId;
    @NotNull
    Long destinoId;
    @NotNull
    BigDecimal precio;

}
