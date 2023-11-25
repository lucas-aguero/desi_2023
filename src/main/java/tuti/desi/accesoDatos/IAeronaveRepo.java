package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aeronave;
import tuti.desi.entidades.Aeropuerto;

import java.util.Optional;

@Repository
public interface IAeronaveRepo extends JpaRepository<Aeronave, Long> {

    Optional<Aeropuerto> findByModelo(String modelo);

}
