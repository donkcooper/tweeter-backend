package in.tweeter.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FeedRequest {
    private String message;
    private String location;
    private long peopleId;
}