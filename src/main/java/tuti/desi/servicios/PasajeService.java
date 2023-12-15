package tuti.desi.servicios;

import tuti.desi.entidades.Pasaje;
import java.util.List;
import java.util.UUID;

public interface PasajeService {

	List<Pasaje> getAll();
    
	List<Pasaje> getPasajeNroVuelo(UUID nroVuelo);

	void guardarPasaje(Pasaje pasajeNuevo);

}