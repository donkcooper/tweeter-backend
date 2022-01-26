package in.tweeter.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConfigRequest {
    private String key;
    private String action;
}