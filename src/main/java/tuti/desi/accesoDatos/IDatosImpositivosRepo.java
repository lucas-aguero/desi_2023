package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;

import tuti.desi.entidades.DatosImpositivos;

public interface IDatosImpositivosRepo extends JpaRepository<DatosImpositivos, Long> {

}
