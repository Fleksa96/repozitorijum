package com.example.djolexa;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyEndpoint {

    @GET("v2/5d2edf4d340000610064d358")
    Call<MenuWrapperClass> getMenuClass();
}
