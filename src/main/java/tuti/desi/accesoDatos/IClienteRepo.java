package tuti.desi.accesoDatos;


import org.springframework.data.jpa.repository.JpaRepository;

import tuti.desi.entidades.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Long> {
	
}
