//package tuti.desi.presentacion.controller.globalexceptionhandler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.validation.FieldError;
//import org.springframework.web.servlet.ModelAndView;
//import tuti.desi.excepciones.errorinfo.ErrorInfo;
//import tuti.desi.excepciones.vueloexception.VueloConDestinoYFechaExistenteException;
//import tuti.desi.excepciones.vueloexception.VueloNoCreadoException;
//import tuti.desi.excepciones.vueloexception.VueloNoEncontradoException;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(VueloNoCreadoException.class)
//    public ModelAndView handleVueloNoCreadoException(VueloNoCreadoException e) {
//        return getModelAndView(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(VueloConDestinoYFechaExistenteException.class)
//    public ModelAndView handleVueloConDestinoYFechaExistenteException(VueloConDestinoYFechaExistenteException e) {
//        return getModelAndView(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(VueloNoEncontradoException.class)
//    public ModelAndView handleVueloNoEncontradoException(VueloNoEncontradoException e) {
//        return getModelAndView(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    private ModelAndView getModelAndView(String message, HttpStatus status) {
//        ErrorInfo errorInfo = new ErrorInfo();
//        errorInfo.setMessage(message);
//        errorInfo.setStatusCode(status.value());
//        errorInfo.setTimestamp(new Date().getTime());
//
//        ModelAndView modelAndView = new ModelAndView("crearVuelo"); // Vista de edición
//        modelAndView.addObject("errorInfo", errorInfo);
//        return modelAndView;
//    }
//
//    private ModelAndView getModelAndView(String message, HttpStatus status, BindingResult bindingResult) {
//        ErrorInfo errorInfo = new ErrorInfo();
//        errorInfo.setMessage(message);
//        errorInfo.setStatusCode(status.value());
//        errorInfo.setTimestamp(new Date().getTime());
//
//        List<String> fieldErrors = bindingResult.getFieldErrors()
//                .stream()
//                .map(FieldError::getDefaultMessage)
//                .collect(Collectors.toList());
//
//        errorInfo.setErrors(fieldErrors);
//
//        ModelAndView modelAndView = new ModelAndView("crearVuelo"); // Vista de edición
//        modelAndView.addObject("errorInfo", errorInfo);
//        return modelAndView;
//    }
//
//
//}



