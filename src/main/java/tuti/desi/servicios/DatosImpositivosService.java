package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.excepciones.Excepcion;

public interface DatosImpositivosService {
	
	List<DatosImpositivos> getAll();
	
	DatosImpositivos getById(Optional<Long> id);
	
	void save(DatosImpositivos datosImpositivos) throws Excepcion;
	
	void saveOrUpdate(DatosImpositivos datosImpositivos) throws Excepcion;

}
