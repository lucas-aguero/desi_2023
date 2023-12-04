//package tuti.desi.presentacion.controller.globalexceptionhandler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import tuti.desi.excepciones.vueloexception.VueloConDestinoYFechaExistenteException;
//import tuti.desi.excepciones.vueloexception.VueloNoCreadoException;
//import tuti.desi.excepciones.errorinfo.ErrorInfo;
//import tuti.desi.excepciones.vueloexception.VueloNoEncontradoException;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestControllerAdvice
//public class VueloGlobalExceptionHandler {
//
//    @ExceptionHandler(VueloNoCreadoException.class)
//    public ResponseEntity<Object> handleVueloNoCreadoException(VueloNoCreadoException e){
//
//        var errorInfo = new ErrorInfo();
//        errorInfo.setMessage(e.getMessage());
//        errorInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        errorInfo.setTimestamp(new Date().getTime());
//
//        return new ResponseEntity<>(errorInfo,
//                HttpStatus.valueOf(errorInfo.getStatusCode()));
//    }
//
//    @ExceptionHandler(VueloConDestinoYFechaExistenteException.class)
//    public ResponseEntity<Object> handleVueloConDestinoYFechaExistenteException(VueloConDestinoYFechaExistenteException e){
//
//        var errorInfo = new ErrorInfo();
//        errorInfo.setMessage(e.getMessage());
//        errorInfo.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        errorInfo.setTimestamp(new Date().getTime());
//
//        return new ResponseEntity<>(errorInfo,
//                HttpStatus.valueOf(errorInfo.getStatusCode()));
//
//
//    }
//
//    @ExceptionHandler(VueloNoEncontradoException.class)
//    public ResponseEntity<Object> handleVueloNoEncontradoException(VueloNoEncontradoException e){
//
//        var errorInfo = new ErrorInfo();
//        errorInfo.setMessage(e.getMessage());
//        errorInfo.setStatusCode(HttpStatus.NOT_FOUND.value());
//        errorInfo.setTimestamp(new Date().getTime());
//
//        return new ResponseEntity<>(errorInfo,
//                HttpStatus.valueOf(errorInfo.getStatusCode()));
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleValidationExcepton(MethodArgumentNotValidException e){
//        var errorInfo = new ErrorInfo();
//        errorInfo.setMessage("Error de validación en el formulario");
//        errorInfo.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        errorInfo.setTimestamp(new Date().getTime());
//
//        List<String> errors = e.getBindingResult().getFieldErrors().stream()
//                .map(this::buildValidationError)
//                .collect(Collectors.toList());
//
//        errorInfo.setErrors(errors);
//
//        return new ResponseEntity<>(errorInfo, HttpStatus.valueOf(errorInfo.getStatusCode()));
//    }
//
//    private String buildValidationError(FieldError fieldError) {
//        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
//    }
//    }
//}
