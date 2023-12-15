package tuti.desi.excepciones.aeropuertoexception;

import lombok.experimental.StandardException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No se ha encontrado el aeropuerto solicitado")
public class AeropuertoNoEncontradoException extends DataAccessException {
}
