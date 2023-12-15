package tuti.desi.accesoDatos;


import tuti.desi.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IClienteRepo extends JpaRepository<Cliente, Long> {
    Cliente findByCorreoElectronico(String correoElectronico);
}