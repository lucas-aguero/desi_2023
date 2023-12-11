package tuti.desi.excepciones.vueloexception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import tuti.desi.excepciones.vueloexception.VueloPersistenceException;

@StandardException
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="El vuelo ya existe para ese origen y fecha")
public class VueloConOrigenYFechaExistenteException extends VueloPersistenceException {

}
