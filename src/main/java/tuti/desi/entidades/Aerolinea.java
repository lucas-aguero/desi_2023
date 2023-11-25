package tuti.desi.entidades;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@Entity
@Table(name="aerolineas")
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    @OneToMany(mappedBy = "aerolinea")
    private @Singular("vueloAerolinea") List<Vuelo> vuelosAerolinea;

}
