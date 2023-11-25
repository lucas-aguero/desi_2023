package tuti.desi.entidades;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@NoArgsConstructor
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

    public Aeronave(String modelo, int nroFilas, int asientosPorFila){

     this.modelo = modelo;
     this.nroFilas = nroFilas;
     this.asientosPorFila = asientosPorFila;
     capacidad = nroFilas*asientosPorFila;

    }
}
