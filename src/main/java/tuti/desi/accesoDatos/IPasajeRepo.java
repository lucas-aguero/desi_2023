package tuti.desi.accesoDatos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import tuti.desi.entidades.Pasaje;

public interface IPasajeRepo extends JpaRepository<Pasaje, Long> {

	List<Pasaje> findByVuelo_NroVuelo(UUID nroVuelo);
	
}