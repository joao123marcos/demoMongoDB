package com.JoaoMarcos.demoMongoDB.controllers.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class URL {
    
    public static String decodeParam(String param){
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static LocalDateTime convertDate(String textDate, boolean isMaxDate){
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;

        if (isMaxDate) {
            if ((textDate == null) || (textDate.isEmpty())) {
                return null;
            } else {
                date = LocalDate.parse(textDate, dtf);
                return date.atTime(23, 59, 59);
            }
        }else{
            if ((textDate == null) || (textDate.isEmpty())) {
                return null;
            } else {
                date = LocalDate.parse(textDate, dtf);
                return date.atTime(0, 0, 0);
            }    
            
        }
    }
}
