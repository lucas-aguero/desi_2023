package tuti.desi.excepciones.aeropuertoexception;

import lombok.experimental.StandardException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Error Interno, No se pudieron crear los registros. " +
        "Comuníquese con un administrador del sistema.")
public class AeropuertoPersistenceException extends DataAccessException {
}
