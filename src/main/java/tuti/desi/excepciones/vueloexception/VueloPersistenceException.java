package tuti.desi.excepciones.vueloexception;

import lombok.experimental.StandardException;
import org.springframework.dao.DataAccessException;
@StandardException
public class VueloPersistenceException extends DataAccessException {
}
