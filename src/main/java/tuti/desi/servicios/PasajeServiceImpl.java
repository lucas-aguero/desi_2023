package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.entidades.Pasaje;
import tuti.desi.entidades.Vuelo;
import tuti.desi.accesoDatos.IClienteRepo;
import tuti.desi.accesoDatos.IPasajeRepo;
import tuti.desi.accesoDatos.IVueloRepo;

import java.util.List;
import java.util.UUID;

@Service
public class PasajeServiceImpl implements PasajeService {

    private final IVueloRepo vueloRepo;
    private final IPasajeRepo pasajeRepo;

    public PasajeServiceImpl(IClienteRepo clienteRepo, IVueloRepo vueloRepo, IPasajeRepo pasajeRepo) {
        this.vueloRepo = vueloRepo;
		this.pasajeRepo = pasajeRepo;
    }

	@Override
	public List<Pasaje> getAll() {
		return pasajeRepo.findAll();
	} 
 
    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloRepo.findAll();
    }
    
	public List<Pasaje> getPasajeNroVuelo(UUID nroVuelo) {
		
		return pasajeRepo.findByVuelo_NroVuelo(nroVuelo) ;
	}

	@Override
	public void guardarPasaje(Pasaje pasajeNuevo) {
		pasajeRepo.save(pasajeNuevo);
	}


}