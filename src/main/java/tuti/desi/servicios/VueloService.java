package tuti.desi.servicios;

import tuti.desi.presentacion.form.VueloForm;
import tuti.desi.excepciones.VueloNoCreadoException;

public interface VueloService {

    public VueloForm crearVuelo(VueloForm vueloDTO) throws VueloNoCreadoException;

    public VueloForm findById(Long nroVuelo);
}
