package in.tweeter.exception.handler;

import in.tweeter.controller.dto.ErrorResponse;
import in.tweeter.exception.InvalidLoginException;
import in.tweeter.exception.UsernameExistsException;
import in.tweeter.exception.EmptyCheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class TweeterErrorHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleError(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .traceElements(ex.getStackTrace())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmptyCheckException.class)
    public final ResponseEntity<ErrorResponse> handleValidateError(EmptyCheckException exp) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exp.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsernameExistsException.class, InvalidLoginException.class})
    public final ResponseEntity<ErrorResponse> handleExistingUserError(Exception exp) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exp.getMessage())
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.FORBIDDEN);
    }
}