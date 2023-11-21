package tuti.desi.accesoDatos;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tuti.desi.entidades.DatosImpositivos;

public interface IDatosImpositivosRepo extends JpaRepository<DatosImpositivos, Long> {
	@Modifying
	@Query (value= "UPDATE datos_impositivos d SET d.cotizacion_dolar = :cotizacion_dolar, d.fecha_modificacion = :fecha_modificacion, d.iva = :iva, d.tasa_aeroportuaria_internacional = :tasa_aeroportuaria_internacional, d.tasa_aeroportuaria_nacional = :tasa_aeroportuaria_nacional", nativeQuery= true)
	public void updateDatosImpositivos(@Param ("cotizacion_dolar") double cotizacion_dolar, @Param ("fecha_modificacion") LocalDateTime fecha_modificacion, @Param ("iva") int iva, @Param ("tasa_aeroportuaria_internacional") double tasa_aeroportuaria_internacional, @Param ("tasa_aeroportuaria_nacional") double tasa_aeroportuaria_nacional );
}
