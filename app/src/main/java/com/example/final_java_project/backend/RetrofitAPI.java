package com.example.final_java_project.backend;
import com.example.final_java_project.backend.for_guide.GuideSignUpRequest;
import com.example.final_java_project.backend.for_guide.GuideSignUpResponse;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitAPI {

    //@POST("guide/signIn")
    //Call<Model__CheckAlready> postOverlapCheck(@Body Model__CheckAlready modelCheckAlready); //이건 바디 요청시 사용하는거

    @FormUrlEncoded
    @POST("guide/signIn")
    //Call<Model__CheckAlready> postOverlapCheck(@Field("phone") String phoneNum, @Field("message") String message); //이건 요청시 사용하는거 (*데이터를 보낼때)*/
    Call<GuideSignUpResponse> getGuideSignUpResponse(@Body GuideSignUpRequest loginRequest);
    ;
}


