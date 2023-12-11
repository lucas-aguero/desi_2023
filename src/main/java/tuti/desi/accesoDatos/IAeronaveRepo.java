package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aerolinea;
import tuti.desi.entidades.Aeronave;

import java.util.Optional;

@Repository
public interface IAeronaveRepo extends JpaRepository<Aeronave, Long> {

    Optional<Aeronave> findByModelo(String modelo);
    boolean existsByModelo(String modelo);

    @Query("SELECT a.capacidad FROM Aeronave a WHERE a.id = :id")
    int getNroAsientos(@Param("id") Long id);

    @Query(value = "SELECT * FROM aeronaves ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    Optional<Aeronave> findRandomAeronave();
}
