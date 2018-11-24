package com.example.stimulus.API;

import com.example.stimulus.API.Model_POST_getNews.Data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIPost {

    @POST("/news/getNews")
    @FormUrlEncoded
    Call<Data> getData(@Field("sign") String sign, @Field("phrase") String phrase);

    @Headers("Content-Type: application/json")
    @GET
    Call<Data> getSearchResult(@Url String url);

}
