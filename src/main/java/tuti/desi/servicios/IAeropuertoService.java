package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.excepciones.VueloNoEncontradoException;

import java.util.List;

@Service
public interface IAeropuertoService {
    AeropuertoDTO getAeropuertoLocal() throws VueloNoEncontradoException;

    List<AeropuertoDTO> getAllAeropuertos();

    public List<AeropuertoDTO> getAeropuertosAleatorios();

    List<AeropuertoDTO> getAeropuertosArgentinosAleatorios();

    List<AeropuertoDTO> getAllAeropuertosArgentinos();

    List<AeropuertoDTO> getAeropuertosExtranjerosAleatorios();

    List<AeropuertoDTO> getAeropuertosUsaAleatorios();

    public void loadAirportsFromJsonFile();
}
