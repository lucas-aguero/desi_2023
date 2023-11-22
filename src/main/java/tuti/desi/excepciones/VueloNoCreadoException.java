package tuti.desi.excepciones;

import lombok.experimental.StandardException;
import org.springframework.dao.DataAccessException;

@StandardException
public class VueloNoCreadoException extends DataAccessException {

}
