package com.quad.trivia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

public class TriviaDeserializer {

    /** Deserializes an opentdb response json into a TriviaAPIResponse object
     *
     * @param json  the raw json string returned by opentdb
     * @return      an object containing the response code and results (questions) contained in the json data
     */
    public static TriviaAPIResponse deserialize(String json) {
        TriviaAPIResponse tar = new TriviaAPIResponse();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(json);
            tar.responseCode = rootNode.get("response_code").asLong();
            ObjectReader reader = mapper.readerFor(new TypeReference<List<TriviaQuestion>>() {
            });
            tar.results = reader.readValue(rootNode.get("results"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tar;
    }
}
