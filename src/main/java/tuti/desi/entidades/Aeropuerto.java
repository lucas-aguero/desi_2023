package tuti.desi.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@Entity
@Table(name="aeropuertos")
public class Aeropuerto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "icao", unique = true)
    private String icao;
    private String iata;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    private long elevation;
    private double lat;
    private double lon;
    private String tz;
    @OneToMany(mappedBy = "origen")
    private @Singular List<Vuelo> vuelosOrigen;
    @OneToMany(mappedBy = "destino")
    private @Singular List<Vuelo> vuelosDestino;
}
