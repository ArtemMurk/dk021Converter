package com.murk.converter.utils;

import com.murk.converter.model.Classificator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

public class ClassificatorReader {
    public static Set<Classificator> read(String path)
    {
        try {
        URI jsonUrl = new URI("https://gist.githubusercontent.com/anonymous/4b32c7ef1ceb5dd48bf5/raw/ef1987551faa3fb61473bb0e7aad70a228dc36d6/gistfile1.txt");


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
