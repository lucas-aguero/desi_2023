package tuti.desi.servicios;

import tuti.desi.dto.VueloDTO;

public interface VueloService {

    public VueloDTO crearVuelo(VueloDTO vueloDTO);

    public VueloDTO findById(Long nroVuelo);
}
