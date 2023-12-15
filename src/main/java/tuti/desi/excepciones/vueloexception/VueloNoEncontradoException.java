package tuti.desi.excepciones.vueloexception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No se ha encontrado el vuelo solicitado")
public class VueloNoEncontradoException extends VueloPersistenceException {
}
