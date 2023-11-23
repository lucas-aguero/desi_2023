package tuti.desi.servicios;


import java.util.List;

import tuti.desi.entidades.Provincia;
/**
 * Clase que permite gestionar la entidad Provincia en el sistema.
 * @author kuttel
 * @version 1.0
 */

public interface ProvinciaService {

	

	/**
	 * Obtiene la lista completa de Provincias
	 * @return Todas las Provincias
	 */
	@Deprecated
	List<Provincia> getAll();
	
	/**
	 * Obtiene una Provincia determinada
	 * @param idCiudad Identificador de la Provincia buscada
	 * @return Provincia encontrada
	 */
	Provincia getById(Long idProv) ;
}
