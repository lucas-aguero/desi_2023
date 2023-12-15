package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.entidades.Cliente;
import tuti.desi.accesoDatos.IClienteRepo;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private IClienteRepo clienteRepo;
    
    @Override
	public List<Cliente> getAll() {
		return clienteRepo.findAll();
	}

    @Override
    public Cliente buscarPorCorreoElectronico(String correoElectronico) {
        return clienteRepo.findByCorreoElectronico(correoElectronico);
    }

	@Override
	public Optional<Cliente> getById(Long id) {
		return clienteRepo.findById(id);
	}
}