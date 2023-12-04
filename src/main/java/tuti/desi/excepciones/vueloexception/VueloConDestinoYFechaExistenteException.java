package tuti.desi.excepciones.vueloexception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Ya existe un vuelo programado con esa fecha, para ese destino")
public class VueloConDestinoYFechaExistenteException extends VueloPersistenceException {


}
