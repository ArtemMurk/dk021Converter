package com.murk.converter.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.murk.converter.model.Classificator;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassificatorsDeserializer extends StdDeserializer<Set> {

    public ClassificatorsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Set deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Set<Classificator> classificators = new HashSet<>();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Iterator<Map.Entry<String,JsonNode>> iteratorCS = node.fields();

        while (iteratorCS.hasNext())
        {
            Map.Entry<String,JsonNode> classificatorJson = iteratorCS.next();

            String code = classificatorJson.getKey();
            String name = classificatorJson.getValue().asText();

            String[] codeSplit = code.split("-");

            int id = Integer.parseInt(codeSplit[0]);
            short num = Short.parseShort(codeSplit[1]);

            Integer parentId= getParentId(codeSplit[0]);

            Classificator classificator = new Classificator(id,num,parentId,name);
            System.out.println(classificator);
        }
            return classificators;
    }

    private Integer getParentId(String id) {
        Integer result = null;

        //check chars for root
        short checkChar = 2;
        if (id.charAt(checkChar) !='0' && !id.equals("99999999"))
        {
            int charNum = firstIndexCharBeforeZero(id);

            String parentIdString = replaceLastDigitToZero(id,charNum);

            result = Integer.parseInt(parentIdString);
        }
        return result;
    }

    private String replaceLastDigitToZero(String id, int charNum) {
        StringBuilder replacedId = new StringBuilder(id);
        replacedId.setCharAt(charNum, '0');
        return replacedId.toString();
    }


    private int firstIndexCharBeforeZero(String id) {
        String idWithoutRoot = id.substring(2);
        Pattern pattern = Pattern.compile("0");
        Matcher matcher = pattern.matcher(idWithoutRoot);
        return matcher.find() ? matcher.start()+1 : 7;
    }
}
