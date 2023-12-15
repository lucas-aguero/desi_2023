package tuti.desi.excepciones.vueloexception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Error Interno, No se pudo crear la aeronave. " +
        "Comuníquese con un administrador del sistema.")
public class VueloNoCreadoException extends VueloPersistenceException {

}
