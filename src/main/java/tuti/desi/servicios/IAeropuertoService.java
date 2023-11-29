package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.dto.AeropuertoDTO;

import java.util.List;

@Service
public interface IAeropuertoService {
    public List<AeropuertoDTO> getAeropuertosAleatorios();
    public void loadAirportsFromJsonFile();
}
