package tuti.desi.excepciones.vueloexception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="El vuelo no puede tener mismo origen y destino")
public class VueloConOrigenYDestinoDuplicados extends VueloPersistenceException{
}
