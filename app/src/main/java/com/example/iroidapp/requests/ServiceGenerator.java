package com.example.iroidapp.requests;


import com.example.iroidapp.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static IroidApi iroidApi = retrofit.create(IroidApi.class);

    public static IroidApi getIroidApi(){
        return iroidApi;
    }
}
