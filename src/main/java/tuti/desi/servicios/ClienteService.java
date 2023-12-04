package tuti.desi.servicios;

import java.util.List;

import tuti.desi.entidades.Cliente;

public interface ClienteService {
	List<Cliente> getAll();
	
	Cliente getById(Long id);
	
	void save(Cliente cliente) throws Exception;
}



