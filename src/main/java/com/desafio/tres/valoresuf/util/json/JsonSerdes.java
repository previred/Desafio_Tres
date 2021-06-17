package com.desafio.tres.valoresuf.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.slf4j.LoggerFactory.getLogger;

public class JsonSerdes {
    private static final Logger logger = getLogger(JsonSerdes.class);
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(NON_EMPTY)
                .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .build();
    }

    //-- File Operations

    private JsonSerdes() {
        throw new AssertionError("No 'JsonSerdes' instances for you!");
    }

    /**
     * Serializes the given object to <i>JSON formatted</i> string
     *
     * @param obj The object to be serialized.
     * @return A resultant string that is the json representation of the specified object, <b>null</b> in case of face
     * an unexpected error.
     */
    public static String jsonfy(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception ex) {
            logger.trace("Error detected when trying to covert an object to JSON", ex);
            return null;
        }
    }

}
