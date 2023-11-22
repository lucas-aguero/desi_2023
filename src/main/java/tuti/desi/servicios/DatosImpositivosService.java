package tuti.desi.servicios;

import java.util.List;
import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.excepciones.Excepcion;

public interface DatosImpositivosService {
	
	List<DatosImpositivos> getAll();
	
	void save(DatosImpositivos datosImpositivos) throws Excepcion;

}
