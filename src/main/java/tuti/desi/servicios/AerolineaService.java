package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.dto.AerolineaDTO;
import tuti.desi.entidades.Aerolinea;

import java.util.List;
import java.util.Optional;

@Service
public interface AerolineaService {
    List<AerolineaDTO> getAerolineas();
    //Optional<AerolineaDTO> findByNombre();

}
