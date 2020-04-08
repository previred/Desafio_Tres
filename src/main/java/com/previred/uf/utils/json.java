package com.previred.uf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class json {
    public static String objetToJson(Object o) throws JsonProcessingException {
        ObjectMapper Obj = new ObjectMapper();
        //System.out.println("JSON: \n" + jsonStr);

        return Obj.writeValueAsString(o);
    }
}
