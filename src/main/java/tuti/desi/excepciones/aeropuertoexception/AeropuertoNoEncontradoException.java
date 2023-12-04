package tuti.desi.excepciones.aeropuertoexception;

import lombok.experimental.StandardException;
import org.springframework.dao.DataAccessException;

@StandardException
public class AeropuertoNoEncontradoException extends DataAccessException {
}
