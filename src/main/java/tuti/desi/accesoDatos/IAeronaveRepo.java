package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aeronave;

import java.util.Optional;

@Repository
public interface IAeronaveRepo extends JpaRepository<Aeronave, Long> {

    Optional<Aeronave> findByModelo(String modelo);
    boolean existsByModelo(String modelo);
}
