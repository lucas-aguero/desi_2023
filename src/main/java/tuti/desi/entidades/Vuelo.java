package tuti.desi.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import tuti.desi.entidades.enums.TipoVuelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_vuelo")
    private Long nroVuelo;
    @Column(name = "fecha_partida")
    @DateTimeFormat
    @NotNull
    private LocalDate fechaPartida;
    @Column(name = "hora_partida")
    @DateTimeFormat
    @NotNull
    private LocalTime horaPartida;
    private String avion;
    @Column(name = "nro_filas")
    private int nroFilas;
    @Column(name = "asiento_por_fila")
    private int asientosPorFila;
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
    @NotNull
    private BigDecimal precio;

    public void setNroAsientos(int nroFilas, int asientosPorFila) {
        this.nroAsientos = nroFilas * asientosPorFila;
    }

    @Autowired //fuerzo a spring que utilice este metodo durante la IOC
    public void setTipoVuelo() {
        String argentina = "AR";

        if(argentina.equalsIgnoreCase(this.destino.getCountry())) {
            this.tipoVuelo = TipoVuelo.NACIONAL;
        }
        else {
            this.tipoVuelo = TipoVuelo.INTERNACIONAL;
        }
    }

}
