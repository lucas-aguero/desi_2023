package tuti.desi.servicios;

import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.entidades.Vuelo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VueloService {

    void crearVuelo(NuevoVueloForm vueloDTO);

    List<Vuelo> getVuelos();

    List<Vuelo> filtrarbyFechaVuelo(LocalDate fecha);


    long contarAeropuertos();
	
	Vuelo getByNroVuelo(UUID nroVuelo);

}
