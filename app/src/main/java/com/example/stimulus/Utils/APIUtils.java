package com.example.stimulus.Utils;

import com.example.stimulus.API.APIPost;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "http://35.229.66.18:3000";

    public static APIPost getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIPost.class);
    }

}
