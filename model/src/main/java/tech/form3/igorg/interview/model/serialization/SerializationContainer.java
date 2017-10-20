package tech.form3.igorg.interview.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Serialization container interface.
 */
public interface SerializationContainer {

    /**
     * Serializes the data (before save or update).
     *
     * @param objectMapper the object mapper
     * @throws JsonProcessingException thrown if there's an issue with the serialization
     */
    void serialize(ObjectMapper objectMapper) throws JsonProcessingException;

    /**
     * De-serializes the data (on load).
     *
     * @param objectMapper the object mapper
     * @throws IOException thrown if there's an issue with reading the data
     */
    void deserialize(ObjectMapper objectMapper) throws IOException;

}
