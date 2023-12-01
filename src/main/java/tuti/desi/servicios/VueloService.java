package tuti.desi.servicios;

import tuti.desi.presentacion.form.NuevoVueloForm;
import tuti.desi.entidades.Vuelo;
import tuti.desi.excepciones.vueloexception.VueloNoCreadoException;

import java.util.List;

public interface VueloService {

    public NuevoVueloForm crearVuelo(NuevoVueloForm vueloDTO) throws VueloNoCreadoException;

    public NuevoVueloForm findById(Long nroVuelo);

    public List<Vuelo> getVuelos();

}
