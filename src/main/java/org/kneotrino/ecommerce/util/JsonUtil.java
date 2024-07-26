package org.kneotrino.ecommerce.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper = null;
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static ObjectMapper GetDefaultModelMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(df);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    public static String[] toKeySet(String data) throws JsonProcessingException {
        ObjectMapper mapper = GetDefaultModelMapper();
        return mapper.readValue(data, String[].class);
    }

    public static <T> String getString(T request) {
        try {
            ObjectMapper mapper = GetDefaultModelMapper();
            return mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(request);
        } catch (JsonProcessingException e) {
            LOGGER.error("ERROR failed to get json string {}",request.getClass().getName(), e);
        }
        return "[ERROR_PARSE]";
    }

    public static Object mapObject(String data, Object object) {
        try {
            ObjectMapper mapper = GetDefaultModelMapper();
            return mapper.readValue(data, object.getClass());
        } catch (IOException e) {
            LOGGER.error("Map Object failed {}", e.getMessage());
            return "[ERROR_PARSE]";
        }
    }

    public static String checkStringOrDefault(String request,String Default) {
        if (request == null || request.isEmpty() || request.isBlank()) {
            return Default;
        }
        return request;
    }

    public static String convertToTitleCase(String camelCaseText) {
        // Use regular expressions to insert spaces before capital letters
        String titleCaseText = camelCaseText.replaceAll("([a-z])([A-Z])", "$1 $2");

        // Split the text into words and capitalize the first letter of each word
        String[] words = titleCaseText.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)));
                result.append(word.substring(1)).append(" ");
            }
        }

        return result.toString().trim();
    }


    public static String safeToUpperCase(String safeToUpperCase) {
        try {
            return safeToUpperCase.toUpperCase();
        } catch (Exception ex) {
            LOGGER.debug("safeToUpperCase = {} ", safeToUpperCase,ex);
            return safeToUpperCase;
        }

    }
}
