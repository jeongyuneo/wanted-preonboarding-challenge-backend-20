package com.jeongyuneo.wantedpreonboardingchallengebackend20.global.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> getBody(HttpServletRequest request) throws IOException {
        return OBJECT_MAPPER.readValue(
                StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8), Map.class);
    }

    public static String getWriteValueAsString(Object value) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(value);
    }
}
