package com.ll.domain;


import com.ll.standard.util.Ut;

import java.util.HashMap;
import java.util.Map;


public class Rq {
    private String cmd;
    private String action;
    private String queryString;
    private Map<String, String> paramsMap;


    public Rq(String cmd){
        this.cmd = cmd;
        paramsMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();
        if(cmdBits.length == 1){ //cmd에 ?가 없는 경우 = 즉 명령만 있는경우
            return;
        }

        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");
        for(int i=0; i<queryStringBits.length; i++){
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryStringBits[i].split("=");

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramsMap.put(paramName, paramValue);
        }


    }

    public String getAction(){
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue){
        return Ut.str.parseInt(paramsMap.get(paramName), defaultValue);
     /*
        String paramValue = paramsMap.get(paramName);

        if(paramValue != null){
            try {
                return Integer.parseInt(paramValue);
            }catch(NumberFormatException e){
                //ignored
            }
        }

        return defaultValue;

      */

    }


}
