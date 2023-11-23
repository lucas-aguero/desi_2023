package tuti.desi.excepciones.errorinfo.handler;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tuti.desi.excepciones.VueloNoCreadoException;
import tuti.desi.excepciones.errorinfo.ErrorInfo;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VueloNoCreadoException.class)
    public ResponseEntity<Object> handleVueloNoCreadoException(VueloNoCreadoException e){

        var errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setTimestamp(new Date().getTime());

        return new ResponseEntity<>(errorInfo,
                HttpStatus.valueOf(errorInfo.getStatusCode()));
    }
}
