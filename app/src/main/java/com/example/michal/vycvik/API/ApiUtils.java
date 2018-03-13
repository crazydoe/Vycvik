package com.example.michal.vycvik.API;

/**
 * Created by michal on 30.05.2017.
 */

public class ApiUtils {
    public static final  String BASE_URL = "http://217.182.64.46:8080";

    public static ApiService getApiService(){

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }

}
