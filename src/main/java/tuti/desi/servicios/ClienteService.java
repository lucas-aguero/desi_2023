package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import tuti.desi.entidades.Cliente;

public interface ClienteService {

	List<Cliente> getAll();
	
	Optional<Cliente> getById(Long id);
	
    Cliente buscarPorCorreoElectronico(String correoElectronico);

}