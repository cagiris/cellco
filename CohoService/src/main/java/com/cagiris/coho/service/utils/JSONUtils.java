/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.utils;

import java.io.IOException;
import java.util.Map;

import com.cagiris.coho.service.exception.CohoException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author: ssnk
 */

public class JSONUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    {
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    public static String getJsonStringForObject(Object object) throws CohoException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            throw new CohoException(e);
        } catch (JsonMappingException e) {
            throw new CohoException(e);
        } catch (IOException e) {
            throw new CohoException(e);
        }
    }

    public static <T> T parseJSON(String jsonString, Class<T> clazz) throws CohoException {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new CohoException(e);
        }

    }

    public static Map<String, Object> getMapForObject(Object object) {
        return objectMapper.convertValue(object, Map.class);
    }

}
