package tuti.desi.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IDatosImpositivosRepo;
import tuti.desi.entidades.DatosImpositivos;
import tuti.desi.excepciones.Excepcion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class DatosImpositivosServiceImpl implements DatosImpositivosService {

	@Autowired
	private IDatosImpositivosRepo datosImpositivosRepo;
	
	@Override
	public List<DatosImpositivos> getAll() {
		return datosImpositivosRepo.findAll();
	}
	
	@Override 
	public DatosImpositivos getById(Optional<Long> id) {
		return datosImpositivosRepo.findById(id.orElse(null)).orElse(null);
	}

	@Override
	public void save(DatosImpositivos datosImpositivos) throws Excepcion {
		//Actualizar la fecha de modificación antes de guardar
		datosImpositivos.setFechaModificacion(LocalDateTime.now());
		validarDatosImpositivos(datosImpositivos);
		
		datosImpositivosRepo.save(datosImpositivos);
	}
	
	private void validarDatosImpositivos(DatosImpositivos datosImpositivos) throws Excepcion {
		Integer iva = datosImpositivos.getIva();
        if ( iva == null || datosImpositivos.getIva() < 0 || datosImpositivos.getIva() > 100) {
            throw new Excepcion("El campo IVA debe estar entre 0 y 100. Recuerda que es un porcentaje.");
        }
        
        if (datosImpositivos.getTasaAeroportuariaNacional() == null || datosImpositivos.getTasaAeroportuariaNacional() < 0 || !validarDecimales( datosImpositivos.getTasaAeroportuariaNacional(), 2)) {
            throw new Excepcion("El campo de Tasa Aeroportuaria Nacional no puede ser negativo y cómo máximo debe tener dos decimales. Debe estar en pesos.");
        }
        
        if (datosImpositivos.getTasaAeroportuariaInternacional() == null || datosImpositivos.getTasaAeroportuariaInternacional() < 0 || !validarDecimales(datosImpositivos.getTasaAeroportuariaInternacional(), 2)) {
            throw new Excepcion("El campo de Tasa Aeroportuaria Internacional no puede ser negativo y cómo máximo debe tener dos decimales. Debe estar en dólares.");
        }
        
        if (datosImpositivos.getCotizacionDolar() == null || datosImpositivos.getCotizacionDolar() < 0 || !validarDecimales( datosImpositivos.getCotizacionDolar(), 2)) {
        	throw new Excepcion("El campo de Cotización del dolar no puede ser negativo y cómo máximo debe tener dos decimales. Debe estar en pesos.");
        }
    }
	
	@Override
	public void saveOrUpdate(DatosImpositivos datosImpositivos) throws Excepcion {
		
		if (datosImpositivosRepo.findAll() != null) {
			validarDatosImpositivos(datosImpositivos);
			//Actualizar la fecha de modificación antes de guardar
			datosImpositivos.setFechaModificacion(LocalDateTime.now());
			datosImpositivosRepo.updateDatosImpositivos(datosImpositivos.getCotizacionDolar(), datosImpositivos.getFechaModificacion(),datosImpositivos.getIva() , datosImpositivos.getTasaAeroportuariaInternacional(), datosImpositivos.getTasaAeroportuariaNacional());
		} else {
			save(datosImpositivos);
		}
	}
	
    private boolean validarDecimales(Double valor, int maxDecimales) throws Excepcion {
        if (valor != null) {
            String valorComoString = String.valueOf(valor);
            Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1," + maxDecimales + "})?$");
            java.util.regex.Matcher matcher = pattern.matcher(valorComoString);

            if (!matcher.matches()) {
                return false;
            }
            return true; 
        }
        throw new Excepcion("Todos los campos deben tener un valor válido mayor a 0.");
    }

}
