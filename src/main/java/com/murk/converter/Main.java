package com.murk.converter;

import com.murk.converter.dao.ClassificatorDao;
import com.murk.converter.model.Classificator;
import com.murk.converter.utils.ClassificatorReader;
import com.murk.converter.utils.PropertiesReader;

import java.util.Set;

public class Main {
    private static ClassificatorDao dao = new ClassificatorDao();

    public static void main(String[] args) {
        System.out.println("Start converter");

        String classificatorURIPath = PropertiesReader.readProperty("./help/classificator.properties","path");
        Set<Classificator> classificators = ClassificatorReader.read(classificatorURIPath);

        dao.save(classificators);
        System.out.println("End converter");

    }
}
