package com.murk.converter;

import com.murk.converter.dao.ClassificatorDao;
import com.murk.converter.model.Classificator;
import com.murk.converter.service.ClassificatorService;
import com.murk.converter.utils.ClassificatorReader;
import com.murk.converter.utils.PropertiesReader;

import java.util.Map;
import java.util.Set;

public class Main {
    private static ClassificatorService service = new ClassificatorService();

    public static void main(String[] args) {
        System.out.println("Start converter");

        String classificatorURIPath = PropertiesReader.readProperty("./help/classificator.properties","path");
        Map<String,String> classificators = ClassificatorReader.read(classificatorURIPath);

        service.save(classificators);
        System.out.println("End converter");

    }
}
