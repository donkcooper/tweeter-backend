package in.tweeter.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import in.tweeter.repository.entity.FeedEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventProcessResponse {
    private String key;
    private List<FeedEntity> feeds;
    private String action;
}