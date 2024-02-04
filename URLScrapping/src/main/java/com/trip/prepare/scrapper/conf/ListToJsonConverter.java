package com.trip.prepare.scrapper.conf;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ListToJsonConverter implements AttributeConverter<List<Object>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Object> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            // Handle exception or throw a custom one
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> convertToEntityAttribute(String json) {
        if (json == null) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<Object>>() {});
        } catch (IOException e) {
            // Handle exception or throw a custom one
            e.printStackTrace();
            return null;
        }
    }
}
