package tuti.desi.excepciones.errorinfo;

import lombok.Data;

import java.util.List;

@Data
public class ErrorInfo {
    private String message;
    private int statusCode;
    private long timestamp;
    private List<String> errors;
}
