package com.example.user.data.api;

public class UtilsApi {
    private static String BASE_URL = "http://192.168.0.103:5000/";
//    private static String BASE_URL = "http://68.183.226.200:3000/api/test/";

    public static ApiClient getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiClient.class);
    }

}
