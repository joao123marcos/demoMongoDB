package com.JoaoMarcos.demoMongoDB.controllers.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
    
    public static String decodeParam(String param){
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
