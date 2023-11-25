package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aeropuerto;

import java.util.Optional;

@Repository
public interface IAeropuertoRepo extends JpaRepository<Aeropuerto, Long> {

    Optional<Aeropuerto> findByName(String name);

}
