package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aeropuerto;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAeropuertoRepo extends JpaRepository<Aeropuerto, Long> {

    boolean existsByIcao(String icao);
    Optional<Aeropuerto> findByName(String name);
    Optional<Aeropuerto> findByIcao(String icao);
    Optional<Aeropuerto> findByIata(String iata);

    @Query("SELECT a FROM Aeropuerto a WHERE a.country = 'AR' " +
            "AND a.city <>''")
    List<Aeropuerto> findAllAeropuertosArgentinos();

    @Query("SELECT a FROM Aeropuerto a WHERE a.country <> 'AR' " +
            "AND a.city <>''")
    List<Aeropuerto> findAllAeropuertosExtranjeros();

    @Query("SELECT a FROM Aeropuerto a WHERE a.city <>''")
    List<Aeropuerto> findAllAeropuertos();

    @Query("SELECT a FROM Aeropuerto a WHERE a.country = 'AR' " +
            "AND a.city <> '' " +
            "ORDER BY RAND() LIMIT 100")
    List<Aeropuerto> getAeropuertosArgentinosAleatorios();

    @Query("SELECT a FROM Aeropuerto a WHERE a.country <> 'AR' " +
            "AND a.country <> 'US' " +
            "AND a.city <> ''" +
            "ORDER BY RAND() LIMIT 100")
    List<Aeropuerto> getAeropuertosExtranjerosAleatorios();

    @Query("SELECT a FROM Aeropuerto a WHERE a.country = 'US' " +
            "AND a.city <> '' " +
            "ORDER BY RAND() LIMIT 50")
    List<Aeropuerto> getAeropuertosUsaAleatorios();




}
