package tuti.desi.entidades;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@Entity
@Table(name="aeronaves")
public class Aeronave {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String modelo;
    @Column(name="nro_filas")
    private int nroFilas;
    @Column(name="asientos_por_filas")
    private int asientosPorFila;
    private int capacidad;
    @OneToMany(mappedBy = "aeronave")
    private @Singular("vueloAeronave") List<Vuelo> vuelosAeronave;
}
