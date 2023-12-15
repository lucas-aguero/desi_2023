package tuti.desi.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tuti.desi.entidades.enums.EstadoVuelo;
import tuti.desi.entidades.enums.TipoVuelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vuelos")
public class Vuelo {
    @Id
    @GeneratedValue
    @Column(name = "nro_vuelo")
    private UUID nroVuelo;
    @Column(name = "fecha_partida")
    @DateTimeFormat
    @NotNull
    private LocalDate fechaPartida;
    @Column(name = "hora_partida")
    @DateTimeFormat
    @NotNull
    private LocalTime horaPartida;
    @ManyToOne
    @JoinColumn(name="aerolinea_id")
    private Aerolinea aerolinea;
    @ManyToOne
    @JoinColumn(name="aeronave_id")
    private Aeronave aeronave;
    @Column(name = "nro_asientos")
    private int nroAsientos;
    //Origen y destino
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="origen_id")
    private Aeropuerto origen;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="destino_id")
    private Aeropuerto destino;
    @Column(name="tipo_vuelo")
    private TipoVuelo tipoVuelo;
    @Column(name="estado_vuelo")
    private EstadoVuelo estadoVuelo;
    @NotNull
    @Column(name="precio_neto")
    private BigDecimal precioNeto;

}
