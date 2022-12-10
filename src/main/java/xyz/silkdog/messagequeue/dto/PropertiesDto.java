package xyz.silkdog.messagequeue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PropertiesDto {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("phone")
    private String phone;
}
