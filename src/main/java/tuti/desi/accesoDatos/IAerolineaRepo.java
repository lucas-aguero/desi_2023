package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aerolinea;
import tuti.desi.entidades.Aeropuerto;

import java.util.Optional;

@Repository
public interface IAerolineaRepo extends JpaRepository<Aerolinea, Long> {

    Optional<Aeropuerto> findByNombre(String nombre);

}
