package com.idwzx.klinedemo;

import com.google.gson.Gson;

/**
 * Created by 小朱先森 on 2018/4/20.
 */

public class GsonUtil {
    public static final Gson sGson = new Gson();


    public String toJsonStr(){
        return null;
    }

    public static  <T> T toObj(String str,Class<T> tClass){
        return sGson.fromJson(str,tClass);
    }

    public static String toJson(Object obj) {
        return sGson.toJson(obj);
    }
}
