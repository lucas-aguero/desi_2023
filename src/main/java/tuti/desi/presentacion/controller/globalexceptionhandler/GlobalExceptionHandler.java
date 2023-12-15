package tuti.desi.presentacion.controller.globalexceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLSyntaxErrorException;
import java.net.ConnectException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ConnectException.class, SQLSyntaxErrorException.class})
    public String handleException(Exception e, Model model) {
        model.addAttribute("globalError", "Ha ocurrido un error interno.<br>" +
                "Comuníquese con un administrador del sistema.");

        return "parent/error";
    }
}