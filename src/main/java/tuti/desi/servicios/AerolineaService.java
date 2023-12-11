package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.dto.AerolineaDTO;

import java.util.TreeSet;

@Service
public interface AerolineaService {
    TreeSet<AerolineaDTO> getAerolineas();

    long contarAerolineas();
    //Optional<AerolineaDTO> findByNombre();

}
