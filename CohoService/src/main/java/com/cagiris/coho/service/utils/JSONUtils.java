/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.cagiris.coho.service.exception.CohoException;

/**
 *
 * @author: ssnk
 */

public class JSONUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

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

}
