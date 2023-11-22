package tuti.desi.servicios;

import org.springframework.stereotype.Service;

@Service
public interface IAeropuertoService {
    public void loadAirportsFromJsonFile(String jsonFilePath);
}
