package tuti.desi.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.IDatosImpositivosRepo;
import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.excepciones.Excepcion;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DatosImpositivosServiceImpl implements DatosImpositivosService {

	@Autowired
	private IDatosImpositivosRepo datosImpositivosRepo;
	
	@Override
	public List<DatosImpositivos> getAll() {
		return datosImpositivosRepo.findAll();
	}
	
	@Override 
	public DatosImpositivos getById(Long id) {
		return datosImpositivosRepo.findById(id).orElse(null);
	}
	
	@Override
	public void save(DatosImpositivos datosImpositivos) throws Exception {
		//Validaciones
		validarDatosImpositivos(datosImpositivos);
		//Actualizar la fecha de modificación antes de guardar
		datosImpositivos.setFechaModificacion(LocalDateTime.now());
		
		datosImpositivosRepo.save(datosImpositivos);
	}
	
	private void validarDatosImpositivos(DatosImpositivos datosImpositivos) throws Excepcion {
        if (datosImpositivos.getIva() == null || datosImpositivos.getIva() < 0 || datosImpositivos.getIva() > 100) {
            throw new Excepcion("El campo IVA debe estar entre 0 y 100.");
        }
        
        // Agregar validación de que tiene que ser en pesos
        if (datosImpositivos.getTasaAeroportuariaNacional() == null || datosImpositivos.getTasaAeroportuariaNacional() < 0) {
            throw new Excepcion("El campo de Tasa Aeroportuaria Nacional no puede ser negativo y debe estar escrito en pesos");
        }
        
        // Agregar validación de que tiene que ser en usd 
        if (datosImpositivos.getTasaAeroportuariaInternacional() == null || datosImpositivos.getTasaAeroportuariaInternacional() < 0) {
            throw new Excepcion("El campo de Tasa Aeroportuaria Nacional no puede ser negativo y debe estar escrito en dólares");
        }
        
        // Agregar validación de que tiene que ser en pesos
        if (datosImpositivos.getCotizacionDolar() == null || datosImpositivos.getCotizacionDolar() < 0) {
        	throw new Excepcion("El campo de Cotización del dolar no puede ser negativo y debe estar escrito en pesos");
        }
    }
	
	
}
