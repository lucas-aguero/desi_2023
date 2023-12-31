package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aeropuerto;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAeropuertoRepo extends JpaRepository<Aeropuerto, Long> {

    @Query("SELECT COUNT(a) > 0 FROM Aeropuerto a WHERE a.id = :id " +
            "AND a.country = 'AR' ")
    boolean existsByIdAndCountryEqualsAR(@Param("id") Long id);
    boolean existsByIcao(String icao);
    Optional<Aeropuerto> findByName(String name);
    Optional<Aeropuerto> findByIcao(String icao);
    Optional<Aeropuerto> findByIata(String iata);
    @Query("SELECT COUNT(a) FROM Aeropuerto a")
    int contarAeropuertos();

    Optional<Aeropuerto> findTopByOrderByIdAsc();

    Optional<Aeropuerto> findTopByOrderByIdDesc();

    @Query(value = "SELECT * FROM aeropuertos a " +
            "WHERE a.icao <> 'SAAV' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Aeropuerto> findRandomAeropuerto();

    @Query(value = "SELECT * FROM aeropuertos a " +
            "WHERE a.icao <> 'SAAV' AND a.country = 'AR' " +
            "ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Aeropuerto> findRandomAeropuertoArgentino();

    @Query(value = "SELECT * FROM aeropuertos a " +
            "WHERE a.icao <> 'SAAV' AND a.country <> 'AR' " +
            "ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Aeropuerto> findRandomAeropuertoExtranjero();

    @Query(value = "SELECT a FROM Aeropuerto a WHERE a.icao = 'SAAV'")
    Optional<Aeropuerto> findAeropuertoLocal();

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
            "ORDER BY RAND() LIMIT 200")
    List<Aeropuerto> getAeropuertosExtranjerosAleatorios();

    @Query("SELECT a FROM Aeropuerto a WHERE a.country = 'US' " +
            "AND a.city <> '' " +
            "ORDER BY RAND() LIMIT 100")
    List<Aeropuerto> getAeropuertosUsaAleatorios();




}
