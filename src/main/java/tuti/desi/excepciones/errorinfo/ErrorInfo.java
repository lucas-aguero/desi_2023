package tuti.desi.excepciones.errorinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    private String message;
    private int statusCode;
    private long timestamp;
    private List<String> errors;
}
