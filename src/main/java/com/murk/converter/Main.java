package com.murk.converter;

import com.murk.converter.model.Classificator;
import com.murk.converter.utils.ClassificatorReader;
import com.murk.converter.utils.PropertiesReader;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String classificatorURIPath = PropertiesReader.readProperty("./help/classificator.properties","path");
        Set<Classificator> classificators = ClassificatorReader.read(classificatorURIPath);

    }
}
