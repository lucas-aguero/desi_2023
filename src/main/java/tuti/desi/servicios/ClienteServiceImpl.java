package tuti.desi.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.IClienteRepo;
import tuti.desi.entidades.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private IClienteRepo clienteRepo;
	
	@Override
	public List<Cliente> getAll() {
		return clienteRepo.findAll();
	}
	
	@Override 
	public Cliente getById(Long id) {
		return clienteRepo.findById(id).orElse(null);
	}
	
	@Override
	public void save(Cliente cliente) throws Exception {
		clienteRepo.save(cliente);
	}	
	
}