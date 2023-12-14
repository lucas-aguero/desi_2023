package tuti.desi.accesoDatos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.enums.EstadoVuelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVueloRepo extends JpaRepository<Vuelo, Long> {
    boolean existsByDestinoIdAndFechaPartida(Long destinoId, LocalDate fechaPartida);
    boolean existsByOrigenIdAndFechaPartida(Long destinoId, LocalDate fechaPartida);

    //BUSCAR POR FECHA PARTIDA
    Optional<Vuelo> findByFechaPartida(LocalDate fechaPartida);
    Optional<Vuelo> findByFechaPartidaOrDestinoIdOrOrigenIdOrEstadoVuelo(LocalDate fechaPartida,
                                                            Long destinoId,
                                                             Long origenId,
                                                             EstadoVuelo estado);
}
