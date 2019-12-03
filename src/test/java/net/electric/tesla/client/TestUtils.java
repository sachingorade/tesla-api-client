package net.electric.tesla.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private TestUtils() {
    }

    public static String loadResourceAsString(String resource) {
        try (InputStream stream = new FileInputStream(new File("src/test/resources/" + resource))) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Specified resource ["+ resource + "] not found in classpath.", e);
        }
    }

    public static <T> T loadResourceAsObject(String resource, Class<T> clazz) {
        try {
            return objectMapper.readValue(loadResourceAsString(resource), clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Specified resource cannot be parsed as JSON for class [" + clazz + "]", e);
        }
    }

}
