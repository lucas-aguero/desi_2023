package tuti.desi.accesoDatos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Vuelo;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVueloRepo extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByFechaPartida(LocalDate fechaPartida);

}
