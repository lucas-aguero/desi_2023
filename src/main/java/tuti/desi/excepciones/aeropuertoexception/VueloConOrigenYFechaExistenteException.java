package tuti.desi.excepciones.aeropuertoexception;

import lombok.experimental.StandardException;
import tuti.desi.excepciones.vueloexception.VueloPersistenceException;

@StandardException
public class VueloConOrigenYFechaExistenteException extends VueloPersistenceException {

}
