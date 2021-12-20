package in.tweeter.controller.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class PeopleRequest {
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Username is mandatory and should be unique")
    private String username;
}
