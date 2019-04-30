package com.murk.converter.utils;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

import java.net.URL;
import java.util.Map;

public class ClassificatorReader {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("classificatorDeser", Version.unknownVersion());
        module.addDeserializer(Map.class, new ClassificatorsDeserializer(null));
        mapper.registerModule(module);

    }

    public static Map<String,String> read(String path)
    {
        Map<String,String> classificators = null;
        try {
            URL  jsonUrl = new URL(path);
            classificators = (Map<String,String>)  mapper.readValue(jsonUrl,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (classificators != null)
        {
            System.out.println("END read classifictors from path = " + path+", classificators length = "+ classificators.size());
        } else
            {
                System.err.println("Fail get data from path = " + path);
            }

        return classificators;
    }
}
