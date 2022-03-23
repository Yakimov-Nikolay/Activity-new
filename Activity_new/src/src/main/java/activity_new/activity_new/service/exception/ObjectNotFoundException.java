package activity_new.activity_new.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends Throwable {
    public ObjectNotFoundException(String message) {
        super(message);
    }

}
