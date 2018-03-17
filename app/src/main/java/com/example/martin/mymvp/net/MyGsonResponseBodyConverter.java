package com.example.martin.mymvp.net;


import com.example.martin.mymvp.untils.L;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
       L.d("MyGsonResponseBodyConve", response);
        try {
            L.json(response);
            try{
                return gson.fromJson(response, type);
            }catch (JsonSyntaxException ex){
                throw new JsonSyntaxException(response);
            }
//            RsponseBody baseResponse = gson.fromJson(response, RsponseBody.class);
//            if (baseResponse.getReturnCode() == ResponseCode.SUCCESS) {
//                return gson.fromJson(response, type);
//            } else {
//                throw new ResultException(baseResponse.getReturnCode(), baseResponse.getMsg());
//            }

        } finally {
            value.close();
        }
    }
}
