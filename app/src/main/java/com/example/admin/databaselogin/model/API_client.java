package com.example.admin.databaselogin.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 6/9/2017.
 */

public class API_client {


    public static final String BASE_URL=" http://surajbhandari.com.np/myportfolio/";

    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }

        return retrofit;
    }
}
