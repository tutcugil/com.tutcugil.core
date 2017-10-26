package com.tutcugil.core.helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.tutcugil.core.io.Logger;

import java.lang.reflect.Type;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class HelperJson {
    public static String stringify(Object object){
        Gson gson = new Gson();
        String json = "";
        try{
            json = gson.toJson(object);
        }catch (Exception ex){
            Logger.error(ex);
        }

        return json;
    }

    public static JsonElement toElement(Object object){
        Gson gson = new Gson();
        JsonElement json = null;
        try{
            json = gson.toJsonTree(object);
        }catch (Exception ex){
            Logger.error(ex);
        }

        return json;
    }

    public static Object cast(String json, Type type){
        Gson gson = new Gson();
        Object obj = null;
        try{
            Logger.trace(json);
            obj = gson.fromJson(json, type);
        }catch (Exception ex){
            Logger.error(ex);
        }

        return obj;
    }
}
