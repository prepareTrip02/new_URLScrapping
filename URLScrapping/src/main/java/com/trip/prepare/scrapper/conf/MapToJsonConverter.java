package com.trip.prepare.scrapper.conf;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MapToJsonConverter implements AttributeConverter<Map<String, List<String>>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, List<String>> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            // Handle exception or throw a custom one
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, List<String>> convertToEntityAttribute(String json) {
        if (json == null) {
            return Collections.emptyMap();
        }
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, List<String>>>() {});
        } catch (IOException e) {
            // Handle exception or throw a custom one
            e.printStackTrace();
            return null;
        }
    }
}
