package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aerolinea;

import java.util.Optional;

@Repository
public interface IAerolineaRepo extends JpaRepository<Aerolinea, Long> {

    Optional<Aerolinea> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

    @Query(value = "SELECT * FROM aerolineas ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    Optional<Aerolinea> findRandomAerolinea();

}
