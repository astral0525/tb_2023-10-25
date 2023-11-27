package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String cmd;
    String action;
    String queryString;
    Map<String, String> paramsMap;


    Rq(String cmd){
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

    String getAction(){
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue){
        String paramValue = paramsMap.get(paramName);

        if(paramValue != null){
            try {
                return Integer.parseInt(paramValue);
            }catch(NumberFormatException e){
                //ignored
            }
        }

        return defaultValue;

    }


}
