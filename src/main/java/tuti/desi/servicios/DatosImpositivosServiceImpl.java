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
	public void save(DatosImpositivos datosImpositivos) throws Excepcion {
		//Actualizar la fecha de modificación antes de guardar
		datosImpositivos.setFechaModificacion(LocalDateTime.now());
		datosImpositivosRepo.save(datosImpositivos);
	}

}
