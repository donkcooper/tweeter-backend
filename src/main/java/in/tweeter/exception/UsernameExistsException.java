package in.tweeter.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsernameExistsException extends RuntimeException {
    private final String message;
}