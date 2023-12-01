package tuti.desi.servicios;

import org.springframework.stereotype.Service;
import tuti.desi.dto.AeropuertoDTO;
import tuti.desi.excepciones.vueloexception.VueloNoEncontradoException;

import java.util.TreeSet;

@Service
public interface IAeropuertoService {
    AeropuertoDTO getAeropuertoLocal() throws VueloNoEncontradoException;

    TreeSet<AeropuertoDTO> getAllAeropuertos();

    public TreeSet<AeropuertoDTO> getAeropuertosAleatorios();

    TreeSet<AeropuertoDTO> getAeropuertosArgentinosAleatorios();

    TreeSet<AeropuertoDTO> getAllAeropuertosArgentinos();

    TreeSet<AeropuertoDTO> getAeropuertosExtranjerosAleatorios();

    TreeSet<AeropuertoDTO> getAeropuertosUsaAleatorios();

    public void loadAirportsFromJsonFile();
}
