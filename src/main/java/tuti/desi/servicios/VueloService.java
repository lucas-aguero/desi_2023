package tuti.desi.servicios;

import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.entidades.Vuelo;
import tuti.desi.excepciones.vueloexception.VueloNoCreadoException;

import java.util.List;
import java.util.UUID;

public interface VueloService {

    void crearVuelo(NuevoVueloForm vueloDTO);

    List<Vuelo> getVuelos();

    long contarAeropuertos();
	
	Vuelo getByNroVuelo(UUID nroVuelo);

}
