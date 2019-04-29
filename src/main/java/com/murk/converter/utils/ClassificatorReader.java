package com.murk.converter.utils;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.murk.converter.model.Classificator;

import java.io.IOException;

import java.net.URL;
import java.util.Set;

public class ClassificatorReader {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("classificatorDeser", Version.unknownVersion());
        module.addDeserializer(Set.class, new ClassificatorsDeserializer(null));
        mapper.registerModule(module);

    }

    public static Set<Classificator> read(String path)
    {
        Set<Classificator> classificators = null;
        try {
            URL  jsonUrl = new URL(path);
            classificators = (Set<Classificator>)  mapper.readValue(jsonUrl,Set.class);
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
