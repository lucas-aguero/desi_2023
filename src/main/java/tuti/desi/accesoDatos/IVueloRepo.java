package tuti.desi.accesoDatos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.enums.EstadoVuelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IVueloRepo extends JpaRepository<Vuelo, UUID> {
    boolean existsByDestinoIdAndFechaPartida(Long destinoId, LocalDate fechaPartida);
    boolean existsByOrigenIdAndFechaPartida(Long destinoId, LocalDate fechaPartida);
    Vuelo findByNroVuelo(UUID nroVuelo);

    //BUSCAR POR FECHA PARTIDA
    List<Vuelo> findByFechaPartida(LocalDate fechaPartida);
    Optional<Vuelo> findByFechaPartidaOrDestinoIdOrOrigenIdOrEstadoVuelo(LocalDate fechaPartida,
                                                            Long destinoId,
                                                             Long origenId,
                                                             EstadoVuelo estado);
}
