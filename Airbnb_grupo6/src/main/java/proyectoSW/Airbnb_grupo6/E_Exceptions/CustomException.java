package proyectoSW.Airbnb_grupo6.E_Exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Data
public class CustomException extends RuntimeException {

    private final HttpStatus status;

    public CustomException(final HttpStatus httpStatus, final String message) {
        super(message);
        this.status = httpStatus;
    }
    public HttpStatus getHttpStatus(){
        return status;
    }
}