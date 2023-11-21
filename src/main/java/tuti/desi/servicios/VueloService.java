package tuti.desi.servicios;

import tuti.desi.accesoDatos.IVueloRepo;
import tuti.desi.presentacion.form.CrearVueloForm;

public interface VueloService {

    public void crearVuelo(CrearVueloForm vueloForm);

}
