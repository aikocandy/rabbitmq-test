package xyz.silkdog.messagequeue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MarketingMessageDto {
    @JsonProperty("target")
    private String target;
    @JsonProperty("event_code")
    private String eventCode;
    @JsonProperty("properties")
    private PropertiesDto properties;
}
