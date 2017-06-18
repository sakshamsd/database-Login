package com.example.admin.databaselogin.interfaces;

import com.example.admin.databaselogin.model.AddResult;
import com.example.admin.databaselogin.model.StudentResult;
import com.example.admin.databaselogin.model.student_test;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Admin on 6/11/2017.
 */

public interface API_interface {



    @GET("index.php/User/test_get")
    Call<StudentResult> getList() ;

    @FormUrlEncoded
    @POST("index.php/User/test_add")
    Call<ResponseBody> insertDetails(@Field("id")String id,
                                     @Field("name")String name,
                                     @Field("address")String address,
                                     @Field("added_by")String added_by);






   /* Call<ResponseBody> insertStudent(@Body student_test student_test);*/


}
