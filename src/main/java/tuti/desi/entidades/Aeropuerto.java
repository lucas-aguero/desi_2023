package tuti.desi.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="aeropuertos")
public class Aeropuerto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
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
    private @Singular("vueloOrigen") List<Vuelo> vuelosOrigen;
    @OneToMany(mappedBy = "destino")
    private @Singular ("vueloDestino") List<Vuelo> vuelosDestino;
}
