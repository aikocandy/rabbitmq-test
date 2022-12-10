package xyz.silkdog.messagequeue.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public MapperUtil() {
        objectMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, false);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    public static <T> T readAndParse(Class<T> clazz, String json){
        try {
            return objectMapper.readerFor(clazz).readValue(json);
        } catch (JsonProcessingException e) {
            log.error("[readAndParse][ERROR] failed to parse string to dto. reason: " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

}
