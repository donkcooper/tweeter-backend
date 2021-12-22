package in.tweeter.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class LoginRequest {
    @NotNull(message = "Username is mandatory and cannot be blank")
    private String username;
}