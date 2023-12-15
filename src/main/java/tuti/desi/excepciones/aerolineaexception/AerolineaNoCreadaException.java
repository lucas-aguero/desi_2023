package tuti.desi.excepciones.aerolineaexception;

import lombok.experimental.StandardException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Ha ocurrido un error interno y no se ha podido crear la aerolínea")
public class AerolineaNoCreadaException extends DataAccessException {
}
