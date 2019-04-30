package com.murk.converter.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.murk.converter.model.Classificator;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassificatorsDeserializer extends StdDeserializer<Map> {

    public ClassificatorsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Map<String,String> classificators = new HashMap<>();

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Iterator<Map.Entry<String,JsonNode>> iteratorCS = node.fields();

        while (iteratorCS.hasNext())
        {
            Map.Entry<String,JsonNode> classificatorJson = iteratorCS.next();

            String code = classificatorJson.getKey();
            String name = classificatorJson.getValue().asText();

            classificators.put(code,name);
        }
            return classificators;
    }
}
