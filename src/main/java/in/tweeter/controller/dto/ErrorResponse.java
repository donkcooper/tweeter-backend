package in.tweeter.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private Instant timestamp;
    private StackTraceElement[] traceElements;
}