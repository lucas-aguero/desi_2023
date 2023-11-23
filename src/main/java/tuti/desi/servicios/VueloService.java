package tuti.desi.servicios;

import tuti.desi.dto.VueloDTO;
import tuti.desi.excepciones.VueloNoCreadoException;

public interface VueloService {

    public VueloDTO crearVuelo(VueloDTO vueloDTO) throws VueloNoCreadoException;

    public VueloDTO findById(Long nroVuelo);
}
