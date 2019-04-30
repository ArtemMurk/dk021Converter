package com.murk.converter.service;

import com.murk.converter.dao.ClassificatorDao;
import com.murk.converter.model.Classificator;

import java.util.Map;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassificatorService {
    private static ClassificatorDao dao = new ClassificatorDao();


    public void save(Map<String,String> classificatorsTO)
    {
        System.out.println("Start converting classificators");
        Map<Integer,Classificator>  classificators = convert(classificatorsTO);
        dao.save(classificators);
    }


    private Map<Integer,Classificator> convert(Map<String,String> classificatorsTo)
    {
        Map<Integer,Classificator> classificators = new TreeMap<>();

        for (Map.Entry<String, String> entry : classificatorsTo.entrySet()) {
            String code = entry.getKey();
            String name = entry.getValue();

            Classificator classificator = convert(code, name);
            classificators.put(classificator.getId(), classificator);

        }
        classificators = checkParentIds(classificators);
        return classificators;
    }

    private Map<Integer, Classificator> checkParentIds(Map<Integer, Classificator> classificators) {

        classificators.forEach((id,classificator)->
        {
            Integer parentId = classificator.getParentId();

            if (classificator.getParentId() != null && !classificators.containsKey(parentId)) {

                Integer newParentId = parentId;
                while (!classificators.containsKey(newParentId)
                        && newParentId.toString().charAt(2) != '0'
                )
                {
                    newParentId = getParentId(classificator.getParentId().toString());
                }
                classificator.setParentId(newParentId);
            }

        });
        return classificators;
    }

    private Classificator convert(String code, String name) {
        String[] codeSplit = code.split("-");

        int id = Integer.parseInt(codeSplit[0]);
        short num = Short.parseShort(codeSplit[1]);

        Integer parentId= checkParentId(codeSplit[0]);

        Classificator classificator = new Classificator(id,num,parentId,name);

        return classificator;
    }


    private Integer checkParentId(String id) {
        Integer result = null;

        //check chars for root
        short checkChar = 2;
        if (id.charAt(checkChar) !='0' && !id.equals("99999999"))
        {
            result = getParentId(id);
        }
        return result;
    }

    private Integer getParentId(String id)
    {
        Integer result;
        int charNum = firstIndexCharBeforeZero(id);

        String parentIdString = replaceLastDigitToZero(id,charNum);

        result = Integer.parseInt(parentIdString);

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
