package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Aeropuerto;

@Repository
public interface IAeropuertoRepo extends JpaRepository<Aeropuerto, String> {
    

}
