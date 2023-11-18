package tuti.desi.servicios;

import java.util.List;

import tuti.desi.entidades.DatosImpositivos;

public interface DatosImpositivosService {
	
	List<DatosImpositivos> getAll();
	
	DatosImpositivos getById(Long id);
	
	void save(DatosImpositivos datosImpositivos) throws Exception;

}
